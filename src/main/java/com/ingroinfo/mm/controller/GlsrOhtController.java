package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor/glsr-oht")
public class GlsrOhtController {
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
		@GetMapping("/index")
		public String borewellIndex(Model model) {
			return "/pages/glsr_oht/glsr_oht_index";
		}
		
		
		@GetMapping("/indent")
		public String borewellIndent(Model model) {
			return "/pages/glsr_oht/glsr_oht_indent";
		}
		
		@GetMapping("/work-order")
		public String borewellWorkOrder(Model model) {
			return "/pages/glsr_oht/glsr_oht_work_order";
		}
		
		@GetMapping("/work-update")
		public String borewellWorkUpdate(Model model) {
			return "/pages/glsr_oht/glsr_oht_work_update";
		}
		
		@GetMapping("/inspection")
		public String borewellInspection(Model model) {
			return "/pages/glsr_oht/glsr_oht_inspection";
		}
		
		@GetMapping("/history")
		public String borewellHistory(Model model) {
			return "/pages/glsr_oht/glsr_oht_history";
		}

}