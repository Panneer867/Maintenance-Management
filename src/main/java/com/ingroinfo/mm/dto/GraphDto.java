package com.ingroinfo.mm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GraphDto {

	private String stockType;
	private String monthName;
	private int materialsQuantity;
	private int sparesQuantity;
	private int toolsQuantity;
	private int totalQuantity;
	private int year;

}
