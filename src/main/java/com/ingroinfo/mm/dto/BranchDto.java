package com.ingroinfo.mm.dto;

import java.util.Date;
import lombok.Data;

@Data
public class BranchDto {

	private Long branchId;
	private String branchName;
	private Long companyId;
	private String username;
	private String password;
	private String email;
	private String address;
	private String state;
	private String city;
	private String pincode;
	private String mobile;
	private String remarks;
	private Date dateCreated;
	private Date lastUpdated;

}
