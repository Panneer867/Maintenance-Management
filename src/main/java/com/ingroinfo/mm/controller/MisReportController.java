package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mis")
public class MisReportController {
	
	@GetMapping("/daily")
	public String MisDaily() {
		return "/pages/mis_report/daily";
	}
	
	@GetMapping("/monthly")
	public String MisMonthly() {
		return "/pages/mis_report/monthly";
	}

}
