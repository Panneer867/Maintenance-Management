package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
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
		@PreAuthorize("hasAuthority('GLSR_OHT_INDEX')")
		public String borewellIndex(Model model) {
			return "/pages/glsr_oht/glsr_oht_index";
		}
		
		
		@GetMapping("/indent")
		@PreAuthorize("hasAuthority('GLSR_OHT_INDENT')")
		public String borewellIndent(Model model) {
			return "/pages/glsr_oht/glsr_oht_indent";
		}
		
		@GetMapping("/work-order")
		@PreAuthorize("hasAuthority('GLSR_OHT_WORKORDER')")
		public String borewellWorkOrder(Model model) {
			return "/pages/glsr_oht/glsr_oht_work_order";
		}
		
		@GetMapping("/work-update")
		@PreAuthorize("hasAuthority('GLSR_OHT_WORKUPDATE')")
		public String borewellWorkUpdate(Model model) {
			return "/pages/glsr_oht/glsr_oht_work_update";
		}
		
		@GetMapping("/inspection")
		@PreAuthorize("hasAuthority('GLSR_OHT_INSPECTION')")
		public String borewellInspection(Model model) {
			return "/pages/glsr_oht/glsr_oht_inspection";
		}
		
		@GetMapping("/history")
		@PreAuthorize("hasAuthority('GLSR_OHT_HISTORY')")
		public String borewellHistory(Model model) {
			return "/pages/glsr_oht/glsr_oht_history";
		}

}
