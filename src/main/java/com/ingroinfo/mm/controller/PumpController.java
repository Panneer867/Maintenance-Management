package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor/pump")
public class PumpController {	
	
	@GetMapping("/master")
	public String pumpMaster() {
		return "/pages/pump_house/pump_master";
	}
	
	@GetMapping("/maintanance-intent")
	public String pumpMaintananceIntent() {
		return "/pages/pump_house/pump_maintanance_intent";
	}
	
	@GetMapping("/maintanance-work")
	public String pumpMaintananceWork() {
		return "/pages/pump_house/pump_maintanace_work";
	}
	
	@GetMapping("/maintanance-inspection")
	public String pumpMaintananceInspection() {
		return "/pages/pump_house/pump_maintanace_inspection";
	}
	
	@GetMapping("/maintanance-history")
	public String pumpMaintananceHistory() {
		return "/pages/pump_house/pump_maintanace_history";
	}

}
