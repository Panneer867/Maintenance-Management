package com.ingroinfo.mm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MisReportDto {

	private String fromDate;
	private String toDate;
	private String category;
	private String subCategory;
	
}
