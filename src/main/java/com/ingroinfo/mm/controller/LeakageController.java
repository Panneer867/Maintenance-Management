package com.ingroinfo.mm.controller;

import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAuthority('LEAKAGE_DASHBOARD')")
	public String dashboard(Model model) {
		model.addAttribute("title", "Leakage Maintenance Dashboard| Manintenance Management");
		return "/pages/leakage/dashboard";
	}

	@GetMapping("/index")
	public String LeakageIndex(Model model) {
		model.addAttribute("title", "Leakage Maintenance Index | Manintenance Management");
		return "/pages/leakage/leakage_index";
	}
	
	@GetMapping("/indent")
	public String LeakageIndent(Model model) {
		model.addAttribute("title", "Leakage Maintenance Indent | Manintenance Management");
		return "/pages/leakage/leakage_indent";
	}

	@GetMapping("/view")
	public String LeakageView(Model model) {
		model.addAttribute("title", "Leakage Maintenance View | Manintenance Management");
		return "/pages/leakage/leakage_view";
	}
	
	@GetMapping("/update")
	public String LeakageUpdate(Model model) {
		model.addAttribute("title", "Leakage Maintenance Update | Manintenance Management");
		return "/pages/leakage/leakage_update";
	}
	
	@GetMapping("/inspection")
	public String LeakageInspection(Model model) {
		model.addAttribute("title", "Leakage Maintenance Inspection | Manintenance Management");
		return "/pages/leakage/leakage_inspection";
	}

	@GetMapping("/history")
	public String MaintenanceHistory(Model model) {
		model.addAttribute("title", "Leakage Maintenance History| Manintenance Management");
		return "/pages/leakage/leakage_history";
	}


}
