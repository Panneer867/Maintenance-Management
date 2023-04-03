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
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.InwardDto;
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

	private void CopyItemsToStockorders(String username, String complNo, String indentNo) {
		List<IndentApprovedItems> indentApprovedItems = workOrderService.getApprovedIndentItemsByComplNoAndIndentNo(complNo, indentNo);

			for (IndentApprovedItems item : indentApprovedItems) {

				if (item.getApprovedSts().equalsIgnoreCase("N")) {

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

					stockOrderItemsRequestRepository.save(stockOrderItemsRequest);
				}

			}
		
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
			CopyItemsToStockorders(principal.getName(),complNo,indentNo);
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

		public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
			Map<Object, Boolean> seen = new ConcurrentHashMap<>();
			return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
		}

		// Handler For Open Vehicle Indent Approval Page
		@GetMapping("/indent/vehicle")
		public String displayVehicleIndentApproval(Model model) {
			String approvedSts = "N";
			List<IndentApprovedVehicles> notApprovedVehicleList = this.workOrderService
					.getApprovedVehicleIndentByApprovedSts(approvedSts);

			List<IndentApprovedVehicles> uniqueVehicleList = notApprovedVehicleList.stream()
					.filter(labor -> labor.getComplNo() != null && labor.getIndentNo() != null)
					.filter(distinctByKey(labor -> labor.getComplNo() + "_" + labor.getIndentNo()))
					.collect(Collectors.toList());

			model.addAttribute("notApprovedVehicleList", uniqueVehicleList);
			model.addAttribute("title", "Approval | Vehicle | Manintenance Management");
			return "/pages/approvals/vehicle_indent_approvals";
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

		// Handler For get Not Approved Vehicle Indent Data For Approval
		@GetMapping("/indent/vehicle-get/{complNo}/{indentNo}")
		public String getVehicleIndentdataByComplNoAndIndentNo(@PathVariable String complNo, @PathVariable String indentNo,
				Model model) {
			String approvedSts = "N";
			List<IndentApprovedVehicles> notApprovedVehicleList = this.workOrderService
					.getApprovedVehicleIndentByApprovedSts(approvedSts);

			List<IndentApprovedVehicles> uniqueVehicleList = notApprovedVehicleList.stream()
					.filter(labor -> labor.getComplNo() != null && labor.getIndentNo() != null)
					.filter(distinctByKey(labor -> labor.getComplNo() + "_" + labor.getIndentNo()))
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


	// Handler For Open Work Order Approval Page
	@GetMapping("/workorder")
	public String displayWorkOrderApproval(Model model) {
		String complStatus = "WAITING_WORKORDER_APPROVAL";
		List<ComplaintDto> complDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCompl", complDtos);
		model.addAttribute("title", "Approval | WorkOrder | Manintenance Management");
		return "/pages/approvals/workorder_approvals";
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

	/****************** Outward Stocks ***************/

	@GetMapping("/outward/stocks")
	public String approveOutwardStocks(Model model) {

		model.addAttribute("title", "Outward Stocks Approvals | Maintenance Mangement");
		model.addAttribute("outwardStocksLists", stockService.getOutwardStockOrders());
		return "/pages/stock_management/outward_stocks_approval";
	}

	@GetMapping("/outward/stock/items/{stockOrderNo}")
	public String outwardListItemsApprovals(@PathVariable("stockOrderNo") Long stockOrderNo, Model model) {

		model.addAttribute("title", "Outward Stockorder Items Approvals | Maintenance Management");
		model.addAttribute("outwardStocksListItems", stockService.getOutwardStockOrderItems(stockOrderNo));
		model.addAttribute("outwardStockorderNo", stockService.getOutwardStockOrder(stockOrderNo));

		return "/pages/stock_management/outward_stocks_approval_items";
	}

	@GetMapping("/outward/stockorder/items/{stockOrderNo}")
	public String outwardApproveItems(@PathVariable("stockOrderNo") Long stockOrderNo, Model model, HttpSession session,
			Principal principal) {

		model.addAttribute("title", "Outward Stockorder Items Approvals | Maintenance Management");
		stockService.approveOutwardStocks(stockOrderNo, principal.getName());
		session.setAttribute("message",
				new Message("Outward Stockorder Items has been approved successfully !", "success"));
		return "redirect:/approval/outward/stocks";
	}

	@GetMapping("/outward/stockorder/reject/{stockOrderNo}")
	public String rejectOutwardStockorderItems(@PathVariable("stockOrderNo") Long stockOrderNo, HttpSession session,
			Principal principal) {

		stockService.rejectStockorderItems(stockOrderNo, principal.getName());
		session.setAttribute("message",
				new Message("Outward Stockorder Items has been rejected successfully !", "success"));

		return "redirect:/approval/outward/stocks";
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
