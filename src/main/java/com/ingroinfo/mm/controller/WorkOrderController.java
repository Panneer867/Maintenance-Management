package com.ingroinfo.mm.controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.dto.CancelWorkOrderItemsDto;
import com.ingroinfo.mm.dto.CancelWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.CancelWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.HoldWorkOrderItemsDto;
import com.ingroinfo.mm.dto.HoldWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.HoldWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.VerifyIndentStatusDto;
import com.ingroinfo.mm.dto.WapWorkOrderItemsDto;
import com.ingroinfo.mm.dto.WapWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.WapWorkOrderVehiclesDto;
import com.ingroinfo.mm.entity.IndentApprovedItems;
import com.ingroinfo.mm.entity.IndentApprovedLabours;
import com.ingroinfo.mm.entity.IndentApprovedVehicles;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;
import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.TaskUpdateService;
import com.ingroinfo.mm.service.WorkOrderService;

@Controller
@RequestMapping("/workorder")
public class WorkOrderController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private TaskUpdateService taskUpdateService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("dashboard")
	public String dashboard() {
		return "/pages/work_orders/dashboard";
	}

	private List<VerifyIndentStatusDto> executeQuery(String sql) {
		List<VerifyIndentStatusDto> verifyIndentDto = null;
		try {
			verifyIndentDto = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(VerifyIndentStatusDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return verifyIndentDto;
	}

	@GetMapping("/generate")
	@PreAuthorize("hasAuthority('GENERATE_WORKORDER')")
	public String genrateWorkOrder(Model model) {
		List<VerifyIndentStatusDto> verifyIndentStatus = executeQuery("SELECT * FROM VERIFY_INDENT_STATUS");
		model.addAttribute("VerifiedIndentStsList", verifyIndentStatus);
		model.addAttribute("title", "WorkOrder | Generate | Mainteance Management");
		return "/pages/work_orders/generate_work_order";
	}

	// Get Data
	@GetMapping("/generate/get/{complNo}/{indentNo}")
	public String getTempWorkOrderDtlsByComplNoAndIndentNo(Model model, @PathVariable String complNo,
			@PathVariable String indentNo) {

		List<VerifyIndentStatusDto> verifyIndentStatus = executeQuery("SELECT * FROM VERIFY_INDENT_STATUS");
		model.addAttribute("VerifiedIndentStsList", verifyIndentStatus);

		List<TempWorkOrderItemRequest> tempWorkOrderItems = this.workOrderService
				.getTempWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderLabourRequest> tempWorkOrderLabours = this.workOrderService
				.getTempWorkOrderLaborsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles = this.workOrderService
				.getTempWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

		String complNumber = null;
		String indentNumber = null;
		Date expStartDate = null;
		String department = null;
		String division = null;
		String subDivision = null;
		String workPriprity = null;
		String workSite = null;
		String contactNo = null;

		// Check for indent number in TempWorkOrderItemRequest list
		for (TempWorkOrderItemRequest tempWorkOrderItem : tempWorkOrderItems) {
			if (tempWorkOrderItem.getIndentNo() != null || tempWorkOrderItem.getComplNo() != null) {
				indentNumber = tempWorkOrderItem.getIndentNo();
				complNumber = tempWorkOrderItem.getComplNo();
				expStartDate = tempWorkOrderItem.getStartDate();
				department = tempWorkOrderItem.getDepartmentName();
				division = tempWorkOrderItem.getDivision();
				subDivision = tempWorkOrderItem.getSubDivision();
				workPriprity = tempWorkOrderItem.getWorkPriority();
				workSite = tempWorkOrderItem.getWorkSite();
				contactNo = tempWorkOrderItem.getContactNo();
				break;
			}
		}
		// Check for indent number in TempWorkOrderLabourRequest list
		if (indentNumber == null || complNumber == null) {
			for (TempWorkOrderLabourRequest tempWorkOrderLabor : tempWorkOrderLabours) {
				if (tempWorkOrderLabor.getIndentNo() != null || tempWorkOrderLabor.getComplNo() != null) {
					indentNumber = tempWorkOrderLabor.getIndentNo();
					complNumber = tempWorkOrderLabor.getComplNo();
					expStartDate = tempWorkOrderLabor.getStartDate();
					department = tempWorkOrderLabor.getDepartmentName();
					division = tempWorkOrderLabor.getDivision();
					subDivision = tempWorkOrderLabor.getSubDivision();
					workPriprity = tempWorkOrderLabor.getWorkPriority();
					workSite = tempWorkOrderLabor.getWorkSite();
					contactNo = tempWorkOrderLabor.getContactNo();
					break;
				}
			}
		}
		// Check for indent number in TempWorkOrderVehicleRequest list
		if (indentNumber == null || complNumber == null) {
			for (TempWorkOrderVehicleRequest tempWorkOrderVehicle : tempWorkOrderVehicles) {
				if (tempWorkOrderVehicle.getIndentNo() != null || tempWorkOrderVehicle.getComplNo() != null) {
					indentNumber = tempWorkOrderVehicle.getIndentNo();
					complNumber = tempWorkOrderVehicle.getComplNo();
					expStartDate = tempWorkOrderVehicle.getStartDate();
					department = tempWorkOrderVehicle.getDepartmentName();
					division = tempWorkOrderVehicle.getDivision();
					subDivision = tempWorkOrderVehicle.getSubDivision();
					workPriprity = tempWorkOrderVehicle.getWorkPriority();
					workSite = tempWorkOrderVehicle.getWorkSite();
					contactNo = tempWorkOrderVehicle.getContactNo();
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

		model.addAttribute("listOfMaterials", tempWorkOrderItems);
		model.addAttribute("listOfLabors", tempWorkOrderLabours);
		model.addAttribute("listOfVehicles", tempWorkOrderVehicles);
		model.addAttribute("title", "WorkOrder | Generate | Mainteance Management");
		return "/pages/work_orders/generate_work_order";
	}

	// Handler For Submitting TempWorkOrder Data For Approval
	@GetMapping("/generate/submit/{complNo}/{indentNo}")
	public String submitTempWorkorderForGenerateWorkOrder(@PathVariable String complNo, @PathVariable String indentNo,
			Principal principal, HttpSession session) {

		List<TempWorkOrderItemRequest> tempWorkOrderItems = this.workOrderService
				.getTempWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderLabourRequest> tempWorkOrderLabours = this.workOrderService
				.getTempWorkOrderLaborsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles = this.workOrderService
				.getTempWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

		List<IndentApprovedItems> indentApprovedItems = this.workOrderService
				.getApprovedIndentItemsByComplNoAndIndentNo(complNo, indentNo);
		List<IndentApprovedLabours> indentApprovedLabours = this.workOrderService
				.getApprovedIndentLaborsByComplNoAndIndentNo(complNo, indentNo);
		List<IndentApprovedVehicles> indentApprovedVehicles = this.workOrderService
				.getApprovedIndentVehiclesByComplNoAndIndentNo(complNo, indentNo);

		String workOrderNo = "101" + complNo + indentNo;
		
		List<WapWorkOrderItemsDto> wapWorkOrderItemsDtos = new ArrayList<>();
		List<WapWorkOrderLaboursDto> wapWorkOrderLaboursDtos = new ArrayList<>();
		List<WapWorkOrderVehiclesDto> wapWorkOrderVehiclesDtos = new ArrayList<>();
		
		
		if (tempWorkOrderItems != null) {			
			for (TempWorkOrderItemRequest tempWorkOrderItem : tempWorkOrderItems) {
			    WapWorkOrderItemsDto wapWorkOrderItem = new WapWorkOrderItemsDto();
			    wapWorkOrderItem.setUserName(principal.getName());			   
			    wapWorkOrderItem.setWorkOrder(workOrderNo);
			    wapWorkOrderItem.setIndentNo(tempWorkOrderItem.getIndentNo());
			    wapWorkOrderItem.setComplNo(tempWorkOrderItem.getComplNo());
			    wapWorkOrderItem.setStockOrderNo(tempWorkOrderItem.getStockOrderNo());
			    wapWorkOrderItem.setDivision(tempWorkOrderItem.getDivision());
			    wapWorkOrderItem.setSubDivision(tempWorkOrderItem.getSubDivision());
			    wapWorkOrderItem.setWorkSite(tempWorkOrderItem.getWorkSite());
			    wapWorkOrderItem.setStartDate(tempWorkOrderItem.getStartDate());
			    wapWorkOrderItem.setEndDate(tempWorkOrderItem.getEndDate());
			    wapWorkOrderItem.setContactNo(tempWorkOrderItem.getContactNo());
			    wapWorkOrderItem.setWorkPriority(tempWorkOrderItem.getWorkPriority());
			    wapWorkOrderItem.setComplDtls(tempWorkOrderItem.getComplDtls());
			    wapWorkOrderItem.setCategoryName(tempWorkOrderItem.getCategoryName());
			    wapWorkOrderItem.setItemName(tempWorkOrderItem.getItemName());
			    wapWorkOrderItem.setItemId(tempWorkOrderItem.getItemId());
			    wapWorkOrderItem.setUnitOfMesure(tempWorkOrderItem.getUnitOfMesure());
			    wapWorkOrderItem.setHsnCode(tempWorkOrderItem.getHsnCode());
			    wapWorkOrderItem.setQuantity(tempWorkOrderItem.getQuantity());
			    wapWorkOrderItem.setStockType(tempWorkOrderItem.getStockType());
			    wapWorkOrderItem.setStockTypeName(tempWorkOrderItem.getStockTypeName());
			    wapWorkOrderItem.setIndentApproved(tempWorkOrderItem.getIndentApproved());
			    wapWorkOrderItem.setApprovedSts(tempWorkOrderItem.getApprovedSts());
			    wapWorkOrderItem.setCreatedDate(tempWorkOrderItem.getCreatedDate());
			    wapWorkOrderItemsDtos.add(wapWorkOrderItem);
			}
			this.workOrderService.saveAllWapWorkOrderItems(wapWorkOrderItemsDtos);
			this.workOrderService.deleteTempWorkOrderItemRequestByComplNo(complNo);
		}
		 

		if (tempWorkOrderLabours != null) {
			
			for (TempWorkOrderLabourRequest tempWorkOrderLabor : tempWorkOrderLabours) {
			    WapWorkOrderLaboursDto wapWorkOrderLabor = new WapWorkOrderLaboursDto();
			    wapWorkOrderLabor.setUserName(principal.getName());			   
			    wapWorkOrderLabor.setWorkOrder(workOrderNo);
			    wapWorkOrderLabor.setIndentNo(tempWorkOrderLabor.getIndentNo());
			    wapWorkOrderLabor.setComplNo(tempWorkOrderLabor.getComplNo());			 
			    wapWorkOrderLabor.setDivision(tempWorkOrderLabor.getDivision());
			    wapWorkOrderLabor.setSubDivision(tempWorkOrderLabor.getSubDivision());
			    wapWorkOrderLabor.setWorkSite(tempWorkOrderLabor.getWorkSite());
			    wapWorkOrderLabor.setStartDate(tempWorkOrderLabor.getStartDate());
			    wapWorkOrderLabor.setEndDate(tempWorkOrderLabor.getEndDate());
			    wapWorkOrderLabor.setContactNo(tempWorkOrderLabor.getContactNo());
			    wapWorkOrderLabor.setWorkPriority(tempWorkOrderLabor.getWorkPriority());
			    wapWorkOrderLabor.setComplDtls(tempWorkOrderLabor.getComplDtls());
			    wapWorkOrderLabor.setEmpCategory(tempWorkOrderLabor.getEmpCategory());
			    wapWorkOrderLabor.setMembers(tempWorkOrderLabor.getMembers());
			    wapWorkOrderLabor.setDaysRequired(tempWorkOrderLabor.getDaysRequired());
			    wapWorkOrderLabor.setTimeRequired(tempWorkOrderLabor.getTimeRequired());
			    wapWorkOrderLabor.setDepartmentName(tempWorkOrderLabor.getDepartmentName());
			    wapWorkOrderLabor.setCreatedDate(tempWorkOrderLabor.getCreatedDate());
			    wapWorkOrderLabor.setIndentApproved(tempWorkOrderLabor.getIndentApproved());
			    wapWorkOrderLabor.setApprovedSts(tempWorkOrderLabor.getApprovedSts());		 
			    wapWorkOrderLaboursDtos.add(wapWorkOrderLabor);
			}
			
			this.workOrderService.saveAllWapWorkOrderLabours(wapWorkOrderLaboursDtos);
			this.workOrderService.deleteTempWorkOrderLaboursRequestByComplNo(complNo);
		}
		if (tempWorkOrderVehicles != null) {
			
			for (TempWorkOrderVehicleRequest tempWorkOrdervehicle : tempWorkOrderVehicles) {
			    WapWorkOrderVehiclesDto wapWorkOrderVehicle = new WapWorkOrderVehiclesDto();
			    wapWorkOrderVehicle.setUserName(principal.getName());			   
			    wapWorkOrderVehicle.setWorkOrder(workOrderNo);
			    wapWorkOrderVehicle.setIndentNo(tempWorkOrdervehicle.getIndentNo());
			    wapWorkOrderVehicle.setComplNo(tempWorkOrdervehicle.getComplNo());			 
			    wapWorkOrderVehicle.setDivision(tempWorkOrdervehicle.getDivision());
			    wapWorkOrderVehicle.setSubDivision(tempWorkOrdervehicle.getSubDivision());
			    wapWorkOrderVehicle.setWorkSite(tempWorkOrdervehicle.getWorkSite());
			    wapWorkOrderVehicle.setStartDate(tempWorkOrdervehicle.getStartDate());
			    wapWorkOrderVehicle.setEndDate(tempWorkOrdervehicle.getEndDate());
			    wapWorkOrderVehicle.setContactNo(tempWorkOrdervehicle.getContactNo());
			    wapWorkOrderVehicle.setWorkPriority(tempWorkOrdervehicle.getWorkPriority());
			    wapWorkOrderVehicle.setComplDtls(tempWorkOrdervehicle.getComplDtls());
			    wapWorkOrderVehicle.setVehicleType(tempWorkOrdervehicle.getVehicleType());
			    wapWorkOrderVehicle.setVehicleNo(tempWorkOrdervehicle.getVehicleNo());
			    wapWorkOrderVehicle.setDriverName(tempWorkOrdervehicle.getDriverName());
			    wapWorkOrderVehicle.setDriverPhone(tempWorkOrdervehicle.getDriverPhone());
			    wapWorkOrderVehicle.setMeterReading(tempWorkOrdervehicle.getMeterReading());
			    wapWorkOrderVehicle.setStratTime(tempWorkOrdervehicle.getStratTime());
			    wapWorkOrderVehicle.setVehicleId(tempWorkOrdervehicle.getVehicleId());
			    wapWorkOrderVehicle.setDepartmentName(tempWorkOrdervehicle.getDepartmentName());
			    wapWorkOrderVehicle.setCreatedDate(tempWorkOrdervehicle.getCreatedDate());
			    wapWorkOrderVehicle.setIndentApproved(tempWorkOrdervehicle.getIndentApproved());
			    wapWorkOrderVehicle.setApprovedSts(tempWorkOrdervehicle.getApprovedSts());		 
			    wapWorkOrderVehiclesDtos.add(wapWorkOrderVehicle);
			}

			this.workOrderService.saveAllWapWorkOrderVehicles(wapWorkOrderVehiclesDtos);
			this.workOrderService.deleteTempWorkOrderVehicleRequestByComplNo(complNo);
		}

		if (indentApprovedItems != null) {
			for (int i = 0; i < indentApprovedItems.size(); i++) {
				IndentApprovedItems indentItems = indentApprovedItems.get(i);
				indentItems.setApprovedSts("W");
			}
			this.workOrderService.saveAllApprovedIndentItems(indentApprovedItems);
		}
		if (indentApprovedLabours != null) {
			for (int i = 0; i < indentApprovedLabours.size(); i++) {
				IndentApprovedLabours indentLabors = indentApprovedLabours.get(i);
				indentLabors.setApprovedSts("W");
			}
			this.workOrderService.saveAllApprovedIndentLabours(indentApprovedLabours);
		}
		if (indentApprovedVehicles != null) {
			for (int i = 0; i < indentApprovedVehicles.size(); i++) {
				IndentApprovedVehicles indentVehicles = indentApprovedVehicles.get(i);
				indentVehicles.setApprovedSts("W");
			}
			this.workOrderService.saveAllApprovedIndentVehicles(indentApprovedVehicles);
		}

		ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
		oldcomplaintDto.setComplStatus("WAITING_WORKORDER_APPROVAL");
		oldcomplaintDto.setWorkOrder(workOrderNo);
		this.taskUpdateService.saveComplaint(oldcomplaintDto);
		session.setAttribute("message", new Message("Generate For WorkOrder Request Sucessfully Sent !!", "success"));
		return "redirect:/workorder/generate";
	}

	// Handler For Submitting TempWorkOrder Data For Hold Work
	@GetMapping("/generate/hold/{complNo}/{indentNo}")
	public String submitTempWorkorderForHoldWorkOrder(@PathVariable String complNo, @PathVariable String indentNo,
			Principal principal, HttpSession session) {
		
		String workOrderNo = "101" + complNo + indentNo;

		List<TempWorkOrderItemRequest> tempWorkOrderItems = this.workOrderService
				.getTempWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderLabourRequest> tempWorkOrderLabours = this.workOrderService
				.getTempWorkOrderLaborsByComplNoAndIndentNo(complNo, indentNo);
		List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles = this.workOrderService
				.getTempWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

		List<IndentApprovedItems> indentApprovedItems = this.workOrderService
				.getApprovedIndentItemsByComplNoAndIndentNo(complNo, indentNo);
		List<IndentApprovedLabours> indentApprovedLabours = this.workOrderService
				.getApprovedIndentLaborsByComplNoAndIndentNo(complNo, indentNo);
		List<IndentApprovedVehicles> indentApprovedVehicles = this.workOrderService
				.getApprovedIndentVehiclesByComplNoAndIndentNo(complNo, indentNo);

		List<HoldWorkOrderItemsDto> holdWorkOrderItemsDtos = new ArrayList<>();
		List<HoldWorkOrderLaboursDto> holdWorkOrderLaboursDtos = new ArrayList<>();
		List<HoldWorkOrderVehiclesDto> holdWorkOrderVehiclesDtos = new ArrayList<>();
		
		if (tempWorkOrderItems != null) {			
			for (TempWorkOrderItemRequest tempWorkOrderItem : tempWorkOrderItems) {
				HoldWorkOrderItemsDto holdWorkOrderItem = new HoldWorkOrderItemsDto();
				holdWorkOrderItem.setUserName(principal.getName());			   
				holdWorkOrderItem.setWorkOrder(workOrderNo);
				holdWorkOrderItem.setIndentNo(tempWorkOrderItem.getIndentNo());
				holdWorkOrderItem.setComplNo(tempWorkOrderItem.getComplNo());
				holdWorkOrderItem.setStockOrderNo(tempWorkOrderItem.getStockOrderNo());
				holdWorkOrderItem.setDivision(tempWorkOrderItem.getDivision());
				holdWorkOrderItem.setSubDivision(tempWorkOrderItem.getSubDivision());
				holdWorkOrderItem.setWorkSite(tempWorkOrderItem.getWorkSite());
				holdWorkOrderItem.setStartDate(tempWorkOrderItem.getStartDate());
				holdWorkOrderItem.setEndDate(tempWorkOrderItem.getEndDate());
				holdWorkOrderItem.setContactNo(tempWorkOrderItem.getContactNo());
				holdWorkOrderItem.setWorkPriority(tempWorkOrderItem.getWorkPriority());
				holdWorkOrderItem.setComplDtls(tempWorkOrderItem.getComplDtls());
				holdWorkOrderItem.setCategoryName(tempWorkOrderItem.getCategoryName());
				holdWorkOrderItem.setItemName(tempWorkOrderItem.getItemName());
				holdWorkOrderItem.setItemId(tempWorkOrderItem.getItemId());
				holdWorkOrderItem.setUnitOfMesure(tempWorkOrderItem.getUnitOfMesure());
				holdWorkOrderItem.setHsnCode(tempWorkOrderItem.getHsnCode());
				holdWorkOrderItem.setQuantity(tempWorkOrderItem.getQuantity());
				holdWorkOrderItem.setStockType(tempWorkOrderItem.getStockType());
				holdWorkOrderItem.setStockTypeName(tempWorkOrderItem.getStockTypeName());
				holdWorkOrderItem.setIndentApproved(tempWorkOrderItem.getIndentApproved());
				holdWorkOrderItem.setApprovedSts(tempWorkOrderItem.getApprovedSts());
				holdWorkOrderItem.setCreatedDate(tempWorkOrderItem.getCreatedDate());
				holdWorkOrderItemsDtos.add(holdWorkOrderItem);
			}
			this.workOrderService.saveAllHoldWorkOrderItems(holdWorkOrderItemsDtos);
			this.workOrderService.deleteTempWorkOrderItemRequestByComplNo(complNo);
		}
		 

		if (tempWorkOrderLabours != null) {
			
			for (TempWorkOrderLabourRequest tempWorkOrderLabor : tempWorkOrderLabours) {
				HoldWorkOrderLaboursDto holdWorkOrderLabor = new HoldWorkOrderLaboursDto();
				holdWorkOrderLabor.setUserName(principal.getName());			   
				holdWorkOrderLabor.setWorkOrder(workOrderNo);
				holdWorkOrderLabor.setIndentNo(tempWorkOrderLabor.getIndentNo());
				holdWorkOrderLabor.setComplNo(tempWorkOrderLabor.getComplNo());			 
				holdWorkOrderLabor.setDivision(tempWorkOrderLabor.getDivision());
				holdWorkOrderLabor.setSubDivision(tempWorkOrderLabor.getSubDivision());
				holdWorkOrderLabor.setWorkSite(tempWorkOrderLabor.getWorkSite());
				holdWorkOrderLabor.setStartDate(tempWorkOrderLabor.getStartDate());
				holdWorkOrderLabor.setEndDate(tempWorkOrderLabor.getEndDate());
				holdWorkOrderLabor.setContactNo(tempWorkOrderLabor.getContactNo());
				holdWorkOrderLabor.setWorkPriority(tempWorkOrderLabor.getWorkPriority());
				holdWorkOrderLabor.setComplDtls(tempWorkOrderLabor.getComplDtls());
				holdWorkOrderLabor.setEmpCategory(tempWorkOrderLabor.getEmpCategory());
				holdWorkOrderLabor.setMembers(tempWorkOrderLabor.getMembers());
				holdWorkOrderLabor.setDaysRequired(tempWorkOrderLabor.getDaysRequired());
				holdWorkOrderLabor.setTimeRequired(tempWorkOrderLabor.getTimeRequired());
				holdWorkOrderLabor.setDepartmentName(tempWorkOrderLabor.getDepartmentName());
				holdWorkOrderLabor.setCreatedDate(tempWorkOrderLabor.getCreatedDate());
				holdWorkOrderLabor.setIndentApproved(tempWorkOrderLabor.getIndentApproved());
				holdWorkOrderLabor.setApprovedSts(tempWorkOrderLabor.getApprovedSts());		 
				holdWorkOrderLaboursDtos.add(holdWorkOrderLabor);
			}
			
			this.workOrderService.saveAllHoldWorkOrderLabours(holdWorkOrderLaboursDtos);
			this.workOrderService.deleteTempWorkOrderLaboursRequestByComplNo(complNo);
		}
		if (tempWorkOrderVehicles != null) {
			
			for (TempWorkOrderVehicleRequest tempWorkOrdervehicle : tempWorkOrderVehicles) {
				HoldWorkOrderVehiclesDto holdWorkOrderVehicle = new HoldWorkOrderVehiclesDto();
				holdWorkOrderVehicle.setUserName(principal.getName());			   
				holdWorkOrderVehicle.setWorkOrder(workOrderNo);
				holdWorkOrderVehicle.setIndentNo(tempWorkOrdervehicle.getIndentNo());
				holdWorkOrderVehicle.setComplNo(tempWorkOrdervehicle.getComplNo());			 
				holdWorkOrderVehicle.setDivision(tempWorkOrdervehicle.getDivision());
				holdWorkOrderVehicle.setSubDivision(tempWorkOrdervehicle.getSubDivision());
				holdWorkOrderVehicle.setWorkSite(tempWorkOrdervehicle.getWorkSite());
				holdWorkOrderVehicle.setStartDate(tempWorkOrdervehicle.getStartDate());
				holdWorkOrderVehicle.setEndDate(tempWorkOrdervehicle.getEndDate());
				holdWorkOrderVehicle.setContactNo(tempWorkOrdervehicle.getContactNo());
				holdWorkOrderVehicle.setWorkPriority(tempWorkOrdervehicle.getWorkPriority());
				holdWorkOrderVehicle.setComplDtls(tempWorkOrdervehicle.getComplDtls());
				holdWorkOrderVehicle.setVehicleType(tempWorkOrdervehicle.getVehicleType());
				holdWorkOrderVehicle.setVehicleNo(tempWorkOrdervehicle.getVehicleNo());
				holdWorkOrderVehicle.setDriverName(tempWorkOrdervehicle.getDriverName());
				holdWorkOrderVehicle.setDriverPhone(tempWorkOrdervehicle.getDriverPhone());
				holdWorkOrderVehicle.setMeterReading(tempWorkOrdervehicle.getMeterReading());
				holdWorkOrderVehicle.setStratTime(tempWorkOrdervehicle.getStratTime());
				holdWorkOrderVehicle.setVehicleId(tempWorkOrdervehicle.getVehicleId());
				holdWorkOrderVehicle.setDepartmentName(tempWorkOrdervehicle.getDepartmentName());
				holdWorkOrderVehicle.setCreatedDate(tempWorkOrdervehicle.getCreatedDate());
				holdWorkOrderVehicle.setIndentApproved(tempWorkOrdervehicle.getIndentApproved());
				holdWorkOrderVehicle.setApprovedSts(tempWorkOrdervehicle.getApprovedSts());		 
				holdWorkOrderVehiclesDtos.add(holdWorkOrderVehicle);
			}

			this.workOrderService.saveAllHoldWorkOrderVehicles(holdWorkOrderVehiclesDtos);
			this.workOrderService.deleteTempWorkOrderVehicleRequestByComplNo(complNo);
		}

		if (indentApprovedItems != null) {
			for (int i = 0; i < indentApprovedItems.size(); i++) {
				IndentApprovedItems indentItems = indentApprovedItems.get(i);
				indentItems.setApprovedSts("H");
			}
			this.workOrderService.saveAllApprovedIndentItems(indentApprovedItems);
		}
		if (indentApprovedLabours != null) {
			for (int i = 0; i < indentApprovedLabours.size(); i++) {
				IndentApprovedLabours indentLabors = indentApprovedLabours.get(i);
				indentLabors.setApprovedSts("H");
			}
			this.workOrderService.saveAllApprovedIndentLabours(indentApprovedLabours);
		}
		if (indentApprovedVehicles != null) {
			for (int i = 0; i < indentApprovedVehicles.size(); i++) {
				IndentApprovedVehicles indentVehicles = indentApprovedVehicles.get(i);
				indentVehicles.setApprovedSts("H");
			}
			this.workOrderService.saveAllApprovedIndentVehicles(indentApprovedVehicles);
		}

		ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
		oldcomplaintDto.setComplStatus("WORK_ORDER_IN_HOLD");
		oldcomplaintDto.setWorkOrder(workOrderNo);
		oldcomplaintDto.setWorkOrderHoldBy(principal.getName());
		oldcomplaintDto.setWorkOrderHoldDate(new java.sql.Date(System.currentTimeMillis()));
		this.taskUpdateService.saveComplaint(oldcomplaintDto);		
		session.setAttribute("message", new Message("WorkOrder Is In Hold !!", "warning"));
		return "redirect:/workorder/generate";
	}
	
	// Handler For Submitting TempWorkOrder Data For Cancel Work
		@GetMapping("/generate/cancel/{complNo}/{indentNo}")
		public String submitTempWorkorderForCancelWorkOrder(@PathVariable String complNo, @PathVariable String indentNo,
				Principal principal, HttpSession session) {
			
			String workOrderNo = "101" + complNo + indentNo;

			List<TempWorkOrderItemRequest> tempWorkOrderItems = this.workOrderService
					.getTempWorkOrderItemsByComplNoAndIndentNo(complNo, indentNo);
			List<TempWorkOrderLabourRequest> tempWorkOrderLabours = this.workOrderService
					.getTempWorkOrderLaborsByComplNoAndIndentNo(complNo, indentNo);
			List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles = this.workOrderService
					.getTempWorkOrderVehiclesByComplNoAndIndentNo(complNo, indentNo);

			List<IndentApprovedItems> indentApprovedItems = this.workOrderService
					.getApprovedIndentItemsByComplNoAndIndentNo(complNo, indentNo);
			List<IndentApprovedLabours> indentApprovedLabours = this.workOrderService
					.getApprovedIndentLaborsByComplNoAndIndentNo(complNo, indentNo);
			List<IndentApprovedVehicles> indentApprovedVehicles = this.workOrderService
					.getApprovedIndentVehiclesByComplNoAndIndentNo(complNo, indentNo);

			List<CancelWorkOrderItemsDto> cancelWorkOrderItemsDtos = new ArrayList<>();
			List<CancelWorkOrderLaboursDto> cancelWorkOrderLaboursDtos = new ArrayList<>();
			List<CancelWorkOrderVehiclesDto> cancelWorkOrderVehiclesDtos = new ArrayList<>();
			
			if (tempWorkOrderItems != null) {			
				for (TempWorkOrderItemRequest tempWorkOrderItem : tempWorkOrderItems) {
					CancelWorkOrderItemsDto cancelWorkOrderItem = new CancelWorkOrderItemsDto();
					cancelWorkOrderItem.setUserName(principal.getName());			   
					cancelWorkOrderItem.setWorkOrder(workOrderNo);
					cancelWorkOrderItem.setIndentNo(tempWorkOrderItem.getIndentNo());
					cancelWorkOrderItem.setComplNo(tempWorkOrderItem.getComplNo());
					cancelWorkOrderItem.setStockOrderNo(tempWorkOrderItem.getStockOrderNo());
					cancelWorkOrderItem.setDivision(tempWorkOrderItem.getDivision());
					cancelWorkOrderItem.setSubDivision(tempWorkOrderItem.getSubDivision());
					cancelWorkOrderItem.setWorkSite(tempWorkOrderItem.getWorkSite());
					cancelWorkOrderItem.setStartDate(tempWorkOrderItem.getStartDate());
					cancelWorkOrderItem.setEndDate(tempWorkOrderItem.getEndDate());
					cancelWorkOrderItem.setContactNo(tempWorkOrderItem.getContactNo());
					cancelWorkOrderItem.setWorkPriority(tempWorkOrderItem.getWorkPriority());
					cancelWorkOrderItem.setComplDtls(tempWorkOrderItem.getComplDtls());
					cancelWorkOrderItem.setCategoryName(tempWorkOrderItem.getCategoryName());
					cancelWorkOrderItem.setItemName(tempWorkOrderItem.getItemName());
					cancelWorkOrderItem.setItemId(tempWorkOrderItem.getItemId());
					cancelWorkOrderItem.setUnitOfMesure(tempWorkOrderItem.getUnitOfMesure());
					cancelWorkOrderItem.setHsnCode(tempWorkOrderItem.getHsnCode());
					cancelWorkOrderItem.setQuantity(tempWorkOrderItem.getQuantity());
					cancelWorkOrderItem.setStockType(tempWorkOrderItem.getStockType());
					cancelWorkOrderItem.setStockTypeName(tempWorkOrderItem.getStockTypeName());
					cancelWorkOrderItem.setIndentApproved(tempWorkOrderItem.getIndentApproved());
					cancelWorkOrderItem.setApprovedSts(tempWorkOrderItem.getApprovedSts());
					cancelWorkOrderItem.setCreatedDate(tempWorkOrderItem.getCreatedDate());
					cancelWorkOrderItemsDtos.add(cancelWorkOrderItem);
				}
				this.workOrderService.saveAllCancelWorkOrderItems(cancelWorkOrderItemsDtos);
				this.workOrderService.deleteTempWorkOrderItemRequestByComplNo(complNo);
			}
			 

			if (tempWorkOrderLabours != null) {
				
				for (TempWorkOrderLabourRequest tempWorkOrderLabor : tempWorkOrderLabours) {
					CancelWorkOrderLaboursDto cancelWorkOrderLabor = new CancelWorkOrderLaboursDto();
					cancelWorkOrderLabor.setUserName(principal.getName());			   
					cancelWorkOrderLabor.setWorkOrder(workOrderNo);
					cancelWorkOrderLabor.setIndentNo(tempWorkOrderLabor.getIndentNo());
					cancelWorkOrderLabor.setComplNo(tempWorkOrderLabor.getComplNo());			 
					cancelWorkOrderLabor.setDivision(tempWorkOrderLabor.getDivision());
					cancelWorkOrderLabor.setSubDivision(tempWorkOrderLabor.getSubDivision());
					cancelWorkOrderLabor.setWorkSite(tempWorkOrderLabor.getWorkSite());
					cancelWorkOrderLabor.setStartDate(tempWorkOrderLabor.getStartDate());
					cancelWorkOrderLabor.setEndDate(tempWorkOrderLabor.getEndDate());
					cancelWorkOrderLabor.setContactNo(tempWorkOrderLabor.getContactNo());
					cancelWorkOrderLabor.setWorkPriority(tempWorkOrderLabor.getWorkPriority());
					cancelWorkOrderLabor.setComplDtls(tempWorkOrderLabor.getComplDtls());
					cancelWorkOrderLabor.setEmpCategory(tempWorkOrderLabor.getEmpCategory());
					cancelWorkOrderLabor.setMembers(tempWorkOrderLabor.getMembers());
					cancelWorkOrderLabor.setDaysRequired(tempWorkOrderLabor.getDaysRequired());
					cancelWorkOrderLabor.setTimeRequired(tempWorkOrderLabor.getTimeRequired());
					cancelWorkOrderLabor.setDepartmentName(tempWorkOrderLabor.getDepartmentName());
					cancelWorkOrderLabor.setCreatedDate(tempWorkOrderLabor.getCreatedDate());
					cancelWorkOrderLabor.setIndentApproved(tempWorkOrderLabor.getIndentApproved());
					cancelWorkOrderLabor.setApprovedSts(tempWorkOrderLabor.getApprovedSts());		 
					cancelWorkOrderLaboursDtos.add(cancelWorkOrderLabor);
				}
				
				this.workOrderService.saveAllCancelWorkOrderLabours(cancelWorkOrderLaboursDtos);
				this.workOrderService.deleteTempWorkOrderLaboursRequestByComplNo(complNo);
			}
			if (tempWorkOrderVehicles != null) {
				
				for (TempWorkOrderVehicleRequest tempWorkOrdervehicle : tempWorkOrderVehicles) {
					CancelWorkOrderVehiclesDto cancelWorkOrderVehicle = new CancelWorkOrderVehiclesDto();
					cancelWorkOrderVehicle.setUserName(principal.getName());			   
					cancelWorkOrderVehicle.setWorkOrder(workOrderNo);
					cancelWorkOrderVehicle.setIndentNo(tempWorkOrdervehicle.getIndentNo());
					cancelWorkOrderVehicle.setComplNo(tempWorkOrdervehicle.getComplNo());			 
					cancelWorkOrderVehicle.setDivision(tempWorkOrdervehicle.getDivision());
					cancelWorkOrderVehicle.setSubDivision(tempWorkOrdervehicle.getSubDivision());
					cancelWorkOrderVehicle.setWorkSite(tempWorkOrdervehicle.getWorkSite());
					cancelWorkOrderVehicle.setStartDate(tempWorkOrdervehicle.getStartDate());
					cancelWorkOrderVehicle.setEndDate(tempWorkOrdervehicle.getEndDate());
					cancelWorkOrderVehicle.setContactNo(tempWorkOrdervehicle.getContactNo());
					cancelWorkOrderVehicle.setWorkPriority(tempWorkOrdervehicle.getWorkPriority());
					cancelWorkOrderVehicle.setComplDtls(tempWorkOrdervehicle.getComplDtls());
					cancelWorkOrderVehicle.setVehicleType(tempWorkOrdervehicle.getVehicleType());
					cancelWorkOrderVehicle.setVehicleNo(tempWorkOrdervehicle.getVehicleNo());
					cancelWorkOrderVehicle.setDriverName(tempWorkOrdervehicle.getDriverName());
					cancelWorkOrderVehicle.setDriverPhone(tempWorkOrdervehicle.getDriverPhone());
					cancelWorkOrderVehicle.setMeterReading(tempWorkOrdervehicle.getMeterReading());
					cancelWorkOrderVehicle.setStratTime(tempWorkOrdervehicle.getStratTime());
					cancelWorkOrderVehicle.setVehicleId(tempWorkOrdervehicle.getVehicleId());
					cancelWorkOrderVehicle.setDepartmentName(tempWorkOrdervehicle.getDepartmentName());
					cancelWorkOrderVehicle.setCreatedDate(tempWorkOrdervehicle.getCreatedDate());
					cancelWorkOrderVehicle.setIndentApproved(tempWorkOrdervehicle.getIndentApproved());
					cancelWorkOrderVehicle.setApprovedSts(tempWorkOrdervehicle.getApprovedSts());		 
					cancelWorkOrderVehiclesDtos.add(cancelWorkOrderVehicle);
				}

				this.workOrderService.saveAllCancelWorkOrderVehicles(cancelWorkOrderVehiclesDtos);
				this.workOrderService.deleteTempWorkOrderVehicleRequestByComplNo(complNo);
			}

			if (indentApprovedItems != null) {
				for (int i = 0; i < indentApprovedItems.size(); i++) {
					IndentApprovedItems indentItems = indentApprovedItems.get(i);
					indentItems.setApprovedSts("C");
				}
				this.workOrderService.saveAllApprovedIndentItems(indentApprovedItems);
			}
			if (indentApprovedLabours != null) {
				for (int i = 0; i < indentApprovedLabours.size(); i++) {
					IndentApprovedLabours indentLabors = indentApprovedLabours.get(i);
					indentLabors.setApprovedSts("C");
				}
				this.workOrderService.saveAllApprovedIndentLabours(indentApprovedLabours);
			}
			if (indentApprovedVehicles != null) {
				for (int i = 0; i < indentApprovedVehicles.size(); i++) {
					IndentApprovedVehicles indentVehicles = indentApprovedVehicles.get(i);
					indentVehicles.setApprovedSts("C");
				}
				this.workOrderService.saveAllApprovedIndentVehicles(indentApprovedVehicles);
			}

			ComplaintDto oldcomplaintDto = this.taskUpdateService.getComplainDataByComplainNo(complNo);
			oldcomplaintDto.setComplStatus("WORK_ORDER_CANCELD");
			oldcomplaintDto.setWorkOrder(workOrderNo);
			oldcomplaintDto.setWorkOrderCancelBy(principal.getName());
			oldcomplaintDto.setWorkOrderCancelDate(new java.sql.Date(System.currentTimeMillis()));
			this.taskUpdateService.saveComplaint(oldcomplaintDto);		
			session.setAttribute("message", new Message("WorkOrder Canceled Successfully !!", "danger"));
			return "redirect:/workorder/generate";
		}

	@GetMapping("/approved")
	public String approvedWorkOrderList(Model model) {
		return "/pages/work_orders/approved_work_order_list";
	}

	@GetMapping("/hold")
	@PreAuthorize("hasAuthority('HOLD_WORKORDER')")
	public String holdWorkOrder(Model model) {
		String complStatus = "WORK_ORDER_IN_HOLD";
		List<ComplaintDto> holdcomplDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfHoldCompls", holdcomplDtos);
		return "/pages/work_orders/hold_work_order_list";
	}

	@GetMapping("/cancel")
	@PreAuthorize("hasAuthority('CANCEL_WORKORDER')")
	public String cancelWorkOrder(Model model) {
		String complStatus = "WORK_ORDER_CANCELD";
		List<ComplaintDto> cancelcomplDtos = this.taskUpdateService.getListOfComplaintByStatus(complStatus);
		model.addAttribute("listOfCancelCompls", cancelcomplDtos);
		return "/pages/work_orders/cancel_work_order_list";
	}

}
