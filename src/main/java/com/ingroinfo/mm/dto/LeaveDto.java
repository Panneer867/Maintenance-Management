package com.ingroinfo.mm.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LeaveDto {
	
	private Long employeeId;
	private String empCode;
	private String empName;
	private String designation;
	private String department;
	private String Asl;
	private String Acl;
	private String Alwp;
	private String Ssl;
	private String Scl;
	private String Slwp;
	private String deptHead;
	private String remark;
	private String leaveDate;
	private String noOfLeave;
	private String leaveType;
	private String hrApproval;
	private String hrDate;
	private Date dateCreated;
	private Date lastUpdated;
}
