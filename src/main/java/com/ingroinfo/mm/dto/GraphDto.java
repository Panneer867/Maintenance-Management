package com.ingroinfo.mm.dto;

import java.util.Map;

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
	private String category;
	private int jeComplaints;
	private int aeeComplaints;
	private int eeComplaints;
	private int commComplaints;
	private String departments;
	private int count;
	private int complNo;
	private Map<String, Integer> departmentCount;
}
