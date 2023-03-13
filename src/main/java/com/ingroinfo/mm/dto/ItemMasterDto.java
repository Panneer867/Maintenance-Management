package com.ingroinfo.mm.dto;

import com.ingroinfo.mm.entity.Category;

import lombok.Data;

@Data
public class ItemMasterDto {

	private Long itemMasterId;
	private String itemId;	
	private String categoryName;
	private String hsnCode;
	private String itemName;
	private String stockType;
	private String description;	
	private Category category;
}
