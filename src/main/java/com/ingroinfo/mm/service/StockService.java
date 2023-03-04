package com.ingroinfo.mm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.WorkOrderItemsDto;
import com.ingroinfo.mm.entity.InwardApprovedMaterials;
import com.ingroinfo.mm.entity.InwardApprovedSpares;
import com.ingroinfo.mm.entity.InwardApprovedTools;
import com.ingroinfo.mm.entity.InwardMaterials;
import com.ingroinfo.mm.entity.InwardSpares;
import com.ingroinfo.mm.entity.InwardTempMaterials;
import com.ingroinfo.mm.entity.InwardTempSpares;
import com.ingroinfo.mm.entity.InwardTempTools;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.TempWorkOrderNos;
import com.ingroinfo.mm.entity.WorkOrderItems;

public interface StockService {

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

	void saveWorkOrder(TempWorkOrderNos workOrders);

	boolean notAvailableItems(Long workOrderNo);

	void saveRemovedItems(String itemId, Long workOrderNo);

	boolean getTempWorkOrderItems(Long workOrderNo);

	List<TempWorkOrderNos> getOutwardWorkOrders();

	void deleteOutwardWorkorder(Long id);

	List<WorkOrderItems> getOutwardWorkOrderItems(Long workOrderNo);

	TempWorkOrderNos getOutwardWorkOrder(Long workOrderNo);
}
