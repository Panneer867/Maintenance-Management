package com.ingroinfo.mm.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.dao.TempAddedIndentLaboursRepository;
import com.ingroinfo.mm.dao.TempAddedIndentMaterialsRepository;
import com.ingroinfo.mm.dao.TempAddedIndentVehiclesRepository;
import com.ingroinfo.mm.dao.TempIndentItemRequestRepository;
import com.ingroinfo.mm.dao.TempIndentLabourRequestRepository;
import com.ingroinfo.mm.dao.TempIndentVehicleRequestRepository;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.DepartmentIdMasterDto;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.PipeIndexDto;
import com.ingroinfo.mm.dto.UnitMeasureDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedItemsDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedLaboursDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedVehiclesDto;
import com.ingroinfo.mm.dto.WorkPriorityDto;
import com.ingroinfo.mm.entity.TempAddedIndentLabours;
import com.ingroinfo.mm.entity.TempAddedIndentMaterials;
import com.ingroinfo.mm.entity.TempAddedIndentVehicles;
import com.ingroinfo.mm.entity.TempIndentItemRequest;
import com.ingroinfo.mm.entity.TempIndentLabourRequest;
import com.ingroinfo.mm.entity.TempIndentVehicleRequest;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.MasterService;
import com.ingroinfo.mm.service.PipeMaintenanceService;
import com.ingroinfo.mm.service.StockService;
import com.ingroinfo.mm.service.TaskUpdateService;
import com.ingroinfo.mm.service.WorkOrderService;

@Controller
@RequestMapping("/pipe/maintenance")
public class PipeController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private MasterService masterService;
	@Autowired
	private TaskUpdateService taskUpdateService;
	@Autowired
	private StockService stockService;
	@Autowired
	private TempAddedIndentMaterialsRepository tempAddedIndentMaterialsRepo;
	@Autowired
	private TempAddedIndentLaboursRepository tempAddedIndentLaboursRepo;
	@Autowired
	private TempAddedIndentVehiclesRepository tempAddedIndentVehiclesRepo;
	@Autowired
	private TempIndentItemRequestRepository tempIndentItemRequestRepo;
	@Autowired
	private TempIndentLabourRequestRepository tempIndentLabourRequestRepo;
	@Autowired
	private TempIndentVehicleRequestRepository tempIndentVehicleRequestRepo;
	@Autowired
	private WorkOrderService workOrderService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	public static String UploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Autowired
	PipeMaintenanceService pipeMaintenanceService;

	@GetMapping("/dashboard")
	@PreAuthorize("hasAuthority('PIPE_DASHBOARD')")
	public String pipeDashboard() {
		return "/pages/pipe_management/dashboard";
	}

	@GetMapping("/index")
	@PreAuthorize("hasAuthority('PIPE_INDEX')")
	public String pipeIndex() {
		return "/pages/pipe_management/pipe_maintenance_index";
	}

	// Save Pipe Index
	@PostMapping("/savepipe-index")
	public String savePipeIndex(PipeIndexDto pipeIndex, HttpSession session) {
		this.pipeMaintenanceService.savePipeIndex(pipeIndex);
		session.setAttribute("message", new Message("Data Sucessfully Saved !!", "success"));
		return "redirect:/pipe/maintenance/index";
	}

	@GetMapping("/indent")
	@PreAuthorize("hasAuthority('PIPE_INDENT')")
	public String pipeMaintenanceIndent(Model model, Principal principal, HttpSession session) {
		model.addAttribute("deptMaster", new DepartmentIdMasterDto());
		model.addAttribute("complDtls", new ComplaintDto());
		User user = adminService.getUserByUsername(principal.getName());
		String masterIdName = "Indent Id";
		String deptName = "Pipe Dept";
		String userId = user.getUbarmsUserId() + "";
		String complSts = "NeedMaterial";
		try {
			DepartmentIdMasterDto departmentIdMasterDto = this.masterService.getByMasterIdNameAndDeptName(masterIdName,
					deptName);
			model.addAttribute("deptMaster", departmentIdMasterDto);
		} catch (Exception e) {
			session.setAttribute("message", new Message("Indent Id Is Not Pressent Please Add Id First !!", "danger"));
			System.out.println("Exception :: " + e.getMessage());
		}
		try {
			if (userId.equals("1")) {
				List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplSts(deptName, complSts);
				model.addAttribute("complList", complaintDtos);
			} else {
				List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplStsUserId(deptName,
						complSts, userId);
				model.addAttribute("complList", complaintDtos);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message(" No Complain Assigned !! First Add Indent No !!", "danger"));
			System.out.println("Something went Wrong !!" + e.getMessage());
		}

		model.addAttribute("title", "Pipe | Indent | Maintenance Management");
		return "/pages/pipe_management/pipe_maintenance_indent";
	}

	// Get Complain Details For Indent
	@GetMapping("/indent/complDetails/{complId}")
	public String getComplaintDetailsForIndent(@PathVariable Long complId, Model model, HttpSession session,
			Principal principal) {
		User user = adminService.getUserByUsername(principal.getName());
		String masterIdName = "Indent Id";
		String deptName = "Pipe Dept";
		String userId = user.getUbarmsUserId() + "";
		String complSts = "NeedMaterial";
		try {
			DepartmentIdMasterDto departmentIdMasterDto = this.masterService.getByMasterIdNameAndDeptName(masterIdName,
					deptName);
			model.addAttribute("deptMaster", departmentIdMasterDto);
		} catch (Exception e) {
			session.setAttribute("message", new Message("Indent Id Is Not Pressent Please Add Id First !!", "danger"));
			System.out.println("Exception :: " + e.getMessage());
		}
		try {
			if (userId.equals("1")) {
				List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplSts(deptName, complSts);
				model.addAttribute("complList", complaintDtos);
			} else {
				List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplStsUserId(deptName,
						complSts, userId);
				model.addAttribute("complList", complaintDtos);
			}
			ComplaintDto complaintDto = this.taskUpdateService.getComplaintDtlsByComplaintId(complId);
			model.addAttribute("complDtls", complaintDto);
			List<String> divSubDivList = this.masterService.getDistinctDivisions();
			model.addAttribute("divSubDivList", divSubDivList);
			List<UnitMeasureDto> unitMeasureDtos = this.masterService.getAllUnitMeasure();
			model.addAttribute("unitsList", unitMeasureDtos);
			List<String> vehicleTypes = this.masterService.getAllVehicleTypes();
			model.addAttribute("vehicleTypes", vehicleTypes);
			List<WorkPriorityDto> workPriorityDtos = this.masterService.findAllWorkPriority();
			model.addAttribute("workProrityList", workPriorityDtos);
			List<InwardDto> stockList = stockService.getAllStocks();
			List<String> catrgoryList = stockList.stream().map(InwardDto::getCategory).distinct()
					.collect(Collectors.toList());
			model.addAttribute("categoryList", catrgoryList);
		} catch (Exception e) {
			session.setAttribute("message", new Message(" No Complain Assigned !! First Add Indent No !!", "danger"));
			System.out.println("Something went Wrong !!" + e.getMessage());
		}
		return "/pages/pipe_management/pipe_maintenance_indent";
	}

	// Handler For Add Pump Indent Data In TempIndent Table
	@GetMapping("/indent/submit/{complNo}/{indentNo}")
	public String savePipeIndentInTempIndent(@PathVariable String complNo, @PathVariable String indentNo,
			Principal principal, HttpSession session, Model model) {
		String masterIdName = "Indent Id";

		List<TempAddedIndentMaterials> tempAddedIndentItems = this.tempAddedIndentMaterialsRepo
				.getByIndentNoAndComplNo(indentNo, complNo);
		List<TempAddedIndentLabours> tempAddedIndentLabours = this.tempAddedIndentLaboursRepo
				.getByIndentNoAndComplNo(indentNo, complNo);
		List<TempAddedIndentVehicles> tempAddedIndentVehicles = this.tempAddedIndentVehiclesRepo
				.getByIndentNoAndComplNo(indentNo, complNo);

		String indentNumber = null;

		// Check for indent number in TempIndentItemRequest list
		for (TempAddedIndentMaterials itemRequest : tempAddedIndentItems) {
			if (itemRequest.getIndentNo() != null) {
				indentNumber = itemRequest.getIndentNo();
				break;
			}
		}

		// Check for indent number in TempIndentLabourRequest list
		if (indentNumber == null) {
			for (TempAddedIndentLabours labourRequest : tempAddedIndentLabours) {
				if (labourRequest.getIndentNo() != null) {
					indentNumber = labourRequest.getIndentNo();
					break;
				}
			}
		}

		// Check for indent number in TempIndentVehicleRequest list
		if (indentNumber == null) {
			for (TempAddedIndentVehicles vehicleRequest : tempAddedIndentVehicles) {
				if (vehicleRequest.getIndentNo() != null) {
					indentNumber = vehicleRequest.getIndentNo();
					break;
				}
			}
		}

		if (tempAddedIndentItems != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<TempIndentItemRequest> tempIndentItemRequests = tempAddedIndentItems.stream()
					.map(tempIndentItem -> modelMapper.map(tempIndentItem, TempIndentItemRequest.class))
					.collect(Collectors.toList());

			tempIndentItemRequests.forEach(indentItem -> {
				indentItem.setUserName(principal.getName());
			});

			this.tempIndentItemRequestRepo.saveAll(tempIndentItemRequests);
			this.tempAddedIndentMaterialsRepo.deleteByComplNo(complNo);
		}
		if (tempAddedIndentLabours != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<TempIndentLabourRequest> tempIndentLabourRequests = tempAddedIndentLabours.stream()
					.map(tempIndentLabor -> modelMapper.map(tempIndentLabor, TempIndentLabourRequest.class))
					.collect(Collectors.toList());

			tempIndentLabourRequests.forEach(indentLabor -> {
				indentLabor.setUserName(principal.getName());
			});

			this.tempIndentLabourRequestRepo.saveAll(tempIndentLabourRequests);
			this.tempAddedIndentLaboursRepo.deleteByComplNo(complNo);

		}
		if (tempAddedIndentVehicles != null) {

			ModelMapper modelMapper = new ModelMapper();
			List<TempIndentVehicleRequest> tempIndentVehicleRequests = tempAddedIndentVehicles.stream()
					.map(tempIndentVehicle -> modelMapper.map(tempIndentVehicle, TempIndentVehicleRequest.class))
					.collect(Collectors.toList());

			tempIndentVehicleRequests.forEach(indentVehicle -> {
				indentVehicle.setUserName(principal.getName());
			});

			this.tempIndentVehicleRequestRepo.saveAll(tempIndentVehicleRequests);
			this.tempAddedIndentVehiclesRepo.deleteByComplNo(complNo);
		}

		ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
		oldcomplaintDto.setComplStatus("waiting_for_indent_approval");
		oldcomplaintDto.setIndentNo(indentNumber);
		this.taskUpdateService.saveComplaint(oldcomplaintDto);
		String deptName = oldcomplaintDto.getDepartment();
		try {
			DepartmentIdMasterDto deptIdMasterDto = this.masterService.getByMasterIdNameAndDeptName(masterIdName,
					deptName);
			String lastIndentNo = deptIdMasterDto.getDeptLastId();

			StringBuilder letters = new StringBuilder();
			StringBuilder numbers = new StringBuilder();
			for (int i = 0; i < lastIndentNo.length(); i++) {
				char c = lastIndentNo.charAt(i);
				if (Character.isDigit(c)) {
					numbers.append(c);
				} else {
					letters.append(c);
				}
			}
			String lettersString = letters.toString();
			String numbersString = numbers.toString();

			int number = Integer.parseInt(numbersString);
			number++;
			String newStartId = lettersString + Integer.toString(number);
			deptIdMasterDto.setDeptLastId(newStartId);
			this.masterService.saveDepartmentIdMaster(deptIdMasterDto);

		} catch (Exception e) {
			System.out.println("Exception :: " + e.getMessage());
		}

		session.setAttribute("message", new Message("Indent Successfully Created !! Wait For Approval !!", "success"));
		return "redirect:/pipe/maintenance/indent";
	}

	// Display Pipe Maintenance View Page
	@GetMapping("/view")
	@PreAuthorize("hasAuthority('PIPE_VIEW')")
	public String pipeViewWorkOrderList(Model model) {
		String complSts = "APPROVED_WORKORDERS";
		String department = "Pipe Dept";
		List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplSts(department, complSts);
		model.addAttribute("workorderApprovedcompls", complaintDtos);
		model.addAttribute("title", "Pipe | View | Manintenance Management");
		return "/pages/pipe_management/pipe_view_workorder_list";
	}

	// display Approved WorkOrders Details By WorkOrder Number In View Page
	@GetMapping("/view/detalis/{workOrder}")
	public String viewApprovedWorkOrderDtls(@PathVariable String workOrder, Model model) {

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
		model.addAttribute("title", "Pipe | View | Manintenance Management");
		return "/pages/pipe_management/pipe_view_workorder_dtls";
	}

	@GetMapping("/update")
	@PreAuthorize("hasAuthority('PIPE_UPDATE')")
	public String pipeMaintenanceWorkUpdate(Model model) {
		String complSts = "APPROVED_WORKORDERS";
		String department = "Pipe Dept";
		List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplSts(department, complSts);
		model.addAttribute("approvedWorkOrders", complaintDtos);
		model.addAttribute("complDtls", new ComplaintDto());
		model.addAttribute("title", "Pipe | Update | Manintenance Management");
		return "/pages/pipe_management/pipe_maintenance_work_update";
	}

	@GetMapping("/inspection")
	@PreAuthorize("hasAuthority('PIPE_INSPECTION')")
	public String maintenanceInspection(Model model) {
		return "/pages/pipe_management/pipe_maintenance_inspection";
	}

	@GetMapping("/history")
	@PreAuthorize("hasAuthority('PIPE_HISTORY')")
	public String maintenanceHistory() {
		return "/pages/pipe_management/pipe_maintenance_history";
	}

}
