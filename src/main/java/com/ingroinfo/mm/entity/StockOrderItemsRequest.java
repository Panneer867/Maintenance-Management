package com.ingroinfo.mm.entity;

import java.util.Date;

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
@Table(name = "mm_stockorder_items_request")
public class StockOrderItemsRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordId;
	private Long stockOrderNo;
	private String itemId;
	private String stockType;
	private int quantity;
	private String username;
	private String indentNo;
	private String complNo;
	private String division;
	private String subDivision;
	private String workSite;
	private Date startDate;
	private Date endDate;
	private String contactNo;
	private String complDtls;
	private String workPriority;
	private String categoryName;
	private String itemName;
	private String unitOfMesure;
	private String hsnCode;
	private String departmentName;
	private String stockTypeName;
	private String workOrderNo;

}
