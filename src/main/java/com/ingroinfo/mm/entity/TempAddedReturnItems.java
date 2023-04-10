package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "MM_TEMP_ADDED_RETURN_ITEMS")
public class TempAddedReturnItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long returnId;
	private String complNo;
	private String indentNo;
	private String workOrderNo;
	private int orderedQty;
	private int returnQty;
	private String stockType;
	private Long stockOrderNo;
	private String department;
	private String itemId;
	private String itemName;
}
