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
@Table(name="MM_GENERATE_LABOURS_WORK")
public class GenerateLabourWork {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long generateWorkId;
	@Column(length = 6)
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
	private String employeeCategorty;
	@Column(length = 25)
	private String labourQty;
	private String days;
	private String time;
	@Column(length = 50)
	private String totalHours;

}
