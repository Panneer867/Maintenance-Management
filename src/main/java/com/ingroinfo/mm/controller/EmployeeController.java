package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@GetMapping("/master")
	public String employeeMaster() {
		return "/pages/employee_management/employee_master";
	}
	
	@GetMapping("/inspection")
	public String employeeInspection () {
		return "/pages/employee_management/employee_Inspection";
	}
	
	@GetMapping("/dashboard")
	public String employeeDashboard () {
		return "/pages/employee_management/employee_dashboard";
	}
	
	@GetMapping("/salary-generate")
	public String salaryGenerate () {
		return "/pages/employee_management/salary_generate";
	}
	
	
	@GetMapping("/leave")
	public String leave () {
		return "/pages/employee_management/leave";
	}
	
	@GetMapping("/approvals")
	public String approvals () {
		return "/pages/employee_management/hr_approvals";
	}
	
	@GetMapping("/attendenceTracker")
	public String attendenceTracker () {
		return "/pages/employee_management/attendence_tracker";
	}

}
