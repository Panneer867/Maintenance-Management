package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@GetMapping("/dashboard")
	public String openDashboard(Model model) {
		model.addAttribute("title", "Vehicle Management | Dashboard | Manintenance Management");
		return "/pages/vehicle_management/dashboard";
	}
	
	@GetMapping("/management")
	@PreAuthorize("hasAuthority('VEHICLE_MANAGEMENT')")
	public String vehicleManagement() {
		return "/pages/vehicle_management/vehicle_management";
	}

	@GetMapping("/management/index")
	@PreAuthorize("hasAuthority('VEHICLE_MANAGEMENT_INDEX')")
	public String vehicleMangementIndex() {
		return "/pages/vehicle_management/vehicle_management_index";
	}
		
	@GetMapping("/history")
	@PreAuthorize("hasAuthority('VEHICLE_HISTORY')")
	public String vehicleHistory() {
		return "/pages/vehicle_management/vehicle_history";
	}
	
	@GetMapping("/inspection")
	@PreAuthorize("hasAuthority('VEHICLE_INSPECTION')")
	public String vehicleInspection() {
		return "/pages/vehicle_management/vehicle_inspection";
	}

	@GetMapping("/index")
	@PreAuthorize("hasAuthority('VEHICLE_INDEX')")
	public String vehicleIndex() {
		return "/pages/vehicle_management/vehicle_index";
	}

	@GetMapping("/work-order")
	@PreAuthorize("hasAuthority('VEHICLE_WORKORDER')")
	public String workOrderView() {
		return "/pages/vehicle_management/work_order_vehicle";
	}
}
