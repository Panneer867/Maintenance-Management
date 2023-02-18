package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class ChequeBounceDto {

	private String consName;
	private String chequeNo;
	private String chequeDate;
	private String reciptNo;
	private String reciptDate;
	private String bankName;
	private String amount;
	private String bounceCharge;
	private String address;
	private String remarks;
}
