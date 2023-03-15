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
@Table(name = "mm_temp_indent_items")
public class TempIndentItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordId;
	private int qty;
	private int finalQuantity;
	private int slNo;
	private String itemId;
	private String itemName;
	private String aliasName;
	private String itemImage;
	private String imagePath;
	private String category;
	private String unitOfMeasure;
	private Double totalCost;
	private Double mrpRate;
	private String description;
	private Long workOrderNo;
	private String stockType;
	private String username;

}
