package com.ingroinfo.mm.dto;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.ingroinfo.mm.entity.GenerateWorkOrder;

import lombok.Data;

@Data
public class GenerateWorkOrderDto {
	
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
	
	private String category ;	
	private String item;	
	private String hsnCode;	
	private String unitMeassure;	
	private String gentrMaterialQty;
	private String gentrMaterialAmount;

	
	private List<GenerateWorkOrder> items;
	

}
