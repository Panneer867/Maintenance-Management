package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
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
import com.ingroinfo.mm.dto.CancelWorkOrderItemsDto;
import com.ingroinfo.mm.dto.CancelWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.CancelWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.HoldWorkOrderItemsDto;
import com.ingroinfo.mm.dto.HoldWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.HoldWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.VerifyIndentStatusDto;
import com.ingroinfo.mm.dto.WapWorkOrderItemsDto;
import com.ingroinfo.mm.dto.WapWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.WapWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedItemsDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedLaboursDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedVehiclesDto;
import com.ingroinfo.mm.entity.IndentApprovedItems;
import com.ingroinfo.mm.entity.IndentApprovedLabours;
import com.ingroinfo.mm.entity.IndentApprovedVehicles;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;
import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.TaskUpdateService;
import com.ingroinfo.mm.service.WorkOrderService;

@Controller
@RequestMapping("/workorder")
public class WorkOrderController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private TaskUpdateService taskUpdateService;

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

	// Get Data
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

	// Handler For Submitting TempWorkOrder Data For Approval
	@GetMapping("/generate/submit/{complNo}/{indentNo}")
	public String submitTempWorkorderForGenerateWorkOrder(@PathVariable String complNo, @PathVariable String indentNo,
			Principal principal, HttpSession session) {

		List<TempWorkOrderItemRequest> tempWorkOrderItems = this.workOrderService
				.getTempWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderLabourRequest> tempWorkOrderLabours = this.workOrderService
				.getTempWorkOrderLaborsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles = this.workOrderService
				.getTempWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

		List<IndentApprovedItems> indentApprovedItems = this.workOrderService
				.getApprovedIndentItemsByComplNoAndIndentNo(complNo, indentNo);
		List<IndentApprovedLabours> indentApprovedLabours = this.workOrderService
				.getApprovedIndentLaborsByComplNoAndIndentNo(complNo, indentNo);
		List<IndentApprovedVehicles> indentApprovedVehicles = this.workOrderService
				.getApprovedIndentVehiclesByComplNoAndIndentNo(complNo, indentNo);

		String workOrderNo = "101" + complNo + indentNo;

		List<WapWorkOrderItemsDto> wapWorkOrderItemsDtos = new ArrayList<>();
		List<WapWorkOrderLaboursDto> wapWorkOrderLaboursDtos = new ArrayList<>();
		List<WapWorkOrderVehiclesDto> wapWorkOrderVehiclesDtos = new ArrayList<>();

		if (tempWorkOrderItems != null) {
			for (TempWorkOrderItemRequest tempWorkOrderItem : tempWorkOrderItems) {
				WapWorkOrderItemsDto wapWorkOrderItem = new WapWorkOrderItemsDto();
				wapWorkOrderItem.setUserName(principal.getName());
				wapWorkOrderItem.setWorkOrder(workOrderNo);
				wapWorkOrderItem.setIndentNo(tempWorkOrderItem.getIndentNo());
				wapWorkOrderItem.setComplNo(tempWorkOrderItem.getComplNo());				
				wapWorkOrderItem.setDivision(tempWorkOrderItem.getDivision());
				wapWorkOrderItem.setSubDivision(tempWorkOrderItem.getSubDivision());
				wapWorkOrderItem.setWorkSite(tempWorkOrderItem.getWorkSite());
				wapWorkOrderItem.setStartDate(tempWorkOrderItem.getStartDate());
				wapWorkOrderItem.setEndDate(tempWorkOrderItem.getEndDate());
				wapWorkOrderItem.setContactNo(tempWorkOrderItem.getContactNo());
				wapWorkOrderItem.setWorkPriority(tempWorkOrderItem.getWorkPriority());
				wapWorkOrderItem.setComplDtls(tempWorkOrderItem.getComplDtls());
				wapWorkOrderItem.setCategoryName(tempWorkOrderItem.getCategoryName());
				wapWorkOrderItem.setItemName(tempWorkOrderItem.getItemName());
				wapWorkOrderItem.setItemId(tempWorkOrderItem.getItemId());
				wapWorkOrderItem.setUnitOfMesure(tempWorkOrderItem.getUnitOfMesure());
				wapWorkOrderItem.setHsnCode(tempWorkOrderItem.getHsnCode());
				wapWorkOrderItem.setQuantity(tempWorkOrderItem.getQuantity());
				wapWorkOrderItem.setStockType(tempWorkOrderItem.getStockType());
				wapWorkOrderItem.setStockTypeName(tempWorkOrderItem.getStockTypeName());
				wapWorkOrderItem.setIndentApproved(tempWorkOrderItem.getIndentApproved());
				wapWorkOrderItem.setApprovedSts(tempWorkOrderItem.getApprovedSts());
				wapWorkOrderItem.setDepartmentName(tempWorkOrderItem.getDepartmentName());
				wapWorkOrderItem.setCreatedDate(tempWorkOrderItem.getCreatedDate());
				wapWorkOrderItemsDtos.add(wapWorkOrderItem);
			}
			this.workOrderService.saveAllWapWorkOrderItems(wapWorkOrderItemsDtos);
			this.workOrderService.deleteTempWorkOrderItemRequestByComplNo(complNo);
		}

		if (tempWorkOrderLabours != null) {

			for (TempWorkOrderLabourRequest tempWorkOrderLabor : tempWorkOrderLabours) {
				WapWorkOrderLaboursDto wapWorkOrderLabor = new WapWorkOrderLaboursDto();
				wapWorkOrderLabor.setUserName(principal.getName());
				wapWorkOrderLabor.setWorkOrder(workOrderNo);
				wapWorkOrderLabor.setIndentNo(tempWorkOrderLabor.getIndentNo());
				wapWorkOrderLabor.setComplNo(tempWorkOrderLabor.getComplNo());
				wapWorkOrderLabor.setDivision(tempWorkOrderLabor.getDivision());
				wapWorkOrderLabor.setSubDivision(tempWorkOrderLabor.getSubDivision());
				wapWorkOrderLabor.setWorkSite(tempWorkOrderLabor.getWorkSite());
				wapWorkOrderLabor.setStartDate(tempWorkOrderLabor.getStartDate());
				wapWorkOrderLabor.setEndDate(tempWorkOrderLabor.getEndDate());
				wapWorkOrderLabor.setContactNo(tempWorkOrderLabor.getContactNo());
				wapWorkOrderLabor.setWorkPriority(tempWorkOrderLabor.getWorkPriority());
				wapWorkOrderLabor.setComplDtls(tempWorkOrderLabor.getComplDtls());
				wapWorkOrderLabor.setEmpCategory(tempWorkOrderLabor.getEmpCategory());
				wapWorkOrderLabor.setMembers(tempWorkOrderLabor.getMembers());
				wapWorkOrderLabor.setDaysRequired(tempWorkOrderLabor.getDaysRequired());
				wapWorkOrderLabor.setTimeRequired(tempWorkOrderLabor.getTimeRequired());
				wapWorkOrderLabor.setDepartmentName(tempWorkOrderLabor.getDepartmentName());
				wapWorkOrderLabor.setCreatedDate(tempWorkOrderLabor.getCreatedDate());
				wapWorkOrderLabor.setIndentApproved(tempWorkOrderLabor.getIndentApproved());
				wapWorkOrderLabor.setApprovedSts(tempWorkOrderLabor.getApprovedSts());
				wapWorkOrderLaboursDtos.add(wapWorkOrderLabor);
			}

			this.workOrderService.saveAllWapWorkOrderLabours(wapWorkOrderLaboursDtos);
			this.workOrderService.deleteTempWorkOrderLaboursRequestByComplNo(complNo);
		}
		if (tempWorkOrderVehicles != null) {

			for (TempWorkOrderVehicleRequest tempWorkOrdervehicle : tempWorkOrderVehicles) {
				WapWorkOrderVehiclesDto wapWorkOrderVehicle = new WapWorkOrderVehiclesDto();
				wapWorkOrderVehicle.setUserName(principal.getName());
				wapWorkOrderVehicle.setWorkOrder(workOrderNo);
				wapWorkOrderVehicle.setIndentNo(tempWorkOrdervehicle.getIndentNo());
				wapWorkOrderVehicle.setComplNo(tempWorkOrdervehicle.getComplNo());
				wapWorkOrderVehicle.setDivision(tempWorkOrdervehicle.getDivision());
				wapWorkOrderVehicle.setSubDivision(tempWorkOrdervehicle.getSubDivision());
				wapWorkOrderVehicle.setWorkSite(tempWorkOrdervehicle.getWorkSite());
				wapWorkOrderVehicle.setStartDate(tempWorkOrdervehicle.getStartDate());
				wapWorkOrderVehicle.setEndDate(tempWorkOrdervehicle.getEndDate());
				wapWorkOrderVehicle.setContactNo(tempWorkOrdervehicle.getContactNo());
				wapWorkOrderVehicle.setWorkPriority(tempWorkOrdervehicle.getWorkPriority());
				wapWorkOrderVehicle.setComplDtls(tempWorkOrdervehicle.getComplDtls());
				wapWorkOrderVehicle.setVehicleType(tempWorkOrdervehicle.getVehicleType());
				wapWorkOrderVehicle.setVehicleNo(tempWorkOrdervehicle.getVehicleNo());
				wapWorkOrderVehicle.setDriverName(tempWorkOrdervehicle.getDriverName());
				wapWorkOrderVehicle.setDriverPhone(tempWorkOrdervehicle.getDriverPhone());
				wapWorkOrderVehicle.setMeterReading(tempWorkOrdervehicle.getMeterReading());
				wapWorkOrderVehicle.setStratTime(tempWorkOrdervehicle.getStratTime());
				wapWorkOrderVehicle.setVehicleId(tempWorkOrdervehicle.getVehicleId());
				wapWorkOrderVehicle.setDepartmentName(tempWorkOrdervehicle.getDepartmentName());
				wapWorkOrderVehicle.setCreatedDate(tempWorkOrdervehicle.getCreatedDate());
				wapWorkOrderVehicle.setIndentApproved(tempWorkOrdervehicle.getIndentApproved());
				wapWorkOrderVehicle.setApprovedSts(tempWorkOrdervehicle.getApprovedSts());
				wapWorkOrderVehiclesDtos.add(wapWorkOrderVehicle);
			}

			this.workOrderService.saveAllWapWorkOrderVehicles(wapWorkOrderVehiclesDtos);
			this.workOrderService.deleteTempWorkOrderVehicleRequestByComplNo(complNo);
		}

		if (indentApprovedItems != null) {
			for (int i = 0; i < indentApprovedItems.size(); i++) {
				IndentApprovedItems indentItems = indentApprovedItems.get(i);
				indentItems.setApprovedSts("W");
			}
			this.workOrderService.saveAllApprovedIndentItems(indentApprovedItems);
		}
		if (indentApprovedLabours != null) {
			for (int i = 0; i < indentApprovedLabours.size(); i++) {
				IndentApprovedLabours indentLabors = indentApprovedLabours.get(i);
				indentLabors.setApprovedSts("W");
			}
			this.workOrderService.saveAllApprovedIndentLabours(indentApprovedLabours);
		}
		if (indentApprovedVehicles != null) {
			for (int i = 0; i < indentApprovedVehicles.size(); i++) {
				IndentApprovedVehicles indentVehicles = indentApprovedVehicles.get(i);
				indentVehicles.setApprovedSts("W");
			}
			this.workOrderService.saveAllApprovedIndentVehicles(indentApprovedVehicles);
		}

		ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
		oldcomplaintDto.setComplStatus("WAITING_WORKORDER_APPROVAL");
		oldcomplaintDto.setWorkOrder(workOrderNo);
		this.taskUpdateService.saveComplaint(oldcomplaintDto);
		session.setAttribute("message", new Message("Generate For WorkOrder Request Sucessfully Sent !!", "success"));
		return "redirect:/workorder/generate";
	}
	
	// Handler For Show List Of Approved WorkOrders
	@GetMapping("/approved")
	public String approvedWorkOrderList(Model model) {
		String complStatus = "APPROVED_WORKORDERS";
		List<ComplaintDto> approvedcomplDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("workorderApprovedcompls", approvedcomplDtos);
		model.addAttribute("title", "WorkOrder| Approved | Maintenance Mangement");
		return "/pages/work_orders/approved_work_order_list";
	}

	// Handler For Display Approved WorkOrder Details ByWorkOrder Number
	@GetMapping("/approved/view/{workorderNo}")
	public String displayApprovedWorkorderDetails(@PathVariable("workorderNo") String workOrder, Model model) {

		List<WorkOrderApprovedItemsDto> workOrderApprovedItemsDtos = this.workOrderService
				.getApprovedWorkOrderItemsByWorkorderNo(workOrder);
		List<WorkOrderApprovedLaboursDto> workOrderApprovedLaboursDtos = this.workOrderService
				.getApprovedWorkOrderLaboursByWorkorderNo(workOrder);
		List<WorkOrderApprovedVehiclesDto> workOrderApprovedVehiclesDtos = this.workOrderService
				.getApprovedWorkOrderVehiclesByWorkorderNo(workOrder);

		String complNumber = null;
		String indentNumber = null;
		String workorderNo = null;
		Date expStartDate = null;
		String department = null;
		String division = null;
		String subDivision = null;
		String workPriprity = null;
		String workSite = null;
		String contactNo = null;
		Date approvedDate = null;
		String approvedBy = null;

		// Check for indent number in Approved WokroderItemsDto list
		for (WorkOrderApprovedItemsDto approvedWorkorderItems : workOrderApprovedItemsDtos) {
			if (approvedWorkorderItems.getIndentNo() != null || approvedWorkorderItems.getComplNo() != null) {
				indentNumber = approvedWorkorderItems.getIndentNo();
				complNumber = approvedWorkorderItems.getComplNo();
				workorderNo = approvedWorkorderItems.getWorkOrder();
				expStartDate = approvedWorkorderItems.getStartDate();
				department = approvedWorkorderItems.getDepartmentName();
				division = approvedWorkorderItems.getDivision();
				subDivision = approvedWorkorderItems.getSubDivision();
				workPriprity = approvedWorkorderItems.getWorkPriority();
				workSite = approvedWorkorderItems.getWorkSite();
				contactNo = approvedWorkorderItems.getContactNo();
				approvedDate = approvedWorkorderItems.getCreatedDate();
				approvedBy = approvedWorkorderItems.getUserName();
				break;
			}
		}
		// Check for indent number in Approved WorkOrderLabourDto list
		if (indentNumber == null || complNumber == null) {
			for (WorkOrderApprovedLaboursDto approvedWorkorderLabors : workOrderApprovedLaboursDtos) {
				if (approvedWorkorderLabors.getIndentNo() != null || approvedWorkorderLabors.getComplNo() != null) {
					indentNumber = approvedWorkorderLabors.getIndentNo();
					complNumber = approvedWorkorderLabors.getComplNo();
					workorderNo = approvedWorkorderLabors.getWorkOrder();
					expStartDate = approvedWorkorderLabors.getStartDate();
					department = approvedWorkorderLabors.getDepartmentName();
					division = approvedWorkorderLabors.getDivision();
					subDivision = approvedWorkorderLabors.getSubDivision();
					workPriprity = approvedWorkorderLabors.getWorkPriority();
					workSite = approvedWorkorderLabors.getWorkSite();
					contactNo = approvedWorkorderLabors.getContactNo();
					approvedDate = approvedWorkorderLabors.getCreatedDate();
					approvedBy = approvedWorkorderLabors.getUserName();
					break;
				}
			}
		}
		// Check for indent number in Approved WorkOrderVehicleDto list
		if (indentNumber == null || complNumber == null) {
			for (WorkOrderApprovedVehiclesDto approvedWorkorderVehicle : workOrderApprovedVehiclesDtos) {
				if (approvedWorkorderVehicle.getIndentNo() != null || approvedWorkorderVehicle.getComplNo() != null) {
					indentNumber = approvedWorkorderVehicle.getIndentNo();
					complNumber = approvedWorkorderVehicle.getComplNo();
					workorderNo = approvedWorkorderVehicle.getWorkOrder();
					expStartDate = approvedWorkorderVehicle.getStartDate();
					department = approvedWorkorderVehicle.getDepartmentName();
					division = approvedWorkorderVehicle.getDivision();
					subDivision = approvedWorkorderVehicle.getSubDivision();
					workPriprity = approvedWorkorderVehicle.getWorkPriority();
					workSite = approvedWorkorderVehicle.getWorkSite();
					contactNo = approvedWorkorderVehicle.getContactNo();
					approvedDate = approvedWorkorderVehicle.getCreatedDate();
					approvedBy = approvedWorkorderVehicle.getUserName();
					break;
				}
			}
		}

		model.addAttribute("complNo", complNumber);
		model.addAttribute("indentNo", indentNumber);
		model.addAttribute("workOrderNo", workorderNo);
		model.addAttribute("startDate", expStartDate);
		model.addAttribute("department", department);
		model.addAttribute("division", division);
		model.addAttribute("subdivision", subDivision);
		model.addAttribute("workPriority", workPriprity);
		model.addAttribute("worksite", workSite);
		model.addAttribute("contactNo", contactNo);
		model.addAttribute("approvedDate", approvedDate);
		model.addAttribute("approvedBy", approvedBy);

		model.addAttribute("listOfApprovedWorkorderitems", workOrderApprovedItemsDtos);
		model.addAttribute("listOfApprovedWorkorderLabours", workOrderApprovedLaboursDtos);
		model.addAttribute("listOfApprovedWorkorderVehicles", workOrderApprovedVehiclesDtos);

		model.addAttribute("title", "WorkOrder| Approved | Maintenance Mangement");
		return "/pages/work_orders/view_approved_workorder_details";
	}

	// Handler For Display Hold WorkOrders List
	@GetMapping("/hold")
	@PreAuthorize("hasAuthority('HOLD_WORKORDER')")
	public String holdWorkOrder(Model model) {
		String complStatus = "WORK_ORDER_IN_HOLD";
		List<ComplaintDto> holdcomplDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfHoldCompls", holdcomplDtos);
		model.addAttribute("title", "WorkOrder| Hold | Maintenance Mangement");
		return "/pages/work_orders/hold_work_order_list";
	}

	// Handler For Display Hold WorkOrder Details ByWorkOrder Number
	@GetMapping("/hold/view/{workOrder}")
	public String displayholdWorkOrderDetails(@PathVariable String workOrder, Model model) {

		List<HoldWorkOrderItemsDto> holdWorkOrderItemsDtos = this.workOrderService
				.getHoldWorkOrderItemsByWorkorderNo(workOrder);
		List<HoldWorkOrderLaboursDto> holdWorkOrderLaboursDtos = this.workOrderService
				.getHoldWorkOrderLaboursByWorkorderNo(workOrder);
		List<HoldWorkOrderVehiclesDto> holdWorkOrderVehiclesDtos = this.workOrderService
				.getHoldWorkOrderVehiclesByWorkorderNo(workOrder);

		String complNumber = null;
		String indentNumber = null;
		String workorderNo = null;
		Date expStartDate = null;
		String department = null;
		String division = null;
		String subDivision = null;
		String workPriprity = null;
		String workSite = null;
		String contactNo = null;
		Date holdDate = null;
		String holdBy = null;

		// Check for indent number in Hold WokroderItemsDto list
		for (HoldWorkOrderItemsDto holdWorkorderItems : holdWorkOrderItemsDtos) {
			if (holdWorkorderItems.getIndentNo() != null || holdWorkorderItems.getComplNo() != null) {
				indentNumber = holdWorkorderItems.getIndentNo();
				complNumber = holdWorkorderItems.getComplNo();
				workorderNo = holdWorkorderItems.getWorkOrder();
				expStartDate = holdWorkorderItems.getStartDate();
				department = holdWorkorderItems.getDepartmentName();
				division = holdWorkorderItems.getDivision();
				subDivision = holdWorkorderItems.getSubDivision();
				workPriprity = holdWorkorderItems.getWorkPriority();
				workSite = holdWorkorderItems.getWorkSite();
				contactNo = holdWorkorderItems.getContactNo();
				holdDate = holdWorkorderItems.getCreatedDate();
				holdBy = holdWorkorderItems.getUserName();
				break;
			}
		}
		// Check for indent number in Hold WorkOrderLabourDto list
		if (indentNumber == null || complNumber == null) {
			for (HoldWorkOrderLaboursDto holdWorkorderLabors : holdWorkOrderLaboursDtos) {
				if (holdWorkorderLabors.getIndentNo() != null || holdWorkorderLabors.getComplNo() != null) {
					indentNumber = holdWorkorderLabors.getIndentNo();
					complNumber = holdWorkorderLabors.getComplNo();
					workorderNo = holdWorkorderLabors.getWorkOrder();
					expStartDate = holdWorkorderLabors.getStartDate();
					department = holdWorkorderLabors.getDepartmentName();
					division = holdWorkorderLabors.getDivision();
					subDivision = holdWorkorderLabors.getSubDivision();
					workPriprity = holdWorkorderLabors.getWorkPriority();
					workSite = holdWorkorderLabors.getWorkSite();
					contactNo = holdWorkorderLabors.getContactNo();
					holdDate = holdWorkorderLabors.getCreatedDate();
					holdBy = holdWorkorderLabors.getUserName();
					break;
				}
			}
		}
		// Check for indent number in Hold WorkOrderVehicleDto list
		if (indentNumber == null || complNumber == null) {
			for (HoldWorkOrderVehiclesDto holdWorkorderVehicle : holdWorkOrderVehiclesDtos) {
				if (holdWorkorderVehicle.getIndentNo() != null || holdWorkorderVehicle.getComplNo() != null) {
					indentNumber = holdWorkorderVehicle.getIndentNo();
					complNumber = holdWorkorderVehicle.getComplNo();
					workorderNo = holdWorkorderVehicle.getWorkOrder();
					expStartDate = holdWorkorderVehicle.getStartDate();
					department = holdWorkorderVehicle.getDepartmentName();
					division = holdWorkorderVehicle.getDivision();
					subDivision = holdWorkorderVehicle.getSubDivision();
					workPriprity = holdWorkorderVehicle.getWorkPriority();
					workSite = holdWorkorderVehicle.getWorkSite();
					contactNo = holdWorkorderVehicle.getContactNo();
					holdDate = holdWorkorderVehicle.getCreatedDate();
					holdBy = holdWorkorderVehicle.getUserName();
					break;
				}
			}
		}

		model.addAttribute("complNo", complNumber);
		model.addAttribute("indentNo", indentNumber);
		model.addAttribute("workOrderNo", workorderNo);
		model.addAttribute("startDate", expStartDate);
		model.addAttribute("department", department);
		model.addAttribute("division", division);
		model.addAttribute("subdivision", subDivision);
		model.addAttribute("workPriority", workPriprity);
		model.addAttribute("worksite", workSite);
		model.addAttribute("contactNo", contactNo);
		model.addAttribute("holdDate", holdDate);
		model.addAttribute("holdBy", holdBy);

		model.addAttribute("listOfHoldWorkorderitems", holdWorkOrderItemsDtos);
		model.addAttribute("listOfHoldWorkorderLabours", holdWorkOrderLaboursDtos);
		model.addAttribute("listOfHoldWorkorderVehicles", holdWorkOrderVehiclesDtos);

		model.addAttribute("title", "WorkOrder| Hold | Maintenance Mangement");
		return "/pages/work_orders/view_hold_workorder_details";
	}

	// Handler For Display Cancel WorkOrder List
	@GetMapping("/cancel")
	@PreAuthorize("hasAuthority('CANCEL_WORKORDER')")
	public String cancelWorkOrder(Model model) {
		String complStatus = "WORK_ORDER_CANCELD";
		List<ComplaintDto> cancelcomplDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCancelCompls", cancelcomplDtos);
		model.addAttribute("title", "WorkOrder| Cancel | Maintenance Mangement");
		return "/pages/work_orders/cancel_work_order_list";
	}
	
	// Handler For Display Hold WorkOrder Details ByWorkOrder Number
	@GetMapping("/cancel/view/{workOrder}")
	public String displayCancelWorkOrderDetails(@PathVariable String workOrder, Model model) {

		List<CancelWorkOrderItemsDto> cancelWorkOrderItemsDtos = this.workOrderService
				.getCanceledWorkOrderItemsByWorkorderNo(workOrder);
		List<CancelWorkOrderLaboursDto> cancelWorkOrderLaboursDtos = this.workOrderService
				.getCanceledWorkOrderLaboursByWorkorderNo(workOrder);
		List<CancelWorkOrderVehiclesDto> cancelWorkOrderVehiclesDtos = this.workOrderService
				.getCanceledWorkOrderVehiclesByWorkorderNo(workOrder);

		String complNumber = null;
		String indentNumber = null;
		String workorderNo = null;
		Date expStartDate = null;
		String department = null;
		String division = null;
		String subDivision = null;
		String workPriprity = null;
		String workSite = null;
		String contactNo = null;
		Date canceledDate = null;
		String canceledBy = null;

		// Check for indent number in Canceled WokroderItemsDto list
		for (CancelWorkOrderItemsDto canceledWorkorderItems : cancelWorkOrderItemsDtos) {
			if (canceledWorkorderItems.getIndentNo() != null || canceledWorkorderItems.getComplNo() != null) {
				indentNumber = canceledWorkorderItems.getIndentNo();
				complNumber = canceledWorkorderItems.getComplNo();
				workorderNo = canceledWorkorderItems.getWorkOrder();
				expStartDate = canceledWorkorderItems.getStartDate();
				department = canceledWorkorderItems.getDepartmentName();
				division = canceledWorkorderItems.getDivision();
				subDivision = canceledWorkorderItems.getSubDivision();
				workPriprity = canceledWorkorderItems.getWorkPriority();
				workSite = canceledWorkorderItems.getWorkSite();
				contactNo = canceledWorkorderItems.getContactNo();
				canceledDate = canceledWorkorderItems.getCreatedDate();
				canceledBy = canceledWorkorderItems.getUserName();
				break;
			}
		}
		// Check for indent number in Canceled WorkOrderLabourDto list
		if (indentNumber == null || complNumber == null) {
			for (CancelWorkOrderLaboursDto canceledWorkorderLabors : cancelWorkOrderLaboursDtos) {
				if (canceledWorkorderLabors.getIndentNo() != null || canceledWorkorderLabors.getComplNo() != null) {
					indentNumber = canceledWorkorderLabors.getIndentNo();
					complNumber = canceledWorkorderLabors.getComplNo();
					workorderNo = canceledWorkorderLabors.getWorkOrder();
					expStartDate = canceledWorkorderLabors.getStartDate();
					department = canceledWorkorderLabors.getDepartmentName();
					division = canceledWorkorderLabors.getDivision();
					subDivision = canceledWorkorderLabors.getSubDivision();
					workPriprity = canceledWorkorderLabors.getWorkPriority();
					workSite = canceledWorkorderLabors.getWorkSite();
					contactNo = canceledWorkorderLabors.getContactNo();
					canceledDate = canceledWorkorderLabors.getCreatedDate();
					canceledBy = canceledWorkorderLabors.getUserName();
					break;
				}
			}
		}
		// Check for indent number in Approved WorkOrderVehicleDto list
		if (indentNumber == null || complNumber == null) {
			for (CancelWorkOrderVehiclesDto canceledWorkorderVehicle : cancelWorkOrderVehiclesDtos) {
				if (canceledWorkorderVehicle.getIndentNo() != null || canceledWorkorderVehicle.getComplNo() != null) {
					indentNumber = canceledWorkorderVehicle.getIndentNo();
					complNumber = canceledWorkorderVehicle.getComplNo();
					workorderNo = canceledWorkorderVehicle.getWorkOrder();
					expStartDate = canceledWorkorderVehicle.getStartDate();
					department = canceledWorkorderVehicle.getDepartmentName();
					division = canceledWorkorderVehicle.getDivision();
					subDivision = canceledWorkorderVehicle.getSubDivision();
					workPriprity = canceledWorkorderVehicle.getWorkPriority();
					workSite = canceledWorkorderVehicle.getWorkSite();
					contactNo = canceledWorkorderVehicle.getContactNo();
					canceledDate = canceledWorkorderVehicle.getCreatedDate();
					canceledBy = canceledWorkorderVehicle.getUserName();
					break;
				}
			}
		}

		model.addAttribute("complNo", complNumber);
		model.addAttribute("indentNo", indentNumber);
		model.addAttribute("workOrderNo", workorderNo);
		model.addAttribute("startDate", expStartDate);
		model.addAttribute("department", department);
		model.addAttribute("division", division);
		model.addAttribute("subdivision", subDivision);
		model.addAttribute("workPriority", workPriprity);
		model.addAttribute("worksite", workSite);
		model.addAttribute("contactNo", contactNo);
		model.addAttribute("canceledDate", canceledDate);
		model.addAttribute("canceledBy", canceledBy);

		model.addAttribute("listOfCanceledWorkorderitems", cancelWorkOrderItemsDtos);
		model.addAttribute("listOfCanceledWorkorderLabours", cancelWorkOrderLaboursDtos);
		model.addAttribute("listOfCanceledWorkorderVehicles", cancelWorkOrderVehiclesDtos);

		
		model.addAttribute("title", "WorkOrder| Cancel | Maintenance Mangement");
		return "/pages/work_orders/view_cancel_workorder_details";
	}

}
