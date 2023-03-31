package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.entity.IndentApprovedItems;
import com.ingroinfo.mm.entity.IndentApprovedLabours;
import com.ingroinfo.mm.entity.IndentApprovedVehicles;
import com.ingroinfo.mm.service.TaskUpdateService;
import com.ingroinfo.mm.service.WorkOrderService;

@Controller
public class IndentStatusController {

	@Autowired
	private TaskUpdateService taskUpdateService;
	@Autowired
	private WorkOrderService workOrderService;

	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	@GetMapping("/indent-status")
	public String verifyIndentStatus(Model model) {
		String complStatus = "approved_indent";
		List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCompl", complDtos);
		model.addAttribute("title", "Indent | Status | Manintenance Management");
		return "/pages/indent_status/indent_status";
	}

	// Handler For get Approved Indent Data
	@GetMapping("/indent-status/get/{complNo}/{indentNo}")
	public String getIndentApprovedDataForVerify(@PathVariable String complNo, @PathVariable String indentNo,
			Model model) {
		String complStatus = "approved_indent";
		List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCompl", complDtos);

		List<IndentApprovedItems> approvedIndentItems = this.workOrderService.getApprovedIndentItemsByComplNoAndIndentNo(complNo, indentNo);
		List<IndentApprovedLabours> approvedIndentLabors = this.workOrderService.getApprovedIndentLaborsByComplNoAndIndentNo(complNo, indentNo);
		List<IndentApprovedVehicles> approvedIndentVehicles = this.workOrderService.getApprovedIndentVehiclesByComplNoAndIndentNo(complNo, indentNo);

		String complNumber = null;
		String indentNumber = null;
		Date expStartDate = null;
		String department = null;
		String division = null;
		String subDivision = null;
		String workPriprity = null;
		String workSite = null;
		String contactNo = null;

		// Check for indent number in TempIndentItemRequest list
		for (IndentApprovedItems approvedIndentItem : approvedIndentItems) {
			if (approvedIndentItem.getIndentNo() != null || approvedIndentItem.getComplNo() != null) {
				indentNumber = approvedIndentItem.getIndentNo();
				complNumber = approvedIndentItem.getComplNo();
				expStartDate = approvedIndentItem.getStartDate();
				department = approvedIndentItem.getDepartmentName();
				division = approvedIndentItem.getDivision();
				subDivision = approvedIndentItem.getSubDivision();
				workPriprity = approvedIndentItem.getWorkPriority();
				workSite = approvedIndentItem.getWorkSite();
				contactNo = approvedIndentItem.getContactNo();
				break;
			}
		}
		// Check for indent number in TempIndentLabourRequest list
		if (indentNumber == null || complNumber == null) {
			for (IndentApprovedLabours approvedIndentLabor : approvedIndentLabors) {
				if (approvedIndentLabor.getIndentNo() != null || approvedIndentLabor.getComplNo() != null) {
					indentNumber = approvedIndentLabor.getIndentNo();
					complNumber = approvedIndentLabor.getComplNo();
					expStartDate = approvedIndentLabor.getStartDate();
					department = approvedIndentLabor.getDepartmentName();
					division = approvedIndentLabor.getDivision();
					subDivision = approvedIndentLabor.getSubDivision();
					workPriprity = approvedIndentLabor.getWorkPriority();
					workSite = approvedIndentLabor.getWorkSite();
					contactNo = approvedIndentLabor.getContactNo();
					break;
				}
			}
		}
		// Check for indent number in TempIndentVehicleRequest list
		if (indentNumber == null || complNumber == null) {
			for (IndentApprovedVehicles approvedIndentVehicle : approvedIndentVehicles) {
				if (approvedIndentVehicle.getIndentNo() != null || approvedIndentVehicle.getComplNo() != null) {
					indentNumber = approvedIndentVehicle.getIndentNo();
					complNumber = approvedIndentVehicle.getComplNo();
					expStartDate = approvedIndentVehicle.getStartDate();
					department = approvedIndentVehicle.getDepartmentName();
					division = approvedIndentVehicle.getDivision();
					subDivision = approvedIndentVehicle.getSubDivision();
					workPriprity = approvedIndentVehicle.getWorkPriority();
					workSite = approvedIndentVehicle.getWorkSite();
					contactNo = approvedIndentVehicle.getContactNo();
					break;
				}
			}
		}

		model.addAttribute("complNo", complNumber);
		model.addAttribute("indentNo", indentNumber);
		model.addAttribute("startDate", expStartDate);
		model.addAttribute("department", department);
		model.addAttribute("division", division);
		model.addAttribute("subdivision", subDivision);
		model.addAttribute("workPriority", workPriprity);
		model.addAttribute("worksite", workSite);
		model.addAttribute("contactNo", contactNo);

		model.addAttribute("listOfMaterials", approvedIndentItems);
		model.addAttribute("listOfLabors", approvedIndentLabors);
		model.addAttribute("listOfVehicles", approvedIndentVehicles);
		model.addAttribute("title", "Indent| Status | Manintenance Management");
		return "/pages/indent_status/indent_status";
	}

}
