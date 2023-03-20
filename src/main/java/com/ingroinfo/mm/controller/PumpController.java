package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
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
import com.ingroinfo.mm.dao.TempWorkOderLabourRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderItemRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderVehicleRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderItemsRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderLabourRequestRepository;
import com.ingroinfo.mm.dao.WorkOrderVehicleRequestRepository;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.DepartmentIdMasterDto;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.PumpMaintenanceDto;
import com.ingroinfo.mm.dto.PumpMasterDto;
import com.ingroinfo.mm.dto.UnitMeasureDto;
import com.ingroinfo.mm.dto.WorkPriorityDto;
import com.ingroinfo.mm.entity.TempWorkOderLabourRequest;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.entity.WorkOrderItemsRequest;
import com.ingroinfo.mm.entity.WorkOrderLabourRequest;
import com.ingroinfo.mm.entity.WorkOrderVehicleRequest;
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
	private TempWorkOrderItemRequestRepository tempWorkOrderItemRequestRepo;
	@Autowired
	private TempWorkOderLabourRequestRepository tempWorkOderLabourRequestRepo;
	@Autowired
	private TempWorkOrderVehicleRequestRepository tempWorkOrderVehicleRequestRepo;
	@Autowired
	private WorkOrderItemsRequestRepository workOrderItemRequestRepo;
	@Autowired
	private WorkOrderLabourRequestRepository workOrderLabourRequestRepo;
	@Autowired
	private WorkOrderVehicleRequestRepository workOrderVehicleRequestRepo;

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
	public ResponseEntity<TempWorkOrderItemRequest> saveIndentData(
			@ModelAttribute("materialData") TempWorkOrderItemRequest tempWorkOrderItemRequest) {
		TempWorkOrderItemRequest temWorkOrderItemData = this.tempWorkOrderItemRequestRepo
				.save(tempWorkOrderItemRequest);
		return new ResponseEntity<TempWorkOrderItemRequest>(temWorkOrderItemData, HttpStatus.OK);
	}

	// Get Added Pump Material Data List
	@ResponseBody
	@GetMapping("/add/get/materials/{indentNo}/{complNo}")
	public ResponseEntity<List<TempWorkOrderItemRequest>> getListOfMaterialAddedData(@PathVariable String indentNo,
			@PathVariable String complNo) {
		List<TempWorkOrderItemRequest> tempWorkOrderItemRequests = this.tempWorkOrderItemRequestRepo
				.findListOfAddedMateials(indentNo, complNo);
		return new ResponseEntity<List<TempWorkOrderItemRequest>>(tempWorkOrderItemRequests, HttpStatus.OK);
	}

	// Delete Added Pump Material Data From List
	@DeleteMapping("/delete/materials/{itemReqId}")
	public ResponseEntity<String> deleteMaterialDataFromList(@PathVariable Long itemReqId) {
		this.tempWorkOrderItemRequestRepo.deleteById(itemReqId);
		return new ResponseEntity<>("Are You Sure To Remove This Item !!", HttpStatus.OK);

	}

	// Handler For Adding Pump Labor in List
	@PostMapping("/save/add/labourData")
	@ResponseBody
	public ResponseEntity<TempWorkOderLabourRequest> addLabourData(
			@ModelAttribute("labourData") TempWorkOderLabourRequest tempWorkOderLabourRequest) {
		TempWorkOderLabourRequest temWorkOrderLabourData = this.tempWorkOderLabourRequestRepo
				.save(tempWorkOderLabourRequest);
		return new ResponseEntity<TempWorkOderLabourRequest>(temWorkOrderLabourData, HttpStatus.OK);
	}

	// Get Added Pump Labor Data List
	@ResponseBody
	@GetMapping("/add/get/labour/{indentNo}/{complNo}")
	public ResponseEntity<List<TempWorkOderLabourRequest>> getListOfLaborAddedData(@PathVariable String indentNo,
			@PathVariable String complNo) {
		List<TempWorkOderLabourRequest> tempWorkOrderlaborRequests = this.tempWorkOderLabourRequestRepo
				.findListOfAddedLabors(indentNo, complNo);
		return new ResponseEntity<List<TempWorkOderLabourRequest>>(tempWorkOrderlaborRequests, HttpStatus.OK);
	}

	// Delete Added Pump Labor Data From List
	@DeleteMapping("/delete/labour/{labourReqId}")
	public ResponseEntity<String> deleteLabourDataFromList(@PathVariable Long labourReqId) {
		this.tempWorkOderLabourRequestRepo.deleteById(labourReqId);
		return new ResponseEntity<>("Are You Sure To Remove This Labor Details !!", HttpStatus.OK);

	}

	// Handler For Adding Pump Vehicle in List
	@PostMapping("/save/add/vehicleData")
	@ResponseBody
	public ResponseEntity<TempWorkOrderVehicleRequest> addVehicleData(
			@ModelAttribute("vehicleData") TempWorkOrderVehicleRequest tempWorkOrderVehicleRequest) {
		TempWorkOrderVehicleRequest tempWorkOrderVehicleData = this.tempWorkOrderVehicleRequestRepo
				.save(tempWorkOrderVehicleRequest);
		return new ResponseEntity<TempWorkOrderVehicleRequest>(tempWorkOrderVehicleData, HttpStatus.OK);
	}

	// Get Added Pump Labor Data List
	@ResponseBody
	@GetMapping("/add/get/vehicle/{indentNo}/{complNo}")
	public ResponseEntity<List<TempWorkOrderVehicleRequest>> getListOfVehicleAddedData(@PathVariable String indentNo,
			@PathVariable String complNo) {
		List<TempWorkOrderVehicleRequest> tempWorkOrderVehicleRequests = this.tempWorkOrderVehicleRequestRepo
				.findListOfAddedVehicles(indentNo, complNo);
		return new ResponseEntity<List<TempWorkOrderVehicleRequest>>(tempWorkOrderVehicleRequests, HttpStatus.OK);
	}

	// Delete Added Pump Labor Data From List
	@DeleteMapping("/delete/vehicle/{vehicleReqId}")
	public ResponseEntity<String> deletevehicleDataFromList(@PathVariable Long vehicleReqId) {
		this.tempWorkOrderVehicleRequestRepo.deleteById(vehicleReqId);
		return new ResponseEntity<>("Are You Sure To Remove This Labor Details !!", HttpStatus.OK);

	}

	// Handler For Add Pump Indent Data In WorkOrder Table
	@GetMapping("/generate/workorder/{complNo}")
	public String savePumpIndent(@PathVariable String complNo, Principal principal, HttpSession session, Model model) {
		String masterIdName = "Indent Id";
		//String deptName = "Pump Dept";

		List<TempWorkOrderItemRequest> tempWorkOrderItems = this.tempWorkOrderItemRequestRepo.findByComplNo(complNo);
		List<TempWorkOderLabourRequest> tempWorkOrderLabors = this.tempWorkOderLabourRequestRepo.findByComplNo(complNo);
		List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles = this.tempWorkOrderVehicleRequestRepo
				.findByComplNo(complNo);

		List<WorkOrderItemsRequest> workOrderItemsRequests = new ArrayList<>();
		List<WorkOrderLabourRequest> workOrderLaborRequests = new ArrayList<>();
		List<WorkOrderVehicleRequest> workOrderVehicleRequests = new ArrayList<>();

		if (tempWorkOrderItems != null) {

			for (TempWorkOrderItemRequest tempWorkOrderItem : tempWorkOrderItems) {
				WorkOrderItemsRequest workOrderItems = new WorkOrderItemsRequest();
				String complNumber = "101" + tempWorkOrderItem.getComplNo();
				workOrderItems.setWorkOrderNo(Long.parseLong(complNumber));
				workOrderItems.setDepartmentName(tempWorkOrderItem.getDepartmentName());
				workOrderItems.setItemId(tempWorkOrderItem.getItemId());
				workOrderItems.setStockType(tempWorkOrderItem.getStockType());
				workOrderItems.setQuantity(Integer.parseInt(tempWorkOrderItem.getQuantity()));
				workOrderItems.setUsername(principal.getName());
				workOrderItems.setIndentNo(tempWorkOrderItem.getIndentNo());
				workOrderItems.setComplNo(tempWorkOrderItem.getComplNo());
				workOrderItems.setDivision(tempWorkOrderItem.getDivision());
				workOrderItems.setSubDivision(tempWorkOrderItem.getSubDivision());
				workOrderItems.setWorkSite(tempWorkOrderItem.getWorkSite());
				workOrderItems.setStartDate(tempWorkOrderItem.getStartDate());
				workOrderItems.setEndDate(tempWorkOrderItem.getEndDate());
				workOrderItems.setContactNo(tempWorkOrderItem.getContactNo());
				workOrderItems.setComplDtls(tempWorkOrderItem.getComplDtls());
				workOrderItems.setWorkPriority(tempWorkOrderItem.getWorkPriority());
				workOrderItems.setCategoryName(tempWorkOrderItem.getCategoryName());
				workOrderItems.setItemName(tempWorkOrderItem.getItemName());
				workOrderItems.setUnitOfMesure(tempWorkOrderItem.getUnitOfMesure());
				workOrderItems.setHsnCode(tempWorkOrderItem.getHsnCode());

				workOrderItemsRequests.add(workOrderItems);
			}

			this.workOrderItemRequestRepo.saveAll(workOrderItemsRequests);
			this.tempWorkOrderItemRequestRepo.deleteAddedByComplNo(complNo);
		}
		if (tempWorkOrderLabors != null) {

			for (TempWorkOderLabourRequest tempWorkOrderLabor : tempWorkOrderLabors) {
				WorkOrderLabourRequest workOrderLabors = new WorkOrderLabourRequest();
				String complNumber = "101" + tempWorkOrderLabor.getComplNo();
				workOrderLabors.setWorkOrderNo(Long.parseLong(complNumber));
				workOrderLabors.setUsername(principal.getName());
				workOrderLabors.setDepartmentName(tempWorkOrderLabor.getDepartmentName());
				workOrderLabors.setIndentNo(tempWorkOrderLabor.getIndentNo());
				workOrderLabors.setComplNo(tempWorkOrderLabor.getComplNo());
				workOrderLabors.setDivision(tempWorkOrderLabor.getDivision());
				workOrderLabors.setSubDivision(tempWorkOrderLabor.getSubDivision());
				workOrderLabors.setWorkSite(tempWorkOrderLabor.getWorkSite());
				workOrderLabors.setStartDate(tempWorkOrderLabor.getStartDate());
				workOrderLabors.setEndDate(tempWorkOrderLabor.getEndDate());
				workOrderLabors.setContactNo(tempWorkOrderLabor.getContactNo());
				workOrderLabors.setComplDtls(tempWorkOrderLabor.getComplDtls());
				workOrderLabors.setWorkPriority(tempWorkOrderLabor.getWorkPriority());
				workOrderLabors.setEmpCategory(tempWorkOrderLabor.getEmpCategory());
				workOrderLabors.setMembers(tempWorkOrderLabor.getMembers());
				workOrderLabors.setDaysRequired(tempWorkOrderLabor.getDaysRequired());
				workOrderLabors.setTimeRequired(tempWorkOrderLabor.getTimeRequired());

				workOrderLaborRequests.add(workOrderLabors);
			}

			this.workOrderLabourRequestRepo.saveAll(workOrderLaborRequests);
			this.tempWorkOderLabourRequestRepo.deleteAddedByComplNo(complNo);

		}
		if (tempWorkOrderVehicles != null) {

			for (TempWorkOrderVehicleRequest tempWorkOrderVehicle : tempWorkOrderVehicles) {
				WorkOrderVehicleRequest workOrderVehicles = new WorkOrderVehicleRequest();
				String complNumber = "101" + tempWorkOrderVehicle.getComplNo();
				workOrderVehicles.setWorkOrderNo(Long.parseLong(complNumber));
				workOrderVehicles.setUsername(principal.getName());
				workOrderVehicles.setDepartmentName(tempWorkOrderVehicle.getDepartmentName());
				workOrderVehicles.setIndentNo(tempWorkOrderVehicle.getIndentNo());
				workOrderVehicles.setComplNo(tempWorkOrderVehicle.getComplNo());
				workOrderVehicles.setDivision(tempWorkOrderVehicle.getDivision());
				workOrderVehicles.setSubDivision(tempWorkOrderVehicle.getSubDivision());
				workOrderVehicles.setWorkSite(tempWorkOrderVehicle.getWorkSite());
				workOrderVehicles.setStartDate(tempWorkOrderVehicle.getStartDate());
				workOrderVehicles.setEndDate(tempWorkOrderVehicle.getEndDate());
				workOrderVehicles.setContactNo(tempWorkOrderVehicle.getContactNo());
				workOrderVehicles.setComplDtls(tempWorkOrderVehicle.getComplDtls());
				workOrderVehicles.setWorkPriority(tempWorkOrderVehicle.getWorkPriority());
				workOrderVehicles.setVehicleType(tempWorkOrderVehicle.getVehicleType());
				workOrderVehicles.setVehicleNo(tempWorkOrderVehicle.getVehicleNo());
				workOrderVehicles.setDriverName(tempWorkOrderVehicle.getDriverName());
				workOrderVehicles.setDriverPhone(tempWorkOrderVehicle.getDriverPhone());
				workOrderVehicles.setMeterReading(tempWorkOrderVehicle.getMeterReading());
				workOrderVehicles.setStratTime(tempWorkOrderVehicle.getStratTime());
				workOrderVehicles.setVehicleId(tempWorkOrderVehicle.getVehicleId());

				workOrderVehicleRequests.add(workOrderVehicles);
			}

			this.workOrderVehicleRequestRepo.saveAll(workOrderVehicleRequests);
			this.tempWorkOrderVehicleRequestRepo.deleteAddedByComplNo(complNo);
		}

		ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
		oldcomplaintDto.setComplStatus("Work_Order_Issued");
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

		session.setAttribute("message", new Message("Work Order Requested Successfully", "success"));
		return "redirect:/pump/maintenance/indent";
	}

	// verify Items
	@RequestMapping("/add/materials/item/verify/{itemId}")
	@ResponseBody
	public String verifyBillNo(@PathVariable("itemId") String itemId) {
		String f = "false";
		if (tempWorkOrderItemRequestRepo.existsByItemId(itemId)) {
			f = "true";
		}
		return f;
	}

	@GetMapping("/maintenance/view")
	@PreAuthorize("hasAuthority('PUMP_VIEW')")
	public String pumpViewWork(Model model) {
		model.addAttribute("title", "Pump | View | Manintenance Management");
		return "/pages/pump_house/pump_viewwork";
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
