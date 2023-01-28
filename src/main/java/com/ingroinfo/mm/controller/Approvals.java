package com.ingroinfo.mm.controller;

import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.ApprovalService;
import com.ingroinfo.mm.service.StockService;

@Controller
@RequestMapping("/approvals")
public class Approvals {

	@Autowired
	private StockService stockService;

	@Autowired
	private ApprovalService approvalService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("/stocks")
	public String stockApprovals(Model model) {
		model.addAttribute("title", "Stock Approvals | Maintenance Mangement");
		return "/pages/stock_management/stock_approvals";
	}

	@GetMapping("/get/stocks/material/{id}")
	public @ResponseBody InwardItemDto getMaterialDetails(@PathVariable Long id, Model model) {

		return stockService.getInwarAllMaterialList().stream().filter(f -> f.getBundleId().equals(id)).findFirst()
				.get();
	}

	@GetMapping("/stocks/material")
	public String approveMaterials(Model model) {

		model.addAttribute("title", "Materials Approval | Maintenance Mangement");
		model.addAttribute("inwardItem", new InwardItemDto());
		model.addAttribute("materialsLists", stockService.getInwarAllMaterialList());

		return "/pages/stock_management/inward_materials_approval";
	}

	@PostMapping("/stocks/material/approved")
	public String approvedMaterials(@ModelAttribute InwardItemDto inwardItemDto, Model model, Principal principal,
			HttpSession session) {

		String username = principal.getName();
		inwardItemDto.setApprovedBy(username);
		inwardItemDto.setUsername(username);

		approvalService.saveMaterial(inwardItemDto);
		session.setAttribute("message", new Message("Material has been approved successfully !", "success"));

		return "redirect:/approvals/stocks/material";
	}

}
