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
import com.ingroinfo.mm.service.StocksApprovalService;
import com.ingroinfo.mm.service.StockService;

@Controller
@RequestMapping("/approvals")
public class StocksApprovals {

	@Autowired
	private StockService stockService;

	@Autowired
	private StocksApprovalService approvalService;

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
	public String rejectMaterial(@PathVariable("id") Long id, HttpSession session, Principal principal) {

		approvalService.rejectMaterial(id, principal.getName());
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
	public String rejectSpare(@PathVariable("id") Long id, HttpSession session, Principal principal) {

		approvalService.rejectSpare(id, principal.getName());
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
	public String rejectTool(@PathVariable("id") Long id, HttpSession session, Principal principal) {

		approvalService.rejectTool(id, principal.getName());
		session.setAttribute("message", new Message("Tool has been Rejected !", "danger"));

		return "redirect:/approvals/stocks/tools";

	}

	/****************** Outward Stocks ***************/

	@GetMapping("/outward/stocks")
	public String approveOutwardStocks(Model model) {

		model.addAttribute("title", "Outward Stocks Approvals | Maintenance Mangement");
		model.addAttribute("outwardStocksLists", stockService.getOutwardStockOrders());
		return "/pages/stock_management/outward_stocks_approval";
	}
	
	@GetMapping("/outward/stocks/items/{stockOrderNo}")
	public String outwardListItemsApprovals(@PathVariable("stockOrderNo") Long stockOrderNo, Model model) {
		
		model.addAttribute("title", "Outward Stockorder Items Approvals | Maintenance Management");		
		model.addAttribute("outwardStocksListItems", stockService.getOutwardStockOrderItems(stockOrderNo));
		model.addAttribute("outwardStockorderNo", stockService.getOutwardStockOrder(stockOrderNo));	
		
		return "/pages/stock_management/outward_stocks_approval_items";
	}
		
	@GetMapping("/outward/stockorder/items/{stockOrderNo}")
	public String outwardApproveItems(@PathVariable("stockOrderNo") Long stockOrderNo, Model model, HttpSession session, Principal principal) {
		
		model.addAttribute("title", "Outward Stockorder Items Approvals | Maintenance Management");				
		approvalService.approveOutwardStocks(stockOrderNo, principal.getName());		
		session.setAttribute("message", new Message("Outward Stockorder Items has been approved successfully !", "success"));
		return "redirect:/approvals/outward/stocks";
	}
	
	@GetMapping("/outward/stockorder/reject/{stockOrderNo}")
	public String rejectOutwardStockorderItems(@PathVariable("stockOrderNo") Long stockOrderNo, HttpSession session, Principal principal) {

		approvalService.rejectStockorderItems(stockOrderNo, principal.getName());
		session.setAttribute("message", new Message("Outward Stockorder Items has been rejected successfully !", "success"));

		return "redirect:/approvals/outward/stocks";
	}
	
	/****************** Stocks Return ***************/
	
	@GetMapping("/stocks/item/return")
	public String approveStockReturn(Model model) {

		model.addAttribute("title", "Stocks Return Approvals | Maintenance Mangement");
		model.addAttribute("returnedItemLists", stockService.getStockReturnItemList());
		return "/pages/stock_management/stock_returns_approval";
	}
	
	@GetMapping("/return/item/reject/{id}")
	public String deleteReturnItem(@PathVariable("id") Long id, HttpSession session, Principal principal) {

		approvalService.rejectReturnItem(id , principal.getName());
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "success"));
		return "redirect:/approvals/stocks/item/return";
	}
	
	@GetMapping("/stocks/item/return/{id}")
	public String approveStockReturnItems(@PathVariable("id") Long id, HttpSession session, Principal principal) {
				
		approvalService.approveReturnItem(id, principal.getName());		
		session.setAttribute("message", new Message("Item Return Items has been approved successfully !", "success"));
		return "redirect:/approvals/stocks/item/return";
	}
	

}
