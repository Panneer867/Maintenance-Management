package com.ingroinfo.mm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name="MM_ASSEST_ENTRY")
public class AssestEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long assestEntryId;
	@Column(length = 50)
	private String division ;
	@Column(length = 50)
	private String subDivision;
	@Column(length = 50)
	private String assestsName;
	@Column(length = 6)
	private String assetId;
	@Column(length = 50)
	private String category;
	@Column(length = 50)
	private String subCategory;
	@Column(length = 50)
	private String model;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date purchaseDate;
	@Column(length = 50)
	private String assestType;
	@Column(length = 50)
	private String assignedTo;
	@Column(length = 50)
	private String barCode;
	@Column(length = 50)
	private String hsnCode;
	@Column(length = 50)
	private String cost;
	@Column(length = 50)
	private String assestsLocation;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date retunedDate;
	@Column(length = 50)
	private String storeName;
	
	
	
	

}
