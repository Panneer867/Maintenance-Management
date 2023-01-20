package com.ingroinfo.mm.dto;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DivisionSubdivisionDto {

	private Long divsubId;
	private String division;
	private String subdivision;
	private String serviceStation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
}
