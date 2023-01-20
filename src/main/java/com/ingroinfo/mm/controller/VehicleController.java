package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	@GetMapping("/management")
	public String vehicleManagement() {
		return "/pages/vehicle_management/vehicle_management";
	}

	@GetMapping("/management/index")
	public String vehicleMangementIndex() {
		return "/pages/vehicle_management/vehicle_management_index";
	}
		
	@GetMapping("/history")
	public String vehicleHistory() {
		return "/pages/vehicle_management/vehicle_history";
	}
	
	@GetMapping("/inspection")
	public String vehicleInspection() {
		return "/pages/vehicle_management/vehicle_inspection";
	}

	@GetMapping("/index")
	public String vehicleIndex() {
		return "/pages/vehicle_management/vehicle_index";
	}

	@GetMapping("/work-order")
	public String workOrderView() {
		return "/pages/vehicle_management/work_order_vehicle";
	}
}
