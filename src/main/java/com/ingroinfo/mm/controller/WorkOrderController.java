package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ingroinfo.mm.dto.CancelWorkOrderDto;
import com.ingroinfo.mm.dto.GenerateLabourWorkDto;
import com.ingroinfo.mm.dto.GenerateVehicleWorkDto;
import com.ingroinfo.mm.dto.GenerateWorkOrderDto;
import com.ingroinfo.mm.dto.HoldWorkOrderDto;
import com.ingroinfo.mm.dto.HsnCodeDto;
import com.ingroinfo.mm.dto.WorkEstmationDto;
import com.ingroinfo.mm.entity.GenerateLabourWork;
import com.ingroinfo.mm.entity.GenerateVehicleWork;
import com.ingroinfo.mm.entity.GenerateWorkOrder;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.CancelWorkOrderService;
import com.ingroinfo.mm.service.GenerateWorkOrderService;
import com.ingroinfo.mm.service.HoldWorkOrderService;
import com.ingroinfo.mm.service.HsnCodeService;
import com.ingroinfo.mm.service.WorkEstmationService;

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
	private WorkEstmationService workEstmationService;

	@Autowired
	private HsnCodeService hsnCodeService;

	/***************** genrateWorkOrder********** */
	@GetMapping("/generate")
	public String genrateWorkOrder(Model model) {
		// Show the Generate Work Order Date
		List<GenerateWorkOrderDto> listOfGenWorks = this.generateWorkOrdeService.findAllGenerateWorkOrder();
		model.addAttribute("listofgenWork", listOfGenWorks);
		List<HsnCodeDto> listOfHsnCode = this.hsnCodeService.findAllHsnCode();
		model.addAttribute("listOfHsnCode", listOfHsnCode);
		model.addAttribute("title", "Work Order | Generate Work Order");
		return "/pages/work_orders/generate_work_order";
	}

	// Handler Generate Work Order Data
	@PostMapping("/savegenworkord")
	public String saveGenerateWorkOrder(GenerateWorkOrderDto idGenerate, HttpSession session) {
		this.generateWorkOrdeService.saveGenerateWorkOrder(idGenerate);
		session.setAttribute("message", new Message("Data Saved Successfully....!!", "success"));
		return "redirect:/workorder/generate";
	}

	// save Generate work category data
	@PostMapping(value = "/createBundle", consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public ResponseEntity<String> addBundleData(@RequestBody GenerateWorkOrderDto genworkOrderDto) {

		String indentNo = genworkOrderDto.getIndentNo();
		String indentDepartement = genworkOrderDto.getIndentDepartement();
		String genWorkOrderDepartment = genworkOrderDto.getGenWorkOrderDepartment();
		String workOrderNumber = genworkOrderDto.getWorkOrderNumber();
		Date workOrderDate = genworkOrderDto.getWorkOrderDate();
		String division = genworkOrderDto.getWorkOrderNumber();
		String subDivision = genworkOrderDto.getWorkOrderNumber();
		String workOrderScope = genworkOrderDto.getWorkOrderNumber();
		String workOrderIssuBy = genworkOrderDto.getWorkOrderNumber();
		String workPriority = genworkOrderDto.getWorkOrderNumber();
		String workOrderCost = genworkOrderDto.getWorkOrderNumber();
		String contact = genworkOrderDto.getWorkOrderNumber();
		String workSite = genworkOrderDto.getWorkOrderNumber();
		Date expectedStartDate = genworkOrderDto.getExpectedStartDate();
		Date expectedEndDate = genworkOrderDto.getExpectedEndDate();

		List<GenerateWorkOrder> bundleList = genworkOrderDto.getItems().stream()
				.map(generateWorkOrder -> setDataForSave(indentNo, indentDepartement, genWorkOrderDepartment,
						workOrderNumber, workOrderDate, division, subDivision, workOrderScope, workOrderIssuBy,
						workPriority, workOrderCost, contact, workSite, expectedStartDate, expectedEndDate,
						generateWorkOrder))
				.collect(Collectors.toList());

		List<GenerateWorkOrder> savedBundles = generateWorkOrdeService.createBundle(bundleList);
		if (savedBundles != null && savedBundles.size() > 0) {
			return new ResponseEntity<>("Saved", HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// injecting data to database while creating bundle
	private GenerateWorkOrder setDataForSave(String indentN, String indentDepartement, String genWorkOrderDepartment,
			String workOrderNumber, Date workOrderDate, String division, String subDivision, String workOrderScope,
			String workOrderIssuBy, String workPriority, String workOrderCost, String contact, String workSite,
			Date expectedStartDate, Date expectedEndDate, GenerateWorkOrder generateWorkOrder) {
		generateWorkOrder.setIndentNo(indentN);
		generateWorkOrder.setIndentDepartement(indentDepartement);
		generateWorkOrder.setGenWorkOrderDepartment(genWorkOrderDepartment);
		generateWorkOrder.setWorkOrderNumber(workOrderNumber);
		generateWorkOrder.setWorkOrderDate(workOrderDate);
		generateWorkOrder.setDivision(division);
		generateWorkOrder.setSubDivision(subDivision);
		generateWorkOrder.setWorkOrderScope(workOrderScope);
		generateWorkOrder.setWorkOrderIssuBy(workOrderIssuBy);
		generateWorkOrder.setWorkPriority(workPriority);
		generateWorkOrder.setWorkOrderCost(workOrderCost);
		generateWorkOrder.setContact(contact);
		generateWorkOrder.setWorkSite(workSite);
		generateWorkOrder.setExpectedStartDate(expectedStartDate);
		generateWorkOrder.setExpectedEndDate(expectedEndDate);
		return generateWorkOrder;
	}

	// Save Generate Labour

	@PostMapping(value = "/createLabourBundle", consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public ResponseEntity<String> addGenLabourData(@RequestBody GenerateLabourWorkDto genlabourWorkOrderDto) {

		String indentNo = genlabourWorkOrderDto.getIndentNo();
		String indentDepartement = genlabourWorkOrderDto.getIndentDepartement();
		String genWorkOrderDepartment = genlabourWorkOrderDto.getGenWorkOrderDepartment();
		String workOrderNumber = genlabourWorkOrderDto.getWorkOrderNumber();
		Date workOrderDate = genlabourWorkOrderDto.getWorkOrderDate();
		String division = genlabourWorkOrderDto.getWorkOrderNumber();
		String subDivision = genlabourWorkOrderDto.getWorkOrderNumber();
		String workOrderScope = genlabourWorkOrderDto.getWorkOrderNumber();
		String workOrderIssuBy = genlabourWorkOrderDto.getWorkOrderNumber();
		String workPriority = genlabourWorkOrderDto.getWorkOrderNumber();
		String workOrderCost = genlabourWorkOrderDto.getWorkOrderNumber();
		String contact = genlabourWorkOrderDto.getWorkOrderNumber();
		String workSite = genlabourWorkOrderDto.getWorkOrderNumber();
		Date expectedStartDate = genlabourWorkOrderDto.getExpectedStartDate();
		Date expectedEndDate = genlabourWorkOrderDto.getExpectedEndDate();

		List<GenerateLabourWork> genLabourList = genlabourWorkOrderDto.getGenLabour().stream()
				.map(generateLabourWork -> setLabourDataForSave(indentNo, indentDepartement, genWorkOrderDepartment,
						workOrderNumber, workOrderDate, division, subDivision, workOrderScope, workOrderIssuBy,
						workPriority, workOrderCost, contact, workSite, expectedStartDate, expectedEndDate,
						generateLabourWork))
				.collect(Collectors.toList());
		System.out.println(genLabourList);
		List<GenerateLabourWork> savedLabourBundles = generateWorkOrdeService.genLabourBundle(genLabourList);
		if (savedLabourBundles != null && savedLabourBundles.size() > 0) {
			return new ResponseEntity<>("Saved", HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// injecting data to database while creating bundle
	private GenerateLabourWork setLabourDataForSave(String indentN, String indentDepartement,
			String genWorkOrderDepartment, String workOrderNumber, Date workOrderDate, String division,
			String subDivision, String workOrderScope, String workOrderIssuBy, String workPriority,
			String workOrderCost, String contact, String workSite, Date expectedStartDate, Date expectedEndDate,
			GenerateLabourWork generateLabourWork) {
		generateLabourWork.setIndentNo(indentN);
		generateLabourWork.setIndentDepartement(indentDepartement);
		generateLabourWork.setGenWorkOrderDepartment(genWorkOrderDepartment);
		generateLabourWork.setWorkOrderNumber(workOrderNumber);
		generateLabourWork.setWorkOrderDate(workOrderDate);
		generateLabourWork.setDivision(division);
		generateLabourWork.setSubDivision(subDivision);
		generateLabourWork.setWorkOrderScope(workOrderScope);
		generateLabourWork.setWorkOrderIssuBy(workOrderIssuBy);
		generateLabourWork.setWorkPriority(workPriority);
		generateLabourWork.setWorkOrderCost(workOrderCost);
		generateLabourWork.setContact(contact);
		generateLabourWork.setWorkSite(workSite);
		generateLabourWork.setExpectedStartDate(expectedStartDate);
		generateLabourWork.setExpectedEndDate(expectedEndDate);
		return generateLabourWork;
	}

	// Save Generate Vehicle

	@PostMapping(value = "/createVehicleBundle", consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public ResponseEntity<String> addGenVehicleData(@RequestBody GenerateVehicleWorkDto genvehicleWorkOrderDto) {

		String indentNo = genvehicleWorkOrderDto.getIndentNo();
		String indentDepartement = genvehicleWorkOrderDto.getIndentDepartement();
		String genWorkOrderDepartment = genvehicleWorkOrderDto.getGenWorkOrderDepartment();
		String workOrderNumber = genvehicleWorkOrderDto.getWorkOrderNumber();
		Date workOrderDate = genvehicleWorkOrderDto.getWorkOrderDate();
		String division = genvehicleWorkOrderDto.getWorkOrderNumber();
		String subDivision = genvehicleWorkOrderDto.getWorkOrderNumber();
		String workOrderScope = genvehicleWorkOrderDto.getWorkOrderNumber();
		String workOrderIssuBy = genvehicleWorkOrderDto.getWorkOrderNumber();
		String workPriority = genvehicleWorkOrderDto.getWorkOrderNumber();
		String workOrderCost = genvehicleWorkOrderDto.getWorkOrderNumber();
		String contact = genvehicleWorkOrderDto.getWorkOrderNumber();
		String workSite = genvehicleWorkOrderDto.getWorkOrderNumber();
		Date expectedStartDate = genvehicleWorkOrderDto.getExpectedStartDate();
		Date expectedEndDate = genvehicleWorkOrderDto.getExpectedEndDate();

		List<GenerateVehicleWork> genVehicleList = genvehicleWorkOrderDto.getGenVehicle().stream()
				.map(generateVehicleWork -> setVehicleDataForSave(indentNo, indentDepartement, genWorkOrderDepartment,
						workOrderNumber, workOrderDate, division, subDivision, workOrderScope, workOrderIssuBy,
						workPriority, workOrderCost, contact, workSite, expectedStartDate, expectedEndDate,
						generateVehicleWork))
				.collect(Collectors.toList());

		System.out.println(genVehicleList);

		List<GenerateVehicleWork> savedVehicleBundles = generateWorkOrdeService.generateVehicleBundle(genVehicleList);
		if (savedVehicleBundles != null && savedVehicleBundles.size() > 0) {
			return new ResponseEntity<>("Saved", HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// injecting data to database while creating bundle
	private GenerateVehicleWork setVehicleDataForSave(String indentN, String indentDepartement,
			String genWorkOrderDepartment, String workOrderNumber, Date workOrderDate, String division,
			String subDivision, String workOrderScope, String workOrderIssuBy, String workPriority,
			String workOrderCost, String contact, String workSite, Date expectedStartDate, Date expectedEndDate,
			GenerateVehicleWork generateVehicleWork) {
		generateVehicleWork.setIndentNo(indentN);
		generateVehicleWork.setIndentDepartement(indentDepartement);
		generateVehicleWork.setGenWorkOrderDepartment(genWorkOrderDepartment);
		generateVehicleWork.setWorkOrderNumber(workOrderNumber);
		generateVehicleWork.setWorkOrderDate(workOrderDate);
		generateVehicleWork.setDivision(division);
		generateVehicleWork.setSubDivision(subDivision);
		generateVehicleWork.setWorkOrderScope(workOrderScope);
		generateVehicleWork.setWorkOrderIssuBy(workOrderIssuBy);
		generateVehicleWork.setWorkPriority(workPriority);
		generateVehicleWork.setWorkOrderCost(workOrderCost);
		generateVehicleWork.setContact(contact);
		generateVehicleWork.setWorkSite(workSite);
		generateVehicleWork.setExpectedStartDate(expectedStartDate);
		generateVehicleWork.setExpectedEndDate(expectedEndDate);
		return generateVehicleWork;
	}

	// To Get single generate Id Data
	@GetMapping("/viewwork/{generateWorkId}")
	public String getGenerateWorkOrderById(@PathVariable("generateWorkId") Long generateWorkId, Model model) {
		GenerateWorkOrderDto generateWorkOrderDto = this.generateWorkOrdeService.getGenerateWorkOrderById(generateWorkId);
		model.addAttribute("genWorkOrder", generateWorkOrderDto);
		return "/pages/work_orders/viwe_work_order";
	}

	@GetMapping("/getWorkOrder/{indentNo}")
	public ResponseEntity<GenerateWorkOrderDto> getWorkOrderData(@PathVariable String indentNo) {
		GenerateWorkOrderDto generateWorkOrderDto = this.generateWorkOrdeService
				.findGeneWorkOrderIndentNoById(indentNo);
		return new ResponseEntity<GenerateWorkOrderDto>(generateWorkOrderDto, HttpStatus.OK);
	}

	/***************** genrateWorkOrder End********** */

	/***************** HoldWorkOrder Start********** */
	@GetMapping("/hold")
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

	@GetMapping("/budget-estimation")
	public String workEstimationBudget() {
		return "/pages/work_orders/work_estimation_budget";
	}

	@PostMapping("/saveworkestmation")
	public String saveWorkEstmation(WorkEstmationDto workEstmation, HttpSession session) {
		this.workEstmationService.saveWorkEstmation(workEstmation);
		session.setAttribute("message", new Message("Data Saved Successfully....!!", "success"));
		return "redirect:/workorder/budget-estimation";
	}

}
