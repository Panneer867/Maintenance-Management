package com.ingroinfo.mm.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "MM_EMPLOYEE_SALARY")
public class EmployeeSalary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empSalaryId;
	@Column(name="EMPLOYEE_ID_SAL",length = 20)
	private String employeeId;
	@Column(name = "EMPLOYEE_NAME",length = 150)
	private String empName;
	@Column(name = "DEPARTMENT",length = 150)
	private String department;
	@Column(name = "EMP_BASIC_SALARY",length = 50)
	private String basicSalary;
	@Column(name = "EMP_SALARY",length = 50)
	private String empSalary;
	@Column(name = "EMP_DA",length = 20)
	private String empDa;
	@Column(name = "EMP_HRA",length = 150)
	private String empHra;
	@Column(name = "EMP_CONVENCANCE",length = 150)
	private String convencyance;
	@Column(name = "EMP_MEDICAL",length = 20)
	private String medical;
	@Column(name = "EMP_SPECIAL_ALOW",length = 20)
	private String empSplAllow;
	@Column(name = "ACTUAL_SALARY",length = 20)
	private String actualSalary;
	@Column(name = "ACTUAL_HRA",length = 150)
	private String actualHra;
	@Column(name = "ACTUAL_SPECIAL_ALOW",length = 150)
	private String actualSpclAllow;
	@Column(name = "EMP_ESI",length = 20)
	private String empEsi;
	@Column(name = "TEL_ALLOWANCE",length = 20)
	private String telAllowance;
	@Column(name = "ITA_ALLOWANCE",length = 20)
	private String ltaAllow;
	@Column(name = "EMP_PF",length = 20)
	private String empPf;
	@Column(name = "EMP_TDS",length = 20)
	private String empTds;
	@Column(name = "PROF_TAX",length = 20)
	private String profTax;
	@Column(name = "Total_Earnings",length = 50)
	private String totalEarn;
	@Column(name = "NET_PAY",length = 50)
	private String netPay;
	@Column(name = "Actual_Earnings",length = 50)
	private String actualEarnings;
	@Column(name = "Total_Deductions",length = 50)
	private String totalDeductions;
	@Column(name = "Gross_Pay",length = 50)
	private String grossPay;
	@Column(name = "Gross_Deduct",length = 50)
	private String grossDeduct;
	@Column(name = "Total_Net_Pay",length = 50)
	private String totalNetPay;
	
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private EmployeeMaster employeeMaster;
	
	@Column(name = "CREATE_DATE")	
	private Date createDate;
	@Column(name ="UPADATED_DATE")
	@UpdateTimestamp
	private Date lastUpdateDate;	
}
