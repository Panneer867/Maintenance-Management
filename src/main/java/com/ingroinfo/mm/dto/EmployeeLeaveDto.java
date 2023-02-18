package com.ingroinfo.mm.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeLeaveDto {
	
	private Long empLeaveId;
	private String employeeCode;
	private String empName;
	private Long employeeId;
	//private String employeeMaster;
	private String department;
	private String sickLeave;
	private String causalLeave;
	private String annualLwp;
	private Integer sacnSickLeave;
	private Integer sacnCausalLeave;
	private Integer sacnLwp;
	private String deptHead;
	private String remark;
	private String leaveDate;
	private Integer noOfLeave;
	private String leaveType;
	private String hrApproval;
	private String hrDate;
	private Date createDate;

}
