package com.ingroinfo.mm.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name ="mm_contact_mangement")
public class ContactManagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
