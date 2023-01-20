package com.ingroinfo.mm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PipeMaintenanceDto {
	
	private Long pipeId;
	private String pipeIndentNo;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date pipeIndentDate;
	private String indentFor;
	private String pipeFrom;
	private String pipeEnd;
	private String lengthMeterKM;
	private String maintenanceType;
	private String pipeStartNode;
	private String pipeEndNode;
	private String nodeLengthMeterKM;
	private String asstsID;
	private String langitude;
	private String latitude;
	private String lanlatlLengthMeterKM;
	private String pipeLineType;
	private String pipeLineDiameter;
	private String pipeLinepressure;
	private String pipeGRID;
	private String pipeGMIS_ID;
	private String pipeWardNo;
	private String pipeWardName;
	private String pipeMaintenanceReasons;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date pipeBreakdownDate;
	private String pipeBreakdownTime;
	private String siteEngineer;
	private String teamCode;
	private String siteSuperwiser;

}
