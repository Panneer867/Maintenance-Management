package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ingroinfo.mm.dao.TempAddedIndentLaboursRepository;
import com.ingroinfo.mm.dao.TempAddedIndentMaterialsRepository;
import com.ingroinfo.mm.dao.TempAddedIndentVehiclesRepository;
import com.ingroinfo.mm.dao.TempIndentItemRequestRepository;
import com.ingroinfo.mm.dao.TempIndentLabourRequestRepository;
import com.ingroinfo.mm.dao.TempIndentVehicleRequestRepository;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.DepartmentIdMasterDto;
import com.ingroinfo.mm.dto.DmaWardDto;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.MaintenanceTypeDto;
import com.ingroinfo.mm.dto.PumpMaintenanceInspectionDto;
import com.ingroinfo.mm.dto.PumpMaintenanceUpdatedDto;
import com.ingroinfo.mm.dto.PumpMaintenanceDto;
import com.ingroinfo.mm.dto.PumpMasterDto;
import com.ingroinfo.mm.dto.TeamCodeDto;
import com.ingroinfo.mm.dto.UnitMeasureDto;
import com.ingroinfo.mm.dto.WaterSourceDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedItemsDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedLaboursDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedVehiclesDto;
import com.ingroinfo.mm.dto.WorkPriorityDto;
import com.ingroinfo.mm.dto.WorkStatusDto;
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
import com.ingroinfo.mm.service.PumpMaintenanceService;
import com.ingroinfo.mm.service.StockService;
import com.ingroinfo.mm.service.TaskUpdateService;
import com.ingroinfo.mm.service.WorkOrderService;

@Controller
@RequestMapping("/pump")
public class PumpController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@Autowired
	private MasterService masterService;
	@Autowired
	private PumpMaintenanceService pumpMaintenService;
	@Autowired
	private TaskUpdateService taskUpdateService;
	@Autowired
	private AdminService adminService;
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

	// Handler For Open dashBoard
	@GetMapping("/maintenance/dashboard")
	@PreAuthorize("hasAuthority('PUMP_DASHBOARD')")
	public String openDashboard(Model model) {
		model.addAttribute("title", "Pump | Dashboard | Manintenance Management");
		return "/pages/pump_house/dashboard";
	}

	// Handler For Open Maintenance Page
	@GetMapping("/maintenance/index")
	@PreAuthorize("hasAuthority('PUMP_INDEX')")
	public String pumpMaster(Model model) {
		model.addAttribute("title", "Pump | Index | Manintenance Management");
		model.addAttribute("pumps", new PumpMasterDto());
		return "/pages/pump_house/pump_maintenance";
	}

	// Handler For Saving Pump Maintenance Index Data
	@PostMapping("/save/index")
	public String savePumpMaintenance(PumpMaintenanceDto pumpMaintenDto, HttpSession session) {
		this.pumpMaintenService.savePumpMaintenance(pumpMaintenDto);
		session.setAttribute("message", new Message("Data Sucessfully Saved !!", "success"));
		return "redirect:/pump/maintenance";
	}

	// handler For Open Pump Maintenance Indent Page
	@GetMapping("/maintenance/indent")
	@PreAuthorize("hasAuthority('PUMP_INDENT')")
	public String pumpMaintenanceIntent(Model model, Principal principal, HttpSession session) {
		model.addAttribute("pumpMaintenData", new PumpMaintenanceDto());
		model.addAttribute("deptMaster", new DepartmentIdMasterDto());
		model.addAttribute("complDtls", new ComplaintDto());
		User user = adminService.getUserByUsername(principal.getName());
		String masterIdName = "Indent Id";
		String deptName = "Pump Dept";
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
		model.addAttribute("title", "Pump | Indent | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_indent";
	}

	// Handler For Getting Complaint Details For Pump Indent page
	@GetMapping("/maintenance/indent/compl-details/{complId}")
	public String getComplainDtlsForIndent(@PathVariable Long complId, Model model, Principal principal) {
		User user = adminService.getUserByUsername(principal.getName());
		String masterIdName = "Indent Id";
		String deptName = "Pump Dept";
		String userId = user.getUbarmsUserId() + "";
		String complSts = "NeedMaterial";

		try {
			DepartmentIdMasterDto departmentIdMasterDto = this.masterService.getByMasterIdNameAndDeptName(masterIdName,
					deptName);
			model.addAttribute("deptMaster", departmentIdMasterDto);
		} catch (Exception e) {
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
			System.out.println("Something Wrong !! " + e.getMessage());
		}
		model.addAttribute("title", "Pump | Indent | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_indent";
	}

	// Handler For Getting Item List From Stocks
	@GetMapping("/get/itemname/{category}")
	@ResponseBody
	public List<InwardDto> getItemName(@PathVariable("category") String category) {
		List<InwardDto> stockList = stockService.getAllStocks();
		List<InwardDto> itemsList = stockList.stream().filter(item -> item.getCategory().equals(category))
				.collect(Collectors.toList());
		return itemsList;
	}

	// Handler For Getting Item Data From Stocks
	@GetMapping("/get/stockItem/{itemId}")
	@ResponseBody
	public InwardDto getStockItem(@PathVariable("itemId") String itemId) {
		List<InwardDto> stockList = stockService.getAllStocks();
		Optional<InwardDto> stockItem = stockList.stream().filter(item -> item.getItemId().equals(itemId)).findFirst();
		return stockItem.get();
	}

	// Handler For Adding Pump Material List
	@PostMapping("/save/add/materialData")
	@ResponseBody
	public ResponseEntity<TempAddedIndentMaterials> saveAddedIndentData(
			@ModelAttribute("materialData") TempAddedIndentMaterials tempAddedIndentItems) {
		TempAddedIndentMaterials tempAddedIndentItemData = this.tempAddedIndentMaterialsRepo.save(tempAddedIndentItems);
		return new ResponseEntity<TempAddedIndentMaterials>(tempAddedIndentItemData, HttpStatus.OK);
	}

	// Get Added Pump Material Data List
	@ResponseBody
	@GetMapping("/add/get/materials/{indentNo}/{complNo}")
	public ResponseEntity<List<TempAddedIndentMaterials>> getListOfMaterialAddedData(@PathVariable String indentNo,
			@PathVariable String complNo) {
		List<TempAddedIndentMaterials> tempAddedIndentItem = this.tempAddedIndentMaterialsRepo
				.getByIndentNoAndComplNo(indentNo, complNo);
		return new ResponseEntity<List<TempAddedIndentMaterials>>(tempAddedIndentItem, HttpStatus.OK);
	}

	// Delete Added Pump Material Data From List
	@DeleteMapping("/delete/materials/{id}")
	public ResponseEntity<String> deleteMaterialDataFromList(@PathVariable Long id) {
		this.tempAddedIndentMaterialsRepo.deleteById(id);
		return new ResponseEntity<>("Are You Sure To Remove This Item !!", HttpStatus.OK);

	}

	// Handler For Adding Pump Labor in List
	@PostMapping("/save/add/labourData")
	@ResponseBody
	public ResponseEntity<TempAddedIndentLabours> addLabourData(
			@ModelAttribute("labourData") TempAddedIndentLabours tempAddedIndentLabours) {
		TempAddedIndentLabours tempAddedIndentLaborData = this.tempAddedIndentLaboursRepo.save(tempAddedIndentLabours);
		return new ResponseEntity<TempAddedIndentLabours>(tempAddedIndentLaborData, HttpStatus.OK);
	}

	// Get Added Pump Labor Data List
	@ResponseBody
	@GetMapping("/add/get/labour/{indentNo}/{complNo}")
	public ResponseEntity<List<TempAddedIndentLabours>> getListOfLaborAddedData(@PathVariable String indentNo,
			@PathVariable String complNo) {
		List<TempAddedIndentLabours> tempAddedIndentLabours = this.tempAddedIndentLaboursRepo
				.getByIndentNoAndComplNo(indentNo, complNo);
		return new ResponseEntity<List<TempAddedIndentLabours>>(tempAddedIndentLabours, HttpStatus.OK);
	}

	// Delete Added Pump Labor Data From List
	@DeleteMapping("/delete/labour/{id}")
	public ResponseEntity<String> deleteLabourDataFromList(@PathVariable Long id) {
		this.tempAddedIndentLaboursRepo.deleteById(id);
		return new ResponseEntity<>("Are You Sure To Remove This Labor Details !!", HttpStatus.OK);

	}

	// Handler For Adding Pump Vehicle in List
	@PostMapping("/save/add/vehicleData")
	@ResponseBody
	public ResponseEntity<TempAddedIndentVehicles> addTempVehicleData(
			@ModelAttribute("vehicleData") TempAddedIndentVehicles tempAddedIndentVehicles) {
		TempAddedIndentVehicles tempIndentVehicleData = this.tempAddedIndentVehiclesRepo.save(tempAddedIndentVehicles);
		return new ResponseEntity<TempAddedIndentVehicles>(tempIndentVehicleData, HttpStatus.OK);
	}

	// Get Added Pump Labor Data List
	@ResponseBody
	@GetMapping("/add/get/vehicle/{indentNo}/{complNo}")
	public ResponseEntity<List<TempAddedIndentVehicles>> getListOfVehicleAddedData(@PathVariable String indentNo,
			@PathVariable String complNo) {
		List<TempAddedIndentVehicles> tempAddedIndentVehicles = this.tempAddedIndentVehiclesRepo
				.getByIndentNoAndComplNo(indentNo, complNo);
		return new ResponseEntity<List<TempAddedIndentVehicles>>(tempAddedIndentVehicles, HttpStatus.OK);
	}

	// Delete Added Pump Labor Data From List
	@DeleteMapping("/delete/vehicle/{id}")
	public ResponseEntity<String> deletevehicleDataFromList(@PathVariable Long id) {
		this.tempAddedIndentVehiclesRepo.deleteById(id);
		return new ResponseEntity<>("Are You Sure To Remove This Labor Details !!", HttpStatus.OK);

	}

	// Handler For Add Pump Indent Data In TempIndent Table
	@GetMapping("/maintenance/indent/submit/{complNo}/{indentNo}")
	public String savePumpIndent(@PathVariable String complNo, @PathVariable String indentNo, Principal principal,
			HttpSession session, Model model) {
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
		
		String nextIncrimentId = this.masterService.getAutoIncrimentIdForDepartment(masterIdName, deptName);
		DepartmentIdMasterDto deptIdMasterDto = this.masterService.getByMasterIdNameAndDeptName(masterIdName,
				deptName);
		deptIdMasterDto.setDeptLastId(nextIncrimentId);
		this.masterService.saveDepartmentIdMaster(deptIdMasterDto);
		
		session.setAttribute("message", new Message("Indent Successfully Created !! Wait For Approval !!", "success"));
		return "redirect:/pump/maintenance/indent";
	}

	// verify Items
	@RequestMapping("/add/materials/item/verify/{itemId}/{complNo}")
	@ResponseBody
	public String verifyItmName(@PathVariable("itemId") String itemId,@PathVariable("complNo")String complNo) {
		String f = "false";
		if (tempAddedIndentMaterialsRepo.existsByItemIdAndComplNo(itemId,complNo)) {
			f = "true";
		}
		return f;
	}

	// verify Employee Category
	@RequestMapping("/add/labor/empcategory/verify/{empCategory}/{complNo}")
	@ResponseBody
	public String verifyEmployeeCategory(@PathVariable("empCategory") String empCategory,@PathVariable("complNo")String complNo) {
		String f = "false";
		if (tempAddedIndentLaboursRepo.existsByEmpCategoryAndComplNo(empCategory,complNo)) {
			f = "true";
		}
		return f;
	}

	// verify Vehicle Number
	@RequestMapping("/add/vehicle/number/verify/{vehicleNo}/{complNo}")
	@ResponseBody
	public String verifyVehicleNumber(@PathVariable("vehicleNo") String vehicleNo,@PathVariable("complNo")String complNo) {
		String f = "false";
		if (tempAddedIndentVehiclesRepo.existsByVehicleNoAndComplNo(vehicleNo,complNo)) {
			f = "true";
		}
		return f;
	}

	// display Approved WorkOrders List In Pump View
	@GetMapping("/maintenance/view")
	@PreAuthorize("hasAuthority('PUMP_VIEW')")
	public String viewApprovedWorkOrderList(Model model) {
		String complSts = "APPROVED_WORKORDERS";
		String department = "Pump Dept";
		List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplSts(department, complSts);
		model.addAttribute("workorderApprovedcompls", complaintDtos);
		model.addAttribute("title", "Pump | View | Manintenance Management");
		return "/pages/pump_house/pump_view_workorder_list";
	}

	// display Approved WorkOrders Details By WorkOrder Number In View Page
	@GetMapping("/maintenance/view/detalis/{workOrder}")
	public String viewWorkOrderDtls(@PathVariable String workOrder, Model model) {

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
		model.addAttribute("title", "Pump | View | Manintenance Management");
		return "/pages/pump_house/pump_view_workorder_dtls";
	}

	// Display Pump Maintenance Update Form
	@GetMapping("/maintenance/update")
	@PreAuthorize("hasAuthority('PUMP_UPDATE')")
	public String pumpMaintenanceUpdate(Model model) {
		String complSts = "APPROVED_WORKORDERS";
		String department = "Pump Dept";
		List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplSts(department, complSts);
		model.addAttribute("approvedWorkOrders", complaintDtos);
		model.addAttribute("complDtls", new ComplaintDto());
		model.addAttribute("title", "Pump | Update | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_update";
	}

	// Get WorkOrder Data By WorkOrder Number
	@GetMapping("/maintenance/update/get/{workOrderNo}")
	public String getWorkOrderDetailsByWorkOrderNo(@PathVariable("workOrderNo") String workOrderNo, Model model) {
		try {
			String complSts = "APPROVED_WORKORDERS";
			String department = "Pump Dept";
			List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplSts(department, complSts);
			ComplaintDto complaintDto = taskUpdateService.getComplDetailsByWorkOrderNo(workOrderNo);
			List<PumpMasterDto> pumpMasterDtos = this.masterService.getAllPumpMaster();
			List<WaterSourceDto> waterSourceDtos = this.masterService.findAllWaterSource();
			List<MaintenanceTypeDto> maintenanceTypeDtos = this.masterService.findAllMaintenanceType();
			List<DmaWardDto> dmaWardDtos = this.masterService.getAllDmaWard();
			List<TeamCodeDto> teamCodeDtos = this.masterService.getAllTeamCode();
			List<WorkStatusDto> workStatusDtos = this.masterService.getAllWorkStatus();
			List<WorkOrderApprovedItemsDto> workOrderApprovedItemsDtos = this.workOrderService
					.getApprovedWorkOrderItemsByWorkorderNo(workOrderNo);
			model.addAttribute("approvedWorkorderItems", workOrderApprovedItemsDtos);
			model.addAttribute("listOfWorkSts", workStatusDtos);
			model.addAttribute("listOfTeamCode", teamCodeDtos);
			model.addAttribute("listofDmaWard", dmaWardDtos);
			model.addAttribute("listOfMaintenanceType", maintenanceTypeDtos);
			model.addAttribute("listOfWaterSourse", waterSourceDtos);
			model.addAttribute("listOfPumpMaster", pumpMasterDtos);
			model.addAttribute("complDtls", complaintDto);
			model.addAttribute("approvedWorkOrders", complaintDtos);
		} catch (Exception e) {
			System.out.println("Something Wrong !!" + e.getMessage());
		}

		model.addAttribute("title", "Pump | Update | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_update";
	}

	// Submit Pump Updated Maintenance Data
	@PostMapping("/maintenance/submitUpdated")
	public String submitUpdatedMainteance(PumpMaintenanceUpdatedDto pumpDto, HttpSession session,
			Principal principal) {
		pumpDto.setUserName(principal.getName());
		this.pumpMaintenService.savePumpMaintenanceUpdated(pumpDto);
		ComplaintDto oldComplaintDto = this.taskUpdateService.getComplainDataByComplainNo(pumpDto.getComplNo());
		oldComplaintDto.setComplStatus("WORKORDER_UPDATED");
		this.taskUpdateService.saveComplaint(oldComplaintDto);
		session.setAttribute("message", new Message("Updated Data Successfully Updated !!", "success"));
		return "redirect:/pump/maintenance/update";
	}

	// display Inspections Data
	@GetMapping("/maintenance/inspection")
	@PreAuthorize("hasAuthority('PUMP_INSPECTION')")
	public String pumpMaintenanceInspection(Model model) {
		String complSts = "WORKORDER_UPDATED";
		String department = "Pump Dept";
		List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplSts(department, complSts);
		model.addAttribute("updatedWorkOrders", complaintDtos);
		model.addAttribute("maintenanceUpdatedDto", new PumpMaintenanceUpdatedDto());
		model.addAttribute("title", "Pump | Inspection | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_inspection";
	}

	// get Inspections Data By WorkOrderNo
	@GetMapping("/maintenance/inspection/get/{workOrderNo}")
	public String getPumpMaintenanceInspectionByWorkOrderNo(@PathVariable String workOrderNo, Model model) {
		String complSts = "WORKORDER_UPDATED";
		String department = "Pump Dept";
		List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplSts(department, complSts);
		PumpMaintenanceUpdatedDto pumpMaintenainceUpdatedDto = this.pumpMaintenService
				.getPumpMaintenanceUpdatedByWorkorderNo(workOrderNo);
		model.addAttribute("maintenanceUpdatedDto", pumpMaintenainceUpdatedDto);
		model.addAttribute("updatedWorkOrders", complaintDtos);
		model.addAttribute("title", "Pump | Inspection | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_inspection";
	}
	
	// Save Inspections Data 
	@PostMapping("/maintenance/inspection/submit")
	public String submitInspectionWorkOrder(PumpMaintenanceInspectionDto pumpInspectionDto,Principal principal,HttpSession session) {
		try {
			pumpInspectionDto.setUserName(principal.getName());			
			this.pumpMaintenService.savePumpInspectionData(pumpInspectionDto);
			ComplaintDto oldComplaintDto = this.taskUpdateService.getComplainDataByComplainNo(pumpInspectionDto.getComplNo());
			oldComplaintDto.setComplStatus("Completed");
			oldComplaintDto.setInspectionBy(principal.getName());
			oldComplaintDto.setInspectionDate(new java.sql.Date(System.currentTimeMillis()));
			oldComplaintDto.setWorkCompletedDate(new java.sql.Date(System.currentTimeMillis()));
			this.taskUpdateService.saveComplaint(oldComplaintDto);
			this.taskUpdateService.submitInvestigations(oldComplaintDto);
			session.setAttribute("message", new Message("Complain Inspection Successfully Completed !!","success"));
		} catch (Exception e) {
			System.out.println("Something Wrong !!"+e.getMessage());
		}		
		return "redirect:/pump/maintenance/inspection";
	}
	

	@GetMapping("/maintenance/history")
	@PreAuthorize("hasAuthority('PUMP_HISTORY')")
	public String pumpMaintenanceHistory(Model model) {
		model.addAttribute("title", "Pump | History | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_history";
	}
}
