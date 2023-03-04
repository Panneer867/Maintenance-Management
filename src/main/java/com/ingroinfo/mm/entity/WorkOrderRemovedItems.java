package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mm_work_order_removed_items")
public class WorkOrderRemovedItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long workOrderItemRemovedId;
	private String itemId;
	private int requestedQuantity;
	private int stockQuantity;
	private String Availability;
	private Long workOrderNo;
	private String remarks;
	private String stockType;
	

}
