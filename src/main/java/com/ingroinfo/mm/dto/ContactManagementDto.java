package com.ingroinfo.mm.dto;

import lombok.Data;

@Data
public class ContactManagementDto {
	
	private Long contId;
	private String contactId;
	private String division;
	private String subDivision;
	private String dept;
	private String desig;
	private String name;
	private String mobileNo;
	private String emailId;
	private String empStatus;
	private byte[] imageUpload;

}
