package com.ingroinfo.mm.dto;

import java.util.Date;
import lombok.Data;

@Data
public class CompanyDto {

	private Long companyId;
	private String companyName;
	private String username;
	private String password;
	private String email;
	private String address;
	private String state;
	private String city;
	private String pincode;
	private String mobile;
	private String website;
	private String fax;
	private String enableApp;
	private String path;
	private String logo;
	private String noOfBranch;
	private Date dateCreated;
	private Date lastUpdated;

}
