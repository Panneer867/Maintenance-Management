package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/workorder")
public class WorkOrderController {
	
	@GetMapping("/generate")
	public String genrateWorkOrder() {
		return "/pages/work_orders/generate_work_order";
	}
	
	@GetMapping("/hold")
	public String holdWorkOrder() {
		return "/pages/work_orders/hold_work_order";
	}

	@GetMapping("/cancel")
	public String cancelWorkOrder() {
		return "/pages/work_orders/cancel_work_order";
	}

	@GetMapping("/budget-estimation")
	public String workEstimationBudget() {
		return "/pages/work_orders/work_estimation_budget";
	}

}
