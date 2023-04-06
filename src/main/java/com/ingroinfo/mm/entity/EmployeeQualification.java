package com.ingroinfo.mm.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name="MM_EMPLOYEE_QUALIFICATION")
public class EmployeeQualification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empQulifId;
	private String qualification;

}