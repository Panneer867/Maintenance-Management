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
@Table(name = "MM_Leave")

public class Leave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
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
	
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

}
