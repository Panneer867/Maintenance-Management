package com.ingroinfo.mm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Long itemId;
	private int requestedQuantity;
	private int stockQuantity;
	private String Availability;
	private Long workOrderNo;
	private String remarks;
	

}
