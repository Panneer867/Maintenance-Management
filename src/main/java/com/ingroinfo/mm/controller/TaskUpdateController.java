package com.ingroinfo.mm.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskUpdateController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	
	@GetMapping("/je")
	@PreAuthorize("hasAuthority('TASK_JE')")
	public String taskUpdateJE() {
		return "/pages/task_update/je_task";
	}
	
	@GetMapping("/aee")
	@PreAuthorize("hasAuthority('TASK_AEE')")
	public String taskUpdateAEE() {
		return "/pages/task_update/aee_task";
	}
	
	@GetMapping("/ee")
	@PreAuthorize("hasAuthority('TASK_EE')")
	public String taskUpdateEE() {
		return "/pages/task_update/ee_task";
	}
	
	@GetMapping("/commissioner")
	@PreAuthorize("hasAuthority('TASK_COMMISSIONER')")
	public String taskUpdateCommissioner() {
		return "/pages/task_update/commissioner";
	}
	
	@GetMapping("/work-complete")
	@PreAuthorize("hasAuthority('TASK_WORKCOMPLETE')")
	public String workComplete() {
		return "/pages/task_update/work_complete";
	}
	
	@GetMapping("/job-card")
	@PreAuthorize("hasAuthority('TASK_JOBCARD')")
	public String JobCard() {
		return "/pages/task_update/job_card";
	}
	
	@GetMapping("/complaint-history")
	@PreAuthorize("hasAuthority('TASK_COMPLAINTHISTORY')")
	public String ComplaintHistory() {
		return "/pages/task_update/complaint_history";
	}

}
