package com.ingroinfo.mm.dto;

import java.sql.Date;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Data
public class HoldWorkOrderLaboursDto {

	private Long labourReqId;
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
	private String empCategory;
	private String members;
	private String daysRequired;
	private String timeRequired;
	private String departmentName;
	private String indentApproved;
	private String approvedSts;
	private String userName;
	@CreationTimestamp
	private Date createdDate;

}
