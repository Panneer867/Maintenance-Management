package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.mm.dto.PumpMaintenanceDto;
import com.ingroinfo.mm.dto.PumpMasterDto;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.PumpMaintenanceService;
import com.ingroinfo.mm.service.PumpMasterService;

@Controller
@RequestMapping("/pump")
public class PumpController {	
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	@Autowired
	private PumpMasterService pumpMasterService; 	
	@Autowired
	private PumpMaintenanceService pumpMaintenService;
	
	//Handler For Open dashBoard
	@GetMapping("/dashboard")
	@PreAuthorize("hasAuthority('PUMP_DASHBOARD')")
	public String openDashboard(Model model) {
		model.addAttribute("title", "Pump | Dashboard | Manintenance Management");
		return "/pages/pump_house/pump-dashboard";
	}
	
	//Handler For Open Maintenance Page
	@GetMapping("/maintenance")
	@PreAuthorize("hasAuthority('PUMP_MAINTENANCE')")
	public String pumpMaster(Model model) {
		model.addAttribute("title", "Pump | Maintenance | Manintenance Management");
		model.addAttribute("pumps", new PumpMasterDto());
		return "/pages/pump_house/pump_maintenance";
	}
	
	//Handler For get Pipe Master Data
	@GetMapping("/getPumpMaster")
	public String getPumpDataFromMaster(String pumpId,Model model,HttpSession session) {
		try {
			PumpMasterDto pumpMasterDto = this.pumpMasterService.getPumpDataByPumpId(pumpId);
			model.addAttribute("pumps", pumpMasterDto); 
		} catch (Exception e) {
			System.out.println("Exception :: "+e.getMessage());
			session.setAttribute("message", new Message("Pump Id Is Not Present !!","danger"));
			return "redirect:/pump/maintenance";
		}
		model.addAttribute("title", "Pump | Maintenance | Manintenance Management");
		return "/pages/pump_house/pump_maintenance";
	}
	
	//Handler For Saving Pump Maintenance Data
	@PostMapping("/savepump-maintenance")
	public String savePumpMaintenance(PumpMaintenanceDto pumpMaintenDto,HttpSession session) {
		pumpMaintenDto.setPumpMaintenSts("INDENT");
		this.pumpMaintenService.savePumpMaintenance(pumpMaintenDto);
		session.setAttribute("message", new Message("Data Sucessfully Saved !!","success"));
		return "redirect:/pump/maintenance";
	}
	
	//handler For Open Pump Maintenance Indent Page
	@GetMapping("/maintenance-intent")
	@PreAuthorize("hasAuthority('PUMP_INDENT')")
	public String pumpMaintenanceIntent(Model model) {		
		List<PumpMaintenanceDto> listOfPumpMainens = this.pumpMaintenService.getAllMaintenance();
		model.addAttribute("listOfPumpMainens", listOfPumpMainens);
		model.addAttribute("pumpMaintenData", new PumpMaintenanceDto());
		model.addAttribute("title", "Pump | Maintenance Indent | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_intent";
	}
	
	//Handler For Get Pump Maintenance Data By Pump Maintenance Id
	@GetMapping("/getPumpdata/{pumpMaintenId}")
	public String getPumpMaintenDataByPumpMaintenId(@PathVariable Long pumpMaintenId,Model model) {
		List<PumpMaintenanceDto> listOfPumpMainens = this.pumpMaintenService.getAllMaintenance();
		model.addAttribute("listOfPumpMainens", listOfPumpMainens);
		PumpMaintenanceDto pumpMaintenanceDto = this.pumpMaintenService.getPumpMaintenByPumpMaintenId(pumpMaintenId);
		model.addAttribute("pumpMaintenData", pumpMaintenanceDto);
		model.addAttribute("title", "Pump | Maintenance Indent | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_intent";
	}
	
	@GetMapping("/maintenance-work")
	public String pumpMaintenanceWork() {
		return "/pages/pump_house/pump_maintanace_work";
	}
	
	@GetMapping("/maintenance-inspection")
	public String pumpMaintenanceInspection() {
		return "/pages/pump_house/pump_maintanace_inspection";
	}
	
	@GetMapping("/maintenance-history")
	public String pumpMaintenanceHistory() {
		return "/pages/pump_house/pump_maintanace_history";
	}

}
