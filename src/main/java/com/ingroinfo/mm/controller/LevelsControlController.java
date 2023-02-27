package com.ingroinfo.mm.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.dto.LevelControlDto;
import com.ingroinfo.mm.entity.LevelControls;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.LevelContolsService;

@Controller
@RequestMapping("/monitor/levels-control")
public class LevelsControlController {
	
	private static final ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private LevelContolsService levelContolsService;
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
		@GetMapping("/index")
		@PreAuthorize("hasAuthority('LEVELS_CONTROL_INDEX')")
		public String borewellIndex(Model model) {
			model.addAttribute("index", new LevelControlDto());
			return "/pages/levels_control/levels_control_index";
		}
		
		@PostMapping("/index/add")
		public String updateUserRoles(@ModelAttribute("index") LevelControlDto dto, HttpSession session) {

			LevelControls levelControls = modelMapper.map(dto, LevelControls.class);
			levelContolsService.saveLevelControls(levelControls);
			session.setAttribute("message", new Message("Level Controls Index has beed successfully saved", "success"));
			return "redirect:/monitor/levels-control/index";
		}
		
		
		@GetMapping("/indent")
		@PreAuthorize("hasAuthority('LEVELS_CONTROL_INDENT')")
		public String borewellIndent(Model model) {
			return "/pages/levels_control/levels_control_indent";
		}
		
		@GetMapping("/work-order")
		@PreAuthorize("hasAuthority('LEVELS_CONTROL_WORKORDER')")
		public String borewellWorkOrder(Model model) {
			return "/pages/levels_control/levels_control_work_order";
		}
		
		@GetMapping("/work-update")
		@PreAuthorize("hasAuthority('LEVELS_CONTROL_WORKUPDATE')")
		public String borewellWorkUpdate(Model model) {
			return "/pages/levels_control/levels_control_work_update";
		}
		
		@GetMapping("/inspection")
		@PreAuthorize("hasAuthority('LEVELS_CONTROL_INSPECTION')")
		public String borewellInspection(Model model) {
			return "/pages/levels_control/levels_control_inspection";
		}
		
		@GetMapping("/history")
		@PreAuthorize("hasAuthority('LEVELS_CONTROL_HISTORY')")
		public String borewellHistory(Model model) {
			return "/pages/levels_control/levels_control_history";
		}

}
