package com.ingroinfo.mm.dto;

import java.util.Date;
import lombok.Data;

@Data
public class UserDto {

	private Long userId;
	private Long companyId;
	private Long branchId;
	private Long roleId;
	private String name;
	private String username;
	private String password;
	private String email;
	private String mobile;	
	private String remarks;
	private String designation;
	private String userType;
	private String userRole;
	private Date dateCreated;
	private Date lastUpdated;

}
