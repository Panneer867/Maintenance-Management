package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/billing")
public class BillingController {
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	//Handler For Opening Consumer Details Page
	@GetMapping("/consumer-master")
	public String openConsumerMasterPage(Model model) {
		return "/pages/billing/consumer_master";
	}
	
	//Handler For Opening Consumer Transactions Details Page
	@GetMapping("/transactions")
	public String openTransactionPage(Model model) {
		return "/pages/billing/consumer_transactions";
	}
	
	//Handler For Opening Device Details Page
	@GetMapping("/meter-details")
	public String openMeterDetailsPage(Model model) {
		return "/pages/billing/meter_details";
	}

}
