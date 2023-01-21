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
	
	private String branchManagement;
	private String createBranch;
	private String editBranch;
	private String viewBranch;
	private String deleteBranch;
	
	private String userManagement;
	private String createUser;
	private String editUser;
	private String deleteUser;
	
	private String roleManagement;
	private String createRole;
	private String editRole;
	private String deleteRole;
	
	private String userRoles;
	private String updateUserRoles;
	
	private String taskJe;
	private String taskAee;
	private String taskEe;
	private String taskCommissioner;
	private String taskWorkcomplete;
	private String taskJobcard;
	private String taskComplainthistory;

}
