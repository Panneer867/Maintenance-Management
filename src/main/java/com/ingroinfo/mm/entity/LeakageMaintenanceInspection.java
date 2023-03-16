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
@Table(name="MM_Leakage_Maintenance_Update")
public class LeakageMaintenanceInspection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long leakageUpdateId;
	@Column(length = 50)
	private String leakgworkOrderNumber;
	private String leakgworkOrdeDate;
	@Column(length = 50)
	private String leakgworkOrderScope;
	@Column(length = 6)
	private String leakgIndentNo;
	private String leakgIndentDate;
	@Column(length = 50)
	private String leakgType;
	@Column(length = 50)
	private String leakgMainSupplyLine;
	@Column(length = 50)
	private String leakgArea;
	@Column(length = 50)
	private String leakgLengMeterKm;
	@Column(length = 50)
	private String leakgMaintenanceType;
	@Column(length = 50)
	private String leakgLangitude;
	@Column(length = 50)
	private String leakgLatitude;
	@Column(length = 50)
	private String lengMeterKm;
	@Column(length = 50)
	private String leakgSupplyZone;
	@Column(length = 50)
	private String leakgLineStartNode;
	@Column(length = 50)
	private String leakgLineEndNode;
	@Column(length = 50)
	private String leakgLenthMeterKM;
	@Column(length = 10)
	private String leakgAsstsID;
	@Column(length = 50)
	private String leakgLineType;
	@Column(length = 50)
	private String leakgDiameter;
	@Column(length = 50)
	private String leakgpressureRange;
	@Column(length = 50)
	private String leakgDeductionInstrum;
	@Column(length = 50)
	private String leakgGRID;
	@Column(length = 50)
	private String leakgGMIS_ID;
	@Column(length = 50)
	private String leakgWardNo;
	@Column(length = 50)
	private String leakgWardName;
	@Column(length = 50)
	private String leakgMaintenanceReasons;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date leakgBreakdownDate;
	private String leakgBreakdownTime;
	@Column(length = 50)
	private String siteEngineer;
	@Column(length = 50)
	private String teamCode;
	@Column(length = 50)
	private String siteSuperwiser;
	@Column(length = 50)
	private String workStatus;
}
