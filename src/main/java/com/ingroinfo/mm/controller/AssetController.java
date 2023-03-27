package com.ingroinfo.mm.controller;

import java.security.Principal;
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
import com.ingroinfo.mm.dto.AssetEntryDto;
import com.ingroinfo.mm.dto.IdMasterDto;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AssetService;
import com.ingroinfo.mm.service.MasterService;

@Controller
@RequestMapping("/asset")
public class AssetController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@Autowired
	private MasterService masterService;

	@Autowired
	AssetService assetService;

	@GetMapping("/dashboard")
	public String dashboard() {
		return "/pages/asset/dashboard";
	}

	@GetMapping("/entry")
	@PreAuthorize("hasAuthority('ASSET_MANAGEMENT')")
	public String assetEntry(Model model) {
		model.addAttribute("asset", new AssetEntryDto());

		String masterIdName = "Asset Id";

		IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(masterIdName);
		String lastNo = idMasterDto.getLastNumber();
		model.addAttribute("maxAssetId", lastNo);

		return "pages/asset/asset_entry";
	}

	@PostMapping("/entry")
	public String saveAsset(@ModelAttribute("asset") AssetEntryDto assetEntryDto, HttpSession session) {
		this.assetService.saveAsset(assetEntryDto);

		String masterIdName = "Asset Id";

		String assetId = masterService.getAutoIncrementId(masterIdName);

		IdMasterDto idMasterDto = masterService.getIdMasterByMasterIdName(masterIdName);
		idMasterDto.setLastNumber(assetId);

		this.masterService.saveIdMaster(idMasterDto);

		session.setAttribute("message", new Message("Asset Information Saved Successfully !", "success"));
		return "redirect:/asset/entry";
	}

	@GetMapping("/view")
	public String viewAsset(Model model) {
		model.addAttribute("assets", assetService.getAllAssets());
		return "pages/asset/view_assets";
	}

	@GetMapping("/id/view/{assetId}")
	public String viewAssetById(@PathVariable("assetId") Long assetId, Model model) {
		model.addAttribute("asset", assetService.getAssetById(assetId));
		return "pages/asset/asset_view";
	}

}
