package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
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
