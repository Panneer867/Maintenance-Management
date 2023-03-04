package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mm_workorder_items")
public class WorkOrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tempWorkorderItemId;
	private int finalQuantity;
	private int slNo;
	private Long itemId;
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

	@ManyToOne
	@JoinColumn(name = "workorder_id")
	private TempWorkOrderNos workOrderId;

}
