package com.ingroinfo.mm.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="MM_MATERIAL_ADD")
public class PumpMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pumMaterialId;
	private String indentNo;
	private String complNo;
	private String division;
	private String subDivision;
	private String workSite;
	private Date startDate;
	private Date endDate;
	private String contactNo;	
	private String complDtls;
	private String workPriority;
	private String categoryName;
	private String itemName;
	private String unitOfMesure;
	private String hsnCode;
	private String quantity;
	private String stockType;
	private String indentType;
	private String empCategory;
	private String members;
	private String daysRequired;
	private String timeRequired;
	private String vehicleType;
	private String vehicleNo;
	private String driverName;
	private String driverPhone;
	private String meterReading;
	private String stratTime;
	
	@JoinColumn(name ="categoryId")
	@ManyToOne
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="itemId")
	private ItemMaster items;
	
	@ManyToOne
	@JoinColumn(name = "vehicleId")
	private VehicleDtls vehicle;
}
