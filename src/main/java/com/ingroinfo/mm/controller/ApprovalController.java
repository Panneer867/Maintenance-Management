package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.dao.IndentApprovedItemsRepository;
import com.ingroinfo.mm.dao.IndentApprovedLaboursRepository;
import com.ingroinfo.mm.dao.IndentApprovedVehiclesRepository;
import com.ingroinfo.mm.dao.TempIndentItemRequestRepository;
import com.ingroinfo.mm.dao.TempIndentLabourRequestRepository;
import com.ingroinfo.mm.dao.TempIndentVehicleRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderItemsRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderApprovedLaboursRepository;
import com.ingroinfo.mm.dao.WorkOrderApprovedVehiclesRepository;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.entity.IndentApprovedItems;
import com.ingroinfo.mm.entity.IndentApprovedLabours;
import com.ingroinfo.mm.entity.IndentApprovedVehicles;
import com.ingroinfo.mm.entity.TempIndentItemRequest;
import com.ingroinfo.mm.entity.TempIndentLabourRequest;
import com.ingroinfo.mm.entity.TempIndentVehicleRequest;
import com.ingroinfo.mm.entity.WorkOrderItemsRequest;
import com.ingroinfo.mm.entity.WorkOrderApprovedLabours;
import com.ingroinfo.mm.entity.WorkOrderApprovedVehicles;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.TaskUpdateService;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

	@Autowired
	private TaskUpdateService taskUpdateService;
	@Autowired
	private TempIndentItemRequestRepository tempIndentItemRequestRepo;
	@Autowired
	private TempIndentLabourRequestRepository tempIndentLabourRequestRepo;
	@Autowired
	private TempIndentVehicleRequestRepository tempIndentVehicleRequestRepo;
	@Autowired
	private IndentApprovedItemsRepository indentApprovedItemsRepo;
	@Autowired
	private IndentApprovedLaboursRepository indentApprovedLaboursRepo;
	@Autowired
	private IndentApprovedVehiclesRepository indentApprovedVehiclesRepo;
	@Autowired
	private WorkOrderItemsRequestRepository workOrderItemRequestRepo;
	@Autowired
	private WorkOrderApprovedLaboursRepository workOrderLabourRequestRepo;
	@Autowired
	private WorkOrderApprovedVehiclesRepository workOrderVehicleRequestRepo;
	
	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('APPROVALS')")
	public String hrApprovals(Model model) {		
		return "approvals_page";
	}

	// Handler For Open Indent Approval Page
	@GetMapping("/indent")
	public String displayIndentApproval(Model model) {
		String complStatus = "waiting_for_indent_approval";
		List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCompl", complDtos);
		model.addAttribute("title", "Approval | Indent | Manintenance Management");
		return "/pages/approvals/indent_approvals";
	}

	// Handler For get Indent Data For Approval
	@GetMapping("/indent/get/{complNo}/{indentNo}")
	public String getIndentdataByIndentNumber(@PathVariable String complNo, @PathVariable String indentNo,
			Model model) {
		String complStatus = "waiting_for_indent_approval";
		List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCompl", complDtos);

		List<TempIndentItemRequest> tempIndentItemRequests = this.tempIndentItemRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
		List<TempIndentLabourRequest> tempIndentLabourRequests = this.tempIndentLabourRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
		List<TempIndentVehicleRequest> TempIndentVehicleRequests = this.tempIndentVehicleRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);

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
		for (TempIndentItemRequest indentItems : tempIndentItemRequests) {
			if (indentItems.getIndentNo() != null || indentItems.getComplNo() != null) {
				indentNumber = indentItems.getIndentNo();
				complNumber = indentItems.getComplNo();
				expStartDate = indentItems.getStartDate();
				department = indentItems.getDepartmentName();
				division = indentItems.getDivision();
				subDivision = indentItems.getSubDivision();
				workPriprity = indentItems.getWorkPriority();
				workSite = indentItems.getWorkSite();
				contactNo = indentItems.getContactNo();
				break;
			}
		}
		// Check for indent number in TempIndentLabourRequest list
		if (indentNumber == null || complNumber == null) {
			for (TempIndentLabourRequest indentLabors : tempIndentLabourRequests) {
				if (indentLabors.getIndentNo() != null || indentLabors.getComplNo() != null) {
					indentNumber = indentLabors.getIndentNo();
					complNumber = indentLabors.getComplNo();
					expStartDate = indentLabors.getStartDate();
					department = indentLabors.getDepartmentName();
					division = indentLabors.getDivision();
					subDivision = indentLabors.getSubDivision();
					workPriprity = indentLabors.getWorkPriority();
					workSite = indentLabors.getWorkSite();
					contactNo = indentLabors.getContactNo();
					break;
				}
			}
		}
		// Check for indent number in TempIndentVehicleRequest list
		if (indentNumber == null || complNumber == null) {
			for (TempIndentVehicleRequest indentVehicle : TempIndentVehicleRequests) {
				if (indentVehicle.getIndentNo() != null || indentVehicle.getComplNo() != null) {
					indentNumber = indentVehicle.getIndentNo();
					complNumber = indentVehicle.getComplNo();
					expStartDate = indentVehicle.getStartDate();
					department = indentVehicle.getDepartmentName();
					division = indentVehicle.getDivision();
					subDivision = indentVehicle.getSubDivision();
					workPriprity = indentVehicle.getWorkPriority();
					workSite = indentVehicle.getWorkSite();
					contactNo = indentVehicle.getContactNo();
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

		model.addAttribute("listOfMaterials", tempIndentItemRequests);
		model.addAttribute("listOfLabors", tempIndentLabourRequests);
		model.addAttribute("listOfVehicles", TempIndentVehicleRequests);
		model.addAttribute("title", "Approval | Indent | Manintenance Management");
		return "/pages/approvals/indent_approvals";
	}

	// Handler For Submit Approved Indent Data
	@GetMapping("/indent/approve/{complNo}/{indentNo}")
	public String approveIndentData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
			Principal principal, HttpSession session) {

		List<TempIndentItemRequest> tempIndentItemRequests = this.tempIndentItemRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
		List<TempIndentLabourRequest> tempIndentLabourRequests = this.tempIndentLabourRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
		List<TempIndentVehicleRequest> TempIndentVehicleRequests = this.tempIndentVehicleRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);

		if (tempIndentItemRequests != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<IndentApprovedItems> approvedIndentItems = tempIndentItemRequests.stream()
					.map((indentItems) -> modelMapper.map(indentItems, IndentApprovedItems.class))
					.collect(Collectors.toList());

			approvedIndentItems.forEach(indentItem -> {
				indentItem.setUserName(principal.getName());
				indentItem.setIndentApproved("Y");
				indentItem.setApprovedSts("N");
			});

			this.indentApprovedItemsRepo.saveAll(approvedIndentItems);
			this.tempIndentItemRequestRepo.deleteAllByComplNo(complNo);
		}
		if (tempIndentLabourRequests != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<IndentApprovedLabours> approvedIndentLabors = tempIndentLabourRequests.stream()
					.map(indentLabors -> modelMapper.map(indentLabors, IndentApprovedLabours.class))
					.collect(Collectors.toList());

			approvedIndentLabors.forEach(indentLabor -> {
				indentLabor.setUserName(principal.getName());
				indentLabor.setIndentApproved("Y");
				indentLabor.setApprovedSts("N");
			});

			this.indentApprovedLaboursRepo.saveAll(approvedIndentLabors);
			this.tempIndentLabourRequestRepo.deleteAllByComplNo(complNo);
		}
		if (TempIndentVehicleRequests != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<IndentApprovedVehicles> approvedIndentVehicles = TempIndentVehicleRequests.stream()
					.map((indentVehicles) -> modelMapper.map(indentVehicles, IndentApprovedVehicles.class))
					.collect(Collectors.toList());

			approvedIndentVehicles.forEach(indentVehicle -> {
				indentVehicle.setUserName(principal.getName());
				indentVehicle.setIndentApproved("Y");
				indentVehicle.setApprovedSts("N");
			});

			this.indentApprovedVehiclesRepo.saveAll(approvedIndentVehicles);
			this.tempIndentVehicleRequestRepo.deleteAllByComplNo(complNo);
		}

		ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
		oldcomplaintDto.setComplStatus("approved_indent");
		oldcomplaintDto.setIndentApprovedBy(principal.getName());
		oldcomplaintDto.setIndentApprovedDate(new java.sql.Date(System.currentTimeMillis()));
		this.taskUpdateService.saveComplaint(oldcomplaintDto);
		session.setAttribute("message", new Message("Approved Sucessfully Done !!", "success"));
		return "redirect:/approval/indent";
	}
	
	// Handler For Open Work Order Approval Page
		@GetMapping("/workorder")
		public String displayWorkOrderApproval(Model model) {
			String complStatus = "waiting_for_workorder_approval";
			List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
			model.addAttribute("listOfCompl", complDtos);
			model.addAttribute("title", "Approval | WorkOrder | Manintenance Management");
			return "/pages/approvals/workorder_approvals";
		}
		
		// Handler For get WorkOrder Data For Approval
		@GetMapping("/workorder/get/{complNo}/{indentNo}")
		public String getWorkOrderdataByComplNoAndIndentNumber(@PathVariable String complNo, @PathVariable String indentNo,
				Model model) {
			String complStatus = "waiting_for_workorder_approval";
			List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
			model.addAttribute("listOfCompl", complDtos);

			//List<WapWorkOrderItemRequest> wapItemRequests = this.wapWorkOrderItemRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
			//List<WapWorkOrderLabourRequest> wapLabourRequests = this.wapWorkOrderLabourRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
			//List<WapWorkOrderVehicleRequest> WapVehicleRequests = this.wapWorkOrderVehicleRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);

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
			/*
			 * for (WapWorkOrderItemRequest wapWorkOrderItems : wapItemRequests) { if
			 * (wapWorkOrderItems.getIndentNo() != null || wapWorkOrderItems.getComplNo() !=
			 * null) { indentNumber = wapWorkOrderItems.getIndentNo(); complNumber =
			 * wapWorkOrderItems.getComplNo(); expStartDate =
			 * wapWorkOrderItems.getStartDate(); department =
			 * wapWorkOrderItems.getDepartmentName(); division =
			 * wapWorkOrderItems.getDivision(); subDivision =
			 * wapWorkOrderItems.getSubDivision(); workPriprity =
			 * wapWorkOrderItems.getWorkPriority(); workSite =
			 * wapWorkOrderItems.getWorkSite(); contactNo =
			 * wapWorkOrderItems.getContactNo(); break; } } // Check for indent number in
			 * TempIndentLabourRequest list if (indentNumber == null || complNumber == null)
			 * { for (WapWorkOrderLabourRequest wapWorkOrderLabors : wapLabourRequests) { if
			 * (wapWorkOrderLabors.getIndentNo() != null || wapWorkOrderLabors.getComplNo()
			 * != null) { indentNumber = wapWorkOrderLabors.getIndentNo(); complNumber =
			 * wapWorkOrderLabors.getComplNo(); expStartDate =
			 * wapWorkOrderLabors.getStartDate(); department =
			 * wapWorkOrderLabors.getDepartmentName(); division =
			 * wapWorkOrderLabors.getDivision(); subDivision =
			 * wapWorkOrderLabors.getSubDivision(); workPriprity =
			 * wapWorkOrderLabors.getWorkPriority(); workSite =
			 * wapWorkOrderLabors.getWorkSite(); contactNo =
			 * wapWorkOrderLabors.getContactNo(); break; } } } // Check for indent number in
			 * TempIndentVehicleRequest list if (indentNumber == null || complNumber ==
			 * null) { for (WapWorkOrderVehicleRequest wapWorkOrderVehicle :
			 * WapVehicleRequests) { if (wapWorkOrderVehicle.getIndentNo() != null ||
			 * wapWorkOrderVehicle.getComplNo() != null) { indentNumber =
			 * wapWorkOrderVehicle.getIndentNo(); complNumber =
			 * wapWorkOrderVehicle.getComplNo(); expStartDate =
			 * wapWorkOrderVehicle.getStartDate(); department =
			 * wapWorkOrderVehicle.getDepartmentName(); division =
			 * wapWorkOrderVehicle.getDivision(); subDivision =
			 * wapWorkOrderVehicle.getSubDivision(); workPriprity =
			 * wapWorkOrderVehicle.getWorkPriority(); workSite =
			 * wapWorkOrderVehicle.getWorkSite(); contactNo =
			 * wapWorkOrderVehicle.getContactNo(); break; } } }
			 * 
			 * model.addAttribute("complNo", complNumber); model.addAttribute("indentNo",
			 * indentNumber); model.addAttribute("startDate", expStartDate);
			 * model.addAttribute("department", department); model.addAttribute("division",
			 * division); model.addAttribute("subdivision", subDivision);
			 * model.addAttribute("workPriority", workPriprity);
			 * model.addAttribute("worksite", workSite); model.addAttribute("contactNo",
			 * contactNo);
			 * 
			 * model.addAttribute("listOfMaterials", wapItemRequests);
			 * model.addAttribute("listOfLabors", wapLabourRequests);
			 * model.addAttribute("listOfVehicles", WapVehicleRequests);
			 */
			model.addAttribute("title", "Approval | WorkOrder | Manintenance Management");
			return "/pages/approvals/workorder_approvals";
		}

		// Handler For Approve Work Order Data
		@GetMapping("/workorder/approve/{complNo}/{indentNo}")
		public String approveWorkOrderData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
				Principal principal, HttpSession session) {

			/*
			 * List<WapWorkOrderItemRequest> wapItemRequests =
			 * this.wapWorkOrderItemRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
			 * List<WapWorkOrderLabourRequest> wapLabourRequests =
			 * this.wapWorkOrderLabourRequestRepo.getByComplNoAndIndentNo(complNo,
			 * indentNo); List<WapWorkOrderVehicleRequest> WapVehicleRequests =
			 * this.wapWorkOrderVehicleRequestRepo.getByComplNoAndIndentNo(complNo,
			 * indentNo);
			 * 
			 * List<WorkOrderItemsRequest> workOrderItemsRequests = new ArrayList<>();
			 * List<WorkOrderLabourRequest> workOrderLaborRequests = new ArrayList<>();
			 * List<WorkOrderVehicleRequest> workOrderVehicleRequests = new ArrayList<>();
			 * 
			 * if (wapItemRequests != null) {
			 * 
			 * for (WapWorkOrderItemRequest wapWorkOrderItem : wapItemRequests) {
			 * WorkOrderItemsRequest workOrderItems = new WorkOrderItemsRequest(); String
			 * complNumber = "101" + wapWorkOrderItem.getComplNo();
			 * workOrderItems.setWorkOrderNo(Long.parseLong(complNumber));
			 * workOrderItems.setDepartmentName(wapWorkOrderItem.getDepartmentName());
			 * workOrderItems.setItemId(wapWorkOrderItem.getItemId());
			 * workOrderItems.setStockType(wapWorkOrderItem.getStockType());
			 * workOrderItems.setQuantity(Integer.parseInt(wapWorkOrderItem.getQuantity()));
			 * workOrderItems.setUsername(principal.getName());
			 * workOrderItems.setIndentNo(wapWorkOrderItem.getIndentNo());
			 * workOrderItems.setComplNo(wapWorkOrderItem.getComplNo());
			 * workOrderItems.setDivision(wapWorkOrderItem.getDivision());
			 * workOrderItems.setSubDivision(wapWorkOrderItem.getSubDivision());
			 * workOrderItems.setWorkSite(wapWorkOrderItem.getWorkSite());
			 * workOrderItems.setStartDate(wapWorkOrderItem.getStartDate());
			 * workOrderItems.setEndDate(wapWorkOrderItem.getEndDate());
			 * workOrderItems.setContactNo(wapWorkOrderItem.getContactNo());
			 * workOrderItems.setComplDtls(wapWorkOrderItem.getComplDtls());
			 * workOrderItems.setWorkPriority(wapWorkOrderItem.getWorkPriority());
			 * workOrderItems.setCategoryName(wapWorkOrderItem.getCategoryName());
			 * workOrderItems.setItemName(wapWorkOrderItem.getItemName());
			 * workOrderItems.setUnitOfMesure(wapWorkOrderItem.getUnitOfMesure());
			 * workOrderItems.setHsnCode(wapWorkOrderItem.getHsnCode());
			 * workOrderItems.setStockTypeName(wapWorkOrderItem.getStockTypeName());
			 * 
			 * workOrderItemsRequests.add(workOrderItems); }
			 * 
			 * this.workOrderItemRequestRepo.saveAll(workOrderItemsRequests); } if
			 * (wapLabourRequests != null) {
			 * 
			 * for (WapWorkOrderLabourRequest wapWorkOrderLabor : wapLabourRequests) {
			 * WorkOrderLabourRequest workOrderLabors = new WorkOrderLabourRequest(); String
			 * complNumber = "101" + wapWorkOrderLabor.getComplNo();
			 * workOrderLabors.setWorkOrderNo(Long.parseLong(complNumber));
			 * workOrderLabors.setUsername(principal.getName());
			 * workOrderLabors.setDepartmentName(wapWorkOrderLabor.getDepartmentName());
			 * workOrderLabors.setIndentNo(wapWorkOrderLabor.getIndentNo());
			 * workOrderLabors.setComplNo(wapWorkOrderLabor.getComplNo());
			 * workOrderLabors.setDivision(wapWorkOrderLabor.getDivision());
			 * workOrderLabors.setSubDivision(wapWorkOrderLabor.getSubDivision());
			 * workOrderLabors.setWorkSite(wapWorkOrderLabor.getWorkSite());
			 * workOrderLabors.setStartDate(wapWorkOrderLabor.getStartDate());
			 * workOrderLabors.setEndDate(wapWorkOrderLabor.getEndDate());
			 * workOrderLabors.setContactNo(wapWorkOrderLabor.getContactNo());
			 * workOrderLabors.setComplDtls(wapWorkOrderLabor.getComplDtls());
			 * workOrderLabors.setWorkPriority(wapWorkOrderLabor.getWorkPriority());
			 * workOrderLabors.setEmpCategory(wapWorkOrderLabor.getEmpCategory());
			 * workOrderLabors.setMembers(wapWorkOrderLabor.getMembers());
			 * workOrderLabors.setDaysRequired(wapWorkOrderLabor.getDaysRequired());
			 * workOrderLabors.setTimeRequired(wapWorkOrderLabor.getTimeRequired());
			 * 
			 * workOrderLaborRequests.add(workOrderLabors); }
			 * 
			 * this.workOrderLabourRequestRepo.saveAll(workOrderLaborRequests);
			 * 
			 * } if (WapVehicleRequests != null) {
			 * 
			 * for (WapWorkOrderVehicleRequest wapWorkOrderVehicle : WapVehicleRequests) {
			 * WorkOrderVehicleRequest workOrderVehicles = new WorkOrderVehicleRequest();
			 * String complNumber = "101" + wapWorkOrderVehicle.getComplNo();
			 * workOrderVehicles.setWorkOrderNo(Long.parseLong(complNumber));
			 * workOrderVehicles.setUsername(principal.getName());
			 * workOrderVehicles.setDepartmentName(wapWorkOrderVehicle.getDepartmentName());
			 * workOrderVehicles.setIndentNo(wapWorkOrderVehicle.getIndentNo());
			 * workOrderVehicles.setComplNo(wapWorkOrderVehicle.getComplNo());
			 * workOrderVehicles.setDivision(wapWorkOrderVehicle.getDivision());
			 * workOrderVehicles.setSubDivision(wapWorkOrderVehicle.getSubDivision());
			 * workOrderVehicles.setWorkSite(wapWorkOrderVehicle.getWorkSite());
			 * workOrderVehicles.setStartDate(wapWorkOrderVehicle.getStartDate());
			 * workOrderVehicles.setEndDate(wapWorkOrderVehicle.getEndDate());
			 * workOrderVehicles.setContactNo(wapWorkOrderVehicle.getContactNo());
			 * workOrderVehicles.setComplDtls(wapWorkOrderVehicle.getComplDtls());
			 * workOrderVehicles.setWorkPriority(wapWorkOrderVehicle.getWorkPriority());
			 * workOrderVehicles.setVehicleType(wapWorkOrderVehicle.getVehicleType());
			 * workOrderVehicles.setVehicleNo(wapWorkOrderVehicle.getVehicleNo());
			 * workOrderVehicles.setDriverName(wapWorkOrderVehicle.getDriverName());
			 * workOrderVehicles.setDriverPhone(wapWorkOrderVehicle.getDriverPhone());
			 * workOrderVehicles.setMeterReading(wapWorkOrderVehicle.getMeterReading());
			 * workOrderVehicles.setStratTime(wapWorkOrderVehicle.getStratTime());
			 * workOrderVehicles.setVehicleId(wapWorkOrderVehicle.getVehicleId());
			 * 
			 * workOrderVehicleRequests.add(workOrderVehicles); }
			 * 
			 * this.workOrderVehicleRequestRepo.saveAll(workOrderVehicleRequests); }
			 */

			ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
			oldcomplaintDto.setComplStatus("work_order_approved");
			oldcomplaintDto.setWorkorderApprovedBy(principal.getName());
			oldcomplaintDto.setWorkOrder("101"+complNo);
			oldcomplaintDto.setWorkorderApprovedDate(new java.sql.Date(System.currentTimeMillis()));
			this.taskUpdateService.saveComplaint(oldcomplaintDto);
			session.setAttribute("message", new Message("Workorder Approved Sucessfully !!", "success"));
			return "redirect:/approval/workorder";
		}
		
}
