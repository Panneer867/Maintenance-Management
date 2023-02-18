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
@Table(name="MM_PIPE_INDEX")
public class PipeIndex {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pipeMaintenId;
	@Column(name = "PIPE_ID",length = 10)
	private String pipeId;
	@Column(name = "PIPE_MARK",length = 100)
	private String pipeMark;
	@Column(name = "PIPE_TYPE",length = 100)
	private String pipeType;
	@Column(name = "PIPE_DETAILS",length = 200)
	private String pipeDetails;
	@Column(name = "PURCHASE_DATE",length = 15)
	private String purchaseDate;
	@Column(name = "LOCATION" ,length = 150)
	private String pipeLocation;
	@Column(name = "STATION_NAME",length = 150)
	private String pipeStonName;
	@Column(name = "NEXT_MAINTEN_TIME",length = 100)
	private String nextMaintenTime;
	@Column(name = "PIPE_POWER",length = 100)
	private String pipePower;
	@Column(name = "WARRANTY",length = 50)
	private String pipeWarranty;
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
	private String pipeStonNo;
	@Column(name = "PIPE_MAINTEN_STS",length = 20)
	private String pipeMaintenSts;

}
