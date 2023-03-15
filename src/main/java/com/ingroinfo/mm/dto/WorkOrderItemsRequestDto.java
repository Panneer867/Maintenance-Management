package com.ingroinfo.mm.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class WorkOrderItemsRequestDto {
	
	private Long workOrderNo;
	private String itemId;
	private String stockType;
	private int quantity;
	private String username;
	private String indentNo;
	private String complNo;
	private String division;
	private String subDivision;
	private String workSite;
	private Date startDate;
	private Date endDate;
	private String contactNo;	
	private String complDtls;
	private String workPriority;
	private String categoryName;
	private String itemName;
	private String unitOfMesure;
	private String hsnCode;
	private String indentType;
	private String empCategory;
	private String members;
	private String daysRequired;
	private String timeRequired;
	private String vehicleType;
	private String vehicleNo;
	private String driverName;
	private String driverPhone;
	private String meterReading;
	private String stratTime;
	private String stockTypeName;
}
