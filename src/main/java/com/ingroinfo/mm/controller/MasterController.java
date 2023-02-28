package com.ingroinfo.mm.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ingroinfo.mm.dto.BrandMasterDto;
import com.ingroinfo.mm.dto.CategoryDto;
import com.ingroinfo.mm.dto.DepartmentDto;
import com.ingroinfo.mm.dto.DepartmentIdMasterDto;
import com.ingroinfo.mm.dto.DesignationDto;
import com.ingroinfo.mm.dto.DistributionLocationDto;
import com.ingroinfo.mm.dto.DistributionScheduleDto;
import com.ingroinfo.mm.dto.DivisionSubdivisionDto;
import com.ingroinfo.mm.dto.DmaWardDto;
import com.ingroinfo.mm.dto.EmployeePerformanceDto;
import com.ingroinfo.mm.dto.HsnCodeDto;
import com.ingroinfo.mm.dto.IdMasterDto;
import com.ingroinfo.mm.dto.ItemMasterDto;
import com.ingroinfo.mm.dto.LeakageTypeDto;
import com.ingroinfo.mm.dto.MaintanceFrequencyDto;
import com.ingroinfo.mm.dto.MaintenanceActivitiesDto;
import com.ingroinfo.mm.dto.MaintenancePerformanceDto;
import com.ingroinfo.mm.dto.MaintenanceTypeDto;
import com.ingroinfo.mm.dto.MeterManufactureDto;
import com.ingroinfo.mm.dto.MeterTypeDto;
import com.ingroinfo.mm.dto.PipeManufactureDto;
import com.ingroinfo.mm.dto.PressureTypeDto;
import com.ingroinfo.mm.dto.PumpMasterDto;
import com.ingroinfo.mm.dto.SaftyPrecautionsDto;
import com.ingroinfo.mm.dto.ServiceAreaDto;
import com.ingroinfo.mm.dto.ServiceProgressDto;
import com.ingroinfo.mm.dto.ServiceProviderDto;
import com.ingroinfo.mm.dto.SpareEquipmentDto;
import com.ingroinfo.mm.dto.StoreBranchDto;
import com.ingroinfo.mm.dto.SupplierDtlsDto;
import com.ingroinfo.mm.dto.TaskStatusDto;
import com.ingroinfo.mm.dto.TaxMasterDto;
import com.ingroinfo.mm.dto.TeamCodeDto;
import com.ingroinfo.mm.dto.UnitMeasureDto;
import com.ingroinfo.mm.dto.VehicleDtlsDto;
import com.ingroinfo.mm.dto.WaterSourceDto;
import com.ingroinfo.mm.dto.WorkPriorityDto;
import com.ingroinfo.mm.dto.WorkStatusDto;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.BrandMasterService;
import com.ingroinfo.mm.service.CategoryService;
import com.ingroinfo.mm.service.DepartmentIdMasterService;
import com.ingroinfo.mm.service.DepartmentService;
import com.ingroinfo.mm.service.DesignationService;
import com.ingroinfo.mm.service.DistributionLocationService;
import com.ingroinfo.mm.service.DistributionScheduleService;
import com.ingroinfo.mm.service.DivisionSubdivisionService;
import com.ingroinfo.mm.service.DmaWardService;
import com.ingroinfo.mm.service.EmployeePerformService;
import com.ingroinfo.mm.service.HsnCodeService;
import com.ingroinfo.mm.service.IdMasterService;
import com.ingroinfo.mm.service.ItemMasterService;
import com.ingroinfo.mm.service.LeakageTypeService;
import com.ingroinfo.mm.service.MaintanceFrequencyService;
import com.ingroinfo.mm.service.MaintenanceActivitiesService;
import com.ingroinfo.mm.service.MaintenancePerformService;
import com.ingroinfo.mm.service.MaintenanceTypeService;
import com.ingroinfo.mm.service.MeterManufactureService;
import com.ingroinfo.mm.service.MeterTypeService;
import com.ingroinfo.mm.service.PipeManufactureService;
import com.ingroinfo.mm.service.PressureTypeService;
import com.ingroinfo.mm.service.PumpMasterService;
import com.ingroinfo.mm.service.SaftyPrecautionService;
import com.ingroinfo.mm.service.ServiceAreaService;
import com.ingroinfo.mm.service.ServiceProgressService;
import com.ingroinfo.mm.service.ServiceProviderService;
import com.ingroinfo.mm.service.SpareEquipmentService;
import com.ingroinfo.mm.service.StoreBranchService;
import com.ingroinfo.mm.service.SupplierDtlsService;
import com.ingroinfo.mm.service.TaskStatusService;
import com.ingroinfo.mm.service.TaxMasterService;
import com.ingroinfo.mm.service.TeamCodeService;
import com.ingroinfo.mm.service.UnitMeasureService;
import com.ingroinfo.mm.service.VehicleDtlsService;
import com.ingroinfo.mm.service.WaterSourceService;
import com.ingroinfo.mm.service.WorkPriorityService;
import com.ingroinfo.mm.service.WorkStatusService;

@Controller
@RequestMapping("/masters")
public class MasterController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}
	
	@Autowired
	private IdMasterService idMasterService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DistributionLocationService disLocationService;
	@Autowired
	private DistributionScheduleService disScheduleService;
	@Autowired
	private DivisionSubdivisionService divSubDivService;
	@Autowired
	private DmaWardService dmaWardService;
	@Autowired
	private EmployeePerformService empPerformService;
	@Autowired
	private HsnCodeService hsnCodeService;
	@Autowired
	private MaintanceFrequencyService maintanFreqService;
	@Autowired
	private MaintenanceActivitiesService maintenActiveService;
	@Autowired
	private MaintenancePerformService maintenPerformSercice;
	@Autowired
	private MaintenanceTypeService maintenTypeService;
	@Autowired
	private ItemMasterService itemMasterService;
	@Autowired
	private MeterTypeService meterTypeService;
	@Autowired
	private MeterManufactureService meterManufactService;
	@Autowired
	private PipeManufactureService pipeManufactService;
	@Autowired
	private PressureTypeService pressureTypeService;
	@Autowired
	private PumpMasterService pumpService;
	@Autowired
	private SaftyPrecautionService precautionService;
	@Autowired
	private ServiceAreaService serviceAreaService;
	@Autowired
	private ServiceProgressService serviceProgressService;
	@Autowired
	private ServiceProviderService serviceProviderService;
	@Autowired
	private SpareEquipmentService spareEquipmentService;
	@Autowired
	private StoreBranchService storeBranchService;
	@Autowired
	private LeakageTypeService leakageTypeService;
	@Autowired
	private TaskStatusService taskStatusService;
	@Autowired
	private TaxMasterService taxMasterService;
	@Autowired
	private UnitMeasureService unitMeasureService;
	@Autowired
	private VehicleDtlsService vehicleDtlsService;
	@Autowired
	private WorkPriorityService workPriorityService;
	@Autowired
	private WorkStatusService workStatusService;
	@Autowired
	private TeamCodeService teamCodeService;
	@Autowired
	private WaterSourceService waterSourceService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DepartmentIdMasterService deptIdMasterService;
	@Autowired
	private DesignationService designationService;
	@Autowired
	private SupplierDtlsService supplierDtlsService;
	@Autowired
	private BrandMasterService brandMasterService;
	@Autowired
	private AdminService adminService;

	@GetMapping("/barcode")
	@PreAuthorize("hasAuthority('MASTERS')")
	public String openMasterBarcodePage(Model model) {
		model.addAttribute("title", "Master | Barcode | Manintenance Management");
		return "/pages/masters/master-barcode";
	}

	@GetMapping("/department")
	public String openMasterDepartmentPage(Model model,HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Department | Manintenance Management");
		
		try {
			String maxdepartmentId = this.departmentService.getMaxDepartmentId();		     
			if (maxdepartmentId != null) {
				int newStartId = Integer.parseInt(maxdepartmentId) + 1;
				model.addAttribute("maxdeptId", newStartId+"");
			} else {
				String idName = "Department Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("maxdeptId", stratNo);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Department Id Is Not Pressent Please Add Id First !!","danger"));
			 System.out.println("Exception :: " + e.getMessage ()); 
		}
		try {
			List<DepartmentDto> departmentDtos= this.departmentService.getDepartmentsFromUbarms();
		     if (departmentDtos !=null) {
				model.addAttribute("departmentList", departmentDtos);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Ubarms Server Is Not Running !!","danger"));
			 System.out.println("Exception :: " + e.getMessage ()); 
		}
		return "/pages/masters/master-department";
	}

	@GetMapping("/idmaster")
	public String openMasterIdPage(Model model) {
		model.addAttribute("idmaster", new IdMasterDto());
		model.addAttribute("title", "Master | ID | Manintenance Management");
		return "/pages/masters/master-id";
	}

	@GetMapping("/departmentId")
	public String openMasterDepartmentIdPage(Model model) {
		List<DepartmentDto> listOfDepartment = this.departmentService.findAllDepartment();
		model.addAttribute("allDepartment", listOfDepartment);
		model.addAttribute("title", "Master | Department Id | Manintenance Management");
		return "/pages/masters/master-departmentId";
	}

	@GetMapping("/category")
	public String openMasterCategoryPage(Model model,HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Category | Manintenance Management");
		
		try {
			String maxCategoryId = this.categoryService.getMaxCategoryId();
			if (maxCategoryId != null) {
				int newStartId = Integer.parseInt(maxCategoryId) + 1;
				model.addAttribute("maxcategoryid", newStartId+"");
			} else {
				String idName = "Categaroy Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("maxcategoryid", stratNo);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Category Id Is Not Pressent Please Add Id First !!","danger"));
			 System.out.println("Exception :: " + e.getMessage ()); 
		}
		
		return "/pages/masters/master-category";
	}

	@GetMapping("/companymaster")
	public String openMasterCompanyPage(Model model) {
		model.addAttribute("title", "Master | Company | Manintenance Management");
		return "/pages/masters/master-company";
	}
	
	@GetMapping("/brand-master")
	public String openMasterBrandPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Brand | Manintenance Management");
		return "/pages/masters/master-brand";
	}

	@GetMapping("/dislocation")
	public String openDistributionLocaPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Distribution Location | Manintenance Management");
		return "/pages/masters/distribution-location";
	}

	@GetMapping("/schedule")
	public String openDistributionSchedulePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Distribution Schedule | Manintenance Management");
		return "/pages/masters/distribution-schedule";
	}

	@GetMapping("/divsubdivision")
	public String openDivisionSubDivisionPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | DivSubDiv | Manintenance Management");
		return "/pages/masters/division-subdivision";
	}

	@GetMapping("/dmaward")
	public String openDmaWardPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Dma-Ward | Manintenance Management");
		return "/pages/masters/dma-ward";
	}

	@GetMapping("/empperformance")
	public String openEmployeePerfermancePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Employee | Manintenance Management");
		return "/pages/masters/employee-performance";
	}

	@GetMapping("/hsncode")
	public String openHsnCodePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | HSN Code | Manintenance Management");
		List<CategoryDto> listOfCategoryDtos = this.categoryService.findAllCategory();
		model.addAttribute("listOfCategory", listOfCategoryDtos);
		return "/pages/masters/hsncode";
	}

	@GetMapping("/maintenfrequency")
	public String openMaintenanceFrequencyPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | MaintenFrequency | Manintenance Management");
		return "/pages/masters/maintenance-frequency";
	}

	@GetMapping("/maintenactivity")
	public String openMaintenanceActivitesPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | MaintenActivities | Manintenance Management");
		return "/pages/masters/maintenance-activities";
	}

	@GetMapping("/maintenperformance")
	public String openMaintenancePerformancePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | MaintenPerformance | Manintenance Management");
		return "/pages/masters/maintenance-performance";
	}

	@GetMapping("/maintentype")
	public String openMaintenanceTypePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | MaintenType | Manintenance Management");
		return "/pages/masters/maintenance-type";
	}

	@GetMapping("/masteritem")
	public String openMasterItemPage(Model model,HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Item | Manintenance Management");
		
		try {
			String maxItemId = this.itemMasterService.getMaxItemId();
			if (maxItemId != null) {
				int nextItemId = Integer.parseInt(maxItemId) + 1;
				model.addAttribute("masterItemId", nextItemId+"");
			} else {
				String idName = "Item Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("masterItemId", stratNo);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Master Item Id Is Not Present Add Id First !!","danger"));
			System.out.println("Exception :: " + e.getMessage ()); 
		}
		
		return "/pages/masters/master-item";
	}

	@GetMapping("/metermanufacture")
	public String openMeterManufacturePage(Model model,HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Meter Manufacture | Manintenance Management");
		try {
			String maxMeterId = this.meterManufactService.getMaxMeterId();
			if (maxMeterId != null) {
				int nextMeterId = Integer.parseInt(maxMeterId)+1;
				model.addAttribute("meterIdNo", nextMeterId+"");
			}else {
				String idName = "Meter Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("meterIdNo", stratNo);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Meter Id Is Not Pressent Please Add Id First !!","danger"));
			System.out.println("Exception ::"+e.getMessage());
		}		
		return "/pages/masters/meter-manufacture";
	}

	@GetMapping("/metertype")
	public String openMeterTypePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Meter Type | Manintenance Management");
		return "/pages/masters/meter-type";
	}

	@GetMapping("/notifialert")
	public String openNotificationAlertPage(Model model) {
		model.addAttribute("title", "Master | Notification | Manintenance Management");
		return "/pages/masters/notification-alert-page";
	}

	@GetMapping("/pipemanufacture")
	public String openPipeManufacturePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Pipe | Manintenance Management");
		return "/pages/masters/pipe-manufacture";
	}

	@GetMapping("/pressuretype")
	public String openPressureTypePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Pressure | Manintenance Management");
		return "/pages/masters/pressure-type";
	}

	@GetMapping("/masterpumps")
	public String openMasterPumpsPage(Model model,HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Pumps | Manintenance Management");
		try {
			String maxPumpId = this.pumpService.getMaxPumpId();
			if (maxPumpId != null) {
				int nextPumpId = Integer.parseInt(maxPumpId)+1;
				model.addAttribute("masterpumpid", nextPumpId+"");
			}else {
				String idName = "Pump Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("masterpumpid", stratNo);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Pump Id Is Not Pressent Please Add Id First !!","danger"));
			System.out.println("Exception :: "+e.getMessage());
		}		
		return "/pages/masters/master-pumps";
	}

	@GetMapping("/saftyprecaution")
	public String openSaftyPrecautionsPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Safety Precaution | Manintenance Management");
		return "/pages/masters/safty-precution";
	}

	@GetMapping("/servicearea")
	public String openServiceAreaPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Service Area | Manintenance Management");
		return "/pages/masters/service-area";
	}

	@GetMapping("/serviceprogtype")
	public String openServiceProgressTypePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Service Progress | Manintenance Management");
		return "/pages/masters/service-progresstype";
	}

	@GetMapping("/serviceprovider")
	public String openServiceProviderPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Service Provider | Manintenance Management");
		return "/pages/masters/service-provider";
	}

	@GetMapping("/sparepart")
	public String openSparePartAndEquipmentsPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Equipments | Manintenance Management");
		return "/pages/masters/sparepart-equipment";
	}

	@GetMapping("/storebranch")
	public String openStoreAndBranchNamePage(Model model,HttpSession session) {
		model.addAttribute("show", null);
		try {
			String maxStoreBranchId = this.storeBranchService.getMaxStoreBranchId();
			if (maxStoreBranchId != null) {
				int nextStoreBranchId = Integer.parseInt(maxStoreBranchId)+1;
				model.addAttribute("masterbranchid", nextStoreBranchId+"");
			}else {
				String masterIdName ="Branch Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(masterIdName);
				String startNo = idMasterDto.getStatNumber();
				model.addAttribute("masterbranchid", startNo);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Branch Id Is Not Paressent Please Add Id First","danger"));
			System.out.println("Exception :: "+e.getMessage());
		}		
		model.addAttribute("title", "Master | Store/Branch | Manintenance Management");
		return "/pages/masters/store-branch";
	}

	@GetMapping("/supplierdetails")
	public String openSupplierDetailsPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Supplier | Manintenance Management");
		model.addAttribute("states", adminService.getAllStates());
		return "/pages/masters/supplier-details";
	}

	// Handler For Open Designation Master Page
	@GetMapping("/designation")
	public String openDesignation(Model model) throws IOException {
		model.addAttribute("show", null);
		List<DesignationDto> designationDtos = designationService.getDesignationsFormUbarms();
		try {
			if (!designationDtos.isEmpty()) {
				model.addAttribute("ubmdesigList", designationDtos);
			}
		} catch (Exception e) {
			System.out.println("DesignationList Not Found !!"+e.getMessage());
			model.addAttribute("ubmdesigList", new DesignationDto());
		}
		model.addAttribute("title", "Master | Designation | Manintenance Management");
		return "/pages/masters/master-designation";
	}

	@GetMapping("/leakagetype")
	public String openLeakageTypePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Leakage | Manintenance Management");
		return "/pages/masters/leakage-type";
	}

	@GetMapping("/taskstatus")
	public String openTaskStatusPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Task | Manintenance Management");
		return "/pages/masters/task-status";
	}

	@GetMapping("/mastertax")
	public String openMasterTaxPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Tax | Manintenance Management");
		return "/pages/masters/master-tax";
	}

	@GetMapping("/unitmeasure")
	public String openUnitsOMeasuresPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Unit | Manintenance Management");
		return "/pages/masters/unit-measure";
	}

	@GetMapping("/mastervehicle")
	public String openVehicleMasterDetailsPage(Model model,HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Vehicle | Manintenance Management");
		
		try {
			String maxVehicleId = this.vehicleDtlsService.getMaxVehicleId();
			if (maxVehicleId != null) {
				int newStartId = Integer.parseInt(maxVehicleId) + 1;
				model.addAttribute("vehicleMasterId", newStartId+"");
			} else {
				String idName = "Vehicle Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("vehicleMasterId", stratNo);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Vehicle Id Is Not Present !! Please Add Id First !!","danger"));
			 System.out.println("Exception :: " + e.getMessage ()); 
		}
		
		return "/pages/masters/master-vehicle";
	}

	@GetMapping("/workpriority")
	public String openWorkPriorityPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Work Priority | Manintenance Management");
		return "/pages/masters/work-priority";
	}

	@GetMapping("/workstatus")
	public String openWorkStatusPage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Work Status | Manintenance Management");
		return "/pages/masters/work-status";
	}

	@GetMapping("/teamcode")
	public String openTeamcodePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Teamcode | Manintenance Management");
		return "/pages/masters/temcode";
	}

	@GetMapping("/watersouce")
	public String openWaterSourcePage(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | WaterSource | Manintenance Management");
		return "/pages/masters/water-source";
	}

	// Handler For Save Department Data
	@PostMapping("/saveDepartment")
	public String saveDepartmentMaster(DepartmentDto departmentDto, HttpSession session) {
		if (departmentDto.getDepartmentId() !="" && departmentDto.getDepartmentName() !="") {
			this.departmentService.saveDepartment(departmentDto);
		}else {
			return "redirect:/masters/department";
		}		
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/department";
	}

	// Handler For Display Department Master History
	@GetMapping("/deparmentHistory")
	public String displayDepartmentHistory(Model model) {
		List<DepartmentDto> listOfDepartment = this.departmentService.findAllDepartment();
		model.addAttribute("listOfDepartment", listOfDepartment);
		model.addAttribute("show", "show");
		try {
			String maxdepartmentId = this.departmentService.getMaxDepartmentId();
			if (maxdepartmentId != null) {
				int newStartId = Integer.parseInt(maxdepartmentId) + 1;
				model.addAttribute("maxdeptId", newStartId+"");
			} else {
				String idName = "Department Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("maxdeptId", stratNo);
			}
		} catch (Exception e) {
			System.out.println("Exception :: "+e.getMessage());
		}
		try {
			List<DepartmentDto> departmentDtos= this.departmentService.getDepartmentsFromUbarms();
		     if (departmentDtos !=null) {
				model.addAttribute("departmentList", departmentDtos);
			}
		} catch (Exception e) {			
			 System.out.println("Exception :: " + e.getMessage ()); 
		}
		
		model.addAttribute("title", "Master | Department | Manintenance Management");
		return "/pages/masters/master-department";
	}

	// Handler For Save Department Id Master Data
	@PostMapping("/saveDepartmentId")
	public String saveDepartmentIdMaster(DepartmentIdMasterDto deptIdMasterDto, HttpSession session) {
		this.deptIdMasterService.saveDepartmentIdMaster(deptIdMasterDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/departmentId";
	}

	// Handler For Display Department Id Master History
	@GetMapping("/departmentIdHistory")
	public String displayMasterDepartmentIdHistory(Model model) {

		List<String> list = this.deptIdMasterService.findAllDepartmentIdMaster().stream()
				.map((idName) -> idName.getMasterIdName()).collect(Collectors.toList());

		// list.forEach(System.out::println);

		// For Budget Id
		boolean isExist = list.stream().filter(o -> o.contains("Budget Id")).findFirst().isPresent();
		if (isExist) {
			List<DepartmentIdMasterDto> listOfBudgetId = this.deptIdMasterService.getByMasterIdName("Budget Id");
			model.addAttribute("listOfBudgetId", listOfBudgetId);
		}

		// For Out Material Issued Id
		boolean isExist1 = list.stream().filter(o -> o.contains("Out Matl Issue Id")).findFirst().isPresent();
		if (isExist1) {
			List<DepartmentIdMasterDto> listOfOutmatlIsueId = this.deptIdMasterService
					.getByMasterIdName("Out Matl Issue Id");
			model.addAttribute("listOfOutmatlIsueId", listOfOutmatlIsueId);
		}

		// For Out Spare Material Issue Id
		boolean isExist2 = list.stream().filter(o -> o.contains("Out Spr Matl Issue Id")).findFirst().isPresent();
		if (isExist2) {
			List<DepartmentIdMasterDto> listOfOutSparematlIsueId = this.deptIdMasterService
					.getByMasterIdName("Out Spr Matl Issue Id");
			model.addAttribute("listOfOutSparematlIsueId", listOfOutSparematlIsueId);
		}

		// For Out Tools Material Issue Id
		boolean isExist3 = list.stream().filter(o -> o.contains("Out Tools Matl Issue Id")).findFirst().isPresent();
		if (isExist3) {
			List<DepartmentIdMasterDto> listOfOutToolsmatlIsueId = this.deptIdMasterService
					.getByMasterIdName("Out Tools Matl Issue Id");
			model.addAttribute("listOfOutToolsmatlIsueId", listOfOutToolsmatlIsueId);
		}

		// For Sequence Id
		boolean isExist4 = list.stream().filter(o -> o.contains("Sequence Id")).findFirst().isPresent();
		if (isExist4) {
			List<DepartmentIdMasterDto> listOfSequenceId = this.deptIdMasterService.getByMasterIdName("Sequence Id");
			model.addAttribute("listOfSequenceId", listOfSequenceId);
		}

		// For Stock Rejection/Damage Material Return
		boolean isExist5 = list.stream().filter(o -> o.contains("Stk Rej/Dmg Matl Rtn Id")).findFirst().isPresent();
		if (isExist5) {
			List<DepartmentIdMasterDto> listOfStkRejDmgMatlRtnId = this.deptIdMasterService
					.getByMasterIdName("Stk Rej/Dmg Matl Rtn Id");
			model.addAttribute("listOfStkRejDmgMatlRtnId", listOfStkRejDmgMatlRtnId);
		}

		// For Stock Rejection/Damage Spare Return
		boolean isExist6 = list.stream().filter(o -> o.contains("Stk Rej/Dmg Spr Rtn Id")).findFirst().isPresent();
		if (isExist6) {
			List<DepartmentIdMasterDto> listOfStkRejSpareRtn = this.deptIdMasterService
					.getByMasterIdName("Stk Rej/Dmg Spr Rtn Id");
			model.addAttribute("listOfStkRejSpareRtn", listOfStkRejSpareRtn);
		}

		// For Stock Rejection/Damage Tools Return
		boolean isExist7 = list.stream().filter(o -> o.contains("Stk Rej/Dmg Tools Rtn Id")).findFirst().isPresent();
		if (isExist7) {
			List<DepartmentIdMasterDto> listOfStkRejToolsRtn = this.deptIdMasterService
					.getByMasterIdName("Stk Rej/Dmg Tools Rtn Id");
			model.addAttribute("listOfStkRejToolsRtn", listOfStkRejToolsRtn);
		}

		// For Stock Material Return Id
		boolean isExist8 = list.stream().filter(o -> o.contains("Stock Matl Rtn Id")).findFirst().isPresent();
		if (isExist8) {
			List<DepartmentIdMasterDto> listOfstkMtlRtn = this.deptIdMasterService
					.getByMasterIdName("Stock Matl Rtn Id");
			model.addAttribute("listOfstkMtlRtn", listOfstkMtlRtn);
		}

		// For Stock Spares Return Id
		boolean isExist9 = list.stream().filter(o -> o.contains("Stock Spares Rtn Id")).findFirst().isPresent();
		if (isExist9) {
			List<DepartmentIdMasterDto> listOfstkSprRtn = this.deptIdMasterService
					.getByMasterIdName("Stock Spares Rtn Id");
			model.addAttribute("listOfstkSprRtn", listOfstkSprRtn);
		}

		// For Stock Tools Return Id
		boolean isExist10 = list.stream().filter(o -> o.contains("Stock Tools Rtn Id")).findFirst().isPresent();
		if (isExist10) {
			List<DepartmentIdMasterDto> listOfStkTlsRtn = this.deptIdMasterService
					.getByMasterIdName("Stock Tools Rtn Id");
			model.addAttribute("listOfStkTlsRtn", listOfStkTlsRtn);
		}

		// For Indent Id
		boolean isExist11 = list.stream().filter(o -> o.contains("Indent Id")).findFirst().isPresent();
		if (isExist11) {
			List<DepartmentIdMasterDto> listOfindet = this.deptIdMasterService.getByMasterIdName("Indent Id");
			model.addAttribute("listOfindet", listOfindet);
		}

		// For Work Order Cancel Id
		boolean isExist12 = list.stream().filter(o -> o.contains("Work Order Cnsl Id")).findFirst().isPresent();
		if (isExist12) {
			List<DepartmentIdMasterDto> listOfWorkOrderCencel = this.deptIdMasterService
					.getByMasterIdName("Work Order Cnsl Id");
			model.addAttribute("listOfWorkOrderCencel", listOfWorkOrderCencel);
		}

		// For Work Order Id
		boolean isExist13 = list.stream().filter(o -> o.contains("Work Order Id")).findFirst().isPresent();
		if (isExist13) {
			List<DepartmentIdMasterDto> listOfWorkOrder = this.deptIdMasterService.getByMasterIdName("Work Order Id");
			model.addAttribute("listOfWorkOrder", listOfWorkOrder);
		}

		model.addAttribute("title", "Master | DepartmentId | Manintenance Management");
		return "/pages/masters/master-departmentid-history";
	}

	// Handler For Submitting IdMaster Data
	@PostMapping("/saveIdMaster")
	public String saveIdMaster(IdMasterDto idDto, HttpSession session) {
		this.idMasterService.saveIdMaster(idDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/idmasterhistory";
	}

	// Handler For Showing All IdMaster Data
	@GetMapping("/idmasterhistory")
	public String showIdMasterHistory(Model model) {
		List<IdMasterDto> listOfIdMasters = this.idMasterService.findAllIdMaster();
		model.addAttribute("listOfIdMasters", listOfIdMasters);
		model.addAttribute("idMasterDto", new IdMasterDto());
		model.addAttribute("title", "Master | ID | Manintenance Management");
		return "/pages/masters/master-id-history";
	}

	// Handler For Submitting Category Master Data
	@PostMapping("/savecategorydata")
	public String saveCategoryMaster(CategoryDto categoryDto, HttpSession session) {
		this.categoryService.saveCategory(categoryDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/category";
	}

	// Handler For Showing Category Master Data
	@GetMapping("/categoryhistory")
	public String showCategoryMasterHistory(Model model) {
		List<CategoryDto> listOfCategoryDtos = this.categoryService.findAllCategory();
		model.addAttribute("listOfCategory", listOfCategoryDtos);
		model.addAttribute("show", "show");
		try {
			String maxCategoryId = this.categoryService.getMaxCategoryId();
			if (maxCategoryId != null) {
				int newStartId = Integer.parseInt(maxCategoryId) + 1;
				model.addAttribute("maxcategoryid", newStartId+"");
			} else {
				String idName = "Categaroy Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("maxcategoryid", stratNo);
			}
		} catch (Exception e) {
		 System.out.println("Exception :: "+ e.getMessage());	
		}
		
		model.addAttribute("title", "Master | Category | Manintenance Management");
		return "/pages/masters/master-category";
	}

	// Handler For Submitting Distribution Location Data
	@PostMapping("/savedislocation")
	public String saveDistributionLocation(DistributionLocationDto disLocationDto, HttpSession session) {
		this.disLocationService.saveDistributionLocation(disLocationDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/dislocation";
	}

	// Handler For Showing Distribution Location History
	@GetMapping("/dislocationhistory")
	public String showDistibutionLocationHistory(Model model) {
		List<DistributionLocationDto> listOfDistLocations = this.disLocationService.findAllDistributionLocation();
		model.addAttribute("listOfDistLocations", listOfDistLocations);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Distribution Location | Manintenance Management");
		return "/pages/masters/distribution-location";
	}

	// Handler For Submitting Distribution Schedule Data
	@PostMapping("/savedisschedule")
	public String saveDistributionSchedule(DistributionScheduleDto disScheduleDto, HttpSession session) {
		this.disScheduleService.saveDisSchedule(disScheduleDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/schedule";
	}

	// Handler For showing Distribution Schedule Data
	@GetMapping("/disschedulehistory")
	public String showDistributionScheduleHistory(Model model) {
		List<DistributionScheduleDto> listOfDisScheduleDtos = this.disScheduleService.findAllDisSchedule();
		model.addAttribute("listOfdisschedule", listOfDisScheduleDtos);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Distribution Schedule | Manintenance Management");
		return "/pages/masters/distribution-schedule";
	}

	// Handler For Submitting Division Subdivision Data
	@PostMapping("/savedivsubdiv")
	public String saveDivisionSubdivision(DivisionSubdivisionDto divSubdivdto, HttpSession session) {
		this.divSubDivService.saveDivisionSubdivision(divSubdivdto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/divsubdivision";
	}

	// Handler For showing Division Subdivision History Data
	@GetMapping("/divsubdivhistory")
	public String showDivisonSubdivisionHistory(Model model) {
		List<DivisionSubdivisionDto> listOfDivSubdiv = this.divSubDivService.findAllDivSubdiv();
		model.addAttribute("listOfDivSubdiv", listOfDivSubdiv);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | DivSubDiv | Manintenance Management");
		return "/pages/masters/division-subdivision";
	}

	// Handler For Submitting Dma-Ward Data
	@PostMapping("/savedmaward")
	public String saveDmaWard(DmaWardDto dmaWardDto, HttpSession session) {
		this.dmaWardService.saveDmaWard(dmaWardDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/dmaward";
	}

	// Handler For showing Dma-Ward History Data
	@GetMapping("/dmawardhistory")
	public String showDmaWardHistory(Model model) {
		List<DmaWardDto> listOfDmaWard = this.dmaWardService.getAllDmaWard();
		model.addAttribute("listOfDmaWard", listOfDmaWard);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Dma-Ward | Manintenance Management");
		return "/pages/masters/dma-ward";
	}

	// Handler For Submitting Emplyee Performance Data
	@PostMapping("/saveEmpPerform")
	public String saveEmplyeePerformance(EmployeePerformanceDto empPerformDto, HttpSession session) {
		this.empPerformService.saveEmplyeePerformmance(empPerformDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/empperformance";
	}

	// Handler For showing Employee Performance History Data
	@GetMapping("/empperformhistory")
	public String showEmplyeePerformance(Model model) {
		List<EmployeePerformanceDto> listOfEmpPerformDtos = this.empPerformService.getAllEmpPerformance();
		model.addAttribute("listOfEmpPerform", listOfEmpPerformDtos);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Employee | Manintenance Management");
		return "/pages/masters/employee-performance";
	}

	// Handler For Submitting HSN Code Data
	@PostMapping("/savehsncode")
	public String saveHsnCode(HsnCodeDto hsnCodeDto, HttpSession session) {
		this.hsnCodeService.saveHsnCode(hsnCodeDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/hsncode";
	}

	// Handler For showing Employee Performance History Data
	@GetMapping("/hsncodehistory")
	public String showHsnCodeHistory(Model model) {
		List<HsnCodeDto> listOfHsnCode = this.hsnCodeService.findAllHsnCode();
		List<CategoryDto> listOfCategoryDtos = this.categoryService.findAllCategory();
		model.addAttribute("listOfCategory", listOfCategoryDtos);
		model.addAttribute("listOfHsnCode", listOfHsnCode);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | HSN Code | Manintenance Management");
		return "/pages/masters/hsncode";
	}

	// Handler For Submitting MaintanceFrequency Data
	@PostMapping("/savemaintenfreq")
	public String saveMaintanceFrequency(MaintanceFrequencyDto maintenFreqDto, HttpSession session) {
		this.maintanFreqService.saveMaintanceFrequency(maintenFreqDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/maintenfrequency";
	}

	// Handler For showing MaintanceFrequency History Data
	@GetMapping("/maintanfreqhistory")
	public String showMaintanceFrequencyHistory(Model model) {
		List<MaintanceFrequencyDto> listOfMaintanFreq = this.maintanFreqService.getAllMaintanceFrequency();
		model.addAttribute("listOfMaintenFreq", listOfMaintanFreq);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | MaintenFrequency | Manintenance Management");
		return "/pages/masters/maintenance-frequency";
	}

	// Handler For Submitting MaintanceActivety Data
	@PostMapping("/saveMaintenactive")
	public String saveMaintanceActivety(MaintenanceActivitiesDto maintenActivDto, HttpSession session) {
		this.maintenActiveService.saveMaintenActivity(maintenActivDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/maintenactivity";
	}

	// Handler For showing MaintanceActivety History Data
	@GetMapping("/maintenactivehistory")
	public String showMaintanceActivetyHistory(Model model) {
		List<MaintenanceActivitiesDto> listOfMaintenActive = this.maintenActiveService.findAllMaintnActve();
		model.addAttribute("listOfMaintenActive", listOfMaintenActive);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | MaintenFrequency | Manintenance Management");
		return "/pages/masters/maintenance-activities";
	}

	// Handler For Submitting MaintancePerformance Data
	@PostMapping("/savemaintenperform")
	public String saveMaintancePerformance(MaintenancePerformanceDto maintenPerformDto, HttpSession session) {
		this.maintenPerformSercice.saveMaintenPerform(maintenPerformDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/maintenperformance";
	}

	// Handler For showing MaintancePerformance History Data
	@GetMapping("/maintenperformhistory")
	public String showMaintancePerformanceHistory(Model model) {
		List<MaintenancePerformanceDto> listOfMaintenPerform = this.maintenPerformSercice.getAllMaintenPerform();
		model.addAttribute("listOfMaintenPerform", listOfMaintenPerform);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | MaintenActivities | Manintenance Management");
		return "/pages/masters/maintenance-performance";
	}

	// Handler For Submitting MaintancePerformance Data
	@PostMapping("/savemaintentype")
	public String saveMaintenanceType(MaintenanceTypeDto maintenTypeDto, HttpSession session) {
		this.maintenTypeService.saveMaintenanceType(maintenTypeDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/maintentype";
	}

	// Handler For showing Maintenance Type History
	@GetMapping("/maintentypehistory")
	public String showMaintenanceTypeHistory(Model model) {
		List<MaintenanceTypeDto> listOfMaintenType = this.maintenTypeService.findAllMaintenanceType();
		model.addAttribute("listOfMaintenType", listOfMaintenType);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | MaintenType | Manintenance Management");
		return "/pages/masters/maintenance-type";
	}

	// Handler For Submitting Item Master Data
	@PostMapping("/saveItemdata")
	public String saveItemMaster(ItemMasterDto itemMasterDto, HttpSession session) {
		this.itemMasterService.saveItemmaster(itemMasterDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/masteritem";
	}

	// Handler For showing Item Master History
	@GetMapping("/itemhistory")
	public String showItemMasterHistory(Model model) {
		List<ItemMasterDto> listOfItems = this.itemMasterService.getAllItems();
		model.addAttribute("listOfItems", listOfItems);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Item | Manintenance Management");
		try {
			String maxItemId = this.itemMasterService.getMaxItemId();
			if (maxItemId != null) {
				int nextItemId = Integer.parseInt(maxItemId) + 1;
				model.addAttribute("masterItemId", nextItemId+"");
			} else {
				String idName = "Item Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("masterItemId", stratNo);
			}
		} catch (Exception e) {
			System.out.println("Exception :: "+ e.getMessage());
		}
		
		return "/pages/masters/master-item";
	}

	// Handler For Submitting Meter Type Data
	@PostMapping("/savemetertype")
	public String submitMeterTypeData(MeterTypeDto meterTypeDto, HttpSession session) {
		this.meterTypeService.saveMeterType(meterTypeDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/metertype";
	}

	// Handler For Showing Meter Type History
	@GetMapping("/metertypehistory")
	public String showMeterTypeHistory(Model model) {
		List<MeterTypeDto> listOfMeterTypes = this.meterTypeService.getAllMeterType();
		model.addAttribute("listOfMeterTypes", listOfMeterTypes);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Meter Type | Manintenance Management");
		return "/pages/masters/meter-type";
	}

	// Handler For Submitting Meter manufacture Data
	@PostMapping("/savemetermanufact")
	public String saveMeterManuFacture(MeterManufactureDto meterManufactDto, HttpSession session) {
		String meterTyp = meterManufactDto.getMeterType();
		meterManufactDto.setMeterType(meterTyp.toUpperCase());
		this.meterManufactService.saveMeterManufact(meterManufactDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/metermanufacture";
	}

	// Handler for Display MeterManufacture History
	@GetMapping("/metermanufacthistory")
	public String displayMeterManufactHistory(Model model) {
		List<MeterManufactureDto> listOfManufact = this.meterManufactService.findAllMeterManufact();
		model.addAttribute("listOfManufact", listOfManufact);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Meter Manufacture | Manintenance Management");
		try {
			String maxMeterId = this.meterManufactService.getMaxMeterId();
			if (maxMeterId != null) {
				int nextMeterId = Integer.parseInt(maxMeterId)+1;
				model.addAttribute("meterIdNo", nextMeterId+"");
			}else {
				String idName = "Meter Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("meterIdNo", stratNo);
			}
		} catch (Exception e) {
			System.out.println("Exception :: "+e.getMessage());
		}	
		return "/pages/masters/meter-manufacture";
	}

	// Handler For Saving Pipe Manufacture data
	@PostMapping("/savepipemanufact")
	public String savePaipeManufacture(PipeManufactureDto pipeManufactDto, HttpSession session) {
		this.pipeManufactService.savePipeManufacture(pipeManufactDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/pipemanufacture";
	}

	// Handler for Display PipeManuFacture History
	@GetMapping("/pipemanufacthistory")
	public String showPipeManufactureHistory(Model model) {
		List<PipeManufactureDto> listOfPipeManufact = this.pipeManufactService.findAllPipeManufact();
		model.addAttribute("listOfPipeManufact", listOfPipeManufact);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Pipe | Manintenance Management");
		return "/pages/masters/pipe-manufacture";
	}

	// Handler For Submit PressureType Data
	@PostMapping("/savepressure")
	public String submitPressureType(PressureTypeDto pressureDto, HttpSession session) {
		this.pressureTypeService.savePressureType(pressureDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/pressuretype";
	}

	// Handler For Display Pressure Type History
	@GetMapping("/pressurehistory")
	public String showPressureTypeHistory(Model model) {
		List<PressureTypeDto> listOfPressure = this.pressureTypeService.getAllPressureType();
		model.addAttribute("listOfPressure", listOfPressure);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Pressure | Manintenance Management");
		return "/pages/masters/pressure-type";
	}

	// Handler for Submitting Pump master data
	@PostMapping("/savepumps")
	public String savePumpMasterData(PumpMasterDto pumpMasterDto, HttpSession session) {
		this.pumpService.savePumpMaster(pumpMasterDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/masterpumps";
	}

	// Handler For Display pump Master Data
	@GetMapping("/pumpshistory")
	public String displayPumpHistory(Model model,HttpSession session) {
		List<PumpMasterDto> listOfPumps = this.pumpService.getAllPumpMaster();
		model.addAttribute("listOfPumps", listOfPumps);
		model.addAttribute("show", "show");
		try {
			String maxPumpId = this.pumpService.getMaxPumpId();
			if (maxPumpId != null) {
				int nextPumpId = Integer.parseInt(maxPumpId)+1;
				model.addAttribute("masterpumpid", nextPumpId+"");
			}else {
				String idName = "Pump Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String stratNo = idMasterDto.getStatNumber();
				model.addAttribute("masterpumpid", stratNo);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Pump Id Is Not Pressent Please Add Id First !!","info"));
			System.out.println("Exception :: "+e.getMessage());
		}		
		model.addAttribute("title", "Master | Pumps | Manintenance Management");
		return "/pages/masters/master-pumps";
	}

	// Handler for Submitting Safety Precaution data
	@PostMapping("/savesafetyprecaus")
	public String saveSaftyPrecautionData(SaftyPrecautionsDto precautionsDto, HttpSession session) {
		this.precautionService.saveSaftyPrecus(precautionsDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/saftyprecaution";
	}

	// Handler For Display Safety Precaution Data
	@GetMapping("/safetyhistory")
	public String showSafetyPrecautionHistory(Model model) {
		List<SaftyPrecautionsDto> listOfSafetyPrecus = this.precautionService.findAllSaftyPrecus();
		model.addAttribute("safetyPrecaus", listOfSafetyPrecus);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Safety Precaution | Manintenance Management");
		return "/pages/masters/safty-precution";
	}

	// Handler For save SurviceArea Data
	@PostMapping("/savesericarea")
	public String saveServiceArea(ServiceAreaDto serviceAreaDto, HttpSession session) {
		this.serviceAreaService.saveSaerviceArea(serviceAreaDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/servicearea";
	}

	// Handler for Display ServiceArea History
	@GetMapping("/serviceareahistory")
	public String displayServiceAreaHiatory(Model model) {
		List<ServiceAreaDto> listOfServiceArea = this.serviceAreaService.findAllServiceArea();
		model.addAttribute("listOfServiceArea", listOfServiceArea);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Service Area | Manintenance Management");
		return "/pages/masters/service-area";
	}

	// Handler For submitting Service Progress Data
	@PostMapping("/saveServiceprogress")
	public String submitServiceProgress(ServiceProgressDto serviceProgressDto, HttpSession session) {
		this.serviceProgressService.saveServiceProgress(serviceProgressDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/serviceprogtype";
	}

	// Handler For Display Service Progress Data
	@GetMapping("/serviceProgrechistory")
	public String displayServiceProgressHistory(Model model) {
		List<ServiceProgressDto> listOfServiceProgrec = this.serviceProgressService.findAllServiceProgress();
		model.addAttribute("listOfServiceProgrss", listOfServiceProgrec);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Service Progress | Manintenance Management");
		return "/pages/masters/service-progresstype";
	}

	// Handler For Submit Service Provider Data
	@PostMapping("/saveservcprovider")
	public String submitServiceProvider(ServiceProviderDto serviceProviderDto, HttpSession session) {
		this.serviceProviderService.saveServiceProvider(serviceProviderDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/serviceprovider";
	}

	// Handler For Display Service Provider History Data
	@GetMapping("/serviceprovhistory")
	public String displayServiceProviderHistory(Model model) {
		List<ServiceProviderDto> listOfServiceProvider = this.serviceProviderService.getAllServiceProvider();
		model.addAttribute("listOfServiceProvider", listOfServiceProvider);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Service Provider | Manintenance Management");
		return "/pages/masters/service-provider";
	}

	// Hander For SaveSpareParts Or Equipments Data
	@PostMapping("/saveequipments")
	public String submitSparePartsEquipments(SpareEquipmentDto spareEquipmentDto, HttpSession session) {
		this.spareEquipmentService.saveSpareEquipment(spareEquipmentDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/sparepart";
	}

	// Handler For Display SaprePaerts Or Equipment history
	@GetMapping("/partsequipmenthistory")
	public String displaySpareEquipMentHistory(Model model) {
		List<SpareEquipmentDto> listOfEquipments = this.spareEquipmentService.getAllSpareEquipmens();
		model.addAttribute("listOfEquipments", listOfEquipments);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Equipments | Manintenance Management");
		return "/pages/masters/sparepart-equipment";
	}

	// Handler For Submit Store/Branch Data
	@PostMapping("/savestorebranch")
	public String saveStoreBranch(StoreBranchDto storeBranchDto, HttpSession session) {
		this.storeBranchService.saveStoreBranch(storeBranchDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/storebranch";
	}

	// Handler For Display Store/branch History
	@GetMapping("/storebranchistory")
	public String displayStoreBranchHistory(Model model,HttpSession session) {
		List<StoreBranchDto> listOfStoreBranch = this.storeBranchService.findAllStoreBranch();
		model.addAttribute("listOfStoreBranch", listOfStoreBranch);
		model.addAttribute("show", "show");
		try {
			String maxStoreBranchId = this.storeBranchService.getMaxStoreBranchId();
			if (maxStoreBranchId != null) {
				int nextStoreBranchId = Integer.parseInt(maxStoreBranchId)+1;
				model.addAttribute("masterbranchid", nextStoreBranchId+"");
			}else {
				String masterIdName ="Branch Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(masterIdName);
				String startNo = idMasterDto.getStatNumber();
				model.addAttribute("masterbranchid", startNo);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Branch Id Is Not Paressent Please Add Id First","info"));
			System.out.println("Exception :: "+e.getMessage());
		}		
		model.addAttribute("title", "Master | Store/Branch | Manintenance Management");
		return "/pages/masters/store-branch";
	}

	// Handler For Save Leakage Type Data
	@PostMapping("/saveleakageType")
	public String saveLaeakageType(LeakageTypeDto leakageTypeDto, HttpSession session) {
		this.leakageTypeService.saveLeakageType(leakageTypeDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/leakagetype";
	}

	// Handler For Display Leakage type
	@GetMapping("/leakageHistory")
	public String displayLeakageTypeHistory(Model model) {
		List<LeakageTypeDto> listOfLeakageType = this.leakageTypeService.getAllLeakageType();
		model.addAttribute("listOfLikageType", listOfLeakageType);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Leakage | Manintenance Management");
		return "/pages/masters/leakage-type";
	}

	// Handler For Save TaskStatus Data
	@PostMapping("/saveTaskStatus")
	public String submitTaskStatus(TaskStatusDto taskStatusDto, HttpSession session) {
		this.taskStatusService.saveTaskStatus(taskStatusDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/taskstatus";
	}

	// Handler For Show Task Satus History
	@GetMapping("/taskStatusHistory")
	public String showTaskStatusHistory(Model model) {
		List<TaskStatusDto> listOfTaskStatus = this.taskStatusService.findAllTaskStatus();
		model.addAttribute("listOfTaskStatus", listOfTaskStatus);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Task | Manintenance Management");
		return "/pages/masters/task-status";
	}

	// Handler For save Tax Master data
	@PostMapping("/savetaxmaster")
	public String submitTaxMaster(TaxMasterDto taxMasterDto, HttpSession session) {
		this.taxMasterService.saveTaxMaster(taxMasterDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/mastertax";
	}

	// Handler For Display Tax Master history
	@GetMapping("/taxMasterHistory")
	public String showTaxMasterHistory(Model model) {
		List<TaxMasterDto> listOfTaxMaster = this.taxMasterService.getAllTaxMaster();
		model.addAttribute("listOfTaxs", listOfTaxMaster);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Tax | Manintenance Management");
		return "/pages/masters/master-tax";
	}

	// Handler For Submit Unit Measure Data
	@PostMapping("/saveUnitMeasure")
	public String saveUnitMeasure(UnitMeasureDto unitMeasureDto, HttpSession session) {
		this.unitMeasureService.saveUnitMeasure(unitMeasureDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/unitmeasure";
	}

	// Handler for Display Unit Measure History
	@GetMapping("/unitMeasureHistory")
	public String displayUnitMeasureHistory(Model model) {
		List<UnitMeasureDto> listOfunitMeasure = this.unitMeasureService.getAllUnitMeasure();
		model.addAttribute("listOfUnitMeasure", listOfunitMeasure);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Unit | Manintenance Management");
		return "/pages/masters/unit-measure";
	}

	// Handler For submit Vehicle Master Data
	@PostMapping("/saveVehicleDtls")
	public String submitVehicleMaster(VehicleDtlsDto vehicleDtlsDto, HttpSession session) {
		this.vehicleDtlsService.saveVDtls(vehicleDtlsDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/mastervehicle";
	}

	// Handler For Display Vehicle Master History
	@GetMapping("/vehicleDtlsHistory")
	public String displayVehicleMasterHistory(Model model) {
		List<VehicleDtlsDto> listOfVehicle = this.vehicleDtlsService.findAllVehicleDtls();
		model.addAttribute("listOfVehicle", listOfVehicle);
		model.addAttribute("show", "show");
		String maxVehicleId = this.vehicleDtlsService.getMaxVehicleId();
		if (maxVehicleId != null) {
			int newStartId = Integer.parseInt(maxVehicleId) + 1;
			model.addAttribute("vehicleMasterId", newStartId+"");
		} else {
			String idName = "Vehicle Id";
			IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
			String stratNo = idMasterDto.getStatNumber();
			model.addAttribute("vehicleMasterId", stratNo);
		}
		model.addAttribute("title", "Master | Vehicle | Manintenance Management");
		return "/pages/masters/master-vehicle";
	}

	// Handler For save Work Priority Data
	@PostMapping("/saveWorkPriority")
	public String saveWorkPriority(WorkPriorityDto workPriorityDto, HttpSession session) {
		this.workPriorityService.saveWorkPriority(workPriorityDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/workpriority";
	}

	// Display Work Priority History
	@GetMapping("/workPriorityHistory")
	public String displayWorkPriority(Model model) {
		List<WorkPriorityDto> listOfWorkPriority = this.workPriorityService.findAllWorkPriority();
		model.addAttribute("listOfWorkPriority", listOfWorkPriority);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Work Priority | Manintenance Management");
		return "/pages/masters/work-priority";
	}

	// Handler For Save Work Status Data
	@PostMapping("/saveWorkStatus")
	public String saveWorkStatus(WorkStatusDto workStatusDto, HttpSession session) {
		this.workStatusService.saveWorkStatus(workStatusDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/workstatus";
	}

	// Handler For Display Work Status History
	@GetMapping("/workStatusHistory")
	public String displayWorkStatusHistory(Model model) {
		List<WorkStatusDto> listOfWorkStatus = this.workStatusService.getAllWorkStatus();
		model.addAttribute("listOfWorkStatus", listOfWorkStatus);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Work Status | Manintenance Management");
		return "/pages/masters/work-status";
	}

	// Handler For Save TeamCode Data
	@PostMapping("/saveTeamcode")
	public String saveTeamCode(TeamCodeDto teamCodeDto, HttpSession session) {
		this.teamCodeService.saveTeamCode(teamCodeDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/teamcode";
	}

	// Handler For Display TeamCode History
	@GetMapping("/teamcodeHistory")
	public String showTeamCodeHistory(Model model) {
		List<TeamCodeDto> listOfTeamCode = this.teamCodeService.getAllTeamCode();
		model.addAttribute("listOfTeamCode", listOfTeamCode);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Teamcode | Manintenance Management");
		return "/pages/masters/temcode";
	}

	// Handler For save waterSource Data
	@PostMapping("/saveWaterSource")
	public String saveWaterSource(WaterSourceDto waterSourceDto, HttpSession session) {
		this.waterSourceService.saveWaterSource(waterSourceDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/watersouce";
	}

	// Handler For Display Water Source History
	@GetMapping("/waterSourceHistory")
	public String showWaterSourceHistory(Model model) {
		List<WaterSourceDto> listOfWaterSource = this.waterSourceService.findAllWaterSource();
		model.addAttribute("listOfWaterSource", listOfWaterSource);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | WaterSource | Manintenance Management");
		return "/pages/masters/water-source";
	}

	// Handler For Get Id Master Data for Update
	@GetMapping("/getMasterIdData/{masterId}")
	public String getDataByMasterId(@PathVariable("masterId") Long masterId, Model model) {
		IdMasterDto idMasterDtos = this.idMasterService.getByMasterId(masterId);
		model.addAttribute("idMasterDto", idMasterDtos);
		return "/pages/masters/master-id-update";
	}

	// Handler For Update Master Id Data
	@PostMapping("/upadeMasterId")
	public String updateMasterIdData(@ModelAttribute IdMasterDto idMasterDto, HttpSession session) {
		this.idMasterService.saveIdMaster(idMasterDto);
		session.setAttribute("message", new Message("Data Updated Successfully !!", "Warning"));
		return "redirect:/masters/idmasterhistory";
	}

	// Handler For get Department Id Master Data By MasterId Name
	@ResponseBody
	@GetMapping("/getDepartmentIds/{masterIdName}")
	public ResponseEntity<List<DepartmentIdMasterDto>> getByMasterIdName(
			@PathVariable("masterIdName") String masterIdName) {
		List<DepartmentIdMasterDto> departmentIdMasterDtos = deptIdMasterService.getByMasterIdName(masterIdName);
		return new ResponseEntity<List<DepartmentIdMasterDto>>(departmentIdMasterDtos, HttpStatus.OK);
	}

	// Handler For get Department Id Master Data By MasterId
	@ResponseBody
	@GetMapping("/getDeptMasterIdData/{depMasterId}")
	public ResponseEntity<DepartmentIdMasterDto> getDepartmentIdByMasterdeptId(
			@PathVariable("depMasterId") Long depMasterId) {
		DepartmentIdMasterDto departmentIdMasterDto = this.deptIdMasterService
				.getDeptIdMasterByDepMasterId(depMasterId);
		return new ResponseEntity<DepartmentIdMasterDto>(departmentIdMasterDto, HttpStatus.OK);
	}

	// Handler For Update Department Master IdData
	@PostMapping("/updateDeptIdMaster")
	public String updateDepartmentIdMaster(@ModelAttribute DepartmentIdMasterDto deptIdMasterDto, HttpSession session) {
		this.deptIdMasterService.saveDepartmentIdMaster(deptIdMasterDto);
		session.setAttribute("message", new Message("Data Updated Successfully !!", "Warning"));
		return "redirect:/masters/departmentIdHistory";
	}

	// Handler For save Designation Master Data
	@PostMapping("/saveDesignation")
	public String saveDesignationmaster(DesignationDto designationDto, HttpSession session) {
		String desc = designationDto.getDesignation();
		designationDto.setDesignation(desc.toUpperCase());
		this.designationService.saveDesignation(designationDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/designation";
	}

	// Handler For Display Designation Master History
	@GetMapping("/desigHistory")
	public String displayDesignationMasterHistory(Model model) {
		try {
			List<DesignationDto> listOfDesignation = this.designationService.getAllDesignations();
			model.addAttribute("listOfDesignation", listOfDesignation);
			model.addAttribute("show", "show");
			List<DesignationDto> designationDtos = this.designationService.getDesignationsFormUbarms();
			if (!designationDtos.isEmpty()) {
				model.addAttribute("ubmdesigList", designationDtos);
			}			
		} catch (Exception e) {
			System.out.println("Designation Not Found !!"+e.getMessage());
		}
		
		model.addAttribute("title", "Master | Designation | Manintenance Management");
		return "/pages/masters/master-designation";
	}

	// Handler For Submit Supplier Details Data
	@PostMapping("/saveSupplier")
	public String submitSupplierDetails(SupplierDtlsDto supplierDtlsDto, HttpSession session) {
		supplierDtlsDto.setState(adminService.getState(supplierDtlsDto.getState()));
		this.supplierDtlsService.saveSupplierDtls(supplierDtlsDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/supplierdetails";
	}

	// Handler For Display Supplier Details History
	@GetMapping("/supplierHistory")
	public String displaySupplierDetailshistory(Model model) {
		List<SupplierDtlsDto> listOfSupplierDtls = this.supplierDtlsService.getAllSupplierDtls();
		model.addAttribute("listOfSupplierDtls", listOfSupplierDtls);
		model.addAttribute("show", "show");
		model.addAttribute("states", adminService.getAllStates());
		model.addAttribute("title", "Master | Supplier | Manintenance Management");
		return "/pages/masters/supplier-details";
	}
	
	//Handler For Save Brand Master Data
	@PostMapping("/saveBrand-master")
	public String saveBrandMaster(BrandMasterDto brandMasterDto,HttpSession session) {
		this.brandMasterService.saveBrandMaster(brandMasterDto);
		session.setAttribute("message", new Message("Data Saved Successfully !! " ,"success"));
		return "redirect:/masters/brand-master";
	}
	
	//Handler For Display Brand Master History
	@GetMapping("/brandMaster-history")
	public String displayBrandMasterHistory(Model model) {
		List<BrandMasterDto> listOfBrandMaster = this.brandMasterService.getAllBrandMasters();
		model.addAttribute("listOfBrands", listOfBrandMaster);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Brand | Manintenance Management");
		return "/pages/masters/master-brand";
	}

	// Delete Department Master
	@RequestMapping("/deleteDeptMaster/{depMasterId}")
	public String deleteDepartmentMaster(@PathVariable Long depMasterId) {
		this.departmentService.deleteDepartmentMaster(depMasterId);
		return "redirect:/masters/deparmentHistory";
	}

	// Delete Category By CategoryId
	@RequestMapping("/deleteCategory/{catid}")
	public String deleteCategory(@PathVariable("catid") Long catid) {
		this.categoryService.deleteCategory(catid);
		return "redirect:/masters/categoryhistory";
	}
	
	//Delete Brand Master
	@RequestMapping("/deleteBrand/{brandMasterId}")
	public String deleteBrandMaster(@PathVariable Long brandMasterId) {
		this.brandMasterService.deleteBrandMaster(brandMasterId);
		return "redirect:/masters/brandMaster-history";
	}

	// Delete Distribution Location
	@RequestMapping("/delteDistLocation/{disLocId}")
	public String deleteDistributionLocation(@PathVariable Long disLocId) {
		this.disLocationService.deleteDistributeLocation(disLocId);
		return "redirect:/masters/dislocationhistory";
	}

	// Delete Distribution Schedule
	@RequestMapping("/deleteDistSchedule/{disScheduleId}")
	public String deleteDistributionSchedule(@PathVariable Long disScheduleId) {
		this.disScheduleService.deleteDistrbSchedule(disScheduleId);
		return "redirect:/masters/disschedulehistory";
	}

	// Delete Division SubDivision
	@RequestMapping("/deleteDivSubdiv/{divsubId}")
	public String deleteDivisionSubdivision(@PathVariable Long divsubId) {
		this.divSubDivService.deleteDivSubDiv(divsubId);
		return "redirect:/masters/divsubdivhistory";
	}

	// Delete Dma Ward
	@RequestMapping("/deleteDmaWard/{dmaWardId}")
	public String deleteDmaWard(@PathVariable Long dmaWardId) {
		this.dmaWardService.deleteDmaWard(dmaWardId);
		return "redirect:/masters/dmawardhistory";
	}

	// Delete Employee Performance
	@RequestMapping("/deleteEmpPerform/{empPerformId}")
	public String deleteEmployeePerformance(@PathVariable Long empPerformId) {
		this.empPerformService.deleteEmpPerformance(empPerformId);
		return "redirect:/masters/empperformhistory";
	}

	// Delete HsnCode Master Data
	@RequestMapping("/deleteHnsCode/{hsnCodeId}")
	public String deleteHsnCode(@PathVariable Long hsnCodeId) {
		this.hsnCodeService.deleteHsnCode(hsnCodeId);
		return "redirect:/masters/hsncodehistory";
	}

	// Delete Maintains Frequency
	@RequestMapping("/deleteMaintenFrequency/{maintanFrequId}")
	public String deleteMaintainsFrequency(@PathVariable Long maintanFrequId) {
		this.maintanFreqService.deleteMaintenFrequency(maintanFrequId);
		return "redirect:/masters/maintanfreqhistory";
	}

	// Delete Maintains Activities
	@RequestMapping("/deleteMaintenActive/{maintenActiveId}")
	public String deleteMaintainsActivity(@PathVariable Long maintenActiveId) {
		this.maintenActiveService.deleteMaintenActivity(maintenActiveId);
		return "redirect:/masters/maintenactivehistory";
	}

	// Delete maintains Performance
	@RequestMapping("/deleteMaintenPerform/{maintenPerformId}")
	public String deleteMaintainsPerformance(@PathVariable Long maintenPerformId) {
		this.maintenPerformSercice.deleteMaintainsPerformance(maintenPerformId);
		return "redirect:/masters/maintenperformhistory";
	}

	// Delete Maintains Type
	@RequestMapping("/deleteMaintenType/{maintenTypeId}")
	public String deleteMaintainsType(@PathVariable Long maintenTypeId) {
		this.maintenTypeService.deleteMaintainsType(maintenTypeId);
		return "redirect:/masters/maintentypehistory";
	}

	// Delete Item Master
	@RequestMapping("/deleteMasterItem/{itemMasterId}")
	public String deleteItemMaster(@PathVariable Long itemMasterId) {
		this.itemMasterService.deleteMasterItem(itemMasterId);
		return "redirect:/masters/itemhistory";
	}

	// Delete MeterManufacture
	@RequestMapping("/deleteMeterManufact/{mtrmanufacId}")
	public String deleteMeterManufacture(@PathVariable Long mtrmanufacId) {
		this.meterManufactService.deleteMeterManufacture(mtrmanufacId);
		return "redirect:/masters/metermanufacthistory";
	}

	// Delete MeterType
	@RequestMapping("/deleteMeterType/{meterTypeId}")
	public String deleteMeterType(@PathVariable Long meterTypeId) {
		this.meterTypeService.deleteMeterType(meterTypeId);
		return "redirect:/masters/metertypehistory";
	}

	// Delete Pipe Manufacture
	@RequestMapping("/deletePipeManufact/{pipemanufId}")
	public String deletePipeManufacture(@PathVariable Long pipemanufId) {
		this.pipeManufactService.deletePipeManufacture(pipemanufId);
		return "redirect:/masters/pipemanufacthistory";
	}

	// Delete Pressure Type
	@RequestMapping("/deletePressureType/{pressureId}")
	public String deletePressureType(@PathVariable Long pressureId) {
		this.pressureTypeService.deletePressureType(pressureId);
		return "redirect:/masters/pressurehistory";
	}

	// Delete PumpMaster
	@RequestMapping("/deletePumpMaster/{pumpMasterId}")
	public String deletePumpMaster(@PathVariable Long pumpMasterId) {
		this.pumpService.deletePumpMaster(pumpMasterId);
		return "redirect:/masters/pumpshistory";
	}

	// Delete Safety Precautions
	@RequestMapping("/deleteSafetyPrecous/{saftyprecId}")
	public String deleteSafetyPrecautions(@PathVariable Long saftyprecId) {
		this.precautionService.deleteSaftyPrecason(saftyprecId);
		return "redirect:/masters/safetyhistory";
	}

	// Delete Service Area
	@RequestMapping("/deleteServiceArea/{sericAreaId}")
	public String deleteServiceArea(@PathVariable Long sericAreaId) {
		this.serviceAreaService.deleteServiceArea(sericAreaId);
		return "redirect:/masters/serviceareahistory";
	}

	// Delete Service Progress Type
	@RequestMapping("/deleteServiceProgress/{servcProgressId}")
	public String deleteServiceProgressType(@PathVariable Long servcProgressId) {
		this.serviceProgressService.deleteServiceProgressType(servcProgressId);
		return "redirect:/masters/serviceProgrechistory";
	}

	// Delete Service Provider
	@RequestMapping("/deleteServiceProvider/{servProvId}")
	public String deleteServiceProvider(@PathVariable Long servProvId) {
		this.serviceProviderService.deleteServiceProvider(servProvId);
		return "redirect:/masters/serviceprovhistory";
	}

	// Delete Spare Parts And Equipments
	@RequestMapping("/deleteSpareEquipment/{spareequiId}")
	public String deleteSparePartsEqupment(@PathVariable Long spareequiId) {
		this.spareEquipmentService.deleteSpareEquipment(spareequiId);
		return "redirect:/masters/partsequipmenthistory";
	}

	// Delete Store Branch Master
	@RequestMapping("/deleteStoreBranch/{strbraNameId}")
	public String deleteStoreBranch(@PathVariable Long strbraNameId) {
		this.storeBranchService.deleteStoreBranch(strbraNameId);
		return "redirect:/masters/storebranchistory";
	}

	// Delete Supplier Details
	@RequestMapping("/deleteSupplierDtls/{suplyDtlsId}")
	public String deleteSupplierDetails(@PathVariable Long suplyDtlsId) {
		this.supplierDtlsService.deleteSupplierDetails(suplyDtlsId);
		return "redirect:/masters/supplierHistory";
	}

	// Delete Designation Master
	@RequestMapping("/deleteDesignation/{desigId}")
	public String deleteDesignationMaster(@PathVariable Long desigId) {
		this.designationService.deleteDesignations(desigId);
		return "redirect:/masters/desigHistory";
	}

	// Delete Leakage Type
	@RequestMapping("/deleteLeakageType/{leakageId}")
	public String deleteLeakageType(@PathVariable Long leakageId) {
		this.leakageTypeService.deleteLeakageType(leakageId);
		return "redirect:/masters/leakageHistory";
	}

	// Delete Task Status
	@RequestMapping("/deleteTaskStatus/{taskstsId}")
	public String deleteTaskStatus(@PathVariable Long taskstsId) {
		this.taskStatusService.deleteTaskStatus(taskstsId);
		return "redirect:/masters/taskStatusHistory";
	}

	// Delete Tax Master
	@RequestMapping("/deleteTaxmaster/{taxMasterId}")
	public String deleteTaxMaster(@PathVariable Long taxMasterId) {
		this.taxMasterService.deleteTaxMaster(taxMasterId);
		return "redirect:/masters/taxMasterHistory";
	}

	// Delete Unit Of Measure
	@RequestMapping("/deleteUnitMeasure/{unitMeasureId}")
	public String deleteUnitOfMeasure(@PathVariable Long unitMeasureId) {
		this.unitMeasureService.deleteUnitOfMeasure(unitMeasureId);
		return "redirect:/masters/unitMeasureHistory";
	}

	// Delete Vehicle Details
	@RequestMapping("/deleteVehicleDtls/{vehicleDtlsId}")
	public String deleteVehicleDetails(@PathVariable Long vehicleDtlsId) {
		this.vehicleDtlsService.deleteVehicleDetails(vehicleDtlsId);
		return "redirect:/masters/vehicleDtlsHistory";
	}

	// Delete Work Priority
	@RequestMapping("/deleteWorkPriority/{workPrioId}")
	public String deleteWorkPriority(@PathVariable Long workPrioId) {
		this.workPriorityService.deleteWorkPriority(workPrioId);
		return "redirect:/masters/workPriorityHistory";
	}

	// Delete Work Status
	@RequestMapping("/deleteWorkStatus/{workStsId}")
	public String deleteWorkStatus(@PathVariable Long workStsId) {
		this.workStatusService.deleteWorkStatus(workStsId);
		return "redirect:/masters/workStatusHistory";
	}

	// Delete Team Code
	@RequestMapping("/deleteTeamCode/{teamCodeId}")
	public String deleteTeamCode(@PathVariable Long teamCodeId) {
		this.teamCodeService.deleteTeamcode(teamCodeId);
		return "redirect:/masters/teamcodeHistory";
	}

	// Delete Water Source
	@RequestMapping("/deleteWaterSource/{wateSourceId}")
	public String deleteWaterSource(@PathVariable Long wateSourceId) {
		this.waterSourceService.deleteWaterCource(wateSourceId);
		return "redirect:/masters/waterSourceHistory";
	}

}
