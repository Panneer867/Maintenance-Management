package com.ingroinfo.mm.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.ApprovedStocksReturnRepository;
import com.ingroinfo.mm.dao.ApprovedWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.ApprovedWorkOrdersRepository;
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
import com.ingroinfo.mm.dao.RejectedWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.RejectedWorkOrderNosRepository;
import com.ingroinfo.mm.dao.StockReturnsRepository;
import com.ingroinfo.mm.dao.TempWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.TempWorkOrdersRepository;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.entity.ApprovedStocksReturn;
import com.ingroinfo.mm.entity.ApprovedWorkOrderItems;
import com.ingroinfo.mm.entity.ApprovedWorkOrders;
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
import com.ingroinfo.mm.entity.RejectedWorkOrderItems;
import com.ingroinfo.mm.entity.RejectedWorkOrderNos;
import com.ingroinfo.mm.entity.StocksReturn;
import com.ingroinfo.mm.entity.TempWorkOrderItems;
import com.ingroinfo.mm.entity.TempWorkOrders;
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
	private TempWorkOrderItemsRepository tempWorkOrderItemsRepository;

	@Autowired
	private ApprovedWorkOrderItemsRepository approvedWorkOrderItemsRepository;

	@Autowired
	private ApprovedWorkOrdersRepository approvedWorkOrdersRepository;

	@Autowired
	private TempWorkOrdersRepository tempWorkOrdersRepository;

	@Autowired
	private RejectedWorkOrderItemsRepository rejectedWorkOrderItemsRepository;

	@Autowired
	private RejectedWorkOrderNosRepository rejectedWorkOrderNosRepository;

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

	/*******************************
	 * Outwards Work orders
	 ********************************/
	@Override
	public void approveOutwardStocks(Long workOrderNo, String username) {

		TempWorkOrders tempWorkOrders = tempWorkOrdersRepository.findByWorkOrderNo(workOrderNo);

		ApprovedWorkOrders approvedWorkOrders = new ApprovedWorkOrders();

		approvedWorkOrders.setBilledOn(tempWorkOrders.getBilledOn());
		approvedWorkOrders.setCgst(tempWorkOrders.getCgst());
		approvedWorkOrders.setGrandTotal(tempWorkOrders.getGrandTotal());
		approvedWorkOrders.setGstType(tempWorkOrders.getGstType());
		approvedWorkOrders.setIgst(tempWorkOrders.getIgst());
		approvedWorkOrders.setSgst(tempWorkOrders.getSgst());
		approvedWorkOrders.setSubTotal(tempWorkOrders.getSubTotal());
		approvedWorkOrders.setUsername(username);
		approvedWorkOrders.setWorkOrderNo(tempWorkOrders.getWorkOrderNo());

		ApprovedWorkOrders savedApprovedWorkOrders = approvedWorkOrdersRepository.save(approvedWorkOrders);

		List<TempWorkOrderItems> tempWorkOrderItems = tempWorkOrderItemsRepository.findByWorkOrderNo(workOrderNo);

		for (TempWorkOrderItems tempWorkOrderItem : tempWorkOrderItems) {

			ApprovedWorkOrderItems approvedWorkOrderItems = new ApprovedWorkOrderItems();

			approvedWorkOrderItems.setAliasName(tempWorkOrderItem.getAliasName());
			approvedWorkOrderItems.setCategory(tempWorkOrderItem.getCategory());
			approvedWorkOrderItems.setDescription(tempWorkOrderItem.getDescription());
			approvedWorkOrderItems.setFinalQuantity(tempWorkOrderItem.getFinalQuantity());
			approvedWorkOrderItems.setImagePath(tempWorkOrderItem.getImagePath());
			approvedWorkOrderItems.setItemId(tempWorkOrderItem.getItemId());
			approvedWorkOrderItems.setItemImage(tempWorkOrderItem.getItemImage());
			approvedWorkOrderItems.setItemName(tempWorkOrderItem.getItemName());
			approvedWorkOrderItems.setMrpRate(tempWorkOrderItem.getMrpRate());
			approvedWorkOrderItems.setSlNo(tempWorkOrderItem.getSlNo());
			approvedWorkOrderItems.setStockType(tempWorkOrderItem.getStockType());
			approvedWorkOrderItems.setTotalCost(tempWorkOrderItem.getTotalCost());
			approvedWorkOrderItems.setUnitOfMeasure(tempWorkOrderItem.getUnitOfMeasure());
			approvedWorkOrderItems.setWorkOrderNo(tempWorkOrderItem.getWorkOrderNo());
			approvedWorkOrderItems.setOrderId(savedApprovedWorkOrders);
			approvedWorkOrderItems.setUsername(username);
			approvedWorkOrderItemsRepository.save(approvedWorkOrderItems);
			
			
			int requiredQty = tempWorkOrderItem.getFinalQuantity();

			InwardApprovedMaterials inwardApprovedMaterials = inwardApprovedMaterialsRepository
					.findByItemId(approvedWorkOrderItems.getItemId());

			InwardApprovedSpares inwardApprovedSpares = inwardApprovedSparesRepository
					.findByItemId(approvedWorkOrderItems.getItemId());

			InwardApprovedTools inwardApprovedTools = inwardApprovedToolsRepository
					.findByItemId(approvedWorkOrderItems.getItemId());

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

		}

		tempWorkOrdersRepository.deleteById(tempWorkOrders.getOrderId());
	}

	@Override
	public void rejectWorkorderItems(Long workOrderNo, String username) {
		TempWorkOrders tempWorkOrders = tempWorkOrdersRepository.findByWorkOrderNo(workOrderNo);

		RejectedWorkOrderNos rejectedWorkOrderNos = new RejectedWorkOrderNos();
		rejectedWorkOrderNos.setBilledOn(tempWorkOrders.getBilledOn());
		rejectedWorkOrderNos.setCgst(tempWorkOrders.getCgst());
		rejectedWorkOrderNos.setGrandTotal(tempWorkOrders.getGrandTotal());
		rejectedWorkOrderNos.setGstType(tempWorkOrders.getGstType());
		rejectedWorkOrderNos.setIgst(tempWorkOrders.getIgst());
		rejectedWorkOrderNos.setSgst(tempWorkOrders.getSgst());
		rejectedWorkOrderNos.setSubTotal(tempWorkOrders.getSubTotal());
		rejectedWorkOrderNos.setUsername(username);
		rejectedWorkOrderNos.setWorkOrderNo(tempWorkOrders.getWorkOrderNo());

		RejectedWorkOrderNos rejectedWorkOrderNosId = rejectedWorkOrderNosRepository.save(rejectedWorkOrderNos);

		List<TempWorkOrderItems> tempWorkOrderItems = tempWorkOrderItemsRepository.findByWorkOrderNo(workOrderNo);

		for (TempWorkOrderItems workOrderItem : tempWorkOrderItems) {

			RejectedWorkOrderItems rejectedWorkOrderItems = new RejectedWorkOrderItems();

			rejectedWorkOrderItems.setAliasName(workOrderItem.getAliasName());
			rejectedWorkOrderItems.setCategory(workOrderItem.getCategory());
			rejectedWorkOrderItems.setDescription(workOrderItem.getDescription());
			rejectedWorkOrderItems.setFinalQuantity(workOrderItem.getFinalQuantity());
			rejectedWorkOrderItems.setImagePath(workOrderItem.getImagePath());
			rejectedWorkOrderItems.setItemId(workOrderItem.getItemId());
			rejectedWorkOrderItems.setItemImage(workOrderItem.getItemImage());
			rejectedWorkOrderItems.setItemName(workOrderItem.getItemName());
			rejectedWorkOrderItems.setMrpRate(workOrderItem.getMrpRate());
			rejectedWorkOrderItems.setSlNo(workOrderItem.getSlNo());
			rejectedWorkOrderItems.setStockType(workOrderItem.getStockType());
			rejectedWorkOrderItems.setTotalCost(workOrderItem.getTotalCost());
			rejectedWorkOrderItems.setUnitOfMeasure(workOrderItem.getUnitOfMeasure());
			rejectedWorkOrderItems.setWorkOrderNo(workOrderItem.getWorkOrderNo());
			rejectedWorkOrderItems.setOrderId(rejectedWorkOrderNosId);
			rejectedWorkOrderItems.setUsername(username);
			rejectedWorkOrderItemsRepository.save(rejectedWorkOrderItems);
		}

		tempWorkOrdersRepository.deleteById(tempWorkOrders.getOrderId());

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
		approvedStocksReturn.setWorkOrderNo(stocksReturn.getWorkOrderNo());

		approvedStocksReturn.setUsername(username);
		ApprovedStocksReturn newApprovedStockReturns = approvedStocksReturnRepository.save(approvedStocksReturn);
		
		
		int returnQty = stocksReturn.getReturnQuantity();

		InwardApprovedMaterials inwardApprovedMaterials = inwardApprovedMaterialsRepository
				.findByItemId(stocksReturn.getItemId());

		InwardApprovedSpares inwardApprovedSpares = inwardApprovedSparesRepository
				.findByItemId(stocksReturn.getItemId());

		InwardApprovedTools inwardApprovedTools = inwardApprovedToolsRepository
				.findByItemId(stocksReturn.getItemId());

		if (inwardApprovedMaterials != null) {
			int MaterialStockQty = inwardApprovedMaterials.getQuantity();
			inwardApprovedMaterials.setQuantity(MaterialStockQty + returnQty);
			inwardApprovedMaterialsRepository.save(inwardApprovedMaterials);
		}
		if (inwardApprovedSpares != null) {
			int SpareStockQty = inwardApprovedSpares.getQuantity();
			inwardApprovedSpares.setQuantity(SpareStockQty + returnQty);
			inwardApprovedSparesRepository.save(inwardApprovedSpares);
		}
		if (inwardApprovedTools != null) {
			int ToolStockQty = inwardApprovedTools.getQuantity();
			inwardApprovedTools.setQuantity(ToolStockQty + returnQty);
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
		rejectedStockReturns.setWorkOrderNo(stocksReturn.getWorkOrderNo());

		rejectedStockReturns.setUsername(username);
		RejectedStocksReturn newRejectedStocksReturn = rejectedStocksReturnRepository.save(rejectedStockReturns);

		if (newRejectedStocksReturn != null) {
			stockReturnsRepository.deleteById(id);
		}
	}

}
