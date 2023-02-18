package com.ingroinfo.mm.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name ="MM_Contact_Mangement")
public class ContactManagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
