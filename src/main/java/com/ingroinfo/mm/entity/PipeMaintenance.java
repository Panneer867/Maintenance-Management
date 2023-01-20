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
@Table(name ="MM_PIPE_Maintenance")
public class PipeMaintenance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pipeId;
	@Column(length = 6)
	private String pipeIndentNo;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date pipeIndentDate;
	@Column(length = 50)
	private String indentFor;
	@Column(length = 50)
	private String pipeFrom;
	@Column(length = 50)
	private String pipeEnd;
	@Column(length = 50)
	private String lengthMeterKM;
	@Column(length = 50)
	private String maintenanceType;
	@Column(length = 50)
	private String pipeStartNode;
	@Column(length = 50)
	private String pipeEndNode;
	@Column(length = 50)
	private String nodeLengthMeterKM;
	@Column(length = 50)
	private String asstsID;
	@Column(length = 50)
	private String langitude;
	@Column(length = 50)
	private String latitude;
	@Column(length = 50)
	private String lanlatlLengthMeterKM;
	@Column(length = 50)
	private String pipeLineType;
	@Column(length = 50)
	private String pipeLineDiameter;
	@Column(length = 50)
	private String pipeLinepressure;
	@Column(length = 50)
	private String pipeGRID;
	@Column(length = 50)
	private String pipeGMIS_ID;
	@Column(length = 50)
	private String pipeWardNo;
	@Column(length = 50)
	private String pipeWardName;
	@Column(length = 100)
	private String pipeMaintenanceReasons;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date pipeBreakdownDate;
	private String pipeBreakdownTime;
	@Column(length = 50)
	private String siteEngineer;
	@Column(length = 50)
	private String teamCode;
	@Column(length = 50)
	private String siteSuperwiser;

}
