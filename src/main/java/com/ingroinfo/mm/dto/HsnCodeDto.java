package com.ingroinfo.mm.dto;

import com.ingroinfo.mm.entity.Category;

import lombok.Data;

@Data
public class HsnCodeDto {

	private Long hsnCodeId;
	private String categoryName;	
	private String hsnCode;			 
	private Category category;
}
