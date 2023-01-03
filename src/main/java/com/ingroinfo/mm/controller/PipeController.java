package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor/pipe")
public class PipeController {

	@GetMapping("/dashboard")
	public String pipeDashboard() {
		return "/pages/pipe_management/pipe_dashboard";
	}
	
	@GetMapping("/maintanance-indent")
	public String maintananceIndent() {
		return "/pages/pipe_management/pipe_maintanance_indent";
	}
	
	@GetMapping("/maintanance-work-update")
	public String pipeMaintananceWorkUpdate() {
		return "/pages/pipe_management/pipe_maintanance_work_update";
	}
	
	@GetMapping("/maintanance-inspection")
	public String maintananceInspection() {
		return "/pages/pipe_management/pipe_maintanance_inspection";
	}
	
	@GetMapping("/maintanance-history")
	public String maintananceHistory() {
		return "/pages/pipe_management/pipe_maintanance_history";
	}
}
