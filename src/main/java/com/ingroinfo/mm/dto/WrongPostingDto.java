package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class WrongPostingDto {

	private String fromConsId;
	private String toConsId;
	private String fromConsName;
	private String toConsName;
	private String fromRecNo;
	private String toRecNo;
	private String fromRecDate;
	private String toRecDate;
	private String date;
	private String fromAmount;
	private String toAmount;
	private String remarks;
}
