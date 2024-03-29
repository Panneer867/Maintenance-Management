package com.ingroinfo.mm.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ComplaintDto {

	private Long complId;
	private String complNo;
	private String complDate;
	private String department;
	
	private String docketNo;
	private String recivedBy;	
	private String firstname;
	private String lastname;
	private String houseNo;
	private String cross;
	private String main;
	private String landMark;
	private String zone;
	private String ward;
	private String city;
	private String trraif;
	private String serviceLocat;
	private String dma;	
	private String pinCode;
	private String gender;
	private String mobileNo;
	private String landLineNo;
	private String refcontactNo;
	private String emailid;
	private String regOfCompl;
	private String businessDept;
	private String subCategory;
	private String complStatus;
	private String jobCode;
	private String complDetails;	
	private String slg;
	private String slgDuration;
	private String assignDate;
	private String nameOfEngg;
	private String serviceStat;
	
	private String esclatedFrom;
	private String esclatedFromName;
	private String esclatedFromId;		
	private String esclatedTo;
	
	private String visitedDate;
	private String fieldVisitedRemark;
	private String jobDoneRemark;
	private String jobDoneNotDone;
	private String esclatedDate;
	private String esclatedToId;
	private String esclatedToName;
	private String esclatedReason;
	private String esclationType;
	private String esclationLavel;
	
	private Date workCompletedDate;
	private String indentNo;
	private String workOrder;
	private String indentApprovedBy;
	private String workorderApprovedBy;
	private Date indentApprovedDate;
	private Date workorderApprovedDate;
	private String workOrderHoldBy;
	private String workOrderCancelBy;
	private Date workOrderHoldDate;
	private Date workOrderCancelDate;
	private String workOrderReApproveBy;
	private Date workOrderReApprovedDate;
	private Date inspectionDate;
	private String inspectionBy;
}
