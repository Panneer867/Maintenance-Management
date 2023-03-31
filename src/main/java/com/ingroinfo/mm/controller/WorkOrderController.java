package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.dto.VerifyIndentStatusDto;
import com.ingroinfo.mm.dto.WapWorkOrderItemsDto;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;
import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;
import com.ingroinfo.mm.service.WorkOrderService;

@Controller
@RequestMapping("/workorder")
public class WorkOrderController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private WorkOrderService workOrderService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("dashboard")
	public String dashboard() {
		return "/pages/work_orders/dashboard";
	}

	private List<VerifyIndentStatusDto> executeQuery(String sql) {
		List<VerifyIndentStatusDto> verifyIndentDto = null;
		try {
			verifyIndentDto = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(VerifyIndentStatusDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return verifyIndentDto;
	}

	@GetMapping("/generate")
	@PreAuthorize("hasAuthority('GENERATE_WORKORDER')")
	public String genrateWorkOrder(Model model) {
		List<VerifyIndentStatusDto> verifyIndentStatus = executeQuery("SELECT * FROM VERIFY_INDENT_STATUS");
		model.addAttribute("VerifiedIndentStsList", verifyIndentStatus);
		model.addAttribute("title", "WorkOrder | Generate | Mainteance Management");
		return "/pages/work_orders/generate_work_order";
	}

	//Get Data
	@GetMapping("/generate/get/{complNo}/{indentNo}")
	public String getTempWorkOrderDtlsByComplNoAndIndentNo(Model model, @PathVariable String complNo,
			@PathVariable String indentNo) {

		List<VerifyIndentStatusDto> verifyIndentStatus = executeQuery("SELECT * FROM VERIFY_INDENT_STATUS");
		model.addAttribute("VerifiedIndentStsList", verifyIndentStatus);

		List<TempWorkOrderItemRequest> tempWorkOrderItems = this.workOrderService
				.getTempWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderLabourRequest> tempWorkOrderLabours = this.workOrderService
				.getTempWorkOrderLaborsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles = this.workOrderService
				.getTempWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

		String complNumber = null;
		String indentNumber = null;
		Date expStartDate = null;
		String department = null;
		String division = null;
		String subDivision = null;
		String workPriprity = null;
		String workSite = null;
		String contactNo = null;

		// Check for indent number in TempWorkOrderItemRequest list
		for (TempWorkOrderItemRequest tempWorkOrderItem : tempWorkOrderItems) {
			if (tempWorkOrderItem.getIndentNo() != null || tempWorkOrderItem.getComplNo() != null) {
				indentNumber = tempWorkOrderItem.getIndentNo();
				complNumber = tempWorkOrderItem.getComplNo();
				expStartDate = tempWorkOrderItem.getStartDate();
				department = tempWorkOrderItem.getDepartmentName();
				division = tempWorkOrderItem.getDivision();
				subDivision = tempWorkOrderItem.getSubDivision();
				workPriprity = tempWorkOrderItem.getWorkPriority();
				workSite = tempWorkOrderItem.getWorkSite();
				contactNo = tempWorkOrderItem.getContactNo();
				break;
			}
		}
		// Check for indent number in TempWorkOrderLabourRequest list
		if (indentNumber == null || complNumber == null) {
			for (TempWorkOrderLabourRequest tempWorkOrderLabor : tempWorkOrderLabours) {
				if (tempWorkOrderLabor.getIndentNo() != null || tempWorkOrderLabor.getComplNo() != null) {
					indentNumber = tempWorkOrderLabor.getIndentNo();
					complNumber = tempWorkOrderLabor.getComplNo();
					expStartDate = tempWorkOrderLabor.getStartDate();
					department = tempWorkOrderLabor.getDepartmentName();
					division = tempWorkOrderLabor.getDivision();
					subDivision = tempWorkOrderLabor.getSubDivision();
					workPriprity = tempWorkOrderLabor.getWorkPriority();
					workSite = tempWorkOrderLabor.getWorkSite();
					contactNo = tempWorkOrderLabor.getContactNo();
					break;
				}
			}
		}
		// Check for indent number in TempWorkOrderVehicleRequest list
		if (indentNumber == null || complNumber == null) {
			for (TempWorkOrderVehicleRequest tempWorkOrderVehicle : tempWorkOrderVehicles) {
				if (tempWorkOrderVehicle.getIndentNo() != null || tempWorkOrderVehicle.getComplNo() != null) {
					indentNumber = tempWorkOrderVehicle.getIndentNo();
					complNumber = tempWorkOrderVehicle.getComplNo();
					expStartDate = tempWorkOrderVehicle.getStartDate();
					department = tempWorkOrderVehicle.getDepartmentName();
					division = tempWorkOrderVehicle.getDivision();
					subDivision = tempWorkOrderVehicle.getSubDivision();
					workPriprity = tempWorkOrderVehicle.getWorkPriority();
					workSite = tempWorkOrderVehicle.getWorkSite();
					contactNo = tempWorkOrderVehicle.getContactNo();
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

		model.addAttribute("listOfMaterials", tempWorkOrderItems);
		model.addAttribute("listOfLabors", tempWorkOrderLabours);
		model.addAttribute("listOfVehicles", tempWorkOrderVehicles);
		model.addAttribute("title", "WorkOrder | Generate | Mainteance Management");
		return "/pages/work_orders/generate_work_order";
	}
	
	//Handler For Submitting TempWorkOrder Data For Approval
	@GetMapping("/generate/submit/{complNo}/{indentNo}")
	public String submitTempWorkorderForGenerateWorkOrder(@PathVariable String complNo, @PathVariable String indentNo,Principal principal) {

		List<TempWorkOrderItemRequest> tempWorkOrderItems = this.workOrderService
				.getTempWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderLabourRequest> tempWorkOrderLabours = this.workOrderService
				.getTempWorkOrderLaborsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles = this.workOrderService
				.getTempWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

		if (tempWorkOrderItems != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<WapWorkOrderItemsDto> wapWorkOrderItemsDtos = tempWorkOrderItems.stream()
					.map((wapWorkOrderItemDto) -> modelMapper.map(wapWorkOrderItemDto, WapWorkOrderItemsDto.class))
					.collect(Collectors.toList());
			wapWorkOrderItemsDtos.forEach(wapWorkOrderItem -> {
				wapWorkOrderItem.setUserName(principal.getName());
			});
			
			this.workOrderService.saveAllWapWorkOrderItems(wapWorkOrderItemsDtos);
			//this.workOrderService.deleteTempWorkOrderItemRequestByComplNo(complNo);
		}

		return "redirect:/workorder/generate";
	}

	@GetMapping("/approved")
	public String approvedWorkOrderList(Model model) {
		return "/pages/work_orders/approved_work_order_list";
	}

	@GetMapping("/hold")
	@PreAuthorize("hasAuthority('HOLD_WORKORDER')")
	public String holdWorkOrder(Model model) {
		return "/pages/work_orders/hold_work_order";
	}

	@GetMapping("/cancel")
	@PreAuthorize("hasAuthority('CANCEL_WORKORDER')")
	public String cancelWorkOrder(Model model) {
		return "/pages/work_orders/cancel_work_order";
	}

}
