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
@Table(name="MM_PUMP_MAINTENANCE")
public class PumpMaintenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pumpMaintenId;
	@Column(name = "PUMP_ID",length = 10)
	private String pumpId;
	@Column(name = "PUMP_MARK",length = 100)
	private String pumpMark;
	@Column(name = "PUMP_TYPE",length = 100)
	private String pumpType;
	@Column(name = "PUMP_DETAILS",length = 200)
	private String pumpDetails;
	@Column(name = "PURCHASE_DATE",length = 15)
	private String purchaseDate;
	@Column(name = "LOCATION" ,length = 150)
	private String pumpLocation;
	@Column(name = "STATION_NAME",length = 150)
	private String pumpStonName;
	@Column(name = "NEXT_MAINTEN_TIME",length = 100)
	private String nextMaintenTime;
	@Column(name = "PUMP_POWER",length = 100)
	private String pumpPower;
	@Column(name = "WARRANTY",length = 50)
	private String pumpWarranty;
	@Column(name = "WATER_SOURCE",length = 150)
	private String waterSource;
	@Column(name = "SCHEDULE_TYPE",length = 100)
	private String scheduleType;
	@Column(name = "MANUFACTURE_NAME",length = 100)
	private String manufactureName;
	@Column(name = "MANUFACTURE_CONTACT",length = 13)
	private String manufactureContact;
	@Column(name = "MANUFACTURE_ADDR",length = 200)
	private String manufactureAddr;
	@Column(name = "WORK_INTERVAL",length = 100)
	private String workInterval;
	@Column(name = "SUPPLIER_ORDER_NO",length = 20)
	private String supplierOrderNo;
	@Column(name = "DELIVERY_DATE",length = 20)
	private String deliveryDate;
	@Column(name = "INSTALLED_DATE",length = 20)
	private String installDate;
	@Column(name = "LONGITUDE",length = 50)
	private String longitude;
	@Column(name = "LATITUDE",length = 50)
	private String latitude;
	@Column(name = "DISTRIBUTION_TO",length = 150)
	private String distributonTo;
	@Column(name = "GRID",length = 50)
	private String grid;
	@Column(name = "GMIS_ID",length = 20)
	private String gmisId;
	@Column(name = "WARD_NO",length = 20)
	private String wardNo;
	@Column(name = "WARD_NAME",length = 100)
	private String WardName;
	@Column(name = "SITE_ENGINNER",length = 100)
	private String siteEnginner;
	@Column(name = "TEAM_CODE",length = 20)
	private String teamCode;
	@Column(name = "SITE_SUPERVISER",length = 100)
	private String siteSuperviser;
	@Column(name = "STATION_NO",length = 20)
	private String pumpStonNo;
	@Column(name = "PUMP_MAINTEN_STS",length = 20)
	private String pumpMaintenSts;
	
}

