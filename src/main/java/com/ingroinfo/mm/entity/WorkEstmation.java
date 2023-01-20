package com.ingroinfo.mm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Entity
@Data
@Table(name ="MM_WORK_ESTMATION")
public class WorkEstmation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long workEstmationId;
	@Column(length = 50)
	private String division;
	@Column(length = 50)
	private String subDivision;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date workOrderDate;
	@Column(length = 50)
	private String estimationNumber ;
	@Column(length = 50)
	private String workOrderScope;
	@Column(length = 50)
	private String estimationGenratedBy;
	@Column(length = 50)
	private String workPriority;
	@Column(length = 6)
	private String workOrderCost;
	@Column(length = 50)
	private String workSite;
	@Column(length = 10)
	private String contact;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date expectedStartDate;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date expectedEndDate;
	@Column(length = 50)
	private String category ;
	@Column(length = 50)
	private String item;
	@Column(length = 10)
	private String hsnCode;
	@Column(length = 50)
	private String unitMeassure;
	@Column(length = 50)
	private String estMaterialQty;
	@Column(length = 25)
	private String estMaterialAmount;
	@Column(length = 50)
	private String employeeCategorty;
	@Column(length = 25)
	private String employeeType;
	@Column(length = 25)
	private String labourQty;
	
	private String days;
	private String time;
	@Column(length = 50)
	private String totalHours;
	@Column(length = 25)
	private String vehicleType ;
	@Column(length = 50)
	private String vehicleNumber;
	@Column(length = 10)
	private String driverName;
	@Column(length = 50)
	private String driverContactNumber;
	@Column(length = 50)
	private String odoMeterReading;
	private String startTime;
	
}
