package com.ingroinfo.mm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="MM_ITEM_MASTER")
public class ItemMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemMasterId;
	private String itemId;
	private String category;
	private String hsnCode;
	private String itemName;
	private String stockType;
	private String description;
}
