package com.ingroinfo.mm.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InwardItemDto {
	
	private Long itemId;
	private String itemName;
	private String aliasName;
	private String materialImage;
	private String imagePath;
	private String categoryName;
	private String brand;
	private String hsnCode;
	private String unitOfMeasure;
	private String totalQuantity;
	private String costRate;
	private String mrp;
	private String entryDate;
	private String description;
	private Date dateCreated;
	private Date lastUpdated;
}
