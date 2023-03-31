package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor/borewell")
public class BorewellController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		return "/pages/borewell/dashboard";
	}

	@GetMapping("/index")
	@PreAuthorize("hasAuthority('BOREWELL_INDEX')")
	public String borewellIndex(Model model) {
		return "/pages/borewell/borewell_index";
	}

	@GetMapping("/indent")
	@PreAuthorize("hasAuthority('BOREWELL_INDENT')")
	public String borewellIndent(Model model) {
		return "/pages/borewell/borewell_indent";
	}

	@GetMapping("/work-order")
	@PreAuthorize("hasAuthority('BOREWELL_WORKORDER')")
	public String borewellWorkOrder(Model model) {
		return "/pages/borewell/borewell_work_order";
	}

	@GetMapping("/work-update")
	@PreAuthorize("hasAuthority('BOREWELL_WORKUPDATE')")
	public String borewellWorkUpdate(Model model) {
		return "/pages/borewell/borewell_work_update";
	}

	@GetMapping("/inspection")
	@PreAuthorize("hasAuthority('BOREWELL_INSPECTION')")
	public String borewellInspection(Model model) {
		return "/pages/borewell/borewell_inspection";
	}

	@GetMapping("/history")
	@PreAuthorize("hasAuthority('BOREWELL_HISTORY')")
	public String borewellHistory(Model model) {
		return "/pages/borewell/borewell_history";
	}

}
