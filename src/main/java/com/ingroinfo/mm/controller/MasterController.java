package com.ingroinfo.mm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.mm.dto.CategoryDto;
import com.ingroinfo.mm.dto.DistributionLocationDto;
import com.ingroinfo.mm.dto.IdMasterDto;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.CategoryService;
import com.ingroinfo.mm.service.DistributionLocationService;
import com.ingroinfo.mm.service.IdMasterService;

@Controller
@RequestMapping("/masters")
public class MasterController {
	
	
	@Autowired
	private IdMasterService idMasterService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DistributionLocationService disLocationService;
	
	@GetMapping("/barcode")
	public String openMasterBarcodePage(Model model) {
		return "/pages/masters/master-barcode";
	}
	
	@GetMapping("/master-id")
	public String openMasterIdPage(Model model) {
		model.addAttribute("show", null);		
		model.addAttribute("title", "Master | ID | Manintenance Management");
		return "/pages/masters/master-id";
	}
	
	@GetMapping("/master-category")
	public String openMasterCategoryPage(Model model) {
		model.addAttribute("show", null);		
		model.addAttribute("title", "Master | Category | Manintenance Management");
		return "/pages/masters/master-category";
	}
	
	@GetMapping("/master-company")
	public String openMasterCompanyPage(Model model) {
		return "/pages/masters/master-company";
	}
	
	@GetMapping("/dislocation")
	public String openDistributionLocaPage(Model model) {
		model.addAttribute("show", null);	
		model.addAttribute("title", "Master | Distribution | Manintenance Management");
		return "/pages/masters/distribution-location";
	}
	
	@GetMapping("/schedule")
	public String openDistributionSchedulePage(Model model) {
		return "/pages/masters/distribution-schedule";
	}
	
	@GetMapping("/division-subdivision")
	public String openDivisionSubDivisionPage(Model model) {
		return "/pages/masters/division-subdivision";
	}
	
	@GetMapping("/dmaward")
	public String openDmaWardPage(Model model) {
		return "/pages/masters/dma-ward";
	}
	
	@GetMapping("/master-employee")
	public String openMasterEmployeePage(Model model) {
		return "/pages/masters/master-employee";
	}
	
	@GetMapping("/employee-performance")
	public String openEmployeePerfermancePage(Model model) {
		return "/pages/masters/employee-performance";
	}
	
	@GetMapping("/hsncode")
	public String openHsnCodePage(Model model) {
		return "/pages/masters/hsncode";
	}
	
	@GetMapping("/mainten-frequency")
	public String openMaintenanceFrequencyPage(Model model) {
		return "/pages/masters/maintenance-frequency";
	}
	
	@GetMapping("/mainten-activity")
	public String openMaintenanceActivitesPage(Model model) {
		return "/pages/masters/maintenance-activities";
	}
	
	@GetMapping("/mainten-performance")
	public String openMaintenancePerformancePage(Model model) {
		return "/pages/masters/maintenance-performance";
	}
	
	@GetMapping("/mainten-type")
	public String openMaintenanceTypePage(Model model) {
		return "/pages/masters/maintenance-type";
	}
	
	@GetMapping("/master-item")
	public String openMasterItemPage(Model model) {
		return "/pages/masters/master-item";
	}
	
	@GetMapping("/meter-manufacture")
	public String openMeterManufacturePage(Model model) {
		return "/pages/masters/meter-manufacture";
	}
	
	@GetMapping("/meter-type")
	public String openMeterTypePage(Model model) {
		return "/pages/masters/meter-type";
	}
			
	@GetMapping("/notification-alert")
	public String openNotificationAlertPage() {
		return "/pages/masters/notification-alert-page";
	}	
	
	@GetMapping("/pipe-manufacture")
	public String openPipeManufacturePage(Model model) {
		return "/pages/masters/pipe-manufacture";
	}
	
	@GetMapping("/pressure-type")
	public String openPressureTypePage(Model model) {
		return "/pages/masters/pressure-type";
	}
	
	@GetMapping("/master-pumps")
	public String openMasterPumpsPage(Model model) {
		return "/pages/masters/master-pumps";
	}
	
	@GetMapping("/safty-precaution")
	public String openSaftyPrecautionsPage(Model model) {
		return "/pages/masters/safty-precution";
	}
	
	@GetMapping("/service-area")
	public String openServiceAreaPage(Model model) {
		return "/pages/masters/service-area";
	}
	
	@GetMapping("/service-progtype")
	public String openServiceProgressTypePage(Model model) {
		return "/pages/masters/service-progresstype";
	}
	
	@GetMapping("/service-provider")
	public String openServiceProviderPage(Model model) {
		return "/pages/masters/service-provider";
	}
	
	@GetMapping("/spare-part")
	public String openSparePartAndEquipmentsPage(Model model) {
		return "/pages/masters/sparepart-equipment";
	}
	
	@GetMapping("/store-branch")
	public String openStoreAndBranchNamePage(Model model) {
		return "/pages/masters/store-branch";
	}
	
	@GetMapping("/supplier-details")
	public String openSupplierDetailsPage(Model model) {
		return "/pages/masters/supplier-details";
	}
	
	@GetMapping("/leakage-type")
	public String openLeakageTypePage(Model model) {
		return "/pages/masters/leakage-type";
	}
	
	@GetMapping("/task-status")
	public String openTaskStatusPage(Model model) {
		return "/pages/masters/task-status";
	}
	
	@GetMapping("/master-tax")
	public String openMasterTaxPage(Model model) {
		return "/pages/masters/master-tax";
	}
	
	@GetMapping("/unit-measure")
	public String openUnitsOMeasuresPage(Model model) {
		return "/pages/masters/unit-measure";
	}
	
	@GetMapping("/master-vehicle")
	public String openVehicleMasterDetailsPage(Model model) {
		return "/pages/masters/master-vehicle";
	}
	
	@GetMapping("/work-priority")
	public String openWorkPriorityPage(Model model) {
		return "/pages/masters/work-priority";
	}
	
	@GetMapping("/work-status")
	public String openWorkStatusPage(Model model) {
		return "/pages/masters/work-status";
	}
	
	@GetMapping("/teamcode")
	public String openTeamcodePage(Model model) {
		return "/pages/masters/temcode";
	}
	
	@GetMapping("/water-souce")
	public String openWaterSourcePage(Model model) {
		return "/pages/masters/water-source";
	}
	
	//Handler For Submitting IdMaster Data
	@PostMapping("/saveIdMaster")
	public String saveIdMaster(IdMasterDto idDto,HttpSession session) {
		this.idMasterService.saveIdMaster(idDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/master-id";
	}
	
	//Handler For Showing All IdMaster Data
	@GetMapping("/idmaster-history")
	public String showIdMasterHistory(Model model) {
	  List<IdMasterDto> listOfIdMasters = this.idMasterService.findAllIdMaster();
	  model.addAttribute("listOfIdMasters", listOfIdMasters);
	  model.addAttribute("show", "show");
	  return "/pages/masters/master-id";
	}
	
	
	//Handler For Submitting Category Master Data
	@PostMapping("/savecategorydata")
    public String saveCategoryMaster(CategoryDto categoryDto,HttpSession session) {
    	this.categoryService.saveCategory(categoryDto);
    	session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
    	return "redirect:/masters/master-category";
    }
    
	//Handler For Showing Category Master Data
	@GetMapping("/category-history")
	public String showCategoryMasterHistory(Model model) {
	  List<CategoryDto> listOfCategoryDtos = this.categoryService.findAllCategory();
	  model.addAttribute("listOfCategory", listOfCategoryDtos);
	  model.addAttribute("show", "show");
	  model.addAttribute("title", "Master | Category | Manintenance Management");
	  return "/pages/masters/master-category";
	}
    
	//Handler For Submitting Distribution Location Data
	@PostMapping("/savedislocation")
    public String saveDistributionLocation(DistributionLocationDto disLocationDto,
    		HttpSession session) {
    	this.disLocationService.saveDistributionLocation(disLocationDto);
    	session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
    	return "redirect:/masters/dislocation";
    }
	
	

}
