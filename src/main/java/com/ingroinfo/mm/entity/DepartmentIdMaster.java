package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="MM_DEPARTMENT_ID_MASTER")
public class DepartmentIdMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long depMasterId;
	private String masterIdName;
	private String deptName;
	private String deptId;
	private String deptIdDesc;
}
