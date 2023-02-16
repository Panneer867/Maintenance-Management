package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor/levels-control")
public class LevelsControlController {
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
		@GetMapping("/index")
		public String borewellIndex(Model model) {
			return "/pages/levels_control/levels_control_index";
		}
		
		
		@GetMapping("/indent")
		public String borewellIndent(Model model) {
			return "/pages/levels_control/levels_control_indent";
		}
		
		@GetMapping("/work-order")
		public String borewellWorkOrder(Model model) {
			return "/pages/levels_control/levels_control_work_order";
		}
		
		@GetMapping("/work-update")
		public String borewellWorkUpdate(Model model) {
			return "/pages/levels_control/levels_control_work_update";
		}
		
		@GetMapping("/inspection")
		public String borewellInspection(Model model) {
			return "/pages/levels_control/levels_control_inspection";
		}
		
		@GetMapping("/history")
		public String borewellHistory(Model model) {
			return "/pages/levels_control/levels_control_history";
		}

}
