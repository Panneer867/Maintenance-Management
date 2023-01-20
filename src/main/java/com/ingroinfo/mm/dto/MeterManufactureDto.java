package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class MeterManufactureDto {

	private Long mtrmanufacId;
	private String meterId;
	private String meterManufacture;
	private String meterType;
	private String digit;
	private String receivedDate;
	private String meterSlNo;
	private String warranty;
	private String condition;
}
