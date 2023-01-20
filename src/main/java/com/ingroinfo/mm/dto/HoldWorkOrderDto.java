package com.ingroinfo.mm.dto;


import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class HoldWorkOrderDto {
	
	private Long holdWorkId;
	private String indentNo;
	private String indentDepartement;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date workOrderDate;	
	private String workOrderNumber;	
	private String division;	
	private String subDivision;	
	private String workOrderScope;	
	private String workOrderIssuBy;	
	private String workPriority;	
	private String workOrderCost;	
	private String contact;	
	private String workSite;	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date expectedStartDate;	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date expectedEndDate;	
	private String category ;	
	private String item;	
	private String hsnCode;	
	private String unitMeassure;	
	private String holdMaterialQty;
	private String holdMaterialAmount;
	private String employeeCategorty;
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
