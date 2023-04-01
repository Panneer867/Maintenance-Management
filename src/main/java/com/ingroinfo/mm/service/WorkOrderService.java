package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.WapWorkOrderItemsDto;
import com.ingroinfo.mm.dto.WapWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.WapWorkOrderVehiclesDto;
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

	// Get Approved Labour Indent By Approved Status
	List<IndentApprovedLabours> getApprovedLabourIndentByApprovedSts(String approvedSts);

	// Get Approved Vehicle Indent By Approved Status
	List<IndentApprovedVehicles> getApprovedVehicleIndentByApprovedSts(String approvedSts);

	// Save All TempWorkOrder Labors Data
	void saveAllTempWorkOrderLabours(List<TempWorkOrderLabourRequest> tempWorkOrderLabours);

	// Save All Approved Indent Labours
	void saveAllApprovedIndentLabours(List<IndentApprovedLabours> approvedIndentLabours);

	// Save All TempWorkOrder Vehicles Data
	void saveAllTempWorkOrderVehicles(List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles);

	// Save All Approved Indent Vehicles Data
	void saveAllApprovedIndentVehicles(List<IndentApprovedVehicles> approvedIndentVehicles);

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

}
