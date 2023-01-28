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

	private Long materialId;
	private Long spareId;
	private Long toolsId;
	private Long itemId;
	private Long bundleId;
	private String itemName;
	private String aliasName;
	private String categoryName;
	private String brand;
	private String hsnCode;
	private String unitOfMeasure;
	private int totalQuantity;
	private String supplier;
	private String suppliedOn;
	private String gstType;
	private String invoiceNo;
	private String receivedBy;
	private String receivedDate;
	private String entryDate;
	private String approvedBy;
	private String username;
	private String imagePath;
	private String description;
	private String materialImage;
	private String spareImage;
	private String toolsImage;
	private Double costRate;
	private Double mrp;
	private Double igst;
	private Double sgst;
	private Double cgst;
	private Double totalAmount;
	private Double subTotal;
	private Double grandTotal;
	private String supplierName;
	private int noOfMaterials;
	private int noOfSpares;
	private int noOfTools;
	private Date dateCreated;
	private Date lastUpdated;

}
