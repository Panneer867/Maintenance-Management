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
@Table(name = "MM_TEMP_ADDED_INDENT_ITEMS")
public class TempAddedIndentMaterials {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	private int quantity;
	private String stockType;
	private String stockTypeName;
	private String departmentName;
}
