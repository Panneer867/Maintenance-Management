package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor/leakage")
public class LeakageController {	
	
	@GetMapping("/intent")
	public String LeakageIntent() {
		return "/pages/leakage/leakage_intent";
	}
	
	@GetMapping("/maintanance")
	public String LeakageMaintanance() {
		return "/pages/leakage/leakage_maintanance";
	}
	
	@GetMapping("/work")
	public String LeakageWork() {
		return "/pages/leakage/leakage_work";
	}
	
	@GetMapping("/maintanance-history")
	public String MaintananceHistory() {
		return "/pages/leakage/leakage_maintanance_history";
	}

}
