package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String Home() {
		return "/dashboard";
	}	
	
	@GetMapping("/contact-management")
	public String contactMangement() {
		return "/pages/contact_management";
	}
	
	@GetMapping("/approval")
	public String hrApprovals() {
		return "/pages/employee_management/hr_approvals";
	}

}
