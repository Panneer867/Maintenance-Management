package com.ingroinfo.mm.controller;

import java.security.Principal;
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
import com.ingroinfo.mm.dao.IndentItemRequestRepository;
import com.ingroinfo.mm.dao.IndentLabourRequestRepository;
import com.ingroinfo.mm.dao.IndentVehicleRequestRepository;
import com.ingroinfo.mm.dao.TempIndentItemRequestRepository;
import com.ingroinfo.mm.dao.TempIndentLabourRequestRepository;
import com.ingroinfo.mm.dao.TempIndentVehicleRequestRepository;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.DepartmentIdMasterDto;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.PumpMaintenanceDto;
import com.ingroinfo.mm.dto.PumpMasterDto;
import com.ingroinfo.mm.dto.UnitMeasureDto;
import com.ingroinfo.mm.dto.WorkPriorityDto;
import com.ingroinfo.mm.entity.IndentItemRequest;
import com.ingroinfo.mm.entity.IndentLabourRequest;
import com.ingroinfo.mm.entity.IndentVehicleRequest;
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
	private TempIndentItemRequestRepository tempIndentItemRequestRepo;
	@Autowired
	private TempIndentLabourRequestRepository tempIndentLabourRequestRepo;
	@Autowired
	private TempIndentVehicleRequestRepository tempIndentVehicleRequestRepo;
	@Autowired
	private IndentItemRequestRepository indentItemRequestRepo;
	@Autowired
	private IndentLabourRequestRepository indentLabourRequestRepo;
	@Autowired
	private IndentVehicleRequestRepository indentVehicleRequestRepo;

	// Handler For Open dashBoard
	@GetMapping("/dashboard")
	@PreAuthorize("hasAuthority('PUMP_DASHBOARD')")
	public String openDashboard(Model model) {
		model.addAttribute("title", "Pump | Dashboard | Manintenance Management");
		return "/pages/pump_house/pump-dashboard";
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
	@GetMapping("/maintenance/complDtls/{complId}")
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
	public ResponseEntity<TempIndentItemRequest> saveIndentData(
			@ModelAttribute("materialData") TempIndentItemRequest tempIndentItemRequest) {
		TempIndentItemRequest tempIndentItemData = this.tempIndentItemRequestRepo.save(tempIndentItemRequest);
		return new ResponseEntity<TempIndentItemRequest>(tempIndentItemData, HttpStatus.OK);
	}

	// Get Added Pump Material Data List
	@ResponseBody
	@GetMapping("/add/get/materials/{indentNo}/{complNo}")
	public ResponseEntity<List<TempIndentItemRequest>> getListOfMaterialAddedData(@PathVariable String indentNo,
			@PathVariable String complNo) {
		List<TempIndentItemRequest> tempIndentItemRequests = this.tempIndentItemRequestRepo
				.findListOfAddedMateials(indentNo, complNo);
		return new ResponseEntity<List<TempIndentItemRequest>>(tempIndentItemRequests, HttpStatus.OK);
	}

	// Delete Added Pump Material Data From List
	@DeleteMapping("/delete/materials/{itemReqId}")
	public ResponseEntity<String> deleteMaterialDataFromList(@PathVariable Long itemReqId) {
		this.tempIndentItemRequestRepo.deleteById(itemReqId);
		return new ResponseEntity<>("Are You Sure To Remove This Item !!", HttpStatus.OK);

	}

	// Handler For Adding Pump Labor in List
	@PostMapping("/save/add/labourData")
	@ResponseBody
	public ResponseEntity<TempIndentLabourRequest> addLabourData(
			@ModelAttribute("labourData") TempIndentLabourRequest tempIndentLabourRequest) {
		TempIndentLabourRequest tempIndentLaborData = this.tempIndentLabourRequestRepo.save(tempIndentLabourRequest);
		return new ResponseEntity<TempIndentLabourRequest>(tempIndentLaborData, HttpStatus.OK);
	}

	// Get Added Pump Labor Data List
	@ResponseBody
	@GetMapping("/add/get/labour/{indentNo}/{complNo}")
	public ResponseEntity<List<TempIndentLabourRequest>> getListOfLaborAddedData(@PathVariable String indentNo,
			@PathVariable String complNo) {
		List<TempIndentLabourRequest> tempIndentLabourRequests = this.tempIndentLabourRequestRepo
				.findListOfAddedLabors(indentNo, complNo);
		return new ResponseEntity<List<TempIndentLabourRequest>>(tempIndentLabourRequests, HttpStatus.OK);
	}

	// Delete Added Pump Labor Data From List
	@DeleteMapping("/delete/labour/{labourReqId}")
	public ResponseEntity<String> deleteLabourDataFromList(@PathVariable Long labourReqId) {
		this.tempIndentLabourRequestRepo.deleteById(labourReqId);
		return new ResponseEntity<>("Are You Sure To Remove This Labor Details !!", HttpStatus.OK);

	}

	// Handler For Adding Pump Vehicle in List
	@PostMapping("/save/add/vehicleData")
	@ResponseBody
	public ResponseEntity<TempIndentVehicleRequest> addVehicleData(
			@ModelAttribute("vehicleData") TempIndentVehicleRequest tempIndentVehicleRequestn) {
		TempIndentVehicleRequest tempIndentVehicleData = this.tempIndentVehicleRequestRepo
				.save(tempIndentVehicleRequestn);
		return new ResponseEntity<TempIndentVehicleRequest>(tempIndentVehicleData, HttpStatus.OK);
	}

	// Get Added Pump Labor Data List
	@ResponseBody
	@GetMapping("/add/get/vehicle/{indentNo}/{complNo}")
	public ResponseEntity<List<TempIndentVehicleRequest>> getListOfVehicleAddedData(@PathVariable String indentNo,
			@PathVariable String complNo) {
		List<TempIndentVehicleRequest> tempIndentVehicleRequests = this.tempIndentVehicleRequestRepo
				.findListOfAddedVehicles(indentNo, complNo);
		return new ResponseEntity<List<TempIndentVehicleRequest>>(tempIndentVehicleRequests, HttpStatus.OK);
	}

	// Delete Added Pump Labor Data From List
	@DeleteMapping("/delete/vehicle/{vehicleReqId}")
	public ResponseEntity<String> deletevehicleDataFromList(@PathVariable Long vehicleReqId) {
		this.tempIndentVehicleRequestRepo.deleteById(vehicleReqId);
		return new ResponseEntity<>("Are You Sure To Remove This Labor Details !!", HttpStatus.OK);

	}

	// Handler For Add Pump Indent Data In WorkOrder Table
	@GetMapping("/generate/workorder/{complNo}")
	public String savePumpIndent(@PathVariable String complNo, Principal principal, HttpSession session, Model model) {
		String masterIdName = "Indent Id";

		List<TempIndentItemRequest> tempIndentItems = this.tempIndentItemRequestRepo.findByComplNo(complNo);
		List<TempIndentLabourRequest> tempIndentLabours = this.tempIndentLabourRequestRepo.findByComplNo(complNo);
		List<TempIndentVehicleRequest> tempIndentVehicles = this.tempIndentVehicleRequestRepo.findByComplNo(complNo);

		String indentNumber = null;

		// Check for indent number in TempIndentItemRequest list
		for (TempIndentItemRequest itemRequest : tempIndentItems) {
			if (itemRequest.getIndentNo() != null) {
				indentNumber = itemRequest.getIndentNo();
				break;
			}
		}

		// Check for indent number in TempIndentLabourRequest list
		if (indentNumber == null) {
			for (TempIndentLabourRequest labourRequest : tempIndentLabours) {
				if (labourRequest.getIndentNo() != null) {
					indentNumber = labourRequest.getIndentNo();
					break;
				}
			}
		}

		// Check for indent number in TempIndentVehicleRequest list
		if (indentNumber == null) {
			for (TempIndentVehicleRequest vehicleRequest : tempIndentVehicles) {
				if (vehicleRequest.getIndentNo() != null) {
					indentNumber = vehicleRequest.getIndentNo();
					break;
				}
			}
		}

		if (tempIndentItems != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<IndentItemRequest> indentItemRequests = tempIndentItems.stream()
					.map(tempIndentItem -> modelMapper.map(tempIndentItem, IndentItemRequest.class))
					.collect(Collectors.toList());

			indentItemRequests.forEach(indentItem -> {
				indentItem.setUserName(principal.getName());
			});

			this.indentItemRequestRepo.saveAll(indentItemRequests);
			this.tempIndentItemRequestRepo.deleteAddedByComplNo(complNo);
		}
		if (tempIndentLabours != null) {
			ModelMapper modelMapper = new ModelMapper();
			List<IndentLabourRequest> indentLabourRequests = tempIndentLabours.stream()
					.map(tempIndentLabor -> modelMapper.map(tempIndentLabor, IndentLabourRequest.class))
					.collect(Collectors.toList());

			indentLabourRequests.forEach(indentLabor -> {
				indentLabor.setUserName(principal.getName());
			});

			this.indentLabourRequestRepo.saveAll(indentLabourRequests);
			this.tempIndentLabourRequestRepo.deleteAddedByComplNo(complNo);

		}
		if (tempIndentVehicles != null) {

			ModelMapper modelMapper = new ModelMapper();
			List<IndentVehicleRequest> indentVehicleRequests = tempIndentVehicles.stream()
					.map(tempIndentVehicle -> modelMapper.map(tempIndentVehicle, IndentVehicleRequest.class))
					.collect(Collectors.toList());

			indentVehicleRequests.forEach(indentVehicle -> {
				indentVehicle.setUserName(principal.getName());
			});

			this.indentVehicleRequestRepo.saveAll(indentVehicleRequests);
			this.tempIndentVehicleRequestRepo.deleteAddedByComplNo(complNo);
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
		if (tempIndentItemRequestRepo.existsByItemId(itemId)) {
			f = "true";
		}
		return f;
	}

	// verify Employee Category
	@RequestMapping("/add/labor/empcategory/verify/{empCategory}")
	@ResponseBody
	public String verifyEmployeeCategory(@PathVariable("empCategory") String empCategory) {
		String f = "false";
		if (tempIndentLabourRequestRepo.existsByEmpCategory(empCategory)) {
			f = "true";
		}
		return f;
	}

	// verify Vehicle Number
	@RequestMapping("/add/vehicle/number/verify/{vehicleNo}")
	@ResponseBody
	public String verifyVehicleNumber(@PathVariable("vehicleNo") String vehicleNo) {
		String f = "false";
		if (tempIndentVehicleRequestRepo.existsByVehicleNo(vehicleNo)) {
			f = "true";
		}
		return f;
	}
	
	@GetMapping("/maintenance/view")
	@PreAuthorize("hasAuthority('PUMP_VIEW')")
	public String viewApprovedWorkOrderList(Model model) {
		model.addAttribute("title", "Pump | View | Manintenance Management");
		return "/pages/pump_house/pump_view_workorder_list";
	}

	@GetMapping("/maintenance/view/detalis/")
	public String viewWorkOrderDtls(Model model) {
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
