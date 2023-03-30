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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.dao.ApprovedStocksReturnRepository;
import com.ingroinfo.mm.dao.ApprovedStockOrderItemsRepository;
import com.ingroinfo.mm.dao.ApprovedStockOrdersRepository;
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
import com.ingroinfo.mm.dao.TempListItemsRepository;
import com.ingroinfo.mm.dao.TempStocksReturnRepository;
import com.ingroinfo.mm.dao.TempStockOrderItemsRepository;
import com.ingroinfo.mm.dao.StockOrderItemsRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderRemovedItemsRepository;
import com.ingroinfo.mm.dao.TempStockOrdersRepository;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.StockOrderItemsDto;
import com.ingroinfo.mm.entity.ApprovedStocksReturn;
import com.ingroinfo.mm.entity.ApprovedStockOrderItems;
import com.ingroinfo.mm.entity.ApprovedStockOrders;
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
import com.ingroinfo.mm.entity.StocksReturn;
import com.ingroinfo.mm.entity.TempListItems;
import com.ingroinfo.mm.entity.TempStocksReturn;
import com.ingroinfo.mm.entity.TempStockOrderItems;
import com.ingroinfo.mm.entity.StockOrderItemsRequest;
import com.ingroinfo.mm.entity.StockOrderRemovedItems;
import com.ingroinfo.mm.entity.TempStockOrders;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private AdminService adminService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

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
	private StockOrderItemsRequestRepository stockOrderItemsRequestRepository;

	@Autowired
	private TempListItemsRepository tempListItemsRepository;

	@Autowired
	private TempStockOrderItemsRepository tempStockOrderItemsRepository;

	@Autowired
	private TempStockOrdersRepository tempStockOrdersRepository;

	@Autowired
	private WorkOrderRemovedItemsRepository stockOrderRemovedItemsRepository;

	@Autowired
	private ItemMasterRepository itemMasterRepository;

	@Autowired
	private ApprovedStockOrderItemsRepository approvedStockOrderItemsRepository;

	@Autowired
	private ApprovedStockOrdersRepository approvedStockOrdersRepository;

	@Autowired
	private TempStocksReturnRepository tempStocksReturnRepository;

	@Autowired
	private StockReturnsRepository stockReturnsRepository;

	@Autowired
	private ApprovedStocksReturnRepository approvedStocksReturnRepository;

	/***** All Stocks *****/

	@Override
	public List<InwardDto> getAllStocks() {
		List<InwardDto> allLists = new ArrayList<>();

		List<InwardApprovedMaterials> materialsLists = inwardApprovedMaterialsRepository.findAll();
		List<InwardApprovedSpares> sparesLists = inwardApprovedSparesRepository.findAll();
		List<InwardApprovedTools> toolsLists = inwardApprovedToolsRepository.findAll();

		for (InwardApprovedMaterials materialsList : materialsLists) {
			InwardDto inwardTempMaterials = modelMapper.map(materialsList, InwardDto.class);
			allLists.add(inwardTempMaterials);
		}

		for (InwardApprovedSpares sparesList : sparesLists) {
			InwardDto inwardTempSpares = modelMapper.map(sparesList, InwardDto.class);
			allLists.add(inwardTempSpares);
		}

		for (InwardApprovedTools toolsList : toolsLists) {
			InwardDto inwardTempTools = modelMapper.map(toolsList, InwardDto.class);
			allLists.add(inwardTempTools);
		}

		return allLists;

	}

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

			inwardMaterials.setApprovalStatus("PENDING");

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

			inwardSpares.setApprovalStatus("PENDING");

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

			inwardTools.setApprovalStatus("PENDING");

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
	public List<StockOrderItemsRequest> getStockOrderNos(String username) {

		List<StockOrderItemsRequest> graph = null;
		try {
			String sql = "SELECT * FROM STOCKORDER_REQUESTS";
			graph = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StockOrderItemsRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return graph;
	}

	/***** Outward Stocks *****/

	@Override
	public List<StockOrderItemsDto> getStockOrderItems(Long stockOrderNo) {

		List<TempListItems> tempIndentItems = tempListItemsRepository.findByStockOrderNo(stockOrderNo);
		for (TempListItems Items : tempIndentItems) {
			tempListItemsRepository.deleteById(Items.getRecordId());
		}

		List<StockOrderItemsRequest> stockOrderItemsRequest = stockOrderItemsRequestRepository
				.findByStockOrderNo(stockOrderNo);

		// Define the list of StockTypes to filter by
		List<String> stockTypes = Arrays.asList("ML", "SP", "TE");

		// Group the items by StockType
		Map<String, List<StockOrderItemsRequest>> groupedMap = stockOrderItemsRequest.stream()
				.filter(item -> stockTypes.contains(item.getStockType()))
				.collect(Collectors.groupingBy(StockOrderItemsRequest::getStockType));

		// Access the lists for each StockType
		List<StockOrderItemsRequest> mlList = groupedMap.get("ML");
		List<StockOrderItemsRequest> spList = groupedMap.get("SP");
		List<StockOrderItemsRequest> teList = groupedMap.get("TE");

		// Create a new list of lists and add the three lists to it
		List<List<StockOrderItemsRequest>> listOfLists = new ArrayList<>();
		listOfLists.add(mlList);
		listOfLists.add(spList);
		listOfLists.add(teList);

		for (int i = 0; i < listOfLists.size(); i++) {
			if (i == 0 && mlList != null) {
				List<String> itemIds = mlList.stream().map(StockOrderItemsRequest::getItemId)
						.collect(Collectors.toList());

				Map<String, Integer> itemQuantity = mlList.stream().collect(
						Collectors.toMap(StockOrderItemsRequest::getItemId, StockOrderItemsRequest::getQuantity));

				for (String itemId : itemIds) {
					if (tempListItemsRepository.findByItemIdAndStockOrderNo(itemId, stockOrderNo).isEmpty()) {
						InwardApprovedMaterials iam = inwardApprovedMaterialsRepository.findByItemId(itemId);
						int stockQuantity = iam.getAvailableQty();
						int quantity = itemQuantity.get(itemId);
						TempListItems tempIndentMaterialItems = modelMapper.map(iam, TempListItems.class);
						tempIndentMaterialItems.setStockOrderNo(stockOrderNo);
						tempIndentMaterialItems.setQty(quantity);
						if (stockQuantity >= quantity) {
							tempIndentMaterialItems.setFinalQuantity(quantity);
							tempIndentMaterialItems.setTotalCost(iam.getMrpRate() * quantity);
						} else {
							tempIndentMaterialItems.setFinalQuantity(stockQuantity);
							tempIndentMaterialItems.setTotalCost(iam.getMrpRate() * stockQuantity);
						}

						tempListItemsRepository.save(tempIndentMaterialItems);
					}
				}
			}
			if (i == 1 && spList != null) {
				List<String> itemIds = spList.stream().map(StockOrderItemsRequest::getItemId)
						.collect(Collectors.toList());

				Map<String, Integer> itemQuantity = spList.stream().collect(
						Collectors.toMap(StockOrderItemsRequest::getItemId, StockOrderItemsRequest::getQuantity));

				for (String itemId : itemIds) {

					if (tempListItemsRepository.findByItemIdAndStockOrderNo(itemId, stockOrderNo).isEmpty()) {
						InwardApprovedSpares ias = inwardApprovedSparesRepository.findByItemId(itemId);
						int stockQuantity = ias.getAvailableQty();
						int quantity = itemQuantity.get(itemId);
						TempListItems tempIndentSpareItems = modelMapper.map(ias, TempListItems.class);
						tempIndentSpareItems.setStockOrderNo(stockOrderNo);
						tempIndentSpareItems.setQty(quantity);

						if (stockQuantity >= quantity) {
							tempIndentSpareItems.setFinalQuantity(quantity);
							tempIndentSpareItems.setTotalCost(ias.getMrpRate() * quantity);
						} else {
							tempIndentSpareItems.setFinalQuantity(stockQuantity);
							tempIndentSpareItems.setTotalCost(ias.getMrpRate() * quantity);
						}
						tempListItemsRepository.save(tempIndentSpareItems);
					}
				}
			}
			if (i == 2 && teList != null) {
				List<String> itemIds = teList.stream().map(StockOrderItemsRequest::getItemId)
						.collect(Collectors.toList());

				Map<String, Integer> itemQuantity = teList.stream().collect(
						Collectors.toMap(StockOrderItemsRequest::getItemId, StockOrderItemsRequest::getQuantity));

				for (String itemId : itemIds) {
					if (tempListItemsRepository.findByItemIdAndStockOrderNo(itemId, stockOrderNo).isEmpty()) {
						InwardApprovedTools iat = inwardApprovedToolsRepository.findByItemId(itemId);
						int stockQuantity = iat.getAvailableQty();
						int quantity = itemQuantity.get(itemId);
						TempListItems tempIndentToolItems = modelMapper.map(iat, TempListItems.class);
						tempIndentToolItems.setStockOrderNo(stockOrderNo);
						tempIndentToolItems.setQty(quantity);

						if (stockQuantity >= quantity) {
							tempIndentToolItems.setFinalQuantity(quantity);
							tempIndentToolItems.setTotalCost(iat.getMrpRate() * quantity);
						} else {
							tempIndentToolItems.setFinalQuantity(stockQuantity);
							tempIndentToolItems.setTotalCost(iat.getMrpRate() * quantity);
						}
						tempListItemsRepository.save(tempIndentToolItems);
					}
				}
			}
		}

		List<TempListItems> listOfTempIndentItems = tempListItemsRepository.findByStockOrderNo(stockOrderNo);

		int count = 1;
		for (int i = 0; i < listOfTempIndentItems.size(); i++) {
			listOfTempIndentItems.get(i).setSlNo(count);
			tempListItemsRepository.save(listOfTempIndentItems.get(i));
			count++;
		}
		List<StockOrderItemsDto> getIdentDto = getIndentItems(stockOrderNo);

		return getIdentDto;
	}

	private List<StockOrderItemsDto> getIndentItems(Long stockOrderNo) {

		List<TempListItems> listOfTempIndentItems = tempListItemsRepository.findByStockOrderNo(stockOrderNo);
		List<String> stockTypes = Arrays.asList("ML", "SP", "TE");

		List<StockOrderItemsDto> wOIDto = new ArrayList<StockOrderItemsDto>();
		for (TempListItems tempIndentItem : listOfTempIndentItems) {

			StockOrderItemsDto newwOIDto = new StockOrderItemsDto();

			newwOIDto.setAliasName(tempIndentItem.getAliasName());
			newwOIDto.setCategory(tempIndentItem.getCategory());
			newwOIDto.setDescription(tempIndentItem.getDescription());
			newwOIDto.setFinalQuantity(tempIndentItem.getFinalQuantity());
			newwOIDto.setImagePath(tempIndentItem.getImagePath());
			newwOIDto.setItemId(tempIndentItem.getItemId());
			newwOIDto.setItemImage(tempIndentItem.getItemImage());
			newwOIDto.setItemName(tempIndentItem.getItemName());
			newwOIDto.setMrpRate(tempIndentItem.getMrpRate());
			newwOIDto.setQty(tempIndentItem.getQty());
			newwOIDto.setSlNo(tempIndentItem.getSlNo());
			newwOIDto.setStockType(tempIndentItem.getStockType());
			newwOIDto.setTotalCost(tempIndentItem.getTotalCost());
			newwOIDto.setUnitOfMeasure(tempIndentItem.getUnitOfMeasure());
			newwOIDto.setStockOrderNo(tempIndentItem.getStockOrderNo());

			if (inwardApprovedMaterialsRepository.findByItemIdAndStockType(newwOIDto.getItemId(),
					stockTypes.get(0)) != null) {
				int stockQuantity = inwardApprovedMaterialsRepository.findByItemId(newwOIDto.getItemId())
						.getAvailableQty();
				newwOIDto.setStockAvailable(stockQuantity);
			} else if (inwardApprovedSparesRepository.findByItemIdAndStockType(newwOIDto.getItemId(),
					stockTypes.get(1)) != null) {
				int stockQuantity = inwardApprovedSparesRepository.findByItemId(newwOIDto.getItemId())
						.getAvailableQty();
				newwOIDto.setStockAvailable(stockQuantity);

			} else if (inwardApprovedToolsRepository.findByItemIdAndStockType(newwOIDto.getItemId(),
					stockTypes.get(2)) != null) {
				int stockQuantity = inwardApprovedToolsRepository.findByItemId(newwOIDto.getItemId()).getAvailableQty();
				newwOIDto.setStockAvailable(stockQuantity);
			}
			wOIDto.add(newwOIDto);
		}

		return wOIDto;
	}

	@Override
	public List<StockOrderItemsDto> checkStockQuantity(Long stockOrderId) {

		List<StockOrderItemsRequest> stockOrders = stockOrderItemsRequestRepository.findByStockOrderNo(stockOrderId);
		List<String> itemIds = stockOrders.stream().map(StockOrderItemsRequest::getItemId).collect(Collectors.toList());
		Map<String, Integer> itemQuantity = stockOrders.stream()
				.collect(Collectors.toMap(StockOrderItemsRequest::getItemId, StockOrderItemsRequest::getQuantity));
		List<StockOrderItemsDto> twoi = new ArrayList<>();
		for (String itemId : itemIds) {
			StockOrderItemsDto wOI = new StockOrderItemsDto();
			int stockQuantity = 0;
			if (inwardApprovedMaterialsRepository.findByItemIdAndStockType(itemId, "ML") != null) {
				stockQuantity = inwardApprovedMaterialsRepository.findByItemId(itemId).getAvailableQty();

			} else if (inwardApprovedSparesRepository.findByItemIdAndStockType(itemId, "SP") != null) {
				stockQuantity = inwardApprovedSparesRepository.findByItemId(itemId).getAvailableQty();

			} else if (inwardApprovedToolsRepository.findByItemIdAndStockType(itemId, "TE") != null) {
				stockQuantity = inwardApprovedToolsRepository.findByItemId(itemId).getAvailableQty();

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
	public void saveStockOrder(TempStockOrders stockOrders, String username) {
		stockOrders.setUsername(username);
		stockOrders.setApprovalStatus("PENDING");
		TempStockOrders tempStockOrders = tempStockOrdersRepository.save(stockOrders);
		List<TempListItems> tempIndentItems = tempListItemsRepository
				.findByStockOrderNo(tempStockOrders.getStockOrderNo());

		for (TempListItems tempIndentItem : tempIndentItems) {
			TempStockOrderItems tempStockOrderItems = new TempStockOrderItems();
			tempStockOrderItems.setAliasName(tempIndentItem.getAliasName());
			tempStockOrderItems.setCategory(tempIndentItem.getCategory());
			tempStockOrderItems.setDescription(tempIndentItem.getDescription());
			tempStockOrderItems.setFinalQuantity(tempIndentItem.getFinalQuantity());
			tempStockOrderItems.setImagePath(tempIndentItem.getImagePath());
			tempStockOrderItems.setItemId(tempIndentItem.getItemId());
			tempStockOrderItems.setItemImage(tempIndentItem.getItemImage());
			tempStockOrderItems.setItemName(tempIndentItem.getItemName());
			tempStockOrderItems.setMrpRate(tempIndentItem.getMrpRate());
			tempStockOrderItems.setSlNo(tempIndentItem.getSlNo());
			tempStockOrderItems.setStockType(tempIndentItem.getStockType());
			tempStockOrderItems.setTotalCost(tempIndentItem.getTotalCost());
			tempStockOrderItems.setUnitOfMeasure(tempIndentItem.getUnitOfMeasure());
			tempStockOrderItems.setStockOrderNo(tempIndentItem.getStockOrderNo());
			tempStockOrderItems.setOrderId(tempStockOrders.getOrderId());
			tempStockOrderItems.setTotalCost(tempStockOrderItems.getFinalQuantity() * tempStockOrderItems.getMrpRate());
			tempStockOrderItems.setUsername(username);
			tempStockOrderItems.setComplNo(stockOrders.getComplNo());
			tempStockOrderItems.setDepartmentName(stockOrders.getDepartmentName());
			tempStockOrderItems.setIndentNo(stockOrders.getIndentNo());
			tempStockOrderItemsRepository.save(tempStockOrderItems);
			tempListItemsRepository.deleteById(tempIndentItem.getRecordId());
		}

		List<StockOrderItemsRequest> stockOrderItemsRequest = stockOrderItemsRequestRepository
				.findByStockOrderNo(tempStockOrders.getStockOrderNo());
		for (StockOrderItemsRequest stockItems : stockOrderItemsRequest)
			stockOrderItemsRequestRepository.deleteById(stockItems.getRecordId());
	}

	@Override
	public boolean notAvailableItems(Long stockOrderNo) {
		List<StockOrderItemsDto> stockOrderItems = getIndentItems(stockOrderNo);
		for (StockOrderItemsDto stockOrderItem : stockOrderItems) {
			if (stockOrderItem.getStockAvailable() == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void saveRemovedItems(String itemId, Long stockOrderNo, String username) {
		StockOrderRemovedItems removedItem = new StockOrderRemovedItems();

		int stockQuantity = 0;
		String stockType = null;

		InwardApprovedMaterials inwardApprovedMaterials = inwardApprovedMaterialsRepository.findByItemId(itemId);
		InwardApprovedSpares inwardApprovedSpares = inwardApprovedSparesRepository.findByItemId(itemId);
		InwardApprovedTools inwardApprovedTools = inwardApprovedToolsRepository.findByItemId(itemId);

		if (inwardApprovedMaterials != null) {
			stockQuantity = inwardApprovedMaterials.getAvailableQty();
			stockType = inwardApprovedMaterials.getStockType();
		}
		if (inwardApprovedSpares != null) {
			stockQuantity = inwardApprovedSpares.getAvailableQty();
			stockType = inwardApprovedSpares.getStockType();
		}
		if (inwardApprovedTools != null) {
			stockQuantity = inwardApprovedTools.getAvailableQty();
			stockType = inwardApprovedTools.getStockType();
		}

		int requiredQuantity = tempListItemsRepository.findByItemIdAndStockOrderNo(itemId, stockOrderNo).get().getQty();

		removedItem.setItemId(itemId);
		removedItem.setRequestedQuantity(requiredQuantity);
		removedItem.setStockQuantity(stockQuantity);
		removedItem.setWorkOrderNo(stockOrderNo);
		removedItem.setUsername(username);
		removedItem.setStockType(stockType);

		if (stockQuantity == 0) {
			removedItem.setAvailability("Not Available in Stock");
			removedItem.setRemarks("The item is not available, so it has been removed from the list.");
		} else {
			removedItem.setAvailability("Available in Stock");
			removedItem.setRemarks("User removed the item from the list because it was no longer needed.");
		}

		stockOrderRemovedItemsRepository.save(removedItem);
	}

	@Override
	public boolean getTempStockOrderItems(Long stockOrderNo) {
		boolean flag = false;
		List<TempListItems> tempIndentItems = tempListItemsRepository.findByStockOrderNo(stockOrderNo);

		if (tempIndentItems.size() == 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public List<TempStockOrders> getOutwardStockOrders() {
		return tempStockOrdersRepository.findAll();
	}

	@Override
	public List<TempStockOrderItems> getOutwardStockOrderItems(Long stockOrderNo) {
		return tempStockOrderItemsRepository.findByStockOrderNo(stockOrderNo);
	}

	@Override
	public TempStockOrders getOutwardStockOrder(Long stockOrderNo) {
		return tempStockOrdersRepository.findByStockOrderNo(stockOrderNo);
	}

	@Override
	public List<ApprovedStockOrders> getOutwardApprovedStockOrders() {
		return approvedStockOrdersRepository.findAll();
	}

	@Override
	public List<ApprovedStockOrderItems> getOutwardApprovedStockOrderItems(Long stockOrderNo) {
		return approvedStockOrderItemsRepository.findByStockOrderNo(stockOrderNo);
	}

	@Override
	public ApprovedStockOrders getOutwardApprovedStockOrder(Long stockOrderNo) {
		return approvedStockOrdersRepository.findByStockOrderNo(stockOrderNo);
	}

	@Override
	public StockOrderItemsDto getStockorderItemDetails(String itemId, Long stockOrderNo) {
		ApprovedStockOrderItems approvedstockOrderItems = approvedStockOrderItemsRepository
				.findByItemIdAndStockOrderNo(itemId, stockOrderNo);

		ApprovedStockOrders approvedstockOrder = approvedStockOrdersRepository.findByStockOrderNo(stockOrderNo);
		StockOrderItemsDto stockOrderItems = new StockOrderItemsDto();

		stockOrderItems.setAliasName(approvedstockOrderItems.getAliasName());
		stockOrderItems.setCategory(approvedstockOrderItems.getCategory());
		stockOrderItems.setComplNo(approvedstockOrder.getComplNo());
		stockOrderItems.setDepartmentName(approvedstockOrder.getDepartmentName());
		stockOrderItems.setDescription(approvedstockOrderItems.getDescription());
		stockOrderItems.setFinalQuantity(approvedstockOrderItems.getFinalQuantity());
		stockOrderItems.setImagePath(approvedstockOrderItems.getImagePath());
		stockOrderItems.setIndentNo(approvedstockOrder.getIndentNo());
		stockOrderItems.setItemId(approvedstockOrderItems.getItemId());
		stockOrderItems.setItemImage(approvedstockOrderItems.getItemImage());
		stockOrderItems.setItemName(approvedstockOrderItems.getItemName());
		stockOrderItems.setMrpRate(approvedstockOrderItems.getMrpRate());
		stockOrderItems.setRecordId(approvedstockOrderItems.getRecordId());
		stockOrderItems.setSlNo(approvedstockOrderItems.getSlNo());
		stockOrderItems.setStockType(approvedstockOrderItems.getStockType());
		stockOrderItems.setTotalCost(approvedstockOrderItems.getTotalCost());
		stockOrderItems.setUnitOfMeasure(approvedstockOrderItems.getUnitOfMeasure());
		stockOrderItems.setStockOrderNo(approvedstockOrderItems.getStockOrderNo());

		return stockOrderItems;
	}

	/***** Stocks Return *****/

	@Override
	public void saveReturnItem(TempStocksReturn stockReturn, MultipartFile file) {

		TempStocksReturn tempStocksReturn = tempStocksReturnRepository
				.findByStockOrderNoAndItemId(stockReturn.getStockOrderNo(), stockReturn.getItemId());

		Double orderCost = approvedStockOrderItemsRepository
				.findByItemIdAndStockOrderNo(stockReturn.getItemId(), stockReturn.getStockOrderNo()).getTotalCost();

		if (tempStocksReturn != null) {

			int newReturnQuantity = tempStocksReturn.getReturnQuantity() + stockReturn.getReturnQuantity();
			tempStocksReturn.setReturnQuantity(newReturnQuantity);

			// Order Cost with GST
			Double orderGst = (orderCost * stockReturn.getIgst()) / 100;
			tempStocksReturn.setOrderTotalCost(orderGst + orderCost);

			// Return Cost with GST
			Double returnCost = newReturnQuantity * tempStocksReturn.getMrpRate();
			Double returnGst = (returnCost * stockReturn.getIgst()) / 100;
			tempStocksReturn.setReturnTotalCost(returnGst + returnCost);

			tempStocksReturnRepository.save(tempStocksReturn);

		} else {

			String companyName = "";

			Company company = adminService.getCompanyByUsername(stockReturn.getUsername());

			if (company != null) {
				companyName = company.getCompanyName();
			} else {
				companyName = "Admin";
			}

			Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
					.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

			String fileName = stockReturn.getItemName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "."
					+ tokens.get();
			String uploadDir = "C:\\Company\\" + companyName + "\\Stock_Returns\\";

			try {
				adminService.saveFile(uploadDir, fileName, file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			stockReturn.setItemImage(fileName);
			stockReturn.setImagePath("/Company/" + companyName + "/Stock_Returns/");

			// Return Cost with GST
			Double returnCost = stockReturn.getReturnQuantity() * stockReturn.getMrpRate();
			Double returnGst = (returnCost * stockReturn.getIgst()) / 100;
			stockReturn.setReturnTotalCost(returnCost + returnGst);

			// Order Cost with GST

			Double orderGst = (orderCost * stockReturn.getIgst()) / 100;
			stockReturn.setOrderTotalCost(orderGst + orderCost);
			tempStocksReturnRepository.save(stockReturn);
		}

	}

	@Override
	public boolean checkReturnedItem(TempStocksReturn stocksReturn) {

		TempStocksReturn tempStocksReturn = tempStocksReturnRepository
				.findByStockOrderNoAndItemId(stocksReturn.getStockOrderNo(), stocksReturn.getItemId());

		boolean checkReturn = false;

		if (tempStocksReturn != null) {

			int remainingQuantity = tempStocksReturn.getOrderQuantity() - tempStocksReturn.getReturnQuantity();
			int returnQuantity = stocksReturn.getReturnQuantity();

			if (!(remainingQuantity >= returnQuantity)) {
				checkReturn = true;
			}
		}

		return checkReturn;
	}

	@Override
	public TempStocksReturn getReturnQuantity(TempStocksReturn stocksReturn) {

		return tempStocksReturnRepository.findByStockOrderNoAndItemId(stocksReturn.getStockOrderNo(),
				stocksReturn.getItemId());
	}

	@Override
	public List<TempStocksReturn> getTempStockReturn(String username) {

		return tempStocksReturnRepository.findByUsername(username);
	}

	@Override
	public void deleteTempReturnItem(Long id) {
		tempStocksReturnRepository.deleteById(id);
	}

	@Override
	public void deleteAllTempReturnItem() {
		tempStocksReturnRepository.deleteAll();
	}

	@Override
	public void saveReturnItems(TempStocksReturn stockReturn) {

		List<TempStocksReturn> tempStocksReturn = tempStocksReturnRepository.findByUsername(stockReturn.getUsername());

		for (TempStocksReturn tempStockReturn : tempStocksReturn) {
			StocksReturn stocksReturn = modelMapper.map(tempStockReturn, StocksReturn.class);
			stocksReturn.setInvoiceNo(stockReturn.getInvoiceNo());
			stocksReturn.setReturnEntryDate(stockReturn.getReturnEntryDate());
			stockReturnsRepository.save(stocksReturn);
			tempStocksReturnRepository.deleteById(tempStockReturn.getRecordId());
		}
	}

	@Override
	public List<StocksReturn> getStockReturnItemList() {
		return stockReturnsRepository.findAll();
	}

	@Override
	public void deleteReturnItem(Long id) {
		stockReturnsRepository.deleteById(id);
	}

	@Override
	public List<ApprovedStocksReturn> getApprovedStockReturnItemList() {

		return approvedStocksReturnRepository.findAll();
	}

}
