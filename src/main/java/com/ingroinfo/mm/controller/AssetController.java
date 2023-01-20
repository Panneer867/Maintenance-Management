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

import com.ingroinfo.mm.dto.AssestEntryDto;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AssestEntryService;


@Controller
@RequestMapping("/asset")
public class AssetController {
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	@Autowired
	AssestEntryService assestEntryReposiyory;
	
	@GetMapping("/entry")
	public String assetEntry() {
		return "pages/asset/asset_entry";
	}
	
	@PostMapping("/saveassestentry")
	public String saveAssestEntry(AssestEntryDto idAssestEntry,HttpSession session) {
		this.assestEntryReposiyory.saveAssestEntry(idAssestEntry);
		session.setAttribute("message", new Message("Data Saved Successfully....!!", "success"));
		return "redirect:/asset/entry";
		
	}
	

}
