package com.ingroinfo.mm.service;

import java.util.List;

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
}
