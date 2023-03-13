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
@Table(name = "mm_approved_workorder_items")
public class ApprovedWorkOrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long workorderItemId;
	private int finalQuantity;
	private int slNo;
	private String itemId;
	private String itemName;
	private String aliasName;
	private String itemImage;
	private String imagePath;
	private String category;
	private String unitOfMeasure;
	private Double mrpRate;
	private Double totalCost;
	private String description;
	private Long workOrderNo;
	private String stockType;
	private String username;
	
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	@ManyToOne
	@JoinColumn(name = "workorder_id")
	private ApprovedWorkOrderNos workOrderId;

}
