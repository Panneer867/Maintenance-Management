package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/home")
	public String adminHome() {
		return "/pages/admin/home";
	}

	@GetMapping("/account/company")
	public String createCompany() {
		return "/pages/admin/create_company";
	}

	@GetMapping("/account/company/list")
	public String companyList() {
		return "/pages/admin/company_list";
	}

	@GetMapping("/account/branch")
	public String createBranch() {
		return "/pages/admin/create_branch";
	}

	@GetMapping("/account/branch/list")
	public String branchList() {
		return "/pages/admin/branch_list";
	}

	@GetMapping("/user")
	public String createUser() {
		return "/pages/admin/create_user";
	}

	@GetMapping("/user/list")
	public String userList() {
		return "/pages/admin/users_list";
	}

	@GetMapping("/user/role")
	public String userRoles() {
		return "/pages/admin/user_roles";
	}

	@GetMapping("/user/role/master")
	public String roleMaster() {
		return "/pages/admin/roles_master";
	}

	@GetMapping("/user/change-password")
	public String changePassword() {
		return "/pages/admin/change_password";
	}

	@GetMapping("/backup")
	public String backup() {
		return "/pages/admin/backup";
	}

	@GetMapping("/excel/import-export")
	public String excel() {
		return "/pages/admin/excel_import_export";
	}

	@GetMapping("/device/control")
	public String deviceControl() {
		return "/pages/admin/device_control";
	}

}
