package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/leakage")
public class LeakageController {
	
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "/pages/leakage/dashboard";
	}
	
	
	@GetMapping("/intent")
	public String LeakageIntent() {
		return "/pages/leakage/leakage_intent";
	}
	
	@GetMapping("/maintenance")
	public String LeakageMaintenance() {
		return "/pages/leakage/leakage_maintenance";
	}
	
	@GetMapping("/work")
	public String LeakageWork() {
		return "/pages/leakage/leakage_work";
	}
	
	@GetMapping("/maintenance-history")
	public String MaintenanceHistory() {
		return "/pages/leakage/leakage_maintenance_history";
	}

}
