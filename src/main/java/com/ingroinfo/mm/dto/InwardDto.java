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
public class InwardDto {

	private Long materialId;
	private Long spareId;
	private Long toolId;
	private String itemId;
	private String itemName;
	private String aliasName;
	private String category;
	private String brand;
	private String hsnCode;
	private String unitOfMeasure;
	private String supplier;
	private String suppliedOn;
	private String gstType;
	private String invoice;
	private String receivedBy;
	private String receivedDate;
	private String entryDate;
	private String approvedBy;
	private String username;
	private String imagePath;
	private String description;
	private String itemImage;
	private Double costRate;
	private Double mrpRate;
	private Double igst;
	private Double sgst;
	private Double cgst;
	private int quantity;
	private String stockType;

	private String month_name;
	private int material_quantity;
	private int spare_quantity;
	private int tool_quantity;


	private String monthName;
	private int year;
	private int totalQuantity;

	private Double subTotal;
	private Double grandTotal;
	private Date dateCreated;
	private Date lastUpdated;

}
