package com.ingroinfo.mm.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="MM_TEMP_WORKORDER_ITEM_REQUEST")
public class TempWorkOrderItemRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemReqId;
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
	private String itemId;
	private String unitOfMesure;
	private String hsnCode;
	private String quantity;
	private String stockType;
	private String stockTypeName;

}
