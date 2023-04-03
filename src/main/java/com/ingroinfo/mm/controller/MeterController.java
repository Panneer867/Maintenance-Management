package com.ingroinfo.mm.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.entity.MeterReplace;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.MeterService;

@Controller
@RequestMapping("/monitor/meter")
public class MeterController {

	@Autowired
	private MeterService meterService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "/pages/meter_maintenance/dashboard";
	}

	@GetMapping("/index")
	public String index() {
		return "/pages/meter_maintenance/meter_index";
	}

	@GetMapping("/replacement")
	public String replacement(Model model) {
		model.addAttribute("meter", new MeterReplace());
		model.addAttribute("title", "Meter Replacement | Maintenance Management");
		return "/pages/meter_maintenance/meter_replacement";
	}

	@PostMapping("/replacement")
	public String saveReplace(@ModelAttribute("meter") MeterReplace meterReplace, Model model, HttpSession session) {

		meterService.save(meterReplace);
		session.setAttribute("message", new Message("Meter Details has been successfully added !", "success"));
		model.addAttribute("title", "");
		return "redirect:/monitor/meter/replacement";
	}

	@GetMapping("/history")
	public String history() {
		return "/pages/meter_maintenance/meter_history";
	}

}
