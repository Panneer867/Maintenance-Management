package com.ingroinfo.mm.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MM_EMPLOYEE_MASTER")

public class EmployeeMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long employeeId;
	private String employeeCode;
	private String empName;
	private String fatherName;
	private String houseNo;
	private String address;
	private String contactNo;
	private String designation;
	private String bankAccNo;
	private String department;
	private String ifscCode;
	private String dateOfJoin;
	private String bankName;
	private String aadharNo;
	private String passportNo ;
	private String dlNo ;
	private String pfNo ;
	private String panNO;
	private String esiNumber;
	private String refContactNo;
	private String profile;
	private String emptype;
	private String empStatus;
	private String company;
	private String branch;
	private String bloodGroup;
	private String personName;
	private String state;
	private String city;
	private String maritalstatus;
	private String gender;
	private String dateOfBirth;
	private String endDate;
	private String qualification;
	private String sl;
	private String cl;
	private String lwp;
	private String totalLeave;
	private String salary;
	private String basicSalary;
	private String pinCode;

}
