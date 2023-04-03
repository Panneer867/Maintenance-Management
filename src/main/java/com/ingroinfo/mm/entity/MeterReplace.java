package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "mm_meter_replacement")
public class MeterReplace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordId;
	private String complaintNumber;
	private String department;
	private String deviceNo;
	private String deviceMake;
	private String consumerId;
	private String consumerName;
	private String deviceIr;
	private String deviceFr;
	private String deviceLocation;
	private String deviceOwnership;
	private String pipeSize;
	private String deviceWarranty;
	private String plumberName;
	private String plumberId;
	private String dma;
	private String ward;
	private String compliantDetails;
	private String inspectionResult;

}
