package com.ingroinfo.mm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebateHistoryDto {

	private String consumerName;
	private String tariff;
	private String rebateOnArr;
	private String rebateOnInt;
	private String rebateOnTax;
	private String orderDate;
	private String billMonth;
	private String totalRebate;	
	private String remark;
}
