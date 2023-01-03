package com.ingroinfo.mm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inward_items")
public class InwardItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;
	
}
