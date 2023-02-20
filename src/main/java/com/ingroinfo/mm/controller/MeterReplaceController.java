package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor/meter")
public class MeterReplaceController {
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
		@GetMapping("/replacement")
		@PreAuthorize("hasAuthority('METER_REPLACEMENT')")
		public String borewellIndex(Model model) {
			return "/pages/meter_maintenance/meter_replacement";
		}

}
