package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class ConsumerTransactionsDto {

	private String consumerId;
	/*
	 * private String consumerName; private String meterType; private String
	 * consumerType; private String houseNo; private String wardNo; private String
	 * connSts; private String imisId; private String dmaId; private String
	 * meterSts;
	 */
	
	private String billMonth;
	private String tariff;
	private String readingDay;
	private String billDate;
	private String billed;
	private String bm;
	private String pipeSize;
	private String meterStatus;
	private String avgConsuption;
	private String presReading;
	private String prevReading;
	private String consuption;
	private String orb;
	private String obi;
	private String obt;
	private String obTotal;
	private String waterCharges;
	private String fixedCharge;
	private String ugd;
	private String serviceCharge;
	private String intrest;
	private String tax;
	private String otherCharge;
	private String rebate;
	private String adjestment;
	private String billAmt;
	private String totalPaidAmt;
	private String netAmt;
	private String cbr;
	private String cbi;
	private String cbt;
	private String cbTotal;
	private String dueDate;
	private String billNo;
	private String sbmNo;
	private String transId;
	private String userName;
	private String smsSent;
	private String remarks;
	private String imageLink;
	
}
