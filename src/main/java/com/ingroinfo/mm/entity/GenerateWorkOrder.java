package com.ingroinfo.mm.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name ="MM_GENERATE_CATEGORY_WORK")
public class GenerateWorkOrder {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long generateWorkId;
	@Column(length = 60)
	private String indentNo;
	@Column(length = 50)
	private String indentDepartement;
	@Column(length = 50)
	private String genWorkOrderDepartment;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date workOrderDate;
	@Column(length = 50)
	private String workOrderNumber;
	@Column(length = 50)
	private String division;
	@Column(length = 50)
	private String subDivision;
	@Column(length = 50)
	private String workOrderScope;
	@Column(length = 50)
	private String workOrderIssuBy;
	@Column(length = 50)
	private String workPriority;
	@Column(length = 50)
	private String workOrderCost;
	@Column(length = 50)
	private String contact;
	@Column(length = 50)
	private String workSite;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date expectedStartDate;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date expectedEndDate;
	
	@Column(length = 50)
	private String category ;
	@Column(length = 50)
	private String item;
	@Column(length = 50)
	private String hsnCode;
	@Column(length = 50)
	private String unitMeassure;
	@Column(length = 50)
	private String gentrMaterialQty;
	@Column(length = 25)
	private String gentrMaterialAmount;
	
}
