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
@Table(name="MM_VECHILE_MASTER")
public class VehicleDtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleDtlsId;
	@Column(name="VEHICLE_ID",length = 10)
	private String vehicleId;
	@Column(name="DRIVER_NAME",length = 130)
	private String driverName;
	@Column(name="VEHICLE_TYPE",length = 100)
	private String vehicleType;
	@Column(name="RC_NO",length = 30)
	private String rcNumber;
	@Column(name="VEHICLE_MODEL",length = 100)
	private String vehiclemodel;
	@Column(name="PURCHASE_DATE")
	private String purchaseDate;
	@Column(name="INSURANC_NO",length = 30)
	private String insurancNo;
	@Column(name="INSURANCE_TYPE",length = 100)
	private String insurancType;
	@Column(name="DRIVER_CONTACT_NO",length = 13)
	private String driverMob;
	@Column(name="VEHICLE_NO",length = 50)
	private String vehicleNo;
	@Column(name="DRIVER_ADDR",length = 150)
	private String driverAddr;
	
}
