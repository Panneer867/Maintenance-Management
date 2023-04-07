package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ingroinfo.mm.dao.IndentApprovedItemsRepository;
import com.ingroinfo.mm.dao.IndentApprovedLaboursRepository;
import com.ingroinfo.mm.dao.IndentApprovedVehiclesRepository;
import com.ingroinfo.mm.dao.StockOrderItemsRequestRepository;
import com.ingroinfo.mm.dao.TempIndentItemRequestRepository;
import com.ingroinfo.mm.dao.TempIndentLabourRequestRepository;
import com.ingroinfo.mm.dao.TempIndentVehicleRequestRepository;
import com.ingroinfo.mm.dto.CancelWorkOrderItemsDto;
import com.ingroinfo.mm.dto.CancelWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.CancelWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.HoldWorkOrderItemsDto;
import com.ingroinfo.mm.dto.HoldWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.HoldWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.WapWorkOrderItemsDto;
import com.ingroinfo.mm.dto.WapWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.WapWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedItemsDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedLaboursDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedVehiclesDto;
import com.ingroinfo.mm.entity.IndentApprovedItems;
import com.ingroinfo.mm.entity.IndentApprovedLabours;
import com.ingroinfo.mm.entity.IndentApprovedVehicles;
import com.ingroinfo.mm.entity.InwardApprovedMaterials;
import com.ingroinfo.mm.entity.InwardApprovedSpares;
import com.ingroinfo.mm.entity.InwardApprovedTools;
import com.ingroinfo.mm.entity.StockOrderItemsRequest;
import com.ingroinfo.mm.entity.TempIndentItemRequest;
import com.ingroinfo.mm.entity.TempIndentLabourRequest;
import com.ingroinfo.mm.entity.TempIndentVehicleRequest;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;
import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.StockService;
import com.ingroinfo.mm.service.TaskUpdateService;
import com.ingroinfo.mm.service.WorkOrderService;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

	@Autowired
	private TaskUpdateService taskUpdateService;
	@Autowired
	private StockService stockService;
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
	private StockOrderItemsRequestRepository stockOrderItemsRequestRepository;
	@Autowired
	private WorkOrderService workOrderService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	private void CopyItemsToStockorders(String username, String complNo, String indentNo) {
		List<WorkOrderApprovedItemsDto> workOrderApprovedItemsDtos = workOrderService
				.getApprovedWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);

		for (WorkOrderApprovedItemsDto item : workOrderApprovedItemsDtos) {

			if (item.getStockApproveSts().equalsIgnoreCase("N")) {

				StockOrderItemsRequest stockOrderItemsRequest = new StockOrderItemsRequest();

				long stockOrderNo = Long.parseLong(item.getComplNo() + item.getIndentNo());

				stockOrderItemsRequest.setStockOrderNo(stockOrderNo);
				stockOrderItemsRequest.setCategoryName(item.getCategoryName());
				stockOrderItemsRequest.setComplDtls(item.getComplDtls());
				stockOrderItemsRequest.setComplNo(item.getComplNo());
				stockOrderItemsRequest.setContactNo(item.getContactNo());
				stockOrderItemsRequest.setDepartmentName(item.getDepartmentName());
				stockOrderItemsRequest.setDivision(item.getDivision());
				stockOrderItemsRequest.setEndDate(item.getEndDate());
				stockOrderItemsRequest.setHsnCode(item.getHsnCode());
				stockOrderItemsRequest.setIndentNo(item.getIndentNo());
				stockOrderItemsRequest.setItemId(item.getItemId());
				stockOrderItemsRequest.setItemName(item.getItemName());
				stockOrderItemsRequest.setQuantity(item.getQuantity());
				stockOrderItemsRequest.setStartDate(item.getStartDate());
				stockOrderItemsRequest.setStockType(item.getStockType());
				stockOrderItemsRequest.setStockTypeName(item.getStockTypeName());
				stockOrderItemsRequest.setSubDivision(item.getSubDivision());
				stockOrderItemsRequest.setUnitOfMesure(item.getUnitOfMesure());
				stockOrderItemsRequest.setUsername(username);
				stockOrderItemsRequest.setWorkPriority(item.getWorkPriority());
				stockOrderItemsRequest.setWorkSite(item.getWorkSite());
				stockOrderItemsRequest.setWorkOrderNo(item.getWorkOrder());

				stockOrderItemsRequestRepository.save(stockOrderItemsRequest);
			}
		}
	}

	// Handler For Open Approvals Page
	@GetMapping
	@PreAuthorize("hasAuthority('APPROVALS')")
	public String hrApprovals(Model model) {
		model.addAttribute("title", "Approval | Manintenance Management");
		return "approvals_page";
	}

	/********************* Indent Approvals *****************************/

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

		List<TempIndentItemRequest> tempIndentItemRequests = this.tempIndentItemRequestRepo
				.getByComplNoAndIndentNo(complNo, indentNo);
		List<TempIndentLabourRequest> tempIndentLabourRequests = this.tempIndentLabourRequestRepo
				.getByComplNoAndIndentNo(complNo, indentNo);
		List<TempIndentVehicleRequest> TempIndentVehicleRequests = this.tempIndentVehicleRequestRepo
				.getByComplNoAndIndentNo(complNo, indentNo);

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

		List<TempIndentItemRequest> tempIndentItemRequests = this.tempIndentItemRequestRepo
				.getByComplNoAndIndentNo(complNo, indentNo);
		List<TempIndentLabourRequest> tempIndentLabourRequests = this.tempIndentLabourRequestRepo
				.getByComplNoAndIndentNo(complNo, indentNo);
		List<TempIndentVehicleRequest> TempIndentVehicleRequests = this.tempIndentVehicleRequestRepo
				.getByComplNoAndIndentNo(complNo, indentNo);

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

	/********************* Materials Indent Approvals *****************************/

	// Handler For Open Material Indent Approval Page
	@GetMapping("/indent/material")
	public String displayMaterialIndentApproval(Model model) {
		String approvedSts = "N";
		List<IndentApprovedItems> notApprovedItemsList = this.workOrderService
				.getApprovedItemsIndentByApprovedSts(approvedSts);

		List<IndentApprovedItems> uniqueItemsList = notApprovedItemsList.stream()
				.filter(labor -> labor.getComplNo() != null && labor.getIndentNo() != null)
				.filter(distinctByKey(labor -> labor.getComplNo() + "_" + labor.getIndentNo()))
				.collect(Collectors.toList());

		model.addAttribute("notApprovedItemsList", uniqueItemsList);
		model.addAttribute("title", "Approval | Material | Manintenance Management");
		return "/pages/approvals/material_indent_approvals";
	}

	// Handler For get Not Approved Material Indent Data For Approval
	@GetMapping("/indent/material/get/{complNo}/{indentNo}")
	public String getItemsIndentdataByComplNoAndIndentNo(@PathVariable String complNo, @PathVariable String indentNo,
			Model model) {
		String approvedSts = "N";
		List<IndentApprovedItems> notApprovedItemsList = this.workOrderService
				.getApprovedItemsIndentByApprovedSts(approvedSts);

		List<IndentApprovedItems> uniqueItemsList = notApprovedItemsList.stream()
				.filter(labor -> labor.getComplNo() != null && labor.getIndentNo() != null)
				.filter(distinctByKey(labor -> labor.getComplNo() + "_" + labor.getIndentNo()))
				.collect(Collectors.toList());

		List<IndentApprovedItems> approvedItemsList = this.workOrderService
				.getApprovedIndentItemsByComplNoAndIndentNo(complNo, indentNo);

		Date expStartDate = null;
		String departmentName = null;
		String division = null;
		String subDivision = null;
		String workPriority = null;
		String workSite = null;
		String contactNo = null;

		for (IndentApprovedItems indentItems : approvedItemsList) {
			if (indentItems.getIndentNo() != null || indentItems.getComplNo() != null) {
				expStartDate = indentItems.getStartDate();
				departmentName = indentItems.getDepartmentName();
				division = indentItems.getDivision();
				subDivision = indentItems.getSubDivision();
				workPriority = indentItems.getWorkPriority();
				workSite = indentItems.getWorkSite();
				contactNo = indentItems.getContactNo();
				break;
			}
		}

		model.addAttribute("expStartDate", expStartDate);
		model.addAttribute("department", departmentName);
		model.addAttribute("division", division);
		model.addAttribute("subdvision", subDivision);
		model.addAttribute("workPriority", workPriority);
		model.addAttribute("workSite", workSite);
		model.addAttribute("contactNo", contactNo);
		model.addAttribute("notApprovedItemsList", uniqueItemsList);
		model.addAttribute("listOfApprovedItems", approvedItemsList);
		model.addAttribute("title", "Approval | Indent | Material | Manintenance Management");
		return "/pages/approvals/material_indent_approvals";
	}

	// Handler For Submit Approved Material Data
	@GetMapping("/indent/material/approve/{complNo}/{indentNo}")
	public String approveMaterialIndentData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
			Principal principal, HttpSession session) {

		List<IndentApprovedItems> approvedIndentItemsList = this.workOrderService
				.getApprovedIndentItemsByComplNoAndIndentNo(complNo, indentNo);

		if (approvedIndentItemsList != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<TempWorkOrderItemRequest> tempWorkOrderItems = approvedIndentItemsList.stream()
					.map((indentItems) -> modelMapper.map(indentItems, TempWorkOrderItemRequest.class))
					.collect(Collectors.toList());

			tempWorkOrderItems.forEach(tempWorkOrderLabors -> {
				tempWorkOrderLabors.setUserName(principal.getName());
				tempWorkOrderLabors.setApprovedSts("Y");
			});
			this.workOrderService.saveAllTempWorkOrderItems(tempWorkOrderItems);

			for (int i = 0; i < approvedIndentItemsList.size(); i++) {
				IndentApprovedItems indentItems = approvedIndentItemsList.get(i);
				TempWorkOrderItemRequest tempWorkorderItems = tempWorkOrderItems.get(i);

				// Update the approved status based on the corresponding Indent And Compl No
				indentItems.setApprovedSts(tempWorkorderItems.getApprovedSts());
			}
			this.workOrderService.saveAllApprovedIndentItems(approvedIndentItemsList);
		}

		session.setAttribute("message", new Message("Approved Sucessfully Done !!", "success"));
		return "redirect:/approval/indent/material";
	}

	/********************* Labor Indent Approvals *****************************/

	// Handler For Open Labor Indent Approval Page
	@GetMapping("/indent/labour")
	public String displayLaborIndentApproval(Model model) {
		String approvedSts = "N";
		List<IndentApprovedLabours> notApprovedLaborList = this.workOrderService
				.getApprovedLabourIndentByApprovedSts(approvedSts);

		List<IndentApprovedLabours> uniqueLabourList = notApprovedLaborList.stream()
				.filter(labor -> labor.getComplNo() != null && labor.getIndentNo() != null)
				.filter(distinctByKey(labor -> labor.getComplNo() + "_" + labor.getIndentNo()))
				.collect(Collectors.toList());

		model.addAttribute("notApprovedLabourList", uniqueLabourList);
		model.addAttribute("title", "Approval | Labour | Manintenance Management");
		return "/pages/approvals/labour_indent_approvals";
	}

	// Handler For get Not Approved Labor Indent Data For Approval
	@GetMapping("/indent/labour/get/{complNo}/{indentNo}")
	public String getLabourIndentdataByComplNoAndIndentNo(@PathVariable String complNo, @PathVariable String indentNo,
			Model model) {
		String approvedSts = "N";
		List<IndentApprovedLabours> notApprovedLaborList = this.workOrderService
				.getApprovedLabourIndentByApprovedSts(approvedSts);
		List<IndentApprovedLabours> uniqueLabourList = notApprovedLaborList.stream()
				.filter(labor -> labor.getComplNo() != null && labor.getIndentNo() != null)
				.filter(distinctByKey(labor -> labor.getComplNo() + "_" + labor.getIndentNo()))
				.collect(Collectors.toList());

		List<IndentApprovedLabours> approvedIndentLabours = this.workOrderService
				.getApprovedIndentLaborsByComplNoAndIndentNo(complNo, indentNo);

		Date expStartDate = null;
		String departmentName = null;
		String division = null;
		String subDivision = null;
		String workPriority = null;
		String workSite = null;
		String contactNo = null;

		for (IndentApprovedLabours indentLabors : approvedIndentLabours) {
			if (indentLabors.getIndentNo() != null || indentLabors.getComplNo() != null) {
				expStartDate = indentLabors.getStartDate();
				departmentName = indentLabors.getDepartmentName();
				division = indentLabors.getDivision();
				subDivision = indentLabors.getSubDivision();
				workPriority = indentLabors.getWorkPriority();
				workSite = indentLabors.getWorkSite();
				contactNo = indentLabors.getContactNo();
				break;
			}
		}

		model.addAttribute("expStartDate", expStartDate);
		model.addAttribute("department", departmentName);
		model.addAttribute("division", division);
		model.addAttribute("subdvision", subDivision);
		model.addAttribute("workPriority", workPriority);
		model.addAttribute("workSite", workSite);
		model.addAttribute("contactNo", contactNo);
		model.addAttribute("notApprovedLabourList", uniqueLabourList);
		model.addAttribute("listOfLabors", approvedIndentLabours);
		model.addAttribute("title", "Approval | Indent | Labour | Manintenance Management");
		return "/pages/approvals/labour_indent_approvals";
	}

	// Handler For Submit Approved Labor Data
	@GetMapping("/indent/labour/approve/{complNo}/{indentNo}")
	public String approveLabourIndentData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
			Principal principal, HttpSession session) {

		List<IndentApprovedLabours> approvedIndentLabours = this.workOrderService
				.getApprovedIndentLaborsByComplNoAndIndentNo(complNo, indentNo);

		if (approvedIndentLabours != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<TempWorkOrderLabourRequest> tempWorkOrderLabours = approvedIndentLabours.stream()
					.map((indentLabors) -> modelMapper.map(indentLabors, TempWorkOrderLabourRequest.class))
					.collect(Collectors.toList());

			tempWorkOrderLabours.forEach(tempWorkOrderLabors -> {
				tempWorkOrderLabors.setUserName(principal.getName());
				tempWorkOrderLabors.setApprovedSts("Y");
			});
			this.workOrderService.saveAllTempWorkOrderLabours(tempWorkOrderLabours);

			for (int i = 0; i < approvedIndentLabours.size(); i++) {
				IndentApprovedLabours indentLabors = approvedIndentLabours.get(i);
				TempWorkOrderLabourRequest tempWorkorderLabors = tempWorkOrderLabours.get(i);

				// Update the approved status based on the corresponding Indent And Compl No
				indentLabors.setApprovedSts(tempWorkorderLabors.getApprovedSts());
			}
			this.workOrderService.saveAllApprovedIndentLabours(approvedIndentLabours);
		}

		session.setAttribute("message", new Message("Approved Sucessfully Done !!", "success"));
		return "redirect:/approval/indent/labour";
	}

	/********************* Vehicle Indent Approvals *****************************/

	// Handler For Open Vehicle Indent Approval Page
	@GetMapping("/indent/vehicle")
	public String displayVehicleIndentApproval(Model model) {
		String approvedSts = "N";
		List<IndentApprovedVehicles> notApprovedVehicleList = this.workOrderService
				.getApprovedVehicleIndentByApprovedSts(approvedSts);

		List<IndentApprovedVehicles> uniqueVehicleList = notApprovedVehicleList.stream()
				.filter(vehicel -> vehicel.getComplNo() != null && vehicel.getIndentNo() != null)
				.filter(distinctByKey(vehicel -> vehicel.getComplNo() + "_" + vehicel.getIndentNo()))
				.collect(Collectors.toList());

		model.addAttribute("notApprovedVehicleList", uniqueVehicleList);
		model.addAttribute("title", "Approval | Vehicle | Manintenance Management");
		return "/pages/approvals/vehicle_indent_approvals";
	}

	// Handler For get Not Approved Vehicle Indent Data For Approval
	@GetMapping("/indent/vehicle-get/{complNo}/{indentNo}")
	public String getVehicleIndentdataByComplNoAndIndentNo(@PathVariable String complNo, @PathVariable String indentNo,
			Model model) {
		String approvedSts = "N";
		List<IndentApprovedVehicles> notApprovedVehicleList = this.workOrderService
				.getApprovedVehicleIndentByApprovedSts(approvedSts);

		List<IndentApprovedVehicles> uniqueVehicleList = notApprovedVehicleList.stream()
				.filter(vehicel -> vehicel.getComplNo() != null && vehicel.getIndentNo() != null)
				.filter(distinctByKey(vehicel -> vehicel.getComplNo() + "_" + vehicel.getIndentNo()))
				.collect(Collectors.toList());

		List<IndentApprovedVehicles> approvedIndentVehicles = this.workOrderService
				.getApprovedIndentVehiclesByComplNoAndIndentNo(complNo, indentNo);

		Date expStartDate = null;
		String departmentName = null;
		String division = null;
		String subDivision = null;
		String workPriority = null;
		String workSite = null;
		String contactNo = null;

		for (IndentApprovedVehicles indentvehicles : approvedIndentVehicles) {
			if (indentvehicles.getIndentNo() != null || indentvehicles.getComplNo() != null) {
				expStartDate = indentvehicles.getStartDate();
				departmentName = indentvehicles.getDepartmentName();
				division = indentvehicles.getDivision();
				subDivision = indentvehicles.getSubDivision();
				workPriority = indentvehicles.getWorkPriority();
				workSite = indentvehicles.getWorkSite();
				contactNo = indentvehicles.getContactNo();
				break;
			}
		}

		model.addAttribute("expStartDate", expStartDate);
		model.addAttribute("department", departmentName);
		model.addAttribute("division", division);
		model.addAttribute("subdvision", subDivision);
		model.addAttribute("workPriority", workPriority);
		model.addAttribute("workSite", workSite);
		model.addAttribute("contactNo", contactNo);
		model.addAttribute("notApprovedVehicleList", uniqueVehicleList);
		model.addAttribute("listOfVehicles", approvedIndentVehicles);
		model.addAttribute("title", "Approval | Indent | Vehicle | Manintenance Management");
		return "/pages/approvals/vehicle_indent_approvals";
	}

	// Handler For Submit Approved Labor Data
	@GetMapping("/indent/vehicle/approve/{complNo}/{indentNo}")
	public String approveVehicleIndentData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
			Principal principal, HttpSession session) {

		List<IndentApprovedVehicles> approvedIndentVehicles = this.workOrderService
				.getApprovedIndentVehiclesByComplNoAndIndentNo(complNo, indentNo);

		if (approvedIndentVehicles != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles = approvedIndentVehicles.stream()
					.map((indentLabors) -> modelMapper.map(indentLabors, TempWorkOrderVehicleRequest.class))
					.collect(Collectors.toList());

			tempWorkOrderVehicles.forEach(tempWorkOrderVehicle -> {
				tempWorkOrderVehicle.setUserName(principal.getName());
				tempWorkOrderVehicle.setApprovedSts("Y");
			});
			this.workOrderService.saveAllTempWorkOrderVehicles(tempWorkOrderVehicles);

			for (int i = 0; i < approvedIndentVehicles.size(); i++) {
				IndentApprovedVehicles indentVehicles = approvedIndentVehicles.get(i);
				TempWorkOrderVehicleRequest tempWorkOrderVehicle = tempWorkOrderVehicles.get(i);

				// Update the approved status based on the corresponding Indent And Compl No
				indentVehicles.setApprovedSts(tempWorkOrderVehicle.getApprovedSts());
			}
			this.workOrderService.saveAllApprovedIndentVehicles(approvedIndentVehicles);
		}

		session.setAttribute("message", new Message("Approved Sucessfully Done !!", "success"));
		return "redirect:/approval/indent/vehicle";
	}

	/********************* WorkOrder Approvals *****************************/

	// Handler For Open Work Order Approval Page
	@GetMapping("/workorder")
	public String displayWorkOrderApproval(Model model) {
		String complStatus = "WAITING_WORKORDER_APPROVAL";
		List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCompl", complDtos);
		model.addAttribute("title", "Approval | WorkOrder | Manintenance Management");
		return "/pages/approvals/workorder_approvals";
	}

	// Handler For Get Work order Data From TempWorkOrder
	@GetMapping("/workorder/get/{complNo}/{indentNo}")
	public String getWapWorkorderdataByComlNoAndIndentNo(@PathVariable String complNo, @PathVariable String indentNo,
			Model model) {
		String complStatus = "WAITING_WORKORDER_APPROVAL";
		List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCompl", complDtos);

		List<WapWorkOrderItemsDto> wapWorkOrderItemsDtos = this.workOrderService
				.getWapWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
		List<WapWorkOrderLaboursDto> wapWorkOrderLaboursDtos = this.workOrderService
				.getWapWorkOrderLaboursByComplNoAndIndentNo(complNo, indentNo);
		List<WapWorkOrderVehiclesDto> wapWorkOrderVehiclesDtos = this.workOrderService
				.getWapWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

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

		// Check for indent number in WapWokroderItemDto list
		for (WapWorkOrderItemsDto wapWorkorderItems : wapWorkOrderItemsDtos) {
			if (wapWorkorderItems.getIndentNo() != null || wapWorkorderItems.getComplNo() != null) {
				indentNumber = wapWorkorderItems.getIndentNo();
				complNumber = wapWorkorderItems.getComplNo();
				workorderNo = wapWorkorderItems.getWorkOrder();
				expStartDate = wapWorkorderItems.getStartDate();
				department = wapWorkorderItems.getDepartmentName();
				division = wapWorkorderItems.getDivision();
				subDivision = wapWorkorderItems.getSubDivision();
				workPriprity = wapWorkorderItems.getWorkPriority();
				workSite = wapWorkorderItems.getWorkSite();
				contactNo = wapWorkorderItems.getContactNo();
				break;
			}
		}
		// Check for indent number in WapWorkOrderLabourDto list
		if (indentNumber == null || complNumber == null) {
			for (WapWorkOrderLaboursDto wapWorkorderLabors : wapWorkOrderLaboursDtos) {
				if (wapWorkorderLabors.getIndentNo() != null || wapWorkorderLabors.getComplNo() != null) {
					indentNumber = wapWorkorderLabors.getIndentNo();
					complNumber = wapWorkorderLabors.getComplNo();
					workorderNo = wapWorkorderLabors.getWorkOrder();
					expStartDate = wapWorkorderLabors.getStartDate();
					department = wapWorkorderLabors.getDepartmentName();
					division = wapWorkorderLabors.getDivision();
					subDivision = wapWorkorderLabors.getSubDivision();
					workPriprity = wapWorkorderLabors.getWorkPriority();
					workSite = wapWorkorderLabors.getWorkSite();
					contactNo = wapWorkorderLabors.getContactNo();
					break;
				}
			}
		}
		// Check for indent number in WapWorkOrderVehicleDto list
		if (indentNumber == null || complNumber == null) {
			for (WapWorkOrderVehiclesDto wapWorkorderVehicle : wapWorkOrderVehiclesDtos) {
				if (wapWorkorderVehicle.getIndentNo() != null || wapWorkorderVehicle.getComplNo() != null) {
					indentNumber = wapWorkorderVehicle.getIndentNo();
					complNumber = wapWorkorderVehicle.getComplNo();
					workorderNo = wapWorkorderVehicle.getWorkOrder();
					expStartDate = wapWorkorderVehicle.getStartDate();
					department = wapWorkorderVehicle.getDepartmentName();
					division = wapWorkorderVehicle.getDivision();
					subDivision = wapWorkorderVehicle.getSubDivision();
					workPriprity = wapWorkorderVehicle.getWorkPriority();
					workSite = wapWorkorderVehicle.getWorkSite();
					contactNo = wapWorkorderVehicle.getContactNo();
					break;
				}
			}
		}

		model.addAttribute("complNo", complNumber);
		model.addAttribute("workOrderNo", workorderNo);
		model.addAttribute("startDate", expStartDate);
		model.addAttribute("department", department);
		model.addAttribute("division", division);
		model.addAttribute("subdivision", subDivision);
		model.addAttribute("workPriority", workPriprity);
		model.addAttribute("worksite", workSite);
		model.addAttribute("contactNo", contactNo);

		model.addAttribute("listOfWapWorkorderitems", wapWorkOrderItemsDtos);
		model.addAttribute("listOfWapWorkorderLabours", wapWorkOrderLaboursDtos);
		model.addAttribute("listOfWapWorkorderVehicles", wapWorkOrderVehiclesDtos);
		model.addAttribute("title", "Approval | WorkOrder | Manintenance Management");
		return "/pages/approvals/workorder_approvals";
	}

	// Handler For Submit Approved WorkOrder Data
	@GetMapping("/workorder/approve/{complNo}/{indentNo}")
	public String approveWorkorderData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
			Principal principal, HttpSession session) {

		List<WapWorkOrderItemsDto> wapWorkOrderItemsDtos = this.workOrderService
				.getWapWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
		List<WapWorkOrderLaboursDto> wapWorkOrderLaboursDtos = this.workOrderService
				.getWapWorkOrderLaboursByComplNoAndIndentNo(complNo, indentNo);
		List<WapWorkOrderVehiclesDto> wapWorkOrderVehiclesDtos = this.workOrderService
				.getWapWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

		if (wapWorkOrderItemsDtos != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<WorkOrderApprovedItemsDto> workOrderApprovedItemsDtos = wapWorkOrderItemsDtos.stream()
					.map((approvedWorkOrderItems) -> modelMapper.map(approvedWorkOrderItems,
							WorkOrderApprovedItemsDto.class))
					.collect(Collectors.toList());

			workOrderApprovedItemsDtos.forEach(workOrderItem -> {
				workOrderItem.setUserName(principal.getName());
				workOrderItem.setStockApproveSts("N");

			});

			this.workOrderService.saveAllApprovedWorkOrderItems(workOrderApprovedItemsDtos);
			CopyItemsToStockorders(principal.getName(), complNo, indentNo);
			this.workOrderService.deleteAllWapWorkorderItemsByComplNo(complNo);
		}
		if (wapWorkOrderLaboursDtos != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<WorkOrderApprovedLaboursDto> workOrderApprovedLaboursDtos = wapWorkOrderLaboursDtos.stream()
					.map(approvedWorkOrderLabors -> modelMapper.map(approvedWorkOrderLabors,
							WorkOrderApprovedLaboursDto.class))
					.collect(Collectors.toList());

			workOrderApprovedLaboursDtos.forEach(indentLabor -> {
				indentLabor.setUserName(principal.getName());
			});

			this.workOrderService.saveAllApprovedWorkOrderLabours(workOrderApprovedLaboursDtos);
			this.workOrderService.deleteAllWapWorkorderLaboursByComplNo(complNo);
		}
		if (wapWorkOrderVehiclesDtos != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<WorkOrderApprovedVehiclesDto> workOrderApprovedVehiclesDtos = wapWorkOrderVehiclesDtos.stream()
					.map((approvedWorkOrderVehicles) -> modelMapper.map(approvedWorkOrderVehicles,
							WorkOrderApprovedVehiclesDto.class))
					.collect(Collectors.toList());

			workOrderApprovedVehiclesDtos.forEach(indentVehicle -> {
				indentVehicle.setUserName(principal.getName());
			});

			this.workOrderService.saveAllApprovedWorkOrderVehicles(workOrderApprovedVehiclesDtos);
			this.workOrderService.deleteAllWapWorkorderVehiclesByComplNo(complNo);
		}

		ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
		oldcomplaintDto.setComplStatus("APPROVED_WORKORDERS");
		oldcomplaintDto.setWorkorderApprovedBy(principal.getName());
		oldcomplaintDto.setWorkorderApprovedDate(new java.sql.Date(System.currentTimeMillis()));
		this.taskUpdateService.saveComplaint(oldcomplaintDto);
		session.setAttribute("message", new Message("Approved Sucessfully Done !!", "success"));
		return "redirect:/approval/workorder";
	}

	// Handler For Submit Hold WorkOrder Data
	@GetMapping("/workorder/hold/{complNo}/{indentNo}")
	public String submitHoldWorkorderData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
			Principal principal, HttpSession session) {

		List<WapWorkOrderItemsDto> wapWorkOrderItemsDtos = this.workOrderService
				.getWapWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
		List<WapWorkOrderLaboursDto> wapWorkOrderLaboursDtos = this.workOrderService
				.getWapWorkOrderLaboursByComplNoAndIndentNo(complNo, indentNo);
		List<WapWorkOrderVehiclesDto> wapWorkOrderVehiclesDtos = this.workOrderService
				.getWapWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

		if (wapWorkOrderItemsDtos != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<HoldWorkOrderItemsDto> holdWorkOrderItemsDtos = wapWorkOrderItemsDtos.stream()
					.map((holdWorkOrderItems) -> modelMapper.map(holdWorkOrderItems, HoldWorkOrderItemsDto.class))
					.collect(Collectors.toList());

			holdWorkOrderItemsDtos.forEach(holdworkOrderItem -> {
				holdworkOrderItem.setUserName(principal.getName());
				holdworkOrderItem.setStockApproveSts("N");

			});

			this.workOrderService.saveAllHoldWorkOrderItems(holdWorkOrderItemsDtos);
			this.workOrderService.deleteAllWapWorkorderItemsByComplNo(complNo);
		}
		if (wapWorkOrderLaboursDtos != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<HoldWorkOrderLaboursDto> holdWorkOrderLaboursDtos = wapWorkOrderLaboursDtos.stream()
					.map(holdWorkOrderLabors -> modelMapper.map(holdWorkOrderLabors, HoldWorkOrderLaboursDto.class))
					.collect(Collectors.toList());

			holdWorkOrderLaboursDtos.forEach(indentLabor -> {
				indentLabor.setUserName(principal.getName());
			});

			this.workOrderService.saveAllHoldWorkOrderLabours(holdWorkOrderLaboursDtos);
			this.workOrderService.deleteAllWapWorkorderLaboursByComplNo(complNo);
		}
		if (wapWorkOrderVehiclesDtos != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<HoldWorkOrderVehiclesDto> holdWorkOrderVehiclesDtos = wapWorkOrderVehiclesDtos.stream().map(
					(holdWorkOrderVehicles) -> modelMapper.map(holdWorkOrderVehicles, HoldWorkOrderVehiclesDto.class))
					.collect(Collectors.toList());

			holdWorkOrderVehiclesDtos.forEach(indentVehicle -> {
				indentVehicle.setUserName(principal.getName());
			});

			this.workOrderService.saveAllHoldWorkOrderVehicles(holdWorkOrderVehiclesDtos);
			this.workOrderService.deleteAllWapWorkorderVehiclesByComplNo(complNo);
		}

		ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
		oldcomplaintDto.setComplStatus("WORK_ORDER_IN_HOLD");
		oldcomplaintDto.setWorkOrderHoldBy(principal.getName());
		oldcomplaintDto.setWorkOrderHoldDate(new java.sql.Date(System.currentTimeMillis()));
		this.taskUpdateService.saveComplaint(oldcomplaintDto);
		session.setAttribute("message", new Message("The WorkOrder Is On Hold !!", "warning"));
		return "redirect:/approval/workorder";
	}
	
	// Handler For Submit Cancel WorkOrder Data
		@GetMapping("/workorder/cancel/{complNo}/{indentNo}")
		public String submitCancelWorkorderData(@PathVariable String complNo, @PathVariable String indentNo, Model model,
				Principal principal, HttpSession session) {

			List<WapWorkOrderItemsDto> wapWorkOrderItemsDtos = this.workOrderService
					.getWapWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
			List<WapWorkOrderLaboursDto> wapWorkOrderLaboursDtos = this.workOrderService
					.getWapWorkOrderLaboursByComplNoAndIndentNo(complNo, indentNo);
			List<WapWorkOrderVehiclesDto> wapWorkOrderVehiclesDtos = this.workOrderService
					.getWapWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

			if (wapWorkOrderItemsDtos != null) {
				ModelMapper modelMapper = new ModelMapper();
				List<CancelWorkOrderItemsDto> cancelWorkOrderItemsDtos = wapWorkOrderItemsDtos.stream()
						.map((cancelWorkOrderItems) -> modelMapper.map(cancelWorkOrderItems, CancelWorkOrderItemsDto.class))
						.collect(Collectors.toList());

				cancelWorkOrderItemsDtos.forEach(holdworkOrderItem -> {
					holdworkOrderItem.setUserName(principal.getName());
					holdworkOrderItem.setStockApproveSts("N");

				});

				this.workOrderService.saveAllCancelWorkOrderItems(cancelWorkOrderItemsDtos);
				this.workOrderService.deleteAllWapWorkorderItemsByComplNo(complNo);
			}
			if (wapWorkOrderLaboursDtos != null) {
				ModelMapper modelMapper = new ModelMapper();
				List<CancelWorkOrderLaboursDto> cancelWorkOrderLaboursDtos = wapWorkOrderLaboursDtos.stream()
						.map(cancelWorkOrderLabors -> modelMapper.map(cancelWorkOrderLabors, CancelWorkOrderLaboursDto.class))
						.collect(Collectors.toList());

				cancelWorkOrderLaboursDtos.forEach(indentLabor -> {
					indentLabor.setUserName(principal.getName());
				});

				this.workOrderService.saveAllCancelWorkOrderLabours(cancelWorkOrderLaboursDtos);
				this.workOrderService.deleteAllWapWorkorderLaboursByComplNo(complNo);
			}
			if (wapWorkOrderVehiclesDtos != null) {
				ModelMapper modelMapper = new ModelMapper();
				List<CancelWorkOrderVehiclesDto> cancelWorkOrderVehiclesDtos = wapWorkOrderVehiclesDtos.stream().map(
						(cancelWorkOrderVehicles) -> modelMapper.map(cancelWorkOrderVehicles, CancelWorkOrderVehiclesDto.class))
						.collect(Collectors.toList());

				cancelWorkOrderVehiclesDtos.forEach(indentVehicle -> {
					indentVehicle.setUserName(principal.getName());
				});

				this.workOrderService.saveAllCancelWorkOrderVehicles(cancelWorkOrderVehiclesDtos);
				this.workOrderService.deleteAllWapWorkorderVehiclesByComplNo(complNo);
			}

			ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
			oldcomplaintDto.setComplStatus("WORK_ORDER_CANCELD");
			oldcomplaintDto.setWorkOrderCancelBy(principal.getName());
			oldcomplaintDto.setWorkOrderCancelDate(new java.sql.Date(System.currentTimeMillis()));
			this.taskUpdateService.saveComplaint(oldcomplaintDto);
			session.setAttribute("message", new Message("The WorkOrder Is Canceled !!", "danger"));
			return "redirect:/approval/workorder";
		}

	/********************* Stock Approvals *****************************/

	@GetMapping("/stock/materials")
	public String approveMaterials(Model model) {

		model.addAttribute("title", "Material Approvals | Maintenance Mangement");
		model.addAttribute("approval", new InwardDto());
		model.addAttribute("materialsLists", stockService.getInwardAllMaterialsList());

		return "/pages/stock_management/inward_materials_approval";
	}

	@PostMapping("/stock/materials/approve")
	public String approvedMaterials(@ModelAttribute InwardDto inwardItemDto, Model model, Principal principal,
			HttpSession session) {

		String username = principal.getName();
		inwardItemDto.setApprovedBy(username);
		inwardItemDto.setUsername(username);
		stockService.saveMaterial(inwardItemDto);
		session.setAttribute("message", new Message("Material has been approved successfully !", "success"));

		return "redirect:/approval/stock/materials";
	}

	@GetMapping("/inward/approved/materials/get/{id}")
	public @ResponseBody InwardApprovedMaterials getInwardMaterials(@PathVariable("id") Long id) {
		return stockService.getApprovedInwardMaterialById(id);
	}

	@GetMapping("/stock/materials/reject/{id}")
	public String rejectMaterial(@PathVariable("id") Long id, HttpSession session, Principal principal) {

		stockService.rejectMaterial(id, principal.getName());
		session.setAttribute("message", new Message("Material has been Rejected !", "danger"));

		return "redirect:/approval/stock/materials";

	}

	/****************** Spares ***************/

	@GetMapping("/stock/spares")
	public String approveSpares(Model model) {

		model.addAttribute("title", "Spares Approvals | Maintenance Mangement");
		model.addAttribute("approval", new InwardDto());
		model.addAttribute("sparesLists", stockService.getInwardAllSparesList());

		return "/pages/stock_management/inward_spares_approval";
	}

	@PostMapping("/stock/spares/approve")
	public String approvedSpares(@ModelAttribute InwardDto inwardItemDto, Model model, Principal principal,
			HttpSession session) {

		String username = principal.getName();
		inwardItemDto.setApprovedBy(username);
		inwardItemDto.setUsername(username);

		stockService.saveSpare(inwardItemDto);
		session.setAttribute("message", new Message("Spare has been approved successfully !", "success"));

		return "redirect:/approval/stock/spares";
	}

	@GetMapping("/inward/approved/spares/get/{id}")
	public @ResponseBody InwardApprovedSpares getInwardSpares(@PathVariable("id") Long id) {
		return stockService.getApprovedInwardSpareById(id);
	}

	@GetMapping("/stock/spares/reject/{id}")
	public String rejectSpare(@PathVariable("id") Long id, HttpSession session, Principal principal) {

		stockService.rejectSpare(id, principal.getName());
		session.setAttribute("message", new Message("Spare has been Rejected !", "danger"));

		return "redirect:/approval/stock/spares";
	}

	/****************** Tools ***************/

	@GetMapping("/stock/tools")
	public String approveTools(Model model) {

		model.addAttribute("title", "Tools Approvals | Maintenance Mangement");
		model.addAttribute("approval", new InwardDto());
		model.addAttribute("toolsLists", stockService.getInwardAllToolsList());

		return "/pages/stock_management/inward_tools_approval";
	}

	@PostMapping("/stock/tools/approve")
	public String approvedTools(@ModelAttribute InwardDto inwardItemDto, Model model, Principal principal,
			HttpSession session) {

		String username = principal.getName();
		inwardItemDto.setApprovedBy(username);
		inwardItemDto.setUsername(username);

		stockService.saveTool(inwardItemDto);
		session.setAttribute("message", new Message("Tool has been approved successfully !", "success"));

		return "redirect:/approval/stock/tools";
	}

	@GetMapping("/inward/approved/tools/get/{id}")
	public @ResponseBody InwardApprovedTools getInwardTools(@PathVariable("id") Long id) {
		return stockService.getApprovedInwardToolById(id);
	}

	@GetMapping("/stock/tools/reject/{id}")
	public String rejectTool(@PathVariable("id") Long id, HttpSession session, Principal principal) {

		stockService.rejectTool(id, principal.getName());
		session.setAttribute("message", new Message("Tool has been Rejected !", "danger"));

		return "redirect:/approval/stock/tools";

	}

	/****************** Stocks Return ***************/

	@GetMapping("/stock/item/return")
	public String approveStockReturn(Model model) {

		model.addAttribute("title", "Stocks Return Approvals | Maintenance Mangement");
		model.addAttribute("returnedItemLists", stockService.getStockReturnItemList());
		return "/pages/stock_management/stock_returns_approval";
	}

	@GetMapping("/return/item/reject/{id}")
	public String deleteReturnItem(@PathVariable("id") Long id, HttpSession session, Principal principal) {

		stockService.rejectReturnItem(id, principal.getName());
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "success"));
		return "redirect:/approval/stock/item/return";
	}

	@GetMapping("/stock/item/return/{id}")
	public String approveStockReturnItems(@PathVariable("id") Long id, HttpSession session, Principal principal) {

		stockService.approveReturnItem(id, principal.getName());
		session.setAttribute("message", new Message("Item Return Items has been approved successfully !", "success"));
		return "redirect:/approval/stock/item/return";
	}

}
