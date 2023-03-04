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
import org.springframework.web.bind.annotation.ResponseBody;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.entity.InwardApprovedMaterials;
import com.ingroinfo.mm.entity.InwardApprovedSpares;
import com.ingroinfo.mm.entity.InwardApprovedTools;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.ApprovalService;
import com.ingroinfo.mm.service.StockService;

@Controller
@RequestMapping("/approvals")
public class StockApprovals {

	@Autowired
	private StockService stockService;

	@Autowired
	private ApprovalService approvalService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("/stocks")
	@PreAuthorize("hasAuthority('STOCKS_APPROVALS')")
	public String stockApprovals(Model model) {
		model.addAttribute("title", "Stock Approvals | Maintenance Mangement");
		return "/pages/stock_management/stock_approvals";
	}

	@GetMapping("/stocks/materials")
	public String approveMaterials(Model model) {

		model.addAttribute("title", "Material Approvals | Maintenance Mangement");
		model.addAttribute("approval", new InwardDto());
		model.addAttribute("materialsLists", stockService.getInwardAllMaterialsList());

		return "/pages/stock_management/inward_materials_approval";
	}

	@PostMapping("/stocks/materials/approve")
	public String approvedMaterials(@ModelAttribute InwardDto inwardItemDto, Model model, Principal principal,
			HttpSession session) {

		String username = principal.getName();
		inwardItemDto.setApprovedBy(username);
		inwardItemDto.setUsername(username);
		approvalService.saveMaterial(inwardItemDto);
		session.setAttribute("message", new Message("Material has been approved successfully !", "success"));

		return "redirect:/approvals/stocks/materials";
	}

	@GetMapping("/inward/approved/materials/get/{id}")
	public @ResponseBody InwardApprovedMaterials getInwardMaterials(@PathVariable("id") Long id) {
		return stockService.getApprovedInwardMaterialById(id);
	}

	@GetMapping("/stocks/materials/reject/{id}")
	public String rejectMaterial(@PathVariable("id") Long id, HttpSession session) {

		approvalService.rejectMaterial(id);
		session.setAttribute("message", new Message("Material has been Rejected !", "danger"));

		return "redirect:/approvals/stocks/materials";

	}

	/****************** Spares ***************/

	@GetMapping("/stocks/spares")
	public String approveSpares(Model model) {

		model.addAttribute("title", "Spares Approvals | Maintenance Mangement");
		model.addAttribute("approval", new InwardDto());
		model.addAttribute("sparesLists", stockService.getInwardAllSparesList());

		return "/pages/stock_management/inward_spares_approval";
	}

	@PostMapping("/stocks/spares/approve")
	public String approvedSpares(@ModelAttribute InwardDto inwardItemDto, Model model, Principal principal,
			HttpSession session) {

		String username = principal.getName();
		inwardItemDto.setApprovedBy(username);
		inwardItemDto.setUsername(username);

		approvalService.saveSpare(inwardItemDto);
		session.setAttribute("message", new Message("Spare has been approved successfully !", "success"));

		return "redirect:/approvals/stocks/spares";
	}

	@GetMapping("/inward/approved/spares/get/{id}")
	public @ResponseBody InwardApprovedSpares getInwardSpares(@PathVariable("id") Long id) {
		return stockService.getApprovedInwardSpareById(id);
	}

	@GetMapping("/stocks/spares/reject/{id}")
	public String rejectSpare(@PathVariable("id") Long id, HttpSession session) {

		approvalService.rejectSpare(id);
		session.setAttribute("message", new Message("Spare has been Rejected !", "danger"));

		return "redirect:/approvals/stocks/spares";

	}

	/****************** Tools ***************/

	@GetMapping("/stocks/tools")
	public String approveTools(Model model) {

		model.addAttribute("title", "Tools Approvals | Maintenance Mangement");
		model.addAttribute("approval", new InwardDto());
		model.addAttribute("toolsLists", stockService.getInwardAllToolsList());

		return "/pages/stock_management/inward_tools_approval";
	}

	@PostMapping("/stocks/tools/approve")
	public String approvedTools(@ModelAttribute InwardDto inwardItemDto, Model model, Principal principal,
			HttpSession session) {

		String username = principal.getName();
		inwardItemDto.setApprovedBy(username);
		inwardItemDto.setUsername(username);

		approvalService.saveTool(inwardItemDto);
		session.setAttribute("message", new Message("Tool has been approved successfully !", "success"));

		return "redirect:/approvals/stocks/tools";
	}

	@GetMapping("/inward/approved/tools/get/{id}")
	public @ResponseBody InwardApprovedTools getInwardTools(@PathVariable("id") Long id) {
		return stockService.getApprovedInwardToolById(id);
	}

	@GetMapping("/stocks/tools/reject/{id}")
	public String rejectTool(@PathVariable("id") Long id, HttpSession session) {

		approvalService.rejectTool(id);
		session.setAttribute("message", new Message("Tool has been Rejected !", "danger"));

		return "redirect:/approvals/stocks/tools";

	}

	/****************** Outward Stocks ***************/

	@GetMapping("/outward/stocks")
	public String approveOutwardStocks(Model model) {

		model.addAttribute("title", "Outward Stocks Approvals | Maintenance Mangement");
		model.addAttribute("approval", new InwardDto());
		return "/pages/stock_management/outward_stocks_approval";
	}

}
