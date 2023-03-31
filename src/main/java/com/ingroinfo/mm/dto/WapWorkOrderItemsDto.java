package com.ingroinfo.mm.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class WapWorkOrderItemsDto {

	private Long itemReqId;
	private String indentNo;
	private String complNo;
	private Long stockOrderNo;
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
	private String indentApproved;
	private String approvedSts;
	private String userName;
	private Date createdDate;
}
