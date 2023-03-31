package com.ingroinfo.mm.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class WapWorkOrderLaboursDto {

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
	private Date createdDate;
}
