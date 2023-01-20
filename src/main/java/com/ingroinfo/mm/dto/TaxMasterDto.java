package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class TaxMasterDto {

	private Long taxMasterId;
	private String cGst;
	private String sGst;
	private String gstTotal;
}
