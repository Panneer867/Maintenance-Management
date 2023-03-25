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
import com.ingroinfo.mm.dao.IndentItemRequestRepository;
import com.ingroinfo.mm.dao.IndentLabourRequestRepository;
import com.ingroinfo.mm.dao.IndentVehicleRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderItemRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderLabourRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderVehicleRequestRepository;
import com.ingroinfo.mm.dao.WapWorkOrderItemRequestRepository;
import com.ingroinfo.mm.dao.WapWorkOrderLabourRequestRepository;
import com.ingroinfo.mm.dao.WapWorkOrderVehicleRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderItemsRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderLabourRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderVehicleRequestRepository;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.entity.IndentItemRequest;
import com.ingroinfo.mm.entity.IndentLabourRequest;
import com.ingroinfo.mm.entity.IndentVehicleRequest;
import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;
import com.ingroinfo.mm.entity.WapWorkOrderItemRequest;
import com.ingroinfo.mm.entity.WapWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.WapWorkOrderVehicleRequest;
import com.ingroinfo.mm.entity.WorkOrderItemsRequest;
import com.ingroinfo.mm.entity.WorkOrderLabourRequest;
import com.ingroinfo.mm.entity.WorkOrderVehicleRequest;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;
import com.ingroinfo.mm.service.TaskUpdateService;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

	@Autowired
	private TaskUpdateService taskUpdateService;
	@Autowired
	private IndentItemRequestRepository indentItemRequestRepo;
	@Autowired
	private IndentLabourRequestRepository indentLabourRequestRepo;
	@Autowired
	private IndentVehicleRequestRepository indentVehicleRequestRepo;
	@Autowired
	private TempWorkOrderItemRequestRepository tempWorkOrderItemRequestRepo;
	@Autowired
	private TempWorkOrderLabourRequestRepository tempWorkOrderLabourRequestRepo;
	@Autowired
	private TempWorkOrderVehicleRequestRepository tempWorkOrderVehicleRequestRepo;
	@Autowired
	private WapWorkOrderItemRequestRepository wapWorkOrderItemRequestRepo;
	@Autowired
	private WapWorkOrderLabourRequestRepository wapWorkOrderLabourRequestRepo;
	@Autowired
	private WapWorkOrderVehicleRequestRepository wapWorkOrderVehicleRequestRepo;
	@Autowired
	private WorkOrderItemsRequestRepository workOrderItemRequestRepo;
	@Autowired
	private WorkOrderLabourRequestRepository workOrderLabourRequestRepo;
	@Autowired
	private WorkOrderVehicleRequestRepository workOrderVehicleRequestRepo;
	
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

		List<IndentItemRequest> itemRequests = this.indentItemRequestRepo.findByComplNo(complNo, indentNo);
		List<IndentLabourRequest> labourRequests = this.indentLabourRequestRepo.findByComplNo(complNo, indentNo);
		List<IndentVehicleRequest> vehicleRequests = this.indentVehicleRequestRepo.findByComplNo(complNo, indentNo);

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
		for (IndentItemRequest indentItems : itemRequests) {
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
			for (IndentLabourRequest indentLabors : labourRequests) {
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
			for (IndentVehicleRequest indentVehicle : vehicleRequests) {
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

		model.addAttribute("listOfMaterials", itemRequests);
		model.addAttribute("listOfLabors", labourRequests);
		model.addAttribute("listOfVehicles", vehicleRequests);
		model.addAttribute("title", "Approval | Indent | Manintenance Management");
		return "/pages/approvals/indent_approvals";
	}

	// Handler For Submit Approved Indent Data
	@GetMapping("/indent/approve/{complNo}/{indentNo}")
	public String approveIndentData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
			Principal principal, HttpSession session) {

		List<IndentItemRequest> itemRequests = this.indentItemRequestRepo.findByComplNo(complNo, indentNo);
		List<IndentLabourRequest> labourRequests = this.indentLabourRequestRepo.findByComplNo(complNo, indentNo);
		List<IndentVehicleRequest> vehicleRequests = this.indentVehicleRequestRepo.findByComplNo(complNo, indentNo);

		if (itemRequests != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<TempWorkOrderItemRequest> tempWorkOrderItems = itemRequests.stream()
					.map(indentItem -> modelMapper.map(indentItem, TempWorkOrderItemRequest.class))
					.collect(Collectors.toList());

			tempWorkOrderItems.forEach(tempWorkItem -> {
				tempWorkItem.setUserName(principal.getName());
				tempWorkItem.setStatus("Approved");
			});

			this.tempWorkOrderItemRequestRepo.saveAll(tempWorkOrderItems);
			this.indentItemRequestRepo.deleteAllByComplNo(complNo);
		}
		if (labourRequests != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<TempWorkOrderLabourRequest> tempWorkOrderLabors = labourRequests.stream()
					.map(indentLabor -> modelMapper.map(indentLabor, TempWorkOrderLabourRequest.class))
					.collect(Collectors.toList());

			tempWorkOrderLabors.forEach(tempWorkLabor -> {
				tempWorkLabor.setUserName(principal.getName());
				tempWorkLabor.setStatus("Approved");
			});

			this.tempWorkOrderLabourRequestRepo.saveAll(tempWorkOrderLabors);
			this.indentLabourRequestRepo.deleteAllByComplNo(complNo);
		}
		if (vehicleRequests != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles = vehicleRequests.stream()
					.map((indentVehicle) -> modelMapper.map(indentVehicle, TempWorkOrderVehicleRequest.class))
					.collect(Collectors.toList());

			tempWorkOrderVehicles.forEach(tempWorkVehicle -> {
				tempWorkVehicle.setUserName(principal.getName());
				tempWorkVehicle.setStatus("Approved");
			});

			this.tempWorkOrderVehicleRequestRepo.saveAll(tempWorkOrderVehicles);
			this.indentVehicleRequestRepo.deleteAllByComplNo(complNo);
		}

		ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
		oldcomplaintDto.setComplStatus("work_order_request");
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

			List<WapWorkOrderItemRequest> wapItemRequests = this.wapWorkOrderItemRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
			List<WapWorkOrderLabourRequest> wapLabourRequests = this.wapWorkOrderLabourRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
			List<WapWorkOrderVehicleRequest> WapVehicleRequests = this.wapWorkOrderVehicleRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);

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
			for (WapWorkOrderItemRequest wapWorkOrderItems : wapItemRequests) {
				if (wapWorkOrderItems.getIndentNo() != null || wapWorkOrderItems.getComplNo() != null) {
					indentNumber = wapWorkOrderItems.getIndentNo();
					complNumber = wapWorkOrderItems.getComplNo();
					expStartDate = wapWorkOrderItems.getStartDate();
					department = wapWorkOrderItems.getDepartmentName();
					division = wapWorkOrderItems.getDivision();
					subDivision = wapWorkOrderItems.getSubDivision();
					workPriprity = wapWorkOrderItems.getWorkPriority();
					workSite = wapWorkOrderItems.getWorkSite();
					contactNo = wapWorkOrderItems.getContactNo();
					break;
				}
			}
			// Check for indent number in TempIndentLabourRequest list
			if (indentNumber == null || complNumber == null) {
				for (WapWorkOrderLabourRequest wapWorkOrderLabors : wapLabourRequests) {
					if (wapWorkOrderLabors.getIndentNo() != null || wapWorkOrderLabors.getComplNo() != null) {
						indentNumber = wapWorkOrderLabors.getIndentNo();
						complNumber = wapWorkOrderLabors.getComplNo();
						expStartDate = wapWorkOrderLabors.getStartDate();
						department = wapWorkOrderLabors.getDepartmentName();
						division = wapWorkOrderLabors.getDivision();
						subDivision = wapWorkOrderLabors.getSubDivision();
						workPriprity = wapWorkOrderLabors.getWorkPriority();
						workSite = wapWorkOrderLabors.getWorkSite();
						contactNo = wapWorkOrderLabors.getContactNo();
						break;
					}
				}
			}
			// Check for indent number in TempIndentVehicleRequest list
			if (indentNumber == null || complNumber == null) {
				for (WapWorkOrderVehicleRequest wapWorkOrderVehicle : WapVehicleRequests) {
					if (wapWorkOrderVehicle.getIndentNo() != null || wapWorkOrderVehicle.getComplNo() != null) {
						indentNumber = wapWorkOrderVehicle.getIndentNo();
						complNumber = wapWorkOrderVehicle.getComplNo();
						expStartDate = wapWorkOrderVehicle.getStartDate();
						department = wapWorkOrderVehicle.getDepartmentName();
						division = wapWorkOrderVehicle.getDivision();
						subDivision = wapWorkOrderVehicle.getSubDivision();
						workPriprity = wapWorkOrderVehicle.getWorkPriority();
						workSite = wapWorkOrderVehicle.getWorkSite();
						contactNo = wapWorkOrderVehicle.getContactNo();
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

			model.addAttribute("listOfMaterials", wapItemRequests);
			model.addAttribute("listOfLabors", wapLabourRequests);
			model.addAttribute("listOfVehicles", WapVehicleRequests);
			model.addAttribute("title", "Approval | WorkOrder | Manintenance Management");
			return "/pages/approvals/workorder_approvals";
		}

		// Handler For Approve Work Order Data
		@GetMapping("/workorder/approve/{complNo}/{indentNo}")
		public String approveWorkOrderData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
				Principal principal, HttpSession session) {

			List<WapWorkOrderItemRequest> wapItemRequests = this.wapWorkOrderItemRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
			List<WapWorkOrderLabourRequest> wapLabourRequests = this.wapWorkOrderLabourRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
			List<WapWorkOrderVehicleRequest> WapVehicleRequests = this.wapWorkOrderVehicleRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);

			List<WorkOrderItemsRequest> workOrderItemsRequests = new ArrayList<>();
			List<WorkOrderLabourRequest> workOrderLaborRequests = new ArrayList<>();
			List<WorkOrderVehicleRequest> workOrderVehicleRequests = new ArrayList<>();

			if (wapItemRequests != null) {

				for (WapWorkOrderItemRequest wapWorkOrderItem : wapItemRequests) {
					WorkOrderItemsRequest workOrderItems = new WorkOrderItemsRequest();
					String complNumber = "101" + wapWorkOrderItem.getComplNo();
					workOrderItems.setWorkOrderNo(Long.parseLong(complNumber));
					workOrderItems.setDepartmentName(wapWorkOrderItem.getDepartmentName());
					workOrderItems.setItemId(wapWorkOrderItem.getItemId());
					workOrderItems.setStockType(wapWorkOrderItem.getStockType());
					workOrderItems.setQuantity(Integer.parseInt(wapWorkOrderItem.getQuantity()));
					workOrderItems.setUsername(principal.getName());
					workOrderItems.setIndentNo(wapWorkOrderItem.getIndentNo());
					workOrderItems.setComplNo(wapWorkOrderItem.getComplNo());
					workOrderItems.setDivision(wapWorkOrderItem.getDivision());
					workOrderItems.setSubDivision(wapWorkOrderItem.getSubDivision());
					workOrderItems.setWorkSite(wapWorkOrderItem.getWorkSite());
					workOrderItems.setStartDate(wapWorkOrderItem.getStartDate());
					workOrderItems.setEndDate(wapWorkOrderItem.getEndDate());
					workOrderItems.setContactNo(wapWorkOrderItem.getContactNo());
					workOrderItems.setComplDtls(wapWorkOrderItem.getComplDtls());
					workOrderItems.setWorkPriority(wapWorkOrderItem.getWorkPriority());
					workOrderItems.setCategoryName(wapWorkOrderItem.getCategoryName());
					workOrderItems.setItemName(wapWorkOrderItem.getItemName());
					workOrderItems.setUnitOfMesure(wapWorkOrderItem.getUnitOfMesure());
					workOrderItems.setHsnCode(wapWorkOrderItem.getHsnCode());
					workOrderItems.setStockTypeName(wapWorkOrderItem.getStockTypeName());

					workOrderItemsRequests.add(workOrderItems);
				}

				this.workOrderItemRequestRepo.saveAll(workOrderItemsRequests);				
			}
			if (wapLabourRequests != null) {

				for (WapWorkOrderLabourRequest wapWorkOrderLabor : wapLabourRequests) {
					WorkOrderLabourRequest workOrderLabors = new WorkOrderLabourRequest();
					String complNumber = "101" + wapWorkOrderLabor.getComplNo();
					workOrderLabors.setWorkOrderNo(Long.parseLong(complNumber));
					workOrderLabors.setUsername(principal.getName());
					workOrderLabors.setDepartmentName(wapWorkOrderLabor.getDepartmentName());
					workOrderLabors.setIndentNo(wapWorkOrderLabor.getIndentNo());
					workOrderLabors.setComplNo(wapWorkOrderLabor.getComplNo());
					workOrderLabors.setDivision(wapWorkOrderLabor.getDivision());
					workOrderLabors.setSubDivision(wapWorkOrderLabor.getSubDivision());
					workOrderLabors.setWorkSite(wapWorkOrderLabor.getWorkSite());
					workOrderLabors.setStartDate(wapWorkOrderLabor.getStartDate());
					workOrderLabors.setEndDate(wapWorkOrderLabor.getEndDate());
					workOrderLabors.setContactNo(wapWorkOrderLabor.getContactNo());
					workOrderLabors.setComplDtls(wapWorkOrderLabor.getComplDtls());
					workOrderLabors.setWorkPriority(wapWorkOrderLabor.getWorkPriority());
					workOrderLabors.setEmpCategory(wapWorkOrderLabor.getEmpCategory());
					workOrderLabors.setMembers(wapWorkOrderLabor.getMembers());
					workOrderLabors.setDaysRequired(wapWorkOrderLabor.getDaysRequired());
					workOrderLabors.setTimeRequired(wapWorkOrderLabor.getTimeRequired());

					workOrderLaborRequests.add(workOrderLabors);
				}

				this.workOrderLabourRequestRepo.saveAll(workOrderLaborRequests);

			}
			if (WapVehicleRequests != null) {

				for (WapWorkOrderVehicleRequest wapWorkOrderVehicle : WapVehicleRequests) {
					WorkOrderVehicleRequest workOrderVehicles = new WorkOrderVehicleRequest();
					String complNumber = "101" + wapWorkOrderVehicle.getComplNo();
					workOrderVehicles.setWorkOrderNo(Long.parseLong(complNumber));
					workOrderVehicles.setUsername(principal.getName());
					workOrderVehicles.setDepartmentName(wapWorkOrderVehicle.getDepartmentName());
					workOrderVehicles.setIndentNo(wapWorkOrderVehicle.getIndentNo());
					workOrderVehicles.setComplNo(wapWorkOrderVehicle.getComplNo());
					workOrderVehicles.setDivision(wapWorkOrderVehicle.getDivision());
					workOrderVehicles.setSubDivision(wapWorkOrderVehicle.getSubDivision());
					workOrderVehicles.setWorkSite(wapWorkOrderVehicle.getWorkSite());
					workOrderVehicles.setStartDate(wapWorkOrderVehicle.getStartDate());
					workOrderVehicles.setEndDate(wapWorkOrderVehicle.getEndDate());
					workOrderVehicles.setContactNo(wapWorkOrderVehicle.getContactNo());
					workOrderVehicles.setComplDtls(wapWorkOrderVehicle.getComplDtls());
					workOrderVehicles.setWorkPriority(wapWorkOrderVehicle.getWorkPriority());
					workOrderVehicles.setVehicleType(wapWorkOrderVehicle.getVehicleType());
					workOrderVehicles.setVehicleNo(wapWorkOrderVehicle.getVehicleNo());
					workOrderVehicles.setDriverName(wapWorkOrderVehicle.getDriverName());
					workOrderVehicles.setDriverPhone(wapWorkOrderVehicle.getDriverPhone());
					workOrderVehicles.setMeterReading(wapWorkOrderVehicle.getMeterReading());
					workOrderVehicles.setStratTime(wapWorkOrderVehicle.getStratTime());
					workOrderVehicles.setVehicleId(wapWorkOrderVehicle.getVehicleId());

					workOrderVehicleRequests.add(workOrderVehicles);
				}

				this.workOrderVehicleRequestRepo.saveAll(workOrderVehicleRequests);
			}

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
