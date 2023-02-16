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
@Table(name="MM_HANDPUMP")
public class HandPump {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long handpumpIndexId;
	@Column(length = 6)
	private String handpumpId;
	@Column(length = 25)
	private String handpumpMark;
	@Column(length = 25)
	private String handpumpType;
	@Column(length = 50)
	private String handpumpDetails;
	private String installedDate;
	@Column(length = 50)
	private String handpumpLocation;
	@Column(length = 50)
	private String installedMotor;
	@Column(length = 25)
	private String handpumpStoreName;
	@Column(length = 50)
	private String handpumpWarranty;
	private String nextMaintenTime;
	private String waterType;
	@Column(length = 50)
	private String scheduleType;
	@Column(length = 50)
	private String motorType;
	@Column(length = 50)
	private String installedMotorPower;
	@Column(length = 50)
	private String waterSupplyArea;
	@Column(length = 50)
	private String supplierName;
	@Column(length = 10)
	private String supplierContact;
	@Column(length = 50)
	private String supplierAddress;
	@Column(length = 50)
	private String workIntervalHandPump;
	@Column(length = 15)
	private String supplyOrderNumber;
	private String deliveryDate;
	private String longitude;
	private String latitude;
	@Column(length = 20)
	private String grid;
	@Column(length = 20)
	private String gmisId;
	@Column(length = 20)
	private String wardNo;
	@Column(length = 25)
	private String WardName;
	@Column(length = 25)
	private String siteEnginner;
	@Column(length = 25)
	private String teamCode;
	@Column(length = 25)
	private String siteSuperviser;
	@Column(length = 25)
	private String handpumpStatnNo;
	
}
