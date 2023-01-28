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
@Table(name = "mm_inward_temp_materials")
public class InwardTempMaterials {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long materialId;
	private Long itemId;
	private String itemName;
	private String aliasName;
	private String materialImage;
	private String imagePath;
	private String categoryName;
	private String brand;
	private String hsnCode;
	private String unitOfMeasure;
	private Long totalQuantity;
	private Long totalAmount;
	private Long costRate;
	private Long mrp;
	private String entryDate;
	private String description;
	private String username;
	
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;
	
}
