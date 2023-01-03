package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskUpdateController {

	
	@GetMapping("/je")
	public String taskUpdateJE() {
		return "/pages/task_update/je_task";
	}
	
	@GetMapping("/aee")
	public String taskUpdateAEE() {
		return "/pages/task_update/aee_task";
	}
	
	@GetMapping("/ee")
	public String taskUpdateEE() {
		return "/pages/task_update/ee_task";
	}
	
	@GetMapping("/commissioner")
	public String taskUpdateCommissioner() {
		return "/pages/task_update/commissioner";
	}
	
	@GetMapping("/work-complete")
	public String workComplete() {
		return "/pages/task_update/work_complete";
	}
	
	@GetMapping("/job-card")
	public String JobCard() {
		return "/pages/task_update/job_card";
	}
	
	@GetMapping("/complaint-history")
	public String ComplaintHistory() {
		return "/pages/task_update/complaint_history";
	}

}
