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
@Table(name="MM_PIPE_Maintenance_INSPECTION")
public class PipeMaintenanceInspection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pipeInspectionIdLong;
	@Column(length = 50)
	private String pipeFromInsp;
	@Column(length = 50)
	private String pipeEndInsp;
	@Column(length = 50)
	private String lengthMeterKMInsp;
	@Column(length = 50)
	private String maintenanceTypeInsp;
	@Column(length = 50)
	private String pipeStartNodeInsp;
	@Column(length = 50)
	private String pipeEndNodeInsp;
	@Column(length = 50)
	private String nodeLengthMeterKMInsp;
	@Column(length = 50)
	private String asstsIDInsp;
	@Column(length = 50)
	private String langitudeInsp;
	@Column(length = 50)
	private String latitudeInsp;
	@Column(length = 50)
	private String lanlatlLengthMeterKMInsp;
	@Column(length = 50)
	private String pipeLineTypeInsp;
	@Column(length = 50)
	private String pipeLineDiameterInsp;
	@Column(length = 50)
	private String pipeLinepressureInsp;
	@Column(length = 50)
	private String pipeGRIDInsp;
	@Column(length = 50)
	private String pipeGMIS_IDInsp;
	@Column(length = 50)
	private String pipeWardNoInsp;
	@Column(length = 50)
	private String pipeWardNameInsp;
	@Column(length = 50)
	private String pipeMaintenanceReasonsInsp;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date pipeBreakdownDateInsp;
	@Column(length = 50)
	private String pipeBreakdownTimeInsp;
	@Column(length = 50)
	private String siteEngineerInsp;
	@Column(length = 50)
	private String teamCodeInsp;
	@Column(length = 50)
	private String siteSuperwiserInsp;
	@Column(length = 50)
	private String TotalEquipmentCost;
	@Column(length = 50)
	private String serviceCost;
	@Column(length = 50)
	private String labourCost;
	@Column(length = 50)
	private String totalWorkCost;
	@Column(length = 50)
	private String inspectionDoneBy;
	@Column(length = 50)
	private String inspectionWorkStatus;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date inspectionDate;
	
	private String inspectionDocUplode;
	@Column(length = 50)
	private String inspectionRemark;
	
}
