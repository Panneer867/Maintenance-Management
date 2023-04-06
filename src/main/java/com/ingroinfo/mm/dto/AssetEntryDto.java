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
public class AssetEntryDto {

	private Long assetEntryId;
	private String division;
	private String subDivision;
	private String assetName;
	private String assetId;
	private String category;
	private String subCategory;
	private String model;
	private String purchaseDate;
	private String assetType;
	private String assignedTo;
	private String barCode;
	private String hsnCode;
	private String cost;
	private String assetLocation;
	private String returnedDate;
	private String storeName;
	private String department;
	private String assetMake;
	private int quantity;
	private Date dateCreated;
	private Date lastUpdated;
	
    }
	
	
	


