package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mis")
public class MisReportController {
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	@GetMapping("/daily")
	@PreAuthorize("hasAuthority('MIS_DAILY_REPORT')")
	public String MisDaily() {
		return "/pages/mis_report/daily";
	}
	
	@GetMapping("/monthly")
	@PreAuthorize("hasAuthority('MIS_MONTHLY_REPORT')")
	public String MisMonthly() {
		return "/pages/mis_report/monthly";
	}

}
