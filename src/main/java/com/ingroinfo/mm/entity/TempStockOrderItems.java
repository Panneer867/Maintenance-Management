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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mm_temp_stockorder_items")
public class TempStockOrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordId;
	private int finalQuantity;	
	private int slNo;
	private String itemId;
	private Long orderId;
	private String itemName;
	private String aliasName;
	private String itemImage;
	private String imagePath;
	private String category;
	private String unitOfMeasure;
	private Double mrpRate;
	private Double totalCost;
	private String description;
	private Long stockOrderNo;
	private String stockType;
	private String username;
	
	private String indentNo;
	private String complNo;
	private String departmentName;
}
