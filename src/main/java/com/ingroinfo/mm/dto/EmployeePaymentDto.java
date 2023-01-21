package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class EmployeePaymentDto {

	private Long empPaymentId;
	private String employeeId;
	private String empName;
	private String department;
	private String basicSalary;
	private String salary;
	private String da;
	private String hra;
	private String convencyance;
	private String medical;
	private String splAllow;
	private String esi;
	private String telAllowance;
	private String ltaAllow;
	private String pf;
	private String tds;
	private String tax;
	private String netSalary;
}
