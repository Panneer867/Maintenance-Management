package com.ingroinfo.mm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LeakageMainteUpdateDto {
	
	
    private Long leakageUpdateId;
	private String leakgworkOrderNumber;
	private String leakgworkOrdeDate;
	private String leakgworkOrderScope;
	private String leakgIndentNo;
	private String leakgIndentDate;
	private String leakgIndentReason;
	private String leakgType;
	private String leakgMainSupplyLine;	
	private String leakgArea;	
	private String leakgLengMeterKm;	
	private String leakgMaintenanceType;	
	private String leakgLangitude;	
    private String leakgLatitude;
	private String lengMeterKm;	
	private String leakgSupplyZone;	
	private String leakgLineStartNode;	
	private String leakgLineEndNode;	
	private String leakgLenthMeterKM;	
	private String leakgAsstsID;	
	private String leakgLineType;	
	private String leakgDiameter;	
	private String leakgpressureRange;	
	private String leakgDeductionInstrum;	
	private String leakgGRID;	
	private String leakgGMIS_ID;	
	private String leakgWardNo;	
	private String leakgWardName;	
	private String leakgMaintenanceReasons;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date leakgBreakdownDate;
	private String leakgBreakdownTime;	
	private String siteEngineer;	
	private String teamCode;	
	private String siteSuperwiser;
	private String workStatus;
}
