package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.CancelWorkOrderItemsDto;
import com.ingroinfo.mm.dto.CancelWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.CancelWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.HoldWorkOrderItemsDto;
import com.ingroinfo.mm.dto.HoldWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.HoldWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.WapWorkOrderItemsDto;
import com.ingroinfo.mm.dto.WapWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.WapWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedItemsDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedLaboursDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedVehiclesDto;
import com.ingroinfo.mm.entity.IndentApprovedItems;
import com.ingroinfo.mm.entity.IndentApprovedLabours;
import com.ingroinfo.mm.entity.IndentApprovedVehicles;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;
import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;

public interface WorkOrderService {

	// Get List Of Approved Indent Items Request Data By Complaint No And Indent No
	List<IndentApprovedItems> getApprovedIndentItemsByComplNoAndIndentNo(String complNo, String indentNo);

	// Get List Of Approved Indent Labors Request Data By Complaint No And Indent No
	List<IndentApprovedLabours> getApprovedIndentLaborsByComplNoAndIndentNo(String complNo, String indentNo);

	// Get List Of Approved Indent Items Request Data By Complaint No And Indent No
	List<IndentApprovedVehicles> getApprovedIndentVehiclesByComplNoAndIndentNo(String complNo, String indentNo);

	// Get List Of Temporary WorkOrder Items Request Data By Complaint No And Indent
	// No
	List<TempWorkOrderItemRequest> getTempWorkOrderItemsByComplNoAndIndentNo(String complNo, String indentNo);

	// Get List Of Temporary WorkOrder Labors Request Data By Complaint No And
	// Indent No
	List<TempWorkOrderLabourRequest> getTempWorkOrderLaborsByComplNoAndIndentNo(String complNo, String indentNo);

	// Get List Of Temporary WorkOrder Vehicles Request Data By Complaint No And
	// Indent No
	List<TempWorkOrderVehicleRequest> getTempWorkOrderVehiclesByComplNoAndIndentNo(String complNo, String indentNo);

	// Get Approved Materials Indent By Approved Status
	List<IndentApprovedItems> getApprovedItemsIndentByApprovedSts(String approvedSts);

	// Get Approved Labor Indent By Approved Status
	List<IndentApprovedLabours> getApprovedLabourIndentByApprovedSts(String approvedSts);

	// Get Approved Vehicle Indent By Approved Status
	List<IndentApprovedVehicles> getApprovedVehicleIndentByApprovedSts(String approvedSts);

	// Save All TempWorkOrder Items Data
	void saveAllTempWorkOrderItems(List<TempWorkOrderItemRequest> tempWorkOrderItems);

	// Save All TempWorkOrder Labors Data
	void saveAllTempWorkOrderLabours(List<TempWorkOrderLabourRequest> tempWorkOrderLabours);

	// Save All TempWorkOrder Vehicles Data
	void saveAllTempWorkOrderVehicles(List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles);

	// Save All Approved Indent Vehicles Data
	void saveAllApprovedIndentItems(List<IndentApprovedItems> indentApprovedItems);

	// Save All Approved Indent Vehicles Data
	void saveAllApprovedIndentVehicles(List<IndentApprovedVehicles> approvedIndentVehicles);

	// Save All Approved Indent Labors
	void saveAllApprovedIndentLabours(List<IndentApprovedLabours> approvedIndentLabours);

	// Save All WapWorkorderItems Data
	void saveAllWapWorkOrderItems(List<WapWorkOrderItemsDto> wapWorkOrderItemsDtos);

	// Save All WapWorkorderLabours Data
	void saveAllWapWorkOrderLabours(List<WapWorkOrderLaboursDto> wapWorkOrderLaboursDtos);

	// Save All WapWorkorderVehicles Data
	void saveAllWapWorkOrderVehicles(List<WapWorkOrderVehiclesDto> wapWorkOrderVehicleDtos);

	// Delete All TempWorkOrder Item Data By ComplNo
	void deleteTempWorkOrderItemRequestByComplNo(String complNo);

	// Delete All TempWorkOrder Labor Data By ComplNo
	void deleteTempWorkOrderLaboursRequestByComplNo(String complNo);

	// Delete All TempWorkOrder Vehicle Data By ComplNo
	void deleteTempWorkOrderVehicleRequestByComplNo(String complNo);

	// Save All Hold WorkorderItems Data
	void saveAllHoldWorkOrderItems(List<HoldWorkOrderItemsDto> holdWorkOrderItemsDtos);

	// Save All Hold WorkorderLabours Data
	void saveAllHoldWorkOrderLabours(List<HoldWorkOrderLaboursDto> holdWorkOrderLaboursDtos);

	// Save All Hold WorkorderVehicles Data
	void saveAllHoldWorkOrderVehicles(List<HoldWorkOrderVehiclesDto> holdWorkOrderVehiclesDtos);

	// Save All Cancel WorkorderItems Data
	void saveAllCancelWorkOrderItems(List<CancelWorkOrderItemsDto> cancelWorkOrderItemsDtos);

	// Save All Cancel WorkorderLabors Data
	void saveAllCancelWorkOrderLabours(List<CancelWorkOrderLaboursDto> cancelWorkOrderLaboursDtos);

	// Save All Cancel WorkorderVehicles Data
	void saveAllCancelWorkOrderVehicles(List<CancelWorkOrderVehiclesDto> cancelWorkOrderVehiclesDtos);

	// Get List Of WapWorkOrder Items Data By Complaint No And Indent No
	List<WapWorkOrderItemsDto> getWapWorkOrderItemsByComplNoAndIndentNo(String complNo, String indentNo);

	// Get List Of WapWorkOrder Labors Data By Complaint No And Indent No
	List<WapWorkOrderLaboursDto> getWapWorkOrderLaboursByComplNoAndIndentNo(String complNo, String indentNo);

	// Get List Of WapWorkOrder Vehicles Data By Complaint No And Indent No
	List<WapWorkOrderVehiclesDto> getWapWorkOrderVehiclesByComplNoAndIndentNo(String complNo, String indentNo);

	// Save All Approved WorkOrder Items Data
	void saveAllApprovedWorkOrderItems(List<WorkOrderApprovedItemsDto> workOrderApprovedItemsDtos);

	// Save All Approved WorkOrder Labors Data
	void saveAllApprovedWorkOrderLabours(List<WorkOrderApprovedLaboursDto> workOrderApprovedLaboursDtos);

	// Save All Approved WorkOrder Vehicles Data
	void saveAllApprovedWorkOrderVehicles(List<WorkOrderApprovedVehiclesDto> workOrderApprovedVehiclesDtos);

	// Delete All WapWorkOrder Items Data By Complaint No
	void deleteAllWapWorkorderItemsByComplNo(String complNo);

	// Delete All WapWorkOrder Labor Data By Complaint No
	void deleteAllWapWorkorderLaboursByComplNo(String complNo);

	// Delete All WapWorkOrder Vehicle Data By Complaint No
	void deleteAllWapWorkorderVehiclesByComplNo(String complNo);

	// Get Approved WorkOrder Items data By WorkOrder Number
	List<WorkOrderApprovedItemsDto> getApprovedWorkOrderItemsByWorkorderNo(String workOrder);

	// Get Approved WorkOrder Labors data By WorkOrder Number
	List<WorkOrderApprovedLaboursDto> getApprovedWorkOrderLaboursByWorkorderNo(String workOrder);

	// Get Approved WorkOrder Vehicles data By WorkOrder Number
	List<WorkOrderApprovedVehiclesDto> getApprovedWorkOrderVehiclesByWorkorderNo(String workOrder);

	// Get Hold WorkOrder Items data By WorkOrder Number
	List<HoldWorkOrderItemsDto> getHoldWorkOrderItemsByWorkorderNo(String workOrder);

	// Get Hold WorkOrder Labors data By WorkOrder Number
	List<HoldWorkOrderLaboursDto> getHoldWorkOrderLaboursByWorkorderNo(String workOrder);

	// Get Hold WorkOrder Vehicles data By WorkOrder Number
	List<HoldWorkOrderVehiclesDto> getHoldWorkOrderVehiclesByWorkorderNo(String workOrder);

	// Get Canceled WorkOrder Items data By WorkOrder Number
	List<CancelWorkOrderItemsDto> getCanceledWorkOrderItemsByWorkorderNo(String workOrder);

	// Get Canceled WorkOrder Labors data By WorkOrder Number
	List<CancelWorkOrderLaboursDto> getCanceledWorkOrderLaboursByWorkorderNo(String workOrder);

	// Get Canceled WorkOrder Vehicles data By WorkOrder Number
	List<CancelWorkOrderVehiclesDto> getCanceledWorkOrderVehiclesByWorkorderNo(String workOrder);

	// Get Approved WorkOrder Items data to Outward Stock Item request Table
	List<WorkOrderApprovedItemsDto> getApprovedWorkOrderItemsByComplNoAndIndentNo(String complNo, String indentNo);

}
