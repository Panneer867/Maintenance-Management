package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.ingroinfo.mm.dto.EmployeeLeaveDto;
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

	@GetMapping("/approval")
	@PreAuthorize("hasAuthority('APPROVALS')")
	public String hrApprovals(Model model) {
		String keyword = "NO";
		List<EmployeeLeaveDto> listOfEmployees = this.employeeMasterService.getEmployeeLeaveByHrApproval(keyword);
		model.addAttribute("listOfEmployees", listOfEmployees);		
		model.addAttribute("leaveData", new EmployeeLeaveDto());
		model.addAttribute("emp", new EmployeeLeaveDto());
		return "/pages/hr_approvals";
	}
	
	
}
