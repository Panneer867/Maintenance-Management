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
import com.ingroinfo.mm.dao.WorkOrderItemsRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderApprovedLaboursRepository;
import com.ingroinfo.mm.dao.WorkOrderApprovedVehiclesRepository;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.DepartmentIdMasterDto;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.PumpMaintenanceDto;
import com.ingroinfo.mm.dto.PumpMasterDto;
import com.ingroinfo.mm.dto.UnitMeasureDto;
import com.ingroinfo.mm.dto.WorkPriorityDto;
import com.ingroinfo.mm.entity.TempAddedIndentLabours;
import com.ingroinfo.mm.entity.TempAddedIndentMaterials;
import com.ingroinfo.mm.entity.TempAddedIndentVehicles;
import com.ingroinfo.mm.entity.TempIndentItemRequest;
import com.ingroinfo.mm.entity.TempIndentLabourRequest;
import com.ingroinfo.mm.entity.TempIndentVehicleRequest;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.entity.WorkOrderItemsRequest;
import com.ingroinfo.mm.entity.WorkOrderApprovedLabours;
import com.ingroinfo.mm.entity.WorkOrderApprovedVehicles;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.MasterService;
import com.ingroinfo.mm.service.PumpMaintenanceService;
import com.ingroinfo.mm.service.StockService;
import com.ingroinfo.mm.service.TaskUpdateService;

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
	private WorkOrderItemsRequestRepository workOrderItemsRequestRepo;
	@Autowired
	private WorkOrderApprovedLaboursRepository workOrderLabourRequestRepo;
	@Autowired
	private WorkOrderApprovedVehiclesRepository workOrderVehicleRequestRepo;

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
		TempAddedIndentVehicles tempIndentVehicleData = this.tempAddedIndentVehiclesRepo
				.save(tempAddedIndentVehicles);
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

	// Handler For Add Pump Indent Data In WorkOrder Table
	@GetMapping("/maintenance/indent/submit/{complNo}/{indentNo}")
	public String savePumpIndent(@PathVariable String complNo,@PathVariable String indentNo, Principal principal, HttpSession session, Model model) {
		String masterIdName = "Indent Id";

		List<TempAddedIndentMaterials> tempAddedIndentItems = this.tempAddedIndentMaterialsRepo.getByIndentNoAndComplNo(indentNo,complNo);
		List<TempAddedIndentLabours> tempAddedIndentLabours = this.tempAddedIndentLaboursRepo.getByIndentNoAndComplNo(indentNo,complNo);
		List<TempAddedIndentVehicles> tempAddedIndentVehicles = this.tempAddedIndentVehiclesRepo.getByIndentNoAndComplNo(indentNo,complNo);

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
		return "redirect:/pump/maintenance/indent";
	}

	// verify Items
	@RequestMapping("/add/materials/item/verify/{itemId}")
	@ResponseBody
	public String verifyItmName(@PathVariable("itemId") String itemId) {
		String f = "false";
		if (tempAddedIndentMaterialsRepo.existsByItemId(itemId)) {
			f = "true";
		}
		return f;
	}

	// verify Employee Category
	@RequestMapping("/add/labor/empcategory/verify/{empCategory}")
	@ResponseBody
	public String verifyEmployeeCategory(@PathVariable("empCategory") String empCategory) {
		String f = "false";
		if (tempAddedIndentLaboursRepo.existsByEmpCategory(empCategory)) {
			f = "true";
		}
		return f;
	}

	// verify Vehicle Number
	@RequestMapping("/add/vehicle/number/verify/{vehicleNo}")
	@ResponseBody
	public String verifyVehicleNumber(@PathVariable("vehicleNo") String vehicleNo) {
		String f = "false";
		if (tempAddedIndentVehiclesRepo.existsByVehicleNo(vehicleNo)) {
			f = "true";
		}
		return f;
	}
	
	@GetMapping("/maintenance/view")
	@PreAuthorize("hasAuthority('PUMP_VIEW')")
	public String viewApprovedWorkOrderList(Model model) {
		String complSts="work_order_approved";
		String department = "Pump Dept";
		List<ComplaintDto> complaintDtos = this.taskUpdateService.getComplainByDeptComplSts(department, complSts);
		model.addAttribute("workorderApprovedcompls", complaintDtos);
		model.addAttribute("title", "Pump | View | Manintenance Management");
		return "/pages/pump_house/pump_view_workorder_list";
	}

	@GetMapping("/maintenance/view/detalis/{complNo}/{indentNo}")
	public String viewWorkOrderDtls(@PathVariable String complNo,@PathVariable String indentNo,Model model) {
		
		List<WorkOrderItemsRequest> itemRequests = this.workOrderItemsRequestRepo.findByComplNoAndIndentNo(complNo, indentNo);
		List<WorkOrderApprovedLabours> labourRequests = this.workOrderLabourRequestRepo.findByComplNoAndIndentNo(complNo, indentNo);
		List<WorkOrderApprovedVehicles> vehicleRequests = this.workOrderVehicleRequestRepo.findByComplNoAndIndentNo(complNo, indentNo);

		String complNumber = null;
		String indentNumber = null;
		Long workOrderNumber = null;
		Date expStartDate = null;
		Date expEndDate = null;
		String approvedBy = null;
		String department = null;
		String division = null;
		String subDivision = null;
		String workPriprity = null;
		String workSite = null;
		String contactNo = null;		

		// Check for indent number in TempIndentItemRequest list
		for (WorkOrderItemsRequest indentItems : itemRequests) {
			if (indentItems.getIndentNo() != null || indentItems.getComplNo() != null) {
				indentNumber = indentItems.getIndentNo();
				complNumber = indentItems.getComplNo();
				workOrderNumber = indentItems.getWorkOrderNo();
				expStartDate = indentItems.getStartDate();
				expEndDate = indentItems.getEndDate();
				approvedBy = indentItems.getUsername();
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
			for (WorkOrderApprovedLabours indentLabors : labourRequests) {
				if (indentLabors.getIndentNo() != null || indentLabors.getComplNo() != null) {
					indentNumber = indentLabors.getIndentNo();
					complNumber = indentLabors.getComplNo();		
					expStartDate = indentLabors.getStartDate();
					expEndDate = indentLabors.getEndDate();
					approvedBy = indentLabors.getUserName();
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
			for (WorkOrderApprovedVehicles indentVehicle : vehicleRequests) {
				if (indentVehicle.getIndentNo() != null || indentVehicle.getComplNo() != null) {
					indentNumber = indentVehicle.getIndentNo();
					complNumber = indentVehicle.getComplNo();					
					expStartDate = indentVehicle.getStartDate();
					expEndDate = indentVehicle.getEndDate();
					approvedBy = indentVehicle.getUserName();
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
		model.addAttribute("workOrderNo", workOrderNumber);
		model.addAttribute("startDate", expStartDate);
		model.addAttribute("endDate", expEndDate);
		model.addAttribute("approvedBy", approvedBy);
		model.addAttribute("department", department);
		model.addAttribute("division", division);
		model.addAttribute("subdivision", subDivision);
		model.addAttribute("workPriority", workPriprity);
		model.addAttribute("worksite", workSite);
		model.addAttribute("contactNo", contactNo);

		model.addAttribute("listOfMaterials", itemRequests);
		model.addAttribute("listOfLabors", labourRequests);
		model.addAttribute("listOfVehicles", vehicleRequests);
		model.addAttribute("title", "Pump | View | Manintenance Management");
		return "/pages/pump_house/pump_view_workorder_dtls";
	}

	@GetMapping("/maintenance/update")
	@PreAuthorize("hasAuthority('PUMP_UPDATE')")
	public String pumpMaintenanceUpdate(Model model) {
		model.addAttribute("title", "Pump | Update | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_update";
	}

	@GetMapping("/maintenance/inspection")
	@PreAuthorize("hasAuthority('PUMP_INSPECTION')")
	public String pumpMaintenanceInspection(Model model) {
		model.addAttribute("title", "Pump | Inspection | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_inspection";
	}

	@GetMapping("/maintenance/history")
	@PreAuthorize("hasAuthority('PUMP_HISTORY')")
	public String pumpMaintenanceHistory(Model model) {
		model.addAttribute("title", "Pump | History | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_history";
	}
}
