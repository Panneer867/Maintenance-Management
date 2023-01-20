package com.ingroinfo.mm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PipeMaintenanceInspectionDto {
	
	private Long pipeInspectionIdLong;
	private String pipeFromInsp;
	private String pipeEndInsp;
	private String lengthMeterKMInsp;
	private String maintenanceTypeInsp;
	private String pipeStartNodeInsp;
	private String pipeEndNodeInsp;
	private String nodeLengthMeterKMInsp;
	private String asstsIDInsp;
	private String langitudeInsp;
	private String latitudeInsp;
	private String lanlatlLengthMeterKMInsp;
	private String pipeLineTypeInsp;
	private String pipeLineDiameterInsp;
	private String pipeLinepressureInsp;
	private String pipeGRIDInsp;
	private String pipeGMIS_IDInsp;
	private String pipeWardNoInsp;
	private String pipeWardNameInsp;
	private String pipeMaintenanceReasonsInsp;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date pipeBreakdownDateInsp;
	private String pipeBreakdownTimeInsp;
	private String siteEngineerInsp;
	private String teamCodeInsp;
	private String siteSuperwiserInsp;
	private String TotalEquipmentCost;
	private String serviceCost;
	private String labourCost;
	private String totalWorkCost;
	private String inspectionDoneBy;
	private String inspectionWorkStatus;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date inspectionDate;
	private String inspectionDocUplode;
	private String inspectionRemark;

}
