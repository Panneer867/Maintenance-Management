package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.mm.dto.CategoryDto;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.DepartmentIdMasterDto;
import com.ingroinfo.mm.dto.PumpMaintenanceDto;
import com.ingroinfo.mm.dto.PumpMasterDto;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.CategoryService;
import com.ingroinfo.mm.service.DepartmentIdMasterService;
import com.ingroinfo.mm.service.DivisionSubdivisionService;
import com.ingroinfo.mm.service.PumpMaintenanceService;
import com.ingroinfo.mm.service.TaskUpdateService;

@Controller
@RequestMapping("/pump")
public class PumpController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@Autowired
	private DepartmentIdMasterService deptIdMasterService;
	@Autowired
	private PumpMaintenanceService pumpMaintenService;
	@Autowired
	private TaskUpdateService taskUpdateService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private DivisionSubdivisionService divSubDivService;
	@Autowired
	private CategoryService categoryService;

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

	// Handler For Saving Pump Maintenance Data
	@PostMapping("/savepump-maintenance")
	public String savePumpMaintenance(PumpMaintenanceDto pumpMaintenDto, HttpSession session) {
		pumpMaintenDto.setPumpMaintenSts("INDENT");
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
		String userId = user.getUbarmsUserId()+"";
		String complSts = "NeedMaterial";
		
		try {
			DepartmentIdMasterDto departmentIdMasterDto = this.deptIdMasterService
					.getByMasterIdNameAndDeptName(masterIdName, deptName);
			model.addAttribute("deptMaster", departmentIdMasterDto);
		} catch (Exception e) {	
			session.setAttribute("message", new Message("Indent Id Is Not Pressent Please Add Id First !!","danger"));
			 System.out.println("Exception :: " + e.getMessage ()); 
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
			session.setAttribute("message",
					new Message(" No Complain Assigned !! First Add Indent No !!", "danger"));
			System.out.println("Something went Wromg !!" + e.getMessage());
		}
		try {
			List<String> divSubDivList = this.divSubDivService.getDistinctDivisions();
			model.addAttribute("divSubDivList", divSubDivList);
		} catch (Exception e) {
			System.out.println("Something Went Wron !!"+e.getMessage());
		}
		try {
			List<CategoryDto> categoryDtos = this.categoryService.findAllCategory();
			model.addAttribute("categoryList", categoryDtos);
		} catch (Exception e) {
			System.out.println("Something Went Wron !!"+e.getMessage());
		}
		model.addAttribute("title", "Pump | Indent | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_intent";
	}

	// Handler For Get Pump Maintenance Data By Pump Maintenance Id
	@GetMapping("/getPumpdata/{pumpMaintenId}")
	public String getPumpMaintenDataByPumpMaintenId(@PathVariable Long pumpMaintenId, Model model) {
		List<PumpMaintenanceDto> listOfPumpMainens = this.pumpMaintenService.getAllMaintenance();
		model.addAttribute("listOfPumpMainens", listOfPumpMainens);
		PumpMaintenanceDto pumpMaintenanceDto = this.pumpMaintenService.getPumpMaintenByPumpMaintenId(pumpMaintenId);
		model.addAttribute("pumpMaintenData", pumpMaintenanceDto);
		model.addAttribute("title", "Pump | Maintenance Indent | Manintenance Management");
		return "/pages/pump_house/pump_maintenance_intent";
	}

	// Handler For Getting Complaint Details For Pump Indent page
	@GetMapping("/maintenance/complDtls/{complId}")
	public String getComplainDtlsForIndent(@PathVariable Long complId,Model model,Principal principal) {
		User user = adminService.getUserByUsername(principal.getName());
		String masterIdName = "Indent Id";
		String deptName = "Pump Dept";
		String userId = user.getUbarmsUserId()+"";
		String complSts = "NeedMaterial";
		
		try {
			DepartmentIdMasterDto departmentIdMasterDto = this.deptIdMasterService
					.getByMasterIdNameAndDeptName(masterIdName, deptName);
			model.addAttribute("deptMaster", departmentIdMasterDto);
		} catch (Exception e) {			
			 System.out.println("Exception :: " + e.getMessage ()); 
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
		} catch (Exception e) {
              System.out.println("Something Wrong !! "+e.getMessage());
		}
		try {
			List<String> divSubDivList = this.divSubDivService.getDistinctDivisions();
			model.addAttribute("divSubDivList", divSubDivList);
		} catch (Exception e) {
			System.out.println("Something Went Wron !!"+e.getMessage());
		}
		return "/pages/pump_house/pump_maintenance_intent";
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
