package com.ingroinfo.mm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MM_Employee_Inspection")
public class EmployeeInspection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String empCode;
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
	private String passportNo;
	private String dlNo;
	private String pfNo;
	private String panNO;
	private String esiNumber;
	private String refContactNo;
	private String profile;
	
	private String safetyRules;
	private String healthCondtn;
	private String empBehavior;
	private String timePunctlity;
	private String inspectDate;
	private String inspectBy;
	private String inspectRemark;
	
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

}
