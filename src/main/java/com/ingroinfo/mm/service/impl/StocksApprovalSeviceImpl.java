package com.ingroinfo.mm.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.ApprovedStocksReturnRepository;
import com.ingroinfo.mm.dao.ApprovedStockOrderItemsRepository;
import com.ingroinfo.mm.dao.ApprovedStockOrdersRepository;
import com.ingroinfo.mm.dao.InwardApprovedMaterialsRepository;
import com.ingroinfo.mm.dao.InwardApprovedSparesRepository;
import com.ingroinfo.mm.dao.InwardApprovedToolsRepository;
import com.ingroinfo.mm.dao.InwardMaterialsRepository;
import com.ingroinfo.mm.dao.InwardRejectedMaterialsRepository;
import com.ingroinfo.mm.dao.InwardRejectedSparesRepository;
import com.ingroinfo.mm.dao.InwardRejectedToolsRepository;
import com.ingroinfo.mm.dao.InwardSparesRepository;
import com.ingroinfo.mm.dao.InwardToolsRepository;
import com.ingroinfo.mm.dao.RejectedStocksReturnRepository;
import com.ingroinfo.mm.dao.RejectedStockOrderItemsRepository;
import com.ingroinfo.mm.dao.RejectedStockOrdersRepository;
import com.ingroinfo.mm.dao.StockReturnsRepository;
import com.ingroinfo.mm.dao.TempStockOrderItemsRepository;
import com.ingroinfo.mm.dao.TempStockOrdersRepository;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.entity.ApprovedStocksReturn;
import com.ingroinfo.mm.entity.ApprovedStockOrderItems;
import com.ingroinfo.mm.entity.ApprovedStockOrders;
import com.ingroinfo.mm.entity.InwardApprovedMaterials;
import com.ingroinfo.mm.entity.InwardApprovedSpares;
import com.ingroinfo.mm.entity.InwardApprovedTools;
import com.ingroinfo.mm.entity.InwardMaterials;
import com.ingroinfo.mm.entity.InwardRejectedMaterials;
import com.ingroinfo.mm.entity.InwardRejectedSpares;
import com.ingroinfo.mm.entity.InwardRejectedTools;
import com.ingroinfo.mm.entity.InwardSpares;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.RejectedStocksReturn;
import com.ingroinfo.mm.entity.RejectedStockOrderItems;
import com.ingroinfo.mm.entity.RejectedStockOrders;
import com.ingroinfo.mm.entity.StocksReturn;
import com.ingroinfo.mm.entity.TempStockOrderItems;
import com.ingroinfo.mm.entity.TempStockOrders;
import com.ingroinfo.mm.service.StocksApprovalService;

@Service
public class StocksApprovalSeviceImpl implements StocksApprovalService {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private InwardApprovedMaterialsRepository inwardApprovedMaterialsRepository;

	@Autowired
	private InwardRejectedMaterialsRepository inwardRejectedMaterialsRepository;

	@Autowired
	private InwardMaterialsRepository inwardMaterialsRepository;

	@Autowired
	private InwardApprovedSparesRepository inwardApprovedSparesRepository;

	@Autowired
	private InwardRejectedSparesRepository inwardRejectedSparesRepository;

	@Autowired
	private InwardSparesRepository inwardSparesRepository;

	@Autowired
	private InwardApprovedToolsRepository inwardApprovedToolsRepository;

	@Autowired
	private InwardRejectedToolsRepository inwardRejectedToolsRepository;

	@Autowired
	private InwardToolsRepository inwardToolsRepository;

	@Autowired
	private TempStockOrderItemsRepository tempStockOrderItemsRepository;

	@Autowired
	private ApprovedStockOrderItemsRepository approvedStockOrderItemsRepository;

	@Autowired
	private ApprovedStockOrdersRepository approvedStockOrdersRepository;

	@Autowired
	private TempStockOrdersRepository tempStockOrdersRepository;

	@Autowired
	private RejectedStockOrderItemsRepository rejectedStockOrderItemsRepository;

	@Autowired
	private RejectedStockOrdersRepository rejectedStockOrderNosRepository;

	@Autowired
	private ApprovedStocksReturnRepository approvedStocksReturnRepository;

	@Autowired
	private StockReturnsRepository stockReturnsRepository;

	@Autowired
	private RejectedStocksReturnRepository rejectedStocksReturnRepository;

	/**************************** Materials ********************************/

	@Override
	public void saveMaterial(InwardDto inwardItemDto) {

		InwardApprovedMaterials inwardApprovedMaterials = modelMapper.map(inwardItemDto, InwardApprovedMaterials.class);
		inwardApprovedMaterials.setStockType("ML");
		inwardApprovedMaterials.setAvailableQty(inwardItemDto.getQuantity());
		inwardApprovedMaterials.setApprovalStatus("APPROVED");
		InwardApprovedMaterials newApprovedMaterials = inwardApprovedMaterialsRepository.save(inwardApprovedMaterials);

		if (newApprovedMaterials != null) {
			inwardMaterialsRepository.deleteById(inwardItemDto.getMaterialId());
		}
	}

	@Override
	public void rejectMaterial(Long id, String username) {

		InwardMaterials inwardMaterial = inwardMaterialsRepository.findByMaterialId(id);

		InwardRejectedMaterials rejectMaterial = modelMapper.map(inwardMaterial, InwardRejectedMaterials.class);
		rejectMaterial.setUsername(username);
		InwardRejectedMaterials rejectedMaterial = inwardRejectedMaterialsRepository.save(rejectMaterial);

		if (rejectedMaterial != null) {
			inwardMaterialsRepository.deleteById(id);
		}
	}

	/**************************** Spares ********************************/
	@Override
	public void saveSpare(InwardDto inwardItemDto) {

		InwardApprovedSpares inwardApprovedSpares = modelMapper.map(inwardItemDto, InwardApprovedSpares.class);
		inwardApprovedSpares.setStockType("SP");
		inwardApprovedSpares.setAvailableQty(inwardItemDto.getQuantity());
		inwardApprovedSpares.setApprovalStatus("APPROVED");
		InwardApprovedSpares newApprovedSpares = inwardApprovedSparesRepository.save(inwardApprovedSpares);

		if (newApprovedSpares != null) {
			inwardSparesRepository.deleteById(inwardItemDto.getSpareId());
		}
	}

	@Override
	public void rejectSpare(Long id, String username) {

		InwardSpares inwardSpare = inwardSparesRepository.findBySpareId(id);

		InwardRejectedSpares rejectSpare = modelMapper.map(inwardSpare, InwardRejectedSpares.class);
		rejectSpare.setUsername(username);
		InwardRejectedSpares rejectedSpare = inwardRejectedSparesRepository.save(rejectSpare);

		if (rejectedSpare != null) {
			inwardSparesRepository.deleteById(id);
		}
	}

	/**************************** Tools ********************************/

	@Override
	public void saveTool(InwardDto inwardItemDto) {
		InwardApprovedTools inwardApprovedTools = modelMapper.map(inwardItemDto, InwardApprovedTools.class);
		inwardApprovedTools.setStockType("TE");
		inwardApprovedTools.setAvailableQty(inwardItemDto.getQuantity());
		inwardApprovedTools.setApprovalStatus("APPROVED");
		InwardApprovedTools newApprovedTools = inwardApprovedToolsRepository.save(inwardApprovedTools);

		if (newApprovedTools != null) {
			inwardToolsRepository.deleteById(inwardItemDto.getToolId());
		}

	}

	@Override
	public void rejectTool(Long id, String username) {

		InwardTools inwardTool = inwardToolsRepository.findByToolId(id);

		InwardRejectedTools rejectTool = modelMapper.map(inwardTool, InwardRejectedTools.class);
		rejectTool.setUsername(username);
		InwardRejectedTools rejectedTools = inwardRejectedToolsRepository.save(rejectTool);

		if (rejectedTools != null) {
			inwardToolsRepository.deleteById(id);
		}
	}

	/*********************** Outwards Stock orders ********************************/
	@Override
	public void approveOutwardStocks(Long stockOrderNo, String username) {

		TempStockOrders tempStockOrders = tempStockOrdersRepository.findByStockOrderNo(stockOrderNo);

		ApprovedStockOrders approvedStockOrders = new ApprovedStockOrders();

		approvedStockOrders.setBilledOn(tempStockOrders.getBilledOn());
		approvedStockOrders.setCgst(tempStockOrders.getCgst());
		approvedStockOrders.setGrandTotal(tempStockOrders.getGrandTotal());
		approvedStockOrders.setGstType(tempStockOrders.getGstType());
		approvedStockOrders.setIgst(tempStockOrders.getIgst());
		approvedStockOrders.setSgst(tempStockOrders.getSgst());
		approvedStockOrders.setSubTotal(tempStockOrders.getSubTotal());
		approvedStockOrders.setUsername(username);
		approvedStockOrders.setStockOrderNo(tempStockOrders.getStockOrderNo());
		
		approvedStockOrders.setComplDtls(tempStockOrders.getComplDtls());
		approvedStockOrders.setComplNo(tempStockOrders.getComplNo());
		approvedStockOrders.setContactNo(tempStockOrders.getContactNo());
		approvedStockOrders.setDepartmentName(tempStockOrders.getDepartmentName());
		approvedStockOrders.setDivision(tempStockOrders.getDivision());
		approvedStockOrders.setEndDate(tempStockOrders.getEndDate());
		approvedStockOrders.setIndentNo(tempStockOrders.getIndentNo());
		approvedStockOrders.setStartDate(tempStockOrders.getStartDate());
		approvedStockOrders.setSubDivision(tempStockOrders.getSubDivision());
		approvedStockOrders.setWorkPriority(tempStockOrders.getWorkPriority());
		approvedStockOrders.setWorkSite(tempStockOrders.getWorkSite());
		
		

		ApprovedStockOrders savedApprovedStockOrders = approvedStockOrdersRepository.save(approvedStockOrders);

		List<TempStockOrderItems> tempStockOrderItems = tempStockOrderItemsRepository.findByStockOrderNo(stockOrderNo);

		for (TempStockOrderItems tempStockOrderItem : tempStockOrderItems) {

			ApprovedStockOrderItems approvedStockOrderItems = new ApprovedStockOrderItems();

			approvedStockOrderItems.setAliasName(tempStockOrderItem.getAliasName());
			approvedStockOrderItems.setCategory(tempStockOrderItem.getCategory());
			approvedStockOrderItems.setDescription(tempStockOrderItem.getDescription());
			approvedStockOrderItems.setFinalQuantity(tempStockOrderItem.getFinalQuantity());
			approvedStockOrderItems.setImagePath(tempStockOrderItem.getImagePath());
			approvedStockOrderItems.setItemId(tempStockOrderItem.getItemId());
			approvedStockOrderItems.setItemImage(tempStockOrderItem.getItemImage());
			approvedStockOrderItems.setItemName(tempStockOrderItem.getItemName());
			approvedStockOrderItems.setMrpRate(tempStockOrderItem.getMrpRate());
			approvedStockOrderItems.setSlNo(tempStockOrderItem.getSlNo());
			approvedStockOrderItems.setStockType(tempStockOrderItem.getStockType());
			approvedStockOrderItems.setTotalCost(tempStockOrderItem.getTotalCost());
			approvedStockOrderItems.setUnitOfMeasure(tempStockOrderItem.getUnitOfMeasure());
			approvedStockOrderItems.setStockOrderNo(tempStockOrderItem.getStockOrderNo());
			approvedStockOrderItems.setOrderId(savedApprovedStockOrders);
			approvedStockOrderItems.setUsername(username);
			approvedStockOrderItemsRepository.save(approvedStockOrderItems);

			int requiredQty = tempStockOrderItem.getFinalQuantity();

			InwardApprovedMaterials inwardApprovedMaterials = inwardApprovedMaterialsRepository
					.findByItemId(approvedStockOrderItems.getItemId());

			InwardApprovedSpares inwardApprovedSpares = inwardApprovedSparesRepository
					.findByItemId(approvedStockOrderItems.getItemId());

			InwardApprovedTools inwardApprovedTools = inwardApprovedToolsRepository
					.findByItemId(approvedStockOrderItems.getItemId());

			if (inwardApprovedMaterials != null) {
				int MaterialStockQty = inwardApprovedMaterials.getQuantity();
				inwardApprovedMaterials.setAvailableQty(MaterialStockQty - requiredQty);
				inwardApprovedMaterialsRepository.save(inwardApprovedMaterials);
			}
			if (inwardApprovedSpares != null) {
				int SpareStockQty = inwardApprovedSpares.getQuantity();
				inwardApprovedSpares.setAvailableQty(SpareStockQty - requiredQty);
				inwardApprovedSparesRepository.save(inwardApprovedSpares);
			}
			if (inwardApprovedTools != null) {
				int ToolStockQty = inwardApprovedTools.getQuantity();
				inwardApprovedTools.setAvailableQty(ToolStockQty - requiredQty);
				inwardApprovedToolsRepository.save(inwardApprovedTools);
			}

		}

		tempStockOrdersRepository.deleteById(tempStockOrders.getOrderId());
	}

	@Override
	public void rejectStockorderItems(Long stockOrderNo, String username) {
		TempStockOrders tempStockOrders = tempStockOrdersRepository.findByStockOrderNo(stockOrderNo);

		RejectedStockOrders rejectedStockOrderNos = new RejectedStockOrders();
		rejectedStockOrderNos.setBilledOn(tempStockOrders.getBilledOn());
		rejectedStockOrderNos.setCgst(tempStockOrders.getCgst());
		rejectedStockOrderNos.setGrandTotal(tempStockOrders.getGrandTotal());
		rejectedStockOrderNos.setGstType(tempStockOrders.getGstType());
		rejectedStockOrderNos.setIgst(tempStockOrders.getIgst());
		rejectedStockOrderNos.setSgst(tempStockOrders.getSgst());
		rejectedStockOrderNos.setSubTotal(tempStockOrders.getSubTotal());
		rejectedStockOrderNos.setUsername(username);
		rejectedStockOrderNos.setStockOrderNo(tempStockOrders.getStockOrderNo());
		
		rejectedStockOrderNos.setComplNo(tempStockOrders.getComplNo());
		rejectedStockOrderNos.setIndentNo(tempStockOrders.getIndentNo());
		rejectedStockOrderNos.setDepartmentName(tempStockOrders.getDepartmentName());

		RejectedStockOrders rejectedStockOrderNosId = rejectedStockOrderNosRepository.save(rejectedStockOrderNos);

		List<TempStockOrderItems> tempStockOrderItems = tempStockOrderItemsRepository.findByStockOrderNo(stockOrderNo);

		for (TempStockOrderItems StockOrderItem : tempStockOrderItems) {

			RejectedStockOrderItems rejectedStockOrderItems = new RejectedStockOrderItems();

			rejectedStockOrderItems.setAliasName(StockOrderItem.getAliasName());
			rejectedStockOrderItems.setCategory(StockOrderItem.getCategory());
			rejectedStockOrderItems.setDescription(StockOrderItem.getDescription());
			rejectedStockOrderItems.setFinalQuantity(StockOrderItem.getFinalQuantity());
			rejectedStockOrderItems.setImagePath(StockOrderItem.getImagePath());
			rejectedStockOrderItems.setItemId(StockOrderItem.getItemId());
			rejectedStockOrderItems.setItemImage(StockOrderItem.getItemImage());
			rejectedStockOrderItems.setItemName(StockOrderItem.getItemName());
			rejectedStockOrderItems.setMrpRate(StockOrderItem.getMrpRate());
			rejectedStockOrderItems.setSlNo(StockOrderItem.getSlNo());
			rejectedStockOrderItems.setStockType(StockOrderItem.getStockType());
			rejectedStockOrderItems.setTotalCost(StockOrderItem.getTotalCost());
			rejectedStockOrderItems.setUnitOfMeasure(StockOrderItem.getUnitOfMeasure());
			rejectedStockOrderItems.setStockOrderNo(StockOrderItem.getStockOrderNo());
			rejectedStockOrderItems.setOrderId(rejectedStockOrderNosId);
			rejectedStockOrderItems.setUsername(username);
			rejectedStockOrderItemsRepository.save(rejectedStockOrderItems);
		}

		tempStockOrdersRepository.deleteById(tempStockOrders.getOrderId());

	}

	/******************************* Return Stocks ********************************/

	@Override
	public void approveReturnItem(Long id, String username) {

		StocksReturn stocksReturn = stockReturnsRepository.findByRecordId(id);

		ApprovedStocksReturn approvedStocksReturn = new ApprovedStocksReturn();

		approvedStocksReturn.setCategory(stocksReturn.getCategory());
		approvedStocksReturn.setCgst(stocksReturn.getCgst());
		approvedStocksReturn.setDescription(stocksReturn.getDescription());
		approvedStocksReturn.setIgst(stocksReturn.getIgst());
		approvedStocksReturn.setImagePath(stocksReturn.getImagePath());
		approvedStocksReturn.setInvoiceNo(stocksReturn.getInvoiceNo());
		approvedStocksReturn.setItemId(stocksReturn.getItemId());
		approvedStocksReturn.setItemImage(stocksReturn.getItemImage());
		approvedStocksReturn.setItemName(stocksReturn.getItemName());
		approvedStocksReturn.setMrpRate(stocksReturn.getMrpRate());
		approvedStocksReturn.setOrderQuantity(stocksReturn.getOrderQuantity());
		approvedStocksReturn.setOrderTotalCost(stocksReturn.getOrderTotalCost());
		approvedStocksReturn.setReturnEntryDate(stocksReturn.getReturnEntryDate());
		approvedStocksReturn.setReturnQuantity(stocksReturn.getReturnQuantity());
		approvedStocksReturn.setReturnReason(stocksReturn.getReturnReason());
		approvedStocksReturn.setReturnTotalCost(stocksReturn.getReturnTotalCost());
		approvedStocksReturn.setSgst(stocksReturn.getSgst());
		approvedStocksReturn.setStockType(stocksReturn.getStockType());
		approvedStocksReturn.setUnitOfMeasure(stocksReturn.getUnitOfMeasure());
		approvedStocksReturn.setStockOrderNo(stocksReturn.getStockOrderNo());
		
		approvedStocksReturn.setComplNo(stocksReturn.getComplNo());
		approvedStocksReturn.setIndentNo(stocksReturn.getIndentNo());
		approvedStocksReturn.setDepartmentName(stocksReturn.getDepartmentName());

		approvedStocksReturn.setUsername(username);
		ApprovedStocksReturn newApprovedStockReturns = approvedStocksReturnRepository.save(approvedStocksReturn);

		int returnQty = stocksReturn.getReturnQuantity();

		InwardApprovedMaterials inwardApprovedMaterials = inwardApprovedMaterialsRepository
				.findByItemId(stocksReturn.getItemId());

		InwardApprovedSpares inwardApprovedSpares = inwardApprovedSparesRepository
				.findByItemId(stocksReturn.getItemId());

		InwardApprovedTools inwardApprovedTools = inwardApprovedToolsRepository.findByItemId(stocksReturn.getItemId());

		if (inwardApprovedMaterials != null) {
			int availableMaterialsQty = inwardApprovedMaterials.getAvailableQty();
			inwardApprovedMaterials.setAvailableQty(availableMaterialsQty + returnQty);
			inwardApprovedMaterialsRepository.save(inwardApprovedMaterials);
		}
		if (inwardApprovedSpares != null) {
			int availableSparesQty = inwardApprovedSpares.getAvailableQty();
			inwardApprovedSpares.setAvailableQty(availableSparesQty + returnQty);
			inwardApprovedSparesRepository.save(inwardApprovedSpares);
		}
		if (inwardApprovedTools != null) {
			int availableToolsQty = inwardApprovedTools.getAvailableQty();
			inwardApprovedTools.setAvailableQty(availableToolsQty + returnQty);
			inwardApprovedTools.setQuantity(availableToolsQty + returnQty);
			inwardApprovedToolsRepository.save(inwardApprovedTools);
		}

		if (newApprovedStockReturns != null) {
			stockReturnsRepository.deleteById(id);
		}

	}

	@Override
	public void rejectReturnItem(Long id, String username) {

		StocksReturn stocksReturn = stockReturnsRepository.findByRecordId(id);

		RejectedStocksReturn rejectedStockReturns = new RejectedStocksReturn();

		rejectedStockReturns.setCategory(stocksReturn.getCategory());
		rejectedStockReturns.setCgst(stocksReturn.getCgst());
		rejectedStockReturns.setDescription(stocksReturn.getDescription());
		rejectedStockReturns.setIgst(stocksReturn.getIgst());
		rejectedStockReturns.setImagePath(stocksReturn.getImagePath());
		rejectedStockReturns.setInvoiceNo(stocksReturn.getInvoiceNo());
		rejectedStockReturns.setItemId(stocksReturn.getItemId());
		rejectedStockReturns.setItemImage(stocksReturn.getItemImage());
		rejectedStockReturns.setItemName(stocksReturn.getItemName());
		rejectedStockReturns.setMrpRate(stocksReturn.getMrpRate());
		rejectedStockReturns.setOrderQuantity(stocksReturn.getOrderQuantity());
		rejectedStockReturns.setOrderTotalCost(stocksReturn.getOrderTotalCost());
		rejectedStockReturns.setReturnEntryDate(stocksReturn.getReturnEntryDate());
		rejectedStockReturns.setReturnQuantity(stocksReturn.getReturnQuantity());
		rejectedStockReturns.setReturnReason(stocksReturn.getReturnReason());
		rejectedStockReturns.setReturnTotalCost(stocksReturn.getReturnTotalCost());
		rejectedStockReturns.setSgst(stocksReturn.getSgst());
		rejectedStockReturns.setStockType(stocksReturn.getStockType());
		rejectedStockReturns.setUnitOfMeasure(stocksReturn.getUnitOfMeasure());
		rejectedStockReturns.setStockOrderNo(stocksReturn.getStockOrderNo());
		
		rejectedStockReturns.setComplNo(stocksReturn.getComplNo());
		rejectedStockReturns.setIndentNo(stocksReturn.getIndentNo());
		rejectedStockReturns.setDepartmentName(stocksReturn.getDepartmentName());

		rejectedStockReturns.setUsername(username);
		RejectedStocksReturn newRejectedStocksReturn = rejectedStocksReturnRepository.save(rejectedStockReturns);

		if (newRejectedStocksReturn != null) {
			stockReturnsRepository.deleteById(id);
		}
	}

}
