package com.ingroinfo.mm.controller;

import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workorder")
public class WorkOrderController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("dashboard")
	public String dashboard() {
		return "/pages/work_orders/dashboard";
	}

	@GetMapping("/generate")
	@PreAuthorize("hasAuthority('GENERATE_WORKORDER')")
	public String genrateWorkOrder(Model model) {
		model.addAttribute("title", "Work Order | Generate Work Order");
		return "/pages/work_orders/generate_work_order";
	}

	@GetMapping("/hold")
	@PreAuthorize("hasAuthority('HOLD_WORKORDER')")
	public String holdWorkOrder(Model model) {
		return "/pages/work_orders/hold_work_order";
	}

	@GetMapping("/cancel")
	@PreAuthorize("hasAuthority('CANCEL_WORKORDER')")
	public String cancelWorkOrder(Model model) {
		return "/pages/work_orders/cancel_work_order";
	}

}
