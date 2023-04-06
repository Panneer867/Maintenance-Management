package com.ingroinfo.mm.entity;

import java.util.Date;

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
@Table(name = "mm_assets")
public class Assets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@CreationTimestamp
	private Date dateCreated;
	@UpdateTimestamp
	private Date lastUpdated;

}
