package com.ingroinfo.mm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "MM_PUMP_MAINTENANCE_INSPECTION")
public class PumpMaintenanceInspection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inspectionId;
	private String workOrderNo;
	private String workOrderApprovedDate;
	private String complNo;
	private String indentNo;
	private String indentApprovedDate;
	private String pumpId;
	private String pumpLocation;
	private String pumpMake;
	private String nextMaintenDate;
	private String purchageDate;
	private String warranty;
	private String waterSource;
	private String nextMaintentime;
	private String latitude;
	private String langitude;
	private String distributionTo;
	private String maintenanceType;
	private String pumptype;
	private String pumpPower;
	private String manufatureName;
	private String manufactureContact;
	private String grid;
	private String gmisId;
	private String wardNo;
	private String wardName;
	private String maintenanceReasone;
	private String breakDownDate;
	private String breakDowntime;
	private String teamCode;
	private String siteEngineerName;
	private String workSts;
	private String materialReturnSts;
	private String siteSuperWiser;
	private String department;
	private String inspectionDate;
	private String inspectionRemark;
	private String userName;
	@CreationTimestamp
	private Date createdDate;
}
