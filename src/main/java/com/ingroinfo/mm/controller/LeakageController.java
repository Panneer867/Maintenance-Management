package com.ingroinfo.mm.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.mm.dto.LeakageMainteUpdateDto;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.LeakageMaintenanceService;

@Controller
@RequestMapping("/leakage")
public class LeakageController {
	
	@Autowired
	LeakageMaintenanceService leakageMaintUpdateService;

	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "/pages/leakage/dashboard";
	}
	
	
	@GetMapping("/index")
	public String LeakageIntent() {
		return "/pages/leakage/leakage_intent";
	}
	
	@GetMapping("/maintenance")
	public String LeakageMaintenance(Model model) {
		model.addAttribute("title", "Leakage Maintenance Update| Manintenance Management");
		return "/pages/leakage/leakage_maintenance_update";
	}
	
	@GetMapping("/work")
	public String LeakageWork() {
		return "/pages/leakage/leakage_work";
	}
	
	@GetMapping("/maintenance-history")
	public String MaintenanceHistory() {
		return "/pages/leakage/leakage_maintenance_history";
	}
	
	@PostMapping("/saveleakageMainteUpdate")
	public String saveleakageMainteUpdate(LeakageMainteUpdateDto leakageMainteUpdate,HttpSession session,Model model) {
    this.leakageMaintUpdateService.saveMaintenanceType(leakageMainteUpdate);
	session.setAttribute("message", new Message("Data Saved Successfully....!!", "success"));
	model.addAttribute("title", "Leakage Maintenance Update| Manintenance Management");
	return "redirect:/leakage/maintenance";
	}

}
