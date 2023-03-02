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
@Table(name = "mm_workorder_items_request")
public class WorkOrderItemsRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stocksId;
	private Long itemId;
	private Long workOrderNo;
	private String stockType;
	private int quantity;
}
