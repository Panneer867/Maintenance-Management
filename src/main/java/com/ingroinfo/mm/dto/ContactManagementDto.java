package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class ContactManagementDto {
	
	private Long recordId;
	private String contactId;
	private String division;
	private String subDivision;
	private String department;
	private String designation;
	private String name;
	private String mobileNo;
	private String email;
	private String empStatus;
	private String image;

}
