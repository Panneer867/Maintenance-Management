package com.ingroinfo.mm.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.mm.dao.ApprovedWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.ApprovedWorkOrderNosRepository;
import com.ingroinfo.mm.dao.InwardApprovedMaterialsRepository;
import com.ingroinfo.mm.dao.InwardApprovedSparesRepository;
import com.ingroinfo.mm.dao.InwardApprovedToolsRepository;
import com.ingroinfo.mm.dao.InwardMaterialsRepository;
import com.ingroinfo.mm.dao.InwardSparesRepository;
import com.ingroinfo.mm.dao.InwardTempMaterialsRepository;
import com.ingroinfo.mm.dao.InwardTempSparesRepository;
import com.ingroinfo.mm.dao.InwardTempToolsRepository;
import com.ingroinfo.mm.dao.InwardToolsRepository;
import com.ingroinfo.mm.dao.ItemMasterRepository;
import com.ingroinfo.mm.dao.StockReturnsRepository;
import com.ingroinfo.mm.dao.TempStockReturnRepository;
import com.ingroinfo.mm.dao.TempWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.WorkOrderItemsRepository;
import com.ingroinfo.mm.dao.WorkOrderItemsRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderRemovedItemsRepository;
import com.ingroinfo.mm.dao.TempWorkOrderNosRepository;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.WorkOrderItemsDto;
import com.ingroinfo.mm.entity.ApprovedWorkOrderItems;
import com.ingroinfo.mm.entity.ApprovedWorkOrderNos;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.InwardApprovedMaterials;
import com.ingroinfo.mm.entity.InwardApprovedSpares;
import com.ingroinfo.mm.entity.InwardApprovedTools;
import com.ingroinfo.mm.entity.InwardMaterials;
import com.ingroinfo.mm.entity.InwardSpares;
import com.ingroinfo.mm.entity.InwardTempMaterials;
import com.ingroinfo.mm.entity.InwardTempSpares;
import com.ingroinfo.mm.entity.InwardTempTools;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.StockReturns;
import com.ingroinfo.mm.entity.TempStockReturn;
import com.ingroinfo.mm.entity.TempWorkOrderItems;
import com.ingroinfo.mm.entity.WorkOrderItems;
import com.ingroinfo.mm.entity.WorkOrderItemsRequest;
import com.ingroinfo.mm.entity.WorkOrderRemovedItems;
import com.ingroinfo.mm.entity.TempWorkOrderNos;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private AdminService adminService;

	@Autowired
	private InwardTempMaterialsRepository inwardTempMaterialsRepository;

	@Autowired
	private InwardMaterialsRepository inwardMaterialsRepository;

	@Autowired
	private InwardApprovedMaterialsRepository inwardApprovedMaterialsRepository;

	@Autowired
	private InwardTempSparesRepository inwardTempSparesRepository;

	@Autowired
	private InwardSparesRepository inwardSparesRepository;

	@Autowired
	private InwardApprovedSparesRepository inwardApprovedSparesRepository;

	@Autowired
	private InwardTempToolsRepository inwardTempToolsRepository;

	@Autowired
	private InwardToolsRepository inwardToolsRepository;

	@Autowired
	private InwardApprovedToolsRepository inwardApprovedToolsRepository;

	@Autowired
	private WorkOrderItemsRequestRepository workOrderItemsRequestRepository;

	@Autowired
	private TempWorkOrderItemsRepository tempWorkOrderItemsRepository;

	@Autowired
	private WorkOrderItemsRepository workOrderItemsRepository;

	@Autowired
	private TempWorkOrderNosRepository tempWorkOrderNosRepository;

	@Autowired
	private WorkOrderRemovedItemsRepository workOrderRemovedItemsRepository;

	@Autowired
	private ItemMasterRepository itemMasterRepository;

	@Autowired
	private ApprovedWorkOrderItemsRepository approvedWorkOrderItemsRepository;

	@Autowired
	private ApprovedWorkOrderNosRepository approvedWorkOrderNosRepository;

	@Autowired
	private TempStockReturnRepository tempStockReturnRepository;

	@Autowired
	private StockReturnsRepository stockReturnsRepository;

	@Override
	public void saveInwardTempMaterials(InwardDto inward, MultipartFile file) {

		String companyName = "";
		Long id = Long.parseLong(inward.getItemName());
		String itemId = itemMasterRepository.findById(id).get().getItemId();
		String itemName = itemMasterRepository.findById(id).get().getItemName();
		Company company = adminService.getCompanyByUsername(inward.getUsername());

		if (company != null) {
			companyName = company.getCompanyName();
		} else {
			companyName = "Admin";
		}

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		InwardTempMaterials inwardTempMaterials = modelMapper.map(inward, InwardTempMaterials.class);

		String fileName = itemName + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "." + tokens.get();
		String uploadDir = "C:\\Company\\" + companyName + "\\Inward_Materials\\";

		try {
			adminService.saveFile(uploadDir, fileName, file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		inwardTempMaterials.setItemImage(fileName);
		inwardTempMaterials.setItemName(itemName);
		inwardTempMaterials.setImagePath("/Company/" + companyName + "/Inward_Materials/");
		inwardTempMaterials.setUsername(inward.getUsername());
		inwardTempMaterials.setSubTotal(inwardTempMaterials.getCostRate() * inwardTempMaterials.getQuantity());
		inwardTempMaterials.setStockType("ML");
		InwardTempMaterials InwardTempMaterial = inwardTempMaterialsRepository.save(inwardTempMaterials);

		InwardTempMaterial.setItemId(itemId + InwardTempMaterial.getTempMaterialId());
		inwardTempMaterialsRepository.save(InwardTempMaterial);
	}

	@Override
	public void saveInwardMaterials(InwardDto inward) {

		List<InwardTempMaterials> inwardTempMaterials = inwardTempMaterialsRepository
				.findByUsername(inward.getUsername());

		for (InwardTempMaterials inwardTempMaterial : inwardTempMaterials) {

			InwardMaterials inwardMaterials = modelMapper.map(inwardTempMaterial, InwardMaterials.class);

			double subTotal = inwardMaterials.getSubTotal();
			double gst = (subTotal / 100) * inward.getIgst();

			BigDecimal bd = new BigDecimal(subTotal + gst);
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			double roundedNumber = bd.doubleValue();

			inwardMaterials.setSupplier(inward.getSupplier());
			inwardMaterials.setSuppliedOn(inward.getSuppliedOn());
			inwardMaterials.setGstType(inward.getGstType());
			inwardMaterials.setIgst(inward.getIgst());
			inwardMaterials.setSgst(inward.getSgst());
			inwardMaterials.setCgst(inward.getCgst());
			inwardMaterials.setGrandTotal(roundedNumber);
			inwardMaterials.setInvoice(inward.getInvoice());
			inwardMaterials.setReceivedBy(inward.getReceivedBy());
			inwardMaterials.setReceivedDate(inward.getReceivedDate());
			inwardMaterialsRepository.save(inwardMaterials);
			inwardTempMaterialsRepository.deleteById(inwardTempMaterial.getTempMaterialId());
		}

	}

	@Override
	public List<InwardTempMaterials> getInwardTempMaterials(String username) {
		return inwardTempMaterialsRepository.findByUsername(username);
	}

	@Override
	public List<InwardMaterials> getInwardAllMaterialsList() {
		return inwardMaterialsRepository.findAll();
	}

	@Override
	public void deleteTempMaterial(Long materialId) {
		inwardTempMaterialsRepository.deleteById(materialId);
	}

	@Override
	public void deleteMaterial(Long materialId) {
		inwardMaterialsRepository.deleteById(materialId);

	}

	@Override
	public void deleteAllMaterials() {
		inwardTempMaterialsRepository.deleteAll();
	}

	@Override
	public InwardMaterials getInwardMaterial(Long id) {
		return inwardMaterialsRepository.findByMaterialId(id);
	}

	@Override
	public InwardApprovedMaterials getApprovedInwardMaterialById(Long id) {
		return inwardApprovedMaterialsRepository.findByApprovedMaterialId(id);
	}

	@Override
	public List<InwardApprovedMaterials> getApprovedMaterialsLists() {
		return inwardApprovedMaterialsRepository.findAll();
	}

	/***** Spares *****/

	@Override
	public void saveInwardTempSpares(InwardDto inward, MultipartFile file) {

		String companyName = "";
		Long id = Long.parseLong(inward.getItemName());
		String itemId = itemMasterRepository.findById(id).get().getItemId();
		String itemName = itemMasterRepository.findById(id).get().getItemName();
		Company company = adminService.getCompanyByUsername(inward.getUsername());

		if (company != null) {
			companyName = company.getCompanyName();
		} else {
			companyName = "Admin";
		}

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		InwardTempSpares inwardTempSpares = modelMapper.map(inward, InwardTempSpares.class);

		String fileName = itemName + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "." + tokens.get();
		String uploadDir = "C:\\Company\\" + companyName + "\\Inward_Spares\\";

		try {
			adminService.saveFile(uploadDir, fileName, file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		inwardTempSpares.setItemImage(fileName);
		inwardTempSpares.setItemName(itemName);
		inwardTempSpares.setImagePath("/Company/" + companyName + "/Inward_Spares/");
		inwardTempSpares.setUsername(inward.getUsername());
		inwardTempSpares.setSubTotal(inwardTempSpares.getCostRate() * inwardTempSpares.getQuantity());
		inwardTempSpares.setStockType("SP");
		InwardTempSpares inwardTempSpare = inwardTempSparesRepository.save(inwardTempSpares);
		inwardTempSpare.setItemId(itemId + inwardTempSpare.getTempSpareId());
		inwardTempSparesRepository.save(inwardTempSpare);

	}

	@Override
	public void saveInwardSpares(InwardDto inward) {

		List<InwardTempSpares> inwardTempSpares = inwardTempSparesRepository.findByUsername(inward.getUsername());

		for (InwardTempSpares inwardTempSpare : inwardTempSpares) {

			InwardSpares inwardSpares = modelMapper.map(inwardTempSpare, InwardSpares.class);

			double subTotal = inwardSpares.getSubTotal();
			double gst = (subTotal / 100) * inward.getIgst();

			BigDecimal bd = new BigDecimal(subTotal + gst);
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			double roundedNumber = bd.doubleValue();

			inwardSpares.setSupplier(inward.getSupplier());
			inwardSpares.setSuppliedOn(inward.getSuppliedOn());
			inwardSpares.setGstType(inward.getGstType());
			inwardSpares.setIgst(inward.getIgst());
			inwardSpares.setSgst(inward.getSgst());
			inwardSpares.setCgst(inward.getCgst());
			inwardSpares.setGrandTotal(roundedNumber);
			inwardSpares.setInvoice(inward.getInvoice());
			inwardSpares.setReceivedBy(inward.getReceivedBy());
			inwardSpares.setReceivedDate(inward.getReceivedDate());

			inwardSparesRepository.save(inwardSpares);
			inwardTempSparesRepository.deleteById(inwardTempSpare.getTempSpareId());
		}

	}

	@Override
	public List<InwardTempSpares> getInwardTempSpares(String username) {
		return inwardTempSparesRepository.findByUsername(username);
	}

	@Override
	public List<InwardSpares> getInwardAllSparesList() {
		return inwardSparesRepository.findAll();
	}

	@Override
	public void deleteTempSpare(Long spareId) {
		inwardTempSparesRepository.deleteById(spareId);
	}

	@Override
	public void deleteSpare(Long spareId) {
		inwardSparesRepository.deleteById(spareId);
	}

	@Override
	public void deleteAllSpares() {
		inwardTempSparesRepository.deleteAll();
	}

	@Override
	public InwardSpares getInwardSpare(Long id) {
		return inwardSparesRepository.findBySpareId(id);
	}

	@Override
	public InwardApprovedSpares getApprovedInwardSpareById(Long id) {
		return inwardApprovedSparesRepository.findByApprovedSpareId(id);
	}

	@Override
	public List<InwardApprovedSpares> getApprovedSparesLists() {
		return inwardApprovedSparesRepository.findAll();
	}

	/***** Tools *****/

	@Override
	public void saveInwardTempTools(InwardDto inward, MultipartFile file) {

		String companyName = "";
		Long id = Long.parseLong(inward.getItemName());
		String itemId = itemMasterRepository.findById(id).get().getItemId();
		String itemName = itemMasterRepository.findById(id).get().getItemName();
		Company company = adminService.getCompanyByUsername(inward.getUsername());

		if (company != null) {
			companyName = company.getCompanyName();
		} else {
			companyName = "Admin";
		}

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		InwardTempTools inwardTempTools = modelMapper.map(inward, InwardTempTools.class);

		String fileName = itemName + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "." + tokens.get();
		String uploadDir = "C:\\Company\\" + companyName + "\\Inward_Tools\\";

		try {
			adminService.saveFile(uploadDir, fileName, file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		inwardTempTools.setItemImage(fileName);
		inwardTempTools.setItemName(itemName);
		inwardTempTools.setImagePath("/Company/" + companyName + "/Inward_Tools/");
		inwardTempTools.setUsername(inward.getUsername());
		inwardTempTools.setSubTotal(inwardTempTools.getCostRate() * inwardTempTools.getQuantity());
		inwardTempTools.setStockType("TE");
		InwardTempTools inwardTempTool = inwardTempToolsRepository.save(inwardTempTools);
		inwardTempTool.setItemId(itemId + inwardTempTool.getTempToolId());
		inwardTempToolsRepository.save(inwardTempTool);

	}

	@Override
	public void saveInwardTools(InwardDto inward) {

		List<InwardTempTools> inwardTempTools = inwardTempToolsRepository.findByUsername(inward.getUsername());

		for (InwardTempTools inwardTempTool : inwardTempTools) {

			InwardTools inwardTools = modelMapper.map(inwardTempTool, InwardTools.class);

			double subTotal = inwardTools.getSubTotal();
			double gst = (subTotal / 100) * inward.getIgst();

			BigDecimal bd = new BigDecimal(subTotal + gst);
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			double roundedNumber = bd.doubleValue();

			inwardTools.setSupplier(inward.getSupplier());
			inwardTools.setSuppliedOn(inward.getSuppliedOn());
			inwardTools.setGstType(inward.getGstType());
			inwardTools.setIgst(inward.getIgst());
			inwardTools.setSgst(inward.getSgst());
			inwardTools.setCgst(inward.getCgst());
			inwardTools.setGrandTotal(roundedNumber);
			inwardTools.setInvoice(inward.getInvoice());
			inwardTools.setReceivedBy(inward.getReceivedBy());
			inwardTools.setReceivedDate(inward.getReceivedDate());

			inwardToolsRepository.save(inwardTools);
			inwardTempToolsRepository.deleteById(inwardTempTool.getTempToolId());
		}
	}

	@Override
	public List<InwardTempTools> getInwardTempTools(String username) {
		return inwardTempToolsRepository.findByUsername(username);
	}

	@Override
	public List<InwardTools> getInwardAllToolsList() {
		return inwardToolsRepository.findAll();
	}

	@Override
	public void deleteTempTool(Long toolId) {
		inwardTempToolsRepository.deleteById(toolId);
	}

	public void deleteTool(Long toolId) {
		inwardToolsRepository.deleteById(toolId);
	}

	@Override
	public void deleteAllTools() {
		inwardTempToolsRepository.deleteAll();
	}

	@Override
	public InwardTools getInwardTool(Long id) {
		return inwardToolsRepository.findByToolId(id);
	}

	@Override
	public InwardApprovedTools getApprovedInwardToolById(Long id) {
		return inwardApprovedToolsRepository.findByApprovedToolId(id);
	}

	@Override
	public List<InwardApprovedTools> getApprovedToolsLists() {
		return inwardApprovedToolsRepository.findAll();
	}

	@Override
	public List<Long> getWorkOrdersNos() {

		return workOrderItemsRequestRepository.findAll().stream().map(orders -> orders.getWorkOrderNo()).distinct()
				.collect(Collectors.toList());
	}

	@Override
	public List<WorkOrderItemsDto> getWorkOrderItems(Long workOrderNo) {

		List<TempWorkOrderItems> WorkOrderItems = tempWorkOrderItemsRepository.findByWorkOrderNo(workOrderNo);
		for (TempWorkOrderItems Items : WorkOrderItems) {
			tempWorkOrderItemsRepository.deleteById(Items.getTempWorkorderItemId());
		}

		List<WorkOrderItemsRequest> workOrderItemsRequest = workOrderItemsRequestRepository
				.findByWorkOrderNo(workOrderNo);

		// Define the list of StockTypes to filter by
		List<String> stockTypes = Arrays.asList("ML", "SP", "TE");

		// Group the items by StockType
		Map<String, List<WorkOrderItemsRequest>> groupedMap = workOrderItemsRequest.stream()
				.filter(item -> stockTypes.contains(item.getStockType()))
				.collect(Collectors.groupingBy(WorkOrderItemsRequest::getStockType));

		// Access the lists for each StockType
		List<WorkOrderItemsRequest> mlList = groupedMap.get("ML");
		List<WorkOrderItemsRequest> spList = groupedMap.get("SP");
		List<WorkOrderItemsRequest> teList = groupedMap.get("TE");

		// Create a new list of lists and add the three lists to it
		List<List<WorkOrderItemsRequest>> listOfLists = new ArrayList<>();
		listOfLists.add(mlList);
		listOfLists.add(spList);
		listOfLists.add(teList);

		for (int i = 0; i < listOfLists.size(); i++) {
			if (i == 0 && mlList != null) {
				List<String> itemIds = mlList.stream().map(WorkOrderItemsRequest::getItemId)
						.collect(Collectors.toList());

				Map<String, Integer> itemQuantity = mlList.stream().collect(
						Collectors.toMap(WorkOrderItemsRequest::getItemId, WorkOrderItemsRequest::getQuantity));

				for (String itemId : itemIds) {
					if (tempWorkOrderItemsRepository.findByItemIdAndWorkOrderNo(itemId, workOrderNo).isEmpty()) {
						InwardApprovedMaterials iam = inwardApprovedMaterialsRepository.findByItemId(itemId);
						int stockQuantity = iam.getQuantity();
						int quantity = itemQuantity.get(itemId);
						TempWorkOrderItems tempWorkOrderItems = modelMapper.map(iam, TempWorkOrderItems.class);
						tempWorkOrderItems.setWorkOrderNo(workOrderNo);
						tempWorkOrderItems.setQty(quantity);
						if (stockQuantity >= quantity) {
							tempWorkOrderItems.setFinalQuantity(quantity);
							tempWorkOrderItems.setTotalCost(iam.getMrpRate() * quantity);
						} else {
							tempWorkOrderItems.setFinalQuantity(stockQuantity);
							tempWorkOrderItems.setTotalCost(iam.getMrpRate() * stockQuantity);
						}

						tempWorkOrderItemsRepository.save(tempWorkOrderItems);
					}
				}
			}
			if (i == 1 && spList != null) {
				List<String> itemIds = spList.stream().map(WorkOrderItemsRequest::getItemId)
						.collect(Collectors.toList());

				Map<String, Integer> itemQuantity = spList.stream().collect(
						Collectors.toMap(WorkOrderItemsRequest::getItemId, WorkOrderItemsRequest::getQuantity));

				for (String itemId : itemIds) {

					if (tempWorkOrderItemsRepository.findByItemIdAndWorkOrderNo(itemId, workOrderNo).isEmpty()) {
						InwardApprovedSpares ias = inwardApprovedSparesRepository.findByItemId(itemId);
						int stockQuantity = ias.getQuantity();
						int quantity = itemQuantity.get(itemId);
						TempWorkOrderItems tempWorkOrderItems = modelMapper.map(ias, TempWorkOrderItems.class);
						tempWorkOrderItems.setWorkOrderNo(workOrderNo);
						tempWorkOrderItems.setQty(quantity);

						if (stockQuantity >= quantity) {
							tempWorkOrderItems.setFinalQuantity(quantity);
							tempWorkOrderItems.setTotalCost(ias.getMrpRate() * quantity);
						} else {
							tempWorkOrderItems.setFinalQuantity(stockQuantity);
							tempWorkOrderItems.setTotalCost(ias.getMrpRate() * quantity);
						}
						tempWorkOrderItemsRepository.save(tempWorkOrderItems);
					}
				}
			}
			if (i == 2 && teList != null) {
				List<String> itemIds = teList.stream().map(WorkOrderItemsRequest::getItemId)
						.collect(Collectors.toList());

				Map<String, Integer> itemQuantity = teList.stream().collect(
						Collectors.toMap(WorkOrderItemsRequest::getItemId, WorkOrderItemsRequest::getQuantity));

				for (String itemId : itemIds) {
					if (tempWorkOrderItemsRepository.findByItemIdAndWorkOrderNo(itemId, workOrderNo).isEmpty()) {
						InwardApprovedTools iat = inwardApprovedToolsRepository.findByItemId(itemId);
						int stockQuantity = iat.getQuantity();
						int quantity = itemQuantity.get(itemId);
						TempWorkOrderItems tempWorkOrderItems = modelMapper.map(iat, TempWorkOrderItems.class);
						tempWorkOrderItems.setWorkOrderNo(workOrderNo);
						tempWorkOrderItems.setQty(quantity);

						if (stockQuantity >= quantity) {
							tempWorkOrderItems.setFinalQuantity(quantity);
							tempWorkOrderItems.setTotalCost(iat.getMrpRate() * quantity);
						} else {
							tempWorkOrderItems.setFinalQuantity(stockQuantity);
							tempWorkOrderItems.setTotalCost(iat.getMrpRate() * quantity);
						}
						tempWorkOrderItemsRepository.save(tempWorkOrderItems);
					}
				}
			}
		}

		List<TempWorkOrderItems> listTempWorkOrderItems = tempWorkOrderItemsRepository.findByWorkOrderNo(workOrderNo);

		int count = 1;
		for (int i = 0; i < listTempWorkOrderItems.size(); i++) {
			listTempWorkOrderItems.get(i).setSlNo(count);
			tempWorkOrderItemsRepository.save(listTempWorkOrderItems.get(i));
			count++;
		}

		List<WorkOrderItemsDto> wOIDto = new ArrayList<WorkOrderItemsDto>();
		for (TempWorkOrderItems listTempWorkOrderItem : listTempWorkOrderItems) {
			WorkOrderItemsDto newwOIDto = modelMapper.map(listTempWorkOrderItem, WorkOrderItemsDto.class);
			if (inwardApprovedMaterialsRepository.findByItemIdAndStockType(newwOIDto.getItemId(),
					stockTypes.get(0)) != null) {
				int stockQuantity = inwardApprovedMaterialsRepository.findByItemId(newwOIDto.getItemId()).getQuantity();
				newwOIDto.setStockAvailable(stockQuantity);
			} else if (inwardApprovedSparesRepository.findByItemIdAndStockType(newwOIDto.getItemId(),
					stockTypes.get(1)) != null) {
				int stockQuantity = inwardApprovedSparesRepository.findByItemId(newwOIDto.getItemId()).getQuantity();
				newwOIDto.setStockAvailable(stockQuantity);

			} else if (inwardApprovedToolsRepository.findByItemIdAndStockType(newwOIDto.getItemId(),
					stockTypes.get(2)) != null) {
				int stockQuantity = inwardApprovedToolsRepository.findByItemId(newwOIDto.getItemId()).getQuantity();
				newwOIDto.setStockAvailable(stockQuantity);
			}
			wOIDto.add(newwOIDto);
		}
		return wOIDto;
	}

	@Override
	public List<WorkOrderItemsDto> checkStockQuantity(Long workOrderId) {

		List<WorkOrderItemsRequest> workOrders = workOrderItemsRequestRepository.findByWorkOrderNo(workOrderId);
		List<String> itemIds = workOrders.stream().map(WorkOrderItemsRequest::getItemId).collect(Collectors.toList());
		Map<String, Integer> itemQuantity = workOrders.stream()
				.collect(Collectors.toMap(WorkOrderItemsRequest::getItemId, WorkOrderItemsRequest::getQuantity));
		List<WorkOrderItemsDto> twoi = new ArrayList<>();
		for (String itemId : itemIds) {
			WorkOrderItemsDto wOI = new WorkOrderItemsDto();
			int stockQuantity = 0;
			if (inwardApprovedMaterialsRepository.findByItemIdAndStockType(itemId, "ML") != null) {
				stockQuantity = inwardApprovedMaterialsRepository.findByItemId(itemId).getQuantity();

			} else if (inwardApprovedSparesRepository.findByItemIdAndStockType(itemId, "SP") != null) {
				stockQuantity = inwardApprovedSparesRepository.findByItemId(itemId).getQuantity();

			} else if (inwardApprovedToolsRepository.findByItemIdAndStockType(itemId, "TE") != null) {
				stockQuantity = inwardApprovedToolsRepository.findByItemId(itemId).getQuantity();

			}
			int quantity = itemQuantity.get(itemId);
			if (!(stockQuantity >= quantity)) {
				wOI.setStockAvailable(stockQuantity);
				wOI.setStockRequested(itemQuantity.get(itemId));
				wOI.setItemId(itemId);
				twoi.add(wOI);
			}
		}
		return twoi;
	}

	@Override
	public void saveWorkOrder(TempWorkOrderNos workOrders) {
		TempWorkOrderNos savedWorkOrder = tempWorkOrderNosRepository.save(workOrders);
		List<TempWorkOrderItems> tempWorkOrderItems = tempWorkOrderItemsRepository
				.findByWorkOrderNo(savedWorkOrder.getWorkOrderNo());

		tempWorkOrderItems.forEach(tempWorkOrderItem -> {
			WorkOrderItems orderItem = modelMapper.map(tempWorkOrderItem, WorkOrderItems.class);
			orderItem.setWorkOrderId(savedWorkOrder);
			orderItem.setTotalCost(orderItem.getFinalQuantity() * orderItem.getMrpRate());
			workOrderItemsRepository.save(orderItem);

			int requiredQty = tempWorkOrderItem.getFinalQuantity();

			InwardApprovedMaterials inwardApprovedMaterials = inwardApprovedMaterialsRepository
					.findByItemId(tempWorkOrderItem.getItemId());

			InwardApprovedSpares inwardApprovedSpares = inwardApprovedSparesRepository
					.findByItemId(tempWorkOrderItem.getItemId());

			InwardApprovedTools inwardApprovedTools = inwardApprovedToolsRepository
					.findByItemId(tempWorkOrderItem.getItemId());

			if (inwardApprovedMaterials != null) {
				int MaterialStockQty = inwardApprovedMaterials.getQuantity();
				inwardApprovedMaterials.setQuantity(MaterialStockQty - requiredQty);
				inwardApprovedMaterialsRepository.save(inwardApprovedMaterials);
			}
			if (inwardApprovedSpares != null) {
				int SpareStockQty = inwardApprovedSpares.getQuantity();
				inwardApprovedSpares.setQuantity(SpareStockQty - requiredQty);
				inwardApprovedSparesRepository.save(inwardApprovedSpares);
			}
			if (inwardApprovedTools != null) {
				int ToolStockQty = inwardApprovedTools.getQuantity();
				inwardApprovedTools.setQuantity(ToolStockQty - requiredQty);
				inwardApprovedToolsRepository.save(inwardApprovedTools);
			}

			tempWorkOrderItemsRepository.deleteById(tempWorkOrderItem.getTempWorkorderItemId());
			WorkOrderItemsRequest workOrderItemsRequest = workOrderItemsRequestRepository
					.findByWorkOrderNoAndItemId(savedWorkOrder.getWorkOrderNo(), tempWorkOrderItem.getItemId());
			workOrderItemsRequestRepository.deleteById(workOrderItemsRequest.getStocksId());
		});
	}

	@Override
	public boolean notAvailableItems(Long workOrderNo) {
		List<WorkOrderItemsDto> workOrderItems = getWorkOrderItems(workOrderNo);
		for (WorkOrderItemsDto workOrderItem : workOrderItems) {
			if (workOrderItem.getStockAvailable() == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void saveRemovedItems(String itemId, Long workOrderNo) {
		WorkOrderRemovedItems removedItem = new WorkOrderRemovedItems();

		int stockQuantity = 0;

		InwardApprovedMaterials inwardApprovedMaterials = inwardApprovedMaterialsRepository.findByItemId(itemId);
		InwardApprovedSpares inwardApprovedSpares = inwardApprovedSparesRepository.findByItemId(itemId);
		InwardApprovedTools inwardApprovedTools = inwardApprovedToolsRepository.findByItemId(itemId);

		if (inwardApprovedMaterials != null) {
			stockQuantity = inwardApprovedMaterials.getQuantity();
		}
		if (inwardApprovedSpares != null) {
			stockQuantity = inwardApprovedSpares.getQuantity();
		}
		if (inwardApprovedTools != null) {
			stockQuantity = inwardApprovedTools.getQuantity();
		}

		int requiredQuantity = tempWorkOrderItemsRepository.findByItemIdAndWorkOrderNo(itemId, workOrderNo).get()
				.getQty();

		removedItem.setItemId(itemId);
		removedItem.setRequestedQuantity(requiredQuantity);
		removedItem.setStockQuantity(stockQuantity);
		removedItem.setWorkOrderNo(workOrderNo);

		if (stockQuantity == 0) {
			removedItem.setAvailability("Not Available in Stock");
			removedItem.setRemarks("The item is not available, so it has been removed from the list.");
		} else {
			removedItem.setAvailability("Available in Stock");
			removedItem.setRemarks("User removed the item from the list because it was no longer needed.");
		}

		workOrderRemovedItemsRepository.save(removedItem);
	}

	@Override
	public boolean getTempWorkOrderItems(Long workOrderNo) {
		boolean flag = false;
		List<TempWorkOrderItems> tempWorkOrderItems = tempWorkOrderItemsRepository.findByWorkOrderNo(workOrderNo);

		if (tempWorkOrderItems.size() == 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<TempWorkOrderNos> getOutwardWorkOrders() {
		return tempWorkOrderNosRepository.findAll();
	}

	@Override
	public List<WorkOrderItems> getOutwardWorkOrderItems(Long workOrderNo) {
		return workOrderItemsRepository.findByWorkOrderNo(workOrderNo);
	}

	@Override
	public TempWorkOrderNos getOutwardWorkOrder(Long workOrderNo) {
		return tempWorkOrderNosRepository.findByWorkOrderNo(workOrderNo);
	}

	@Override
	public List<ApprovedWorkOrderNos> getOutwardApprovedWorkOrders() {
		return approvedWorkOrderNosRepository.findAll();
	}

	@Override
	public List<ApprovedWorkOrderItems> getOutwardApprovedWorkOrderItems(Long workOrderNo) {
		return approvedWorkOrderItemsRepository.findByWorkOrderNo(workOrderNo);
	}

	@Override
	public ApprovedWorkOrderNos getOutwardApprovedWorkOrder(Long workOrderNo) {
		return approvedWorkOrderNosRepository.findByWorkOrderNo(workOrderNo);
	}

	@Override
	public ApprovedWorkOrderItems getWorkorderItemDetails(String itemId, Long workOrderNo) {
		ApprovedWorkOrderItems approvedWorkOrderItems = approvedWorkOrderItemsRepository
				.findByItemIdAndWorkOrderNo(itemId, workOrderNo);
		return approvedWorkOrderItems;
	}

	@Override
	public void saveReturnItem(TempStockReturn tempStockReturn, MultipartFile file) {

		TempStockReturn oldTempStockReturn = tempStockReturnRepository
				.findByWorkOrderNoAndItemId(tempStockReturn.getWorkOrderNo(), tempStockReturn.getItemId());

		Double orderCost = approvedWorkOrderItemsRepository
				.findByItemIdAndWorkOrderNo(tempStockReturn.getItemId(), tempStockReturn.getWorkOrderNo())
				.getTotalCost();

		if (oldTempStockReturn != null) {

			int newReturnQuantity = oldTempStockReturn.getReturnQuantity() + tempStockReturn.getReturnQuantity();
			oldTempStockReturn.setReturnQuantity(newReturnQuantity);

			// Order Cost with GST
			Double orderGst = (orderCost * tempStockReturn.getIgst()) / 100;
			oldTempStockReturn.setOrderTotalCost(orderGst + orderCost);

			// Return Cost with GST
			Double returnCost = newReturnQuantity * oldTempStockReturn.getMrpRate();
			Double returnGst = (returnCost * tempStockReturn.getIgst()) / 100;
			oldTempStockReturn.setReturnTotalCost(returnGst + returnCost);

			tempStockReturnRepository.save(oldTempStockReturn);

		} else {

			String companyName = "";

			Company company = adminService.getCompanyByUsername(tempStockReturn.getUsername());

			if (company != null) {
				companyName = company.getCompanyName();
			} else {
				companyName = "Admin";
			}

			Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
					.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

			String fileName = tempStockReturn.getItemName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "."
					+ tokens.get();
			String uploadDir = "C:\\Company\\" + companyName + "\\Stock_Returns\\";

			try {
				adminService.saveFile(uploadDir, fileName, file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			tempStockReturn.setItemImage(fileName);
			tempStockReturn.setImagePath("/Company/" + companyName + "/Stock_Returns/");

			// Return Cost with GST
			Double returnCost = tempStockReturn.getReturnQuantity() * tempStockReturn.getMrpRate();
			Double returnGst = (returnCost * tempStockReturn.getIgst()) / 100;
			tempStockReturn.setReturnTotalCost(returnCost + returnGst);

			// Order Cost with GST

			Double orderGst = (orderCost * tempStockReturn.getIgst()) / 100;
			tempStockReturn.setOrderTotalCost(orderGst + orderCost);
			tempStockReturnRepository.save(tempStockReturn);
		}

	}

	@Override
	public boolean checkReturnedItem(TempStockReturn tempStockReturn) {

		TempStockReturn oldTempStockReturn = tempStockReturnRepository
				.findByWorkOrderNoAndItemId(tempStockReturn.getWorkOrderNo(), tempStockReturn.getItemId());

		boolean checkReturn = false;

		if (oldTempStockReturn != null) {

			int remainingQuantity = oldTempStockReturn.getOrderQuantity() - oldTempStockReturn.getReturnQuantity();
			int returnQuantity = tempStockReturn.getReturnQuantity();

			if (!(remainingQuantity >= returnQuantity)) {
				checkReturn = true;
			}
		}

		return checkReturn;
	}

	@Override
	public TempStockReturn getReturnQuantity(TempStockReturn tempStockReturn) {

		return tempStockReturnRepository.findByWorkOrderNoAndItemId(tempStockReturn.getWorkOrderNo(),
				tempStockReturn.getItemId());
	}

	@Override
	public List<TempStockReturn> getTempStockReturn(String username) {

		return tempStockReturnRepository.findByUsername(username);
	}

	@Override
	public void deleteTempReturnItem(Long id) {
		tempStockReturnRepository.deleteById(id);
	}

	@Override
	public void deleteAllTempReturnItem() {
		tempStockReturnRepository.deleteAll();
	}

	@Override
	public void saveReturnItems(TempStockReturn tempStockReturn) {

		List<TempStockReturn> tempStocksReturns = tempStockReturnRepository
				.findByUsername(tempStockReturn.getUsername());

		for (TempStockReturn tempStocksReturn : tempStocksReturns) {
			StockReturns stockReturns = modelMapper.map(tempStocksReturn, StockReturns.class);
			stockReturns.setInvoiceNo(tempStockReturn.getInvoiceNo());
			stockReturns.setReturnEntryDate(tempStockReturn.getReturnEntryDate());
			stockReturnsRepository.save(stockReturns);
			tempStockReturnRepository.deleteById(tempStocksReturn.getTempStockId());
		}

	}

	@Override
	public List<StockReturns> deleteReturnItemList() {		
		return stockReturnsRepository.findAll();
	}

	@Override
	public void deleteReturnItem(Long id) {
		stockReturnsRepository.deleteById(id);
	}

}
