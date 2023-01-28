package com.ingroinfo.mm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "mm_inward_spare_bundles")
public class InwardSpareBundle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bundleId;
	private Long itemId;
	private String itemName;
	private String aliasName;
	private String spareImage;
	private String imagePath;
	private String categoryName;
	private String brand;
	private String hsnCode;
	private String unitOfMeasure;
	private int totalQuantity;
	private Double totalAmount;
	private Double costRate;
	private Double mrp;
	private String entryDate;
	private String description;

	@ManyToOne
	@JoinColumn(name = "inward_spare_id")
	private InwardSpare inwardSpare;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

}
