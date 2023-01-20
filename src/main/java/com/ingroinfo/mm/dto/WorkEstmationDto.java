package com.ingroinfo.mm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class WorkEstmationDto {
	
	private Long workEstmationId;
	private String division;
	private String subDivision;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date workOrderDate;	
	
	private String estimationNumber ;
	private String workOrderScope;	
	private String estimationGenratedBy;	
	private String workPriority;
	private String workOrderCost;	
	private String workSite;	
	private String contact;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date expectedStartDate;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date expectedEndDate;	
	
	private String category ;	
	private String item;	
	private String hsnCode;
	private String unitMeassure;	
	private String estMaterialQty;	
	private String estMaterialAmount;	
	private String employeeCategorty;	
	private String employeeType;	
	private String labourQty;	
	private String days;
	private String time;	
	private String totalHours;	
	private String vehicleType ;
	private String vehicleNumber;
	private String driverName;	
	private String driverContactNumber;	
	private String odoMeterReading;
	private String startTime;

}
