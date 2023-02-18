package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.mm.dto.PipeIndexDto;
import com.ingroinfo.mm.dto.PipeMaintenanceDto;
import com.ingroinfo.mm.dto.PipeMaintenanceInspectionDto;
import com.ingroinfo.mm.dto.PipeMaintenanceUpdateDto;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.PipeMaintenanceService;

@Controller
@RequestMapping("/pipe")
public class PipeController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	public static String UploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Autowired
	PipeMaintenanceService pipeMaintenanceService;

	@GetMapping("/dashboard")
	public String pipeDashboard() {
		return "/pages/pipe_management/pipe_dashboard";
	}

	@GetMapping("/pipe-index")
	public String pipeIndex() {
		return "/pages/pipe_management/pipe_index";
	}
	
	@GetMapping("/pipe-viewwork")
	public String pipeViewWork() {
		return "/pages/pipe_management/pipe_viewwork";
	}

	@GetMapping("/maintenance-indent")
	public String maintenanceIndent() {
		return "/pages/pipe_management/pipe_maintenance_indent";
	}

	@GetMapping("/maintenance-work-update")
	public String pipeMaintenanceWorkUpdate(Model model) {
		List<PipeMaintenanceDto> listofPipe = this.pipeMaintenanceService.findAllPipeMaintenance();
		model.addAttribute("listofpipe", listofPipe);
		model.addAttribute("pipeMaintenance", new PipeMaintenanceDto());
		return "/pages/pipe_management/pipe_maintenance_work_update";
	}

	@GetMapping("/maintenance-inspection")
	public String maintenanceInspection(Model model) {
		List<PipeMaintenanceUpdateDto> listofPipeMaintenUpdateDtos = this.pipeMaintenanceService
				.findAllPipeMaintenanceUpdate();
		model.addAttribute("listofPipeMaintenUpdate", listofPipeMaintenUpdateDtos);
		model.addAttribute("pipeMaintenanceUpdateDto", new PipeMaintenanceUpdateDto());
		return "/pages/pipe_management/pipe_maintenance_inspection";
	}

	@GetMapping("/maintenance-history")
	public String maintenanceHistory() {
		return "/pages/pipe_management/pipe_maintenance_history";
	}

	// save Pipe Maintenance data
	@PostMapping("/savepipemaintenance")
	public String savePipeMaintenance(PipeMaintenanceDto pipeMaintenance, HttpSession session) {
		this.pipeMaintenanceService.savePipeMaintenance(pipeMaintenance);
		return "redirect:/pipe/maintenance-indent";
	}

	
	//Save Pipe Index
	@PostMapping("/savepipe-index")
	public String savePipeIndex(PipeIndexDto pipeIndex,HttpSession session) {
		this.pipeMaintenanceService.savePipeIndex(pipeIndex);
		session.setAttribute("message", new Message("Data Sucessfully Saved !!","success"));
		return "redirect:/pipe/pipe-index";
	}
	
	// save Pipe Maintenance Update
	@PostMapping("/savepipemaintenanceupdate")
	public String savePipeMaintenanceUpdate(PipeMaintenanceUpdateDto pipeMaintenanceUpdateDto, HttpSession session) {
		this.pipeMaintenanceService.savePipeMaintenanceUpdate(pipeMaintenanceUpdateDto);
		return "redirect:/pipe/maintenance-work-update";
	}

	// save Pipe Maintenance Inspection
	@PostMapping("/savepipemaintenanceinspection")
	public String savePipeMaintenanceInspection(PipeMaintenanceInspectionDto pipeMaintenanceInspectionDto,
			HttpSession session) {
		this.pipeMaintenanceService.savePipeMaintenanceInspection(pipeMaintenanceInspectionDto);
		return "redirect:/pipe/maintenance-inspection";
	}

	// show the data by pipeMaintenance Id in pipeMaintenanceUpdate Page
	@GetMapping("/ViewPipeMaintenance/{pipeId}")
	public String getPipeMaintenanceById(@PathVariable("pipeId") Long pipeId, Model model) {
		PipeMaintenanceDto pipeMaintenanceDto = this.pipeMaintenanceService.getPipeMaintenanceById(pipeId);
		model.addAttribute("pipeMaintenance", pipeMaintenanceDto);
		List<PipeMaintenanceDto> listofPipe = this.pipeMaintenanceService.findAllPipeMaintenance();
		model.addAttribute("listofpipe", listofPipe);
		return "/pages/pipe_management/pipe_maintenance_work_update";
	}

	@GetMapping("/viewPipeMaintenanceUpdate/{pipeUpdateId}")
	public String getPipeMaintenanceUpdate(@PathVariable("pipeUpdateId") Long pipeUpdateId, Model model) {
		PipeMaintenanceUpdateDto pipeMaintenanceUpdateDto = this.pipeMaintenanceService
				.getPipeMaintenanceUpdateById(pipeUpdateId);
		model.addAttribute("pipeMaintenanceUpdateDto", pipeMaintenanceUpdateDto);
		List<PipeMaintenanceUpdateDto> listofPipeMaintenUpdateDtos = this.pipeMaintenanceService
				.findAllPipeMaintenanceUpdate();
		model.addAttribute("listofPipeMaintenUpdate", listofPipeMaintenUpdateDtos);
		return "/pages/pipe_management/pipe_maintenance_inspection";
	}

}
