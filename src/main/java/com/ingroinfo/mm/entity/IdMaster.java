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
@Table(name ="MM_ID_MASTER")
public class IdMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long allMasterId;	
	@Column(length = 6)
	private String assertId;
	@Column(length = 6)
	private String branchId;
	@Column(length = 6)
	private String budjetId;
	@Column(length = 6)
	private String categoryId;
	@Column(length = 6)
	private String companyId;
	@Column(length = 6)
	private String departmentId;
	@Column(length = 6)
	private String deviceId;
	@Column(length = 6)
	private String dmaId;
	@Column(length = 6)
	private String employeeId;
	@Column(length = 6)
	private String hsnCodeId;
	@Column(length = 6)
	private String inMaterialId;
	@Column(length = 6)
	private String inSparesMatieralId;
	@Column(length = 6)
	private String inToolsId;
	@Column(length = 6)
	private String itemId;
	@Column(length = 6)
	private String leakageId;
	@Column(length = 6)
	private String meterId;
	@Column(length = 6)
	private String outMaterialIssueId;
	@Column(length = 6)
	private String outSparesIssueId;
	@Column(length = 6)
	private String outToolsIssueId;
	@Column(length = 6)
	private String pumpId;
	@Column(length = 20)
	private String sequenceId;
	@Column(length = 6)
	private String stockId;
	@Column(length = 6)
	private String stockReturnId;
	@Column(length = 6)
	private String stockRejMatieralRtnId;
	@Column(length = 6)
	private String stockRejDmgSparesRtnId;
	@Column(length = 6)
	private String stockRejDmgToolsRtnId;
	@Column(length = 6)
	private String stockSparesRtnId;
	@Column(length = 6)
	private String stockToolsRtnId;
	@Column(length = 6)
	private String vehicleId;
	@Column(length = 6)
	private String vehicelMntIndentId;
	@Column(length = 6)
	private String wardId;
	@Column(length = 6)
	private String workEstBudjetId;
	@Column(length = 6)
	private String workOrderId;
	@Column(length = 6)
	private String workOrderCancelId;
	
	//description
	
	@Column(length = 50)
	private String assertDesc;
	@Column(length = 50)
	private String branchDesc;
	@Column(length = 50)
	private String budjetDesc;
	@Column(length = 50)
	private String categoryDesc;
	@Column(length = 50)
	private String companyDesc;
	@Column(length = 50)
	private String departmentDesc;
	@Column(length = 50)
	private String deviceDesc;
	@Column(length = 50)
	private String dmaDesc;
	@Column(length = 50)
	private String employeeDesc;
	@Column(length = 50)
	private String hsnCodeDesc;
	@Column(length = 50)
	private String inMaterialDesc;
	@Column(length = 50)
	private String inSparesMatieralDesc;
	@Column(length = 50)
	private String inToolsDesc;
	@Column(length = 50)
	private String itemDesc;
	@Column(length = 50)
	private String leakageDesc;
	@Column(length = 50)
	private String meterDesc;
	@Column(length = 50)
	private String outMaterialIssueDesc;
	@Column(length = 50)
	private String outSparesIssueDesc;
	@Column(length = 50)
	private String outToolsIssueDesc;
	@Column(length = 50)
	private String pumpDesc;
	@Column(length = 50)
	private String sequenceDesc;
	@Column(length = 50)
	private String stockDesc;
	@Column(length = 50)
	private String stockReturnDesc;
	@Column(length = 50)
	private String stockRejMatieralRtnDesc;
	@Column(length = 50)
	private String stockRejDmgSparesRtnDesc;
	@Column(length = 50)
	private String stockRejDmgToolsRtnDesc;
	@Column(length = 50)
	private String stockSparesRtnDesc;
	@Column(length = 50)
	private String stockToolsRtnDesc;
	@Column(length = 50)
	private String vehicleDesc;
	@Column(length = 50)
	private String vehicelMntIndentDesc;
	@Column(length = 50)
	private String wardDesc;
	@Column(length = 50)
	private String workEstBudjetDesc;
	@Column(length = 50)
	private String workOrderDesc;
	@Column(length = 50)
	private String workOrderCancelDesc;
	
}
