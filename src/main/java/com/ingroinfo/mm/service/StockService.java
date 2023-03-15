package com.ingroinfo.mm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.WorkOrderItemsDto;
import com.ingroinfo.mm.entity.ApprovedStocksReturn;
import com.ingroinfo.mm.entity.ApprovedWorkOrderItems;
import com.ingroinfo.mm.entity.ApprovedWorkOrders;
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
import com.ingroinfo.mm.entity.TempStocksReturn;
import com.ingroinfo.mm.entity.TempWorkOrderItems;
import com.ingroinfo.mm.entity.TempWorkOrders;

public interface StockService {
	
	List<InwardDto> getAllStocks();

	/******** Materials *********/

	void saveInwardTempMaterials(InwardDto inward, MultipartFile file);

	void saveInwardMaterials(InwardDto inward);

	List<InwardTempMaterials> getInwardTempMaterials(String username);

	List<InwardMaterials> getInwardAllMaterialsList();

	void deleteTempMaterial(Long materialId);

	void deleteMaterial(Long materialId);

	void deleteAllMaterials();

	InwardMaterials getInwardMaterial(Long id);

	InwardApprovedMaterials getApprovedInwardMaterialById(Long id);

	List<InwardApprovedMaterials> getApprovedMaterialsLists();

	/******** Spares *********/

	void saveInwardTempSpares(InwardDto inward, MultipartFile file);

	void saveInwardSpares(InwardDto inward);

	List<InwardTempSpares> getInwardTempSpares(String username);

	List<InwardSpares> getInwardAllSparesList();

	void deleteTempSpare(Long spareId);

	void deleteSpare(Long spareId);

	void deleteAllSpares();

	InwardSpares getInwardSpare(Long id);

	InwardApprovedSpares getApprovedInwardSpareById(Long id);

	List<InwardApprovedSpares> getApprovedSparesLists();

	/******** Tools *********/

	void saveInwardTempTools(InwardDto inward, MultipartFile file);

	void saveInwardTools(InwardDto inward);

	List<InwardTempTools> getInwardTempTools(String username);

	List<InwardTools> getInwardAllToolsList();

	void deleteTempTool(Long toolId);

	void deleteTool(Long toolId);

	void deleteAllTools();

	InwardTools getInwardTool(Long id);

	InwardApprovedTools getApprovedInwardToolById(Long id);

	List<InwardApprovedTools> getApprovedToolsLists();
	
	/******** Workorders *********/

	List<Long> getWorkOrdersNos();

	List<WorkOrderItemsDto> getWorkOrderItems(Long workOrderNo);

	List<WorkOrderItemsDto> checkStockQuantity(Long workOrderNo);

	public void saveWorkOrder(TempWorkOrders workOrders, String username);

	boolean notAvailableItems(Long workOrderNo);

	void saveRemovedItems(String itemId, Long workOrderNo, String username);

	boolean getTempWorkOrderItems(Long workOrderNo);

	public List<TempWorkOrders> getOutwardWorkOrders();

	public List<TempWorkOrderItems> getOutwardWorkOrderItems(Long workOrderNo);

	public TempWorkOrders getOutwardWorkOrder(Long workOrderNo);

	List<ApprovedWorkOrders> getOutwardApprovedWorkOrders();

	List<ApprovedWorkOrderItems> getOutwardApprovedWorkOrderItems(Long workOrderNo);

	ApprovedWorkOrders getOutwardApprovedWorkOrder(Long workOrderNo);

	ApprovedWorkOrderItems getWorkorderItemDetails(String itemId, Long workOrderNo);

	void saveReturnItem(TempStocksReturn tempStocksReturn, MultipartFile file);

	boolean checkReturnedItem(TempStocksReturn tempStocksReturn);

	TempStocksReturn getReturnQuantity(TempStocksReturn tempStocksReturn);

	List<TempStocksReturn> getTempStockReturn(String username);

	void deleteTempReturnItem(Long id);

	void deleteAllTempReturnItem();

	void saveReturnItems(TempStocksReturn tempStocksReturn);

	List<StocksReturn> getStockReturnItemList();

	void deleteReturnItem(Long id);

	List<ApprovedStocksReturn> getApprovedStockReturnItemList();

	

}
