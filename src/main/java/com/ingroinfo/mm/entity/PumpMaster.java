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
@Table(name="MM_PUMP_MASTER")
public class PumpMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pumpMasterId;
	@Column(name="PUMP_ID",length = 50)
	private String pumpId;
	@Column(name="PUMP_TYPE",length = 50)
	private String pumpType;
	@Column(name="PUMP_WARRANTY",length = 50)
	private String warranty;
	@Column(name="PUMP_PURCH_DATE",length = 10)
	private String purchageDate;
	@Column(name="CONTACT_NO",length = 13)
	private String contactNo;
	@Column(name="MANUFACT_NAME",length = 100)
	private String manufactName;
	@Column(name="MANUFACT_ADDRESS",length = 150)
	private String manufactAddress;
}
