package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class ItemMasterDto {

	private Long itemMasterId;
	private String itemId;	
	private String category;
	private String hsnCode;
	private String itemName;
	private String stockType;
	private String description;
}
