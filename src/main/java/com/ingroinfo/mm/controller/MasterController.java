package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/masters")
public class MasterController {
	
	
	@GetMapping
	public String MasterBase() {
		return "/pages/masters/masters";
	}
	
	@GetMapping("/notification-alert")
	public String NotificationAlert() {
		return "/pages/masters/notification_alert";
	}	
	
	

}
