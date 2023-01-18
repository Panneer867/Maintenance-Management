package com.ingroinfo.mm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesDto {
	
	private Long roleId;
	private String adminpage;
	private String companyManagement;
	private String createCompany;
	private String editCompany;
	private String viewCompany;
	private String deleteCompany;

}
