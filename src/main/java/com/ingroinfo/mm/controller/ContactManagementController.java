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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.mm.dto.ContactManagementDto;
import com.ingroinfo.mm.service.ContactManagementService;


@Controller
@RequestMapping("/contact")
public class ContactManagementController {
	
	@Autowired
	ContactManagementService contactMangtService;
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	
	@GetMapping("/management")
	@PreAuthorize("hasAuthority('CONTACT_MANAGEMENT')")
	public String contactMangement(Model model) {	
		model.addAttribute("show", null);
		return "/pages/contact_management";
	}
	
	@PostMapping("/save")
	public String saveContactMangement(ContactManagementDto contactMangement, HttpSession session) {
		this.contactMangtService.saveContactMangement(contactMangement);
		return "redirect:/contact/management";
	}


	@GetMapping("/view")
	public String viewContact(Model model) {
		List<ContactManagementDto> listofcontact= this.contactMangtService.findAllContactManagement();
		model.addAttribute("contactMang", listofcontact);
		model.addAttribute("show", "show");
			return "/pages/contact_management";
	}
}
