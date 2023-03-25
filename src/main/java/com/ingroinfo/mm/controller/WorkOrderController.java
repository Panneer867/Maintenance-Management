package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.dao.TempWorkOrderItemRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderLabourRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderVehicleRequestRepository;
import com.ingroinfo.mm.dao.WapWorkOrderItemRequestRepository;
import com.ingroinfo.mm.dao.WapWorkOrderLabourRequestRepository;
import com.ingroinfo.mm.dao.WapWorkOrderVehicleRequestRepository;
import com.ingroinfo.mm.dto.CancelWorkOrderDto;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.GenerateWorkOrderDto;
import com.ingroinfo.mm.dto.HoldWorkOrderDto;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;
import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;
import com.ingroinfo.mm.entity.WapWorkOrderItemRequest;
import com.ingroinfo.mm.entity.WapWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.WapWorkOrderVehicleRequest;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.CancelWorkOrderService;
import com.ingroinfo.mm.service.GenerateWorkOrderService;
import com.ingroinfo.mm.service.HoldWorkOrderService;
import com.ingroinfo.mm.service.TaskUpdateService;

@Controller
@RequestMapping("/workorder")
public class WorkOrderController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@Autowired
	private GenerateWorkOrderService generateWorkOrdeService;
	@Autowired
	private HoldWorkOrderService holdWorkOrdeService;
	@Autowired
	private CancelWorkOrderService cancelWorkOrdeService;

	@Autowired
	private TaskUpdateService taskUpdateService;
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

	@GetMapping("/generate")
	@PreAuthorize("hasAuthority('GENERATE_WORKORDER')")
	public String genrateWorkOrder(Model model) {
		String complStatus = "work_order_request";
		List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCompl", complDtos);
		model.addAttribute("title", "Work Order | Generate Work Order");
		return "/pages/work_orders/generate_work_order";
	}

	// Handler For get Indent Indent Data For Approval
	@GetMapping("/generate/get/{complNo}/{indentNo}")
	public String getIndentdataByIndentNumber(@PathVariable String complNo, @PathVariable String indentNo,
			Model model) {
		String complStatus = "work_order_request";
		List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCompl", complDtos);

		List<TempWorkOrderItemRequest> tempWorkitemRequests = this.tempWorkOrderItemRequestRepo.findByComplNo(complNo,
				indentNo);
		List<TempWorkOrderLabourRequest> tempWorklabourRequests = this.tempWorkOrderLabourRequestRepo
				.findByComplNo(complNo, indentNo);
		List<TempWorkOrderVehicleRequest> tempWorkvehicleRequests = this.tempWorkOrderVehicleRequestRepo
				.findByComplNo(complNo, indentNo);

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
		for (TempWorkOrderItemRequest tempWorkItems : tempWorkitemRequests) {
			if (tempWorkItems.getIndentNo() != null || tempWorkItems.getComplNo() != null) {
				indentNumber = tempWorkItems.getIndentNo();
				complNumber = tempWorkItems.getComplNo();
				expStartDate = tempWorkItems.getStartDate();
				department = tempWorkItems.getDepartmentName();
				division = tempWorkItems.getDivision();
				subDivision = tempWorkItems.getSubDivision();
				workPriprity = tempWorkItems.getWorkPriority();
				workSite = tempWorkItems.getWorkSite();
				contactNo = tempWorkItems.getContactNo();
				break;
			}
		}
		// Check for indent number in TempIndentLabourRequest list
		if (indentNumber == null || complNumber == null) {
			for (TempWorkOrderLabourRequest tempWorkLabors : tempWorklabourRequests) {
				if (tempWorkLabors.getIndentNo() != null || tempWorkLabors.getComplNo() != null) {
					indentNumber = tempWorkLabors.getIndentNo();
					complNumber = tempWorkLabors.getComplNo();
					expStartDate = tempWorkLabors.getStartDate();
					department = tempWorkLabors.getDepartmentName();
					division = tempWorkLabors.getDivision();
					subDivision = tempWorkLabors.getSubDivision();
					workPriprity = tempWorkLabors.getWorkPriority();
					workSite = tempWorkLabors.getWorkSite();
					contactNo = tempWorkLabors.getContactNo();
					break;
				}
			}
		}
		// Check for indent number in TempIndentVehicleRequest list
		if (indentNumber == null || complNumber == null) {
			for (TempWorkOrderVehicleRequest tempWorkVehicle : tempWorkvehicleRequests) {
				if (tempWorkVehicle.getIndentNo() != null || tempWorkVehicle.getComplNo() != null) {
					indentNumber = tempWorkVehicle.getIndentNo();
					complNumber = tempWorkVehicle.getComplNo();
					expStartDate = tempWorkVehicle.getStartDate();
					department = tempWorkVehicle.getDepartmentName();
					division = tempWorkVehicle.getDivision();
					subDivision = tempWorkVehicle.getSubDivision();
					workPriprity = tempWorkVehicle.getWorkPriority();
					workSite = tempWorkVehicle.getWorkSite();
					contactNo = tempWorkVehicle.getContactNo();
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

		model.addAttribute("listOfMaterials", tempWorkitemRequests);
		model.addAttribute("listOfLabors", tempWorklabourRequests);
		model.addAttribute("listOfVehicles", tempWorkvehicleRequests);
		model.addAttribute("title", "Work Order | Generate Work Order");
		return "/pages/work_orders/generate_work_order";
	}
	
	// Handler For Submit WorkOrder Data For Approval
		@GetMapping("/generate/submit/{complNo}/{indentNo}")
		public String approveIndentData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
				Principal principal, HttpSession session) {

			List<TempWorkOrderItemRequest> tempWorkitemRequests = this.tempWorkOrderItemRequestRepo.findByComplNo(complNo,
					indentNo);
			List<TempWorkOrderLabourRequest> tempWorklabourRequests = this.tempWorkOrderLabourRequestRepo
					.findByComplNo(complNo, indentNo);
			List<TempWorkOrderVehicleRequest> tempWorkvehicleRequests = this.tempWorkOrderVehicleRequestRepo
					.findByComplNo(complNo, indentNo);

			if (tempWorkitemRequests != null) {
				ModelMapper modelMapper = new ModelMapper();
				List<WapWorkOrderItemRequest> wapWorkOrderItems = tempWorkitemRequests.stream()
						.map(wapWorkItems -> modelMapper.map(wapWorkItems, WapWorkOrderItemRequest.class))
						.collect(Collectors.toList());

				wapWorkOrderItems.forEach(wapWorkItem -> {
					wapWorkItem.setUserName(principal.getName());
					wapWorkItem.setStatus("Approved");
				});

				this.wapWorkOrderItemRequestRepo.saveAll(wapWorkOrderItems);
				this.tempWorkOrderItemRequestRepo.deleteAllDataByComplNo(complNo);				
			}
			if (tempWorklabourRequests != null) {
				ModelMapper modelMapper = new ModelMapper();
				List<WapWorkOrderLabourRequest> wapWorkOrderLabors = tempWorklabourRequests.stream()
						.map(wapWorkLabors -> modelMapper.map(wapWorkLabors, WapWorkOrderLabourRequest.class))
						.collect(Collectors.toList());

				wapWorkOrderLabors.forEach(wapWorkLabor -> {
					wapWorkLabor.setUserName(principal.getName());
					wapWorkLabor.setStatus("Approved");
				});

				this.wapWorkOrderLabourRequestRepo.saveAll(wapWorkOrderLabors);	
				this.tempWorkOrderLabourRequestRepo.deleteAllDataByComplNo(complNo);
			}
			if (tempWorkvehicleRequests != null) {
				ModelMapper modelMapper = new ModelMapper();
				List<WapWorkOrderVehicleRequest> wapWorkOrderVehicles = tempWorkvehicleRequests.stream()
						.map((wapWorkVehicles) -> modelMapper.map(wapWorkVehicles, WapWorkOrderVehicleRequest.class))
						.collect(Collectors.toList());

				wapWorkOrderVehicles.forEach(wapWorkVehicle -> {
					wapWorkVehicle.setUserName(principal.getName());
					wapWorkVehicle.setStatus("Approved");
				});

				this.wapWorkOrderVehicleRequestRepo.saveAll(wapWorkOrderVehicles);
				this.tempWorkOrderVehicleRequestRepo.deleteAllDataByComplNo(complNo);
			}

			ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
			oldcomplaintDto.setComplStatus("waiting_for_workorder_approval");			
			this.taskUpdateService.saveComplaint(oldcomplaintDto);
			session.setAttribute("message", new Message("Wokorder Request Successfully Initiated !! Wait For Approval !!", "success"));
			return "redirect:/workorder/generate";
		}

	// To Get single generate Id Data
	@GetMapping("/viewwork/{generateWorkId}")
	public String getGenerateWorkOrderById(@PathVariable("generateWorkId") Long generateWorkId, Model model) {
		GenerateWorkOrderDto generateWorkOrderDto = this.generateWorkOrdeService
				.getGenerateWorkOrderById(generateWorkId);
		model.addAttribute("genWorkOrder", generateWorkOrderDto);
		return "/pages/work_orders/viwe_work_order";
	}

	@GetMapping("/getWorkOrder/{indentNo}")
	public ResponseEntity<GenerateWorkOrderDto> getWorkOrderData(@PathVariable String indentNo) {
		GenerateWorkOrderDto generateWorkOrderDto = this.generateWorkOrdeService
				.findGeneWorkOrderIndentNoById(indentNo);
		return new ResponseEntity<GenerateWorkOrderDto>(generateWorkOrderDto, HttpStatus.OK);
	}

	/***************** HoldWorkOrder Start********** */
	@GetMapping("/hold")
	@PreAuthorize("hasAuthority('HOLD_WORKORDER')")
	public String holdWorkOrder(Model model) {
		List<HoldWorkOrderDto> listOfHoldWorks = this.holdWorkOrdeService.findAllHoldWorkOrder();
		model.addAttribute("listofholdWork", listOfHoldWorks);
		return "/pages/work_orders/hold_work_order";
	}

	// Handler Hold Work Order Data
	@PostMapping("/saveholdworkord")
	public String saveHoldWorkOrder(HoldWorkOrderDto idHold, HttpSession session) {
		this.holdWorkOrdeService.saveHoldWorkOrder(idHold);
		session.setAttribute("message", new Message("Data Saved Successfully....!!", "success"));
		return "redirect:/workorder/hold";
	}

	/***************** HoldWorkOrder End********** */

	/***************** CancelWorkOrder Start********** */
	@GetMapping("/cancel")
	@PreAuthorize("hasAuthority('CANCEL_WORKORDER')")
	public String cancelWorkOrder(Model model) {
		List<CancelWorkOrderDto> listOfCancelWorks = this.cancelWorkOrdeService.findAllCancelWorkOrder();
		model.addAttribute("listOfCancelWorks", listOfCancelWorks);
		return "/pages/work_orders/cancel_work_order";
	}

	// Handler Cancel Work Order Data
	@PostMapping("/savecancelworkord")
	public String saveCancelWorkOrder(CancelWorkOrderDto idCancel, HttpSession session) {
		this.cancelWorkOrdeService.saveCancelWorkOrder(idCancel);
		session.setAttribute("message", new Message("Data Saved Successfully....!!", "success"));
		return "redirect:/workorder/cancel";
	}

	/***************** CancelWorkOrder End********** */
}
