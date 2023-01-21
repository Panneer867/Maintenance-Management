package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.ingroinfo.mm.dto.EmployeeMasterDto;
import com.ingroinfo.mm.service.EmployeeMasterService;

@Controller
public class HomeController {
	
	@Autowired
	private EmployeeMasterService employeeMasterService;
	
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
	public String hrApprovals(Model model) {
		List<EmployeeMasterDto> listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);
		return "/pages/hr_approvals";
	}
}
