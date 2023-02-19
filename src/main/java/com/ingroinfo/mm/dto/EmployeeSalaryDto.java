package com.ingroinfo.mm.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeSalaryDto {
	
	private Long empSalaryId;
	private String employeeId;
	private String empName;
	private String department;
	private String basicSalary;	
	private String empSalary;
	private String empDa;
	private String empHra;	
	private String convencyance;
	private String medical;
	private String empSplAllow;
	private String actualSalary;
	private String actualHra;
	private String actualSpclAllow;
	private String empEsi;
	private String telAllowance;
	private String ltaAllow;	
	private String empPf;	
	private String empTds;	
	private String profTax;
	private String totalEarn;
	private String netPay;
	private String actualEarnings;
	private String totalDeductions;
	private String grossPay;
	private String grossDeduct;
	private String totalNetPay;
	private Date createDate;	
}
