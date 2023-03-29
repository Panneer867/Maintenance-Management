package com.ingroinfo.mm.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "MM_WORKORDER_APPROVED_LABOURS")
public class WorkOrderApprovedLabours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labourReqId;
	private String indentNo;
	private String complNo;
	private String workOrderNo;
	private String division;
	private String subDivision;
	private String workSite;
	private Date startDate;
	private Date endDate;
	private String contactNo;	
	private String complDtls;
	private String workPriority;
	private String empCategory;
	private String members;
	private String daysRequired;
	private String timeRequired;
	private String departmentName;
	private String approvedSts;
	private String userName;
	@CreationTimestamp
	private Date approvedDate;
}
