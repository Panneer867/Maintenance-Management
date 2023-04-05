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
@Table(name = "mm_workorder_approved_items")
public class WorkOrderApprovedItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemReqId;
	private String indentNo;
	private String complNo;
	private Long stockOrderNo;
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
	private String itemId;
	private String unitOfMesure;
	private String hsnCode;
	private int quantity;
	private String stockType;
	private String stockTypeName;
	private String departmentName;
	private String indentApproved;
    private String approvedSts;
	private String userName;
	@CreationTimestamp
	private Date createdDate;
	private String workOrder;
	private String stockApproveSts;

}
