package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="MM_LEVEL_CONTROLS")
public class LevelControls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long levelControlsId;
	private String controlsId;
	private String valveId;
	private String make;
	private String type;
	private String details;
	private String installedDate;
	private String location;
	private String warranty;
	private String nextMaintananceTime;
	private String waterSupplyArea;
	private String supplierName;
	private String supplierContact;
	private String supplierAddress;
	private String supplyOrderNumber;
	private String devliveryDate;
	private String langitude;
	private String latitude;
	private String grid;
	private String gmisId;
	private String wardNo;
	private String wardName;
	private String siteEngineer;
	private String teamCode;
	private String siteSuperwiser;
	private String borewellStationConNo;
	

}
