package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class WorkOrderItemsDto {

	private int stockAvailable;
	private int stockRequested;

	private int qty;
	private int finalQuantity;
	private int slNo;
	private String itemId;
	private String itemName;
	private String aliasName;
	private String itemImage;
	private String stockType;
	private String imagePath;
	private String category;
	private String unitOfMeasure;
	private Double totalCost;
	private Double mrpRate;
	private String description;
	private Long workOrderNo;

}
