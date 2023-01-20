package com.ingroinfo.mm.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ingroinfo.mm.entity.GenerateLabourWork;

import lombok.Data;

@Data
public class GenerateLabourWorkDto {
	private Long generateWorkId;
	private String indentNo;
	private String indentDepartement;
	private String genWorkOrderDepartment;
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

	private String employeeCategorty;
	private String labourQty;	
	private String days;	
	private String time;	
	private String totalHours;
	
	private List<GenerateLabourWork> genLabour;
}
