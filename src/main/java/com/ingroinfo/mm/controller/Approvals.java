package com.ingroinfo.mm.controller;

import java.security.Principal;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/approvals")
public class Approvals {
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("/stocks")
	public String stockApprovals(Model model) {
		model.addAttribute("title", "Stock Approvals | Maintenance Mangement");
		return "/pages/stock_management/stock_approvals";
	}
	
	@GetMapping("/stocks/material")
	public String approveMaterials(Model model) {
		model.addAttribute("title", "Materials Approval | Maintenance Mangement");
		return "/pages/stock_management/materials_approval";
	}
}
