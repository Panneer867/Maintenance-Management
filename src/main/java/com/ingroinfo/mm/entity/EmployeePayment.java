package com.ingroinfo.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "MM_EMPLOYEE_PAYMENT")
public class EmployeePayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empPaymentId;
	@Column(name="EPLOYEE_ID",length = 20)
	private String employeeId;
	@Column(name = "EMPLOYEE_NAME",length = 150)
	private String empName;
	@Column(name = "DEPARTMENT",length = 150)
	private String department;
	@Column(name = "BASIC_SALARY",length = 20)
	private String basicSalary;
	@Column(name = "SALARY",length = 20)
	private String salary;
	@Column(name = "DA",length = 20)
	private String da;
	@Column(name = "HRA",length = 150)
	private String hra;
	@Column(name = "CONVENCANCE",length = 150)
	private String convencyance;
	@Column(name = "MEDICAL",length = 20)
	private String medical;
	@Column(name = "SPECIAL_ALOW",length = 20)
	private String splAllow;
	@Column(name = "ESI",length = 20)
	private String esi;
	@Column(name = "TEL_ALLOWANCE",length = 20)
	private String telAllowance;
	@Column(name = "ITA_ALLOWANCE",length = 20)
	private String ltaAllow;
	@Column(name = "PF",length = 20)
	private String pf;
	@Column(name = "TDS",length = 20)
	private String tds;
	@Column(name = "TAX",length = 20)
	private String tax;
	@Column(name = "NET_SALARY",length = 20)
	private String netSalary;
}
