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
	private Long slNo;
	private String itemName;
	private String materialImage;
	private String categoryName;
	private String categoryCode;
	private String description;
	private String supplierName;
	private String suppliedOn;
	private String brand;
	private String hsnCode;
	private String unitOfMeasure;
	private String totalQuantity;
	private String costRate;
	private String mrp;
	private String invoiceNo;
	private String gstType;
	private String igst;
	private String sgst;
	private String cgst;
	private String receivedBy;
	private String receivedDate;
	private Date dateCreated;
	private Date lastUpdated;
}
