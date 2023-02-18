package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class CashHistoryDto {

	//private String ConsumerId;
	private String receiptNo;
	private String receiptDate;
	private String paymentMode;
	private String amtPaid;
	private String colletCenter;
	private String userName;
	private String cadateStam;
	private String remarks;
}
