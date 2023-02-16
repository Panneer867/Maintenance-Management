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

import com.ingroinfo.mm.dto.HandPumpDto;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.HandPumpService;

@Controller
@RequestMapping("/monitor/handpump")
public class HandpumpController {
	
	@Autowired
	HandPumpService handPumpService;
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
		@GetMapping("/index")
		public String borewellIndex(Model model) {
			return "/pages/handpump/handpump_index";
		}

		
		@GetMapping("/indent")
		public String borewellIndent(Model model) {
			return "/pages/handpump/handpump_indent";
		}
		
		@GetMapping("/work-order")
		public String borewellWorkOrder(Model model) {
			return "/pages/handpump/handpump_work_order";
		}
		
		@GetMapping("/work-update")
		public String borewellWorkUpdate(Model model) {
			return "/pages/handpump/handpump_work_update";
		}
		
		@GetMapping("/inspection")
		public String borewellInspection(Model model) {
			return "/pages/handpump/handpump_inspection";
		}
		
		@GetMapping("/history")
		public String borewellHistory(Model model) {
			return "/pages/handpump/handpump_history";
		}
		
		@PostMapping("/savehandpump")
		public String saveHandPump(HandPumpDto handPumpDto,HttpSession session) {
			this.handPumpService.saveHandPump(handPumpDto);
			session.setAttribute("message", new Message("Data Saved Successfully....!!", "success"));
			return "redirect:/handpump/index";
		}

}
