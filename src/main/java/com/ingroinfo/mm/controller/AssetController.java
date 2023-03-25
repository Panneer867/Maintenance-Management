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
import com.ingroinfo.mm.dto.AssestEntryDto;
import com.ingroinfo.mm.entity.AssestEntry;
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
	AssestEntryService assestEntryService;
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "/pages/asset/dashboard";
	}
	
	@GetMapping("/entry")
	@PreAuthorize("hasAuthority('ASSET_MANAGEMENT')")
	public String assetEntry(Model model) {
		List<AssestEntry> listofassest = this.assestEntryService.findAllAssestEntry();
		model.addAttribute("assestlist",listofassest);
		model.addAttribute("assestData", new AssestEntryDto());
		return "pages/asset/asset_entry";
	}
	
	@GetMapping("/viewAsset")
	public String viewAssetEntry() {
		return "pages/asset/view_asset_entry";
	}
	
	@PostMapping("/saveassestentry")
	public String saveAssestEntry(AssestEntryDto idAssestEntry,HttpSession session) {
		this.assestEntryService.saveAssestEntry(idAssestEntry);
		session.setAttribute("message", new Message("Data Saved Successfully....!!", "success"));
		return "redirect:/asset/entry";
		
	}
	
	
	// To Get single Asset Id Data
	@GetMapping("/viewasset/{assestEntryId}")
	public String getAssetEntryById(@PathVariable("assestEntryId") Long assestEntryId, Model model) {
		AssestEntryDto assestEntryDto = this.assestEntryService.getAssestEntryById(assestEntryId);
		model.addAttribute("assestData", assestEntryDto);
		return "/pages/asset/view_asset_entry";
	}
	

}
