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
import com.ingroinfo.mm.dto.EmployeeCategoryDto;
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
import com.ingroinfo.mm.service.MasterService;

@Controller
@RequestMapping("/masters")
public class MasterController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private MasterService masterService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("/barcode")
	@PreAuthorize("hasAuthority('MASTERS')")
	public String openMasterBarcodePage(Model model) {
		model.addAttribute("title", "Master | Barcode | Manintenance Management");
		return "/pages/masters/master-barcode";
	}

	@GetMapping("/department")
	public String openMasterDepartmentPage(Model model, HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Department | Manintenance Management");
		try {

			String idName = "Department Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String deptIdLastNo = idMasterDto.getLastNumber();
			model.addAttribute("maxdeptId", deptIdLastNo);

		} catch (Exception e) {
			session.setAttribute("message",
					new Message("Department Id Is Not Pressent Please Add Id First !!", "danger"));
			System.out.println("Exception :: " + e.getMessage());
		}
		try {
			List<DepartmentDto> departmentDtos = this.masterService.getDepartmentsFromUbarms();
			if (departmentDtos != null) {
				model.addAttribute("departmentList", departmentDtos);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Ubarms Server Is Not Running !!", "danger"));
			System.out.println("Exception :: " + e.getMessage());
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
		List<DepartmentDto> listOfDepartment = this.masterService.findAllDepartment();
		model.addAttribute("allDepartment", listOfDepartment);
		model.addAttribute("title", "Master | Department Id | Manintenance Management");
		return "/pages/masters/master-departmentId";
	}

	@GetMapping("/category")
	public String openMasterCategoryPage(Model model, HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Category | Manintenance Management");
		try {
			List<BrandMasterDto> brandMasterDtos = this.masterService.getAllBrandMasters();
			model.addAttribute("brandList", brandMasterDtos);
			String idName = "Categaroy Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastNo = idMasterDto.getLastNumber();
			model.addAttribute("maxcategoryid", lastNo);
		} catch (Exception e) {
			session.setAttribute("message",
					new Message("Category Id Is Not Pressent Please Add Id First !!", "danger"));
			System.out.println("Something Wrong !!" + e.getMessage());
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
		List<String> divisions = this.masterService.getDistinctDivisions();
		model.addAttribute("listOfDivisions", divisions);
		model.addAttribute("title", "Master | Distribution Location | Manintenance Management");
		return "/pages/masters/distribution-location";
	}

	@GetMapping("/schedule")
	public String openDistributionSchedulePage(Model model) {
		model.addAttribute("show", null);
		List<String> divisionList = this.masterService.getDistinctDivisions();
		model.addAttribute("divisionList", divisionList);
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
		List<CategoryDto> listOfCategoryDtos = this.masterService.findAllCategory();
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
	public String openMasterItemPage(Model model, HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Item | Manintenance Management");

		try {
			List<CategoryDto> categoryDtos = this.masterService.findAllCategory();
			model.addAttribute("categoryList", categoryDtos);
			String idName = "Item Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastItemId = idMasterDto.getLastNumber();
			model.addAttribute("masterItemId", lastItemId);
		} catch (Exception e) {
			session.setAttribute("message", new Message("Master Item Id Is Not Present Add Id First !!", "danger"));
			System.out.println("Something went Wrong " + e.getMessage());
		}
		return "/pages/masters/master-item";
	}

	@GetMapping("/metermanufacture")
	public String openMeterManufacturePage(Model model, HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Meter Manufacture | Manintenance Management");
		try {
			List<MeterTypeDto> meterTypeDtos = this.masterService.getAllMeterType();
			model.addAttribute("meterTypes", meterTypeDtos);
			String idName = "Meter Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastMeterId = idMasterDto.getLastNumber();
			model.addAttribute("meterIdNo", lastMeterId);
		} catch (Exception e) {
			session.setAttribute("message", new Message("Meter Id Is Not Pressent Please Add Id First !!", "danger"));
			System.out.println("Exception ::" + e.getMessage());
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
	public String openMasterPumpsPage(Model model, HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Pumps | Manintenance Management");
		try {
			String idName = "Pump Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastPumpId = idMasterDto.getLastNumber();
			model.addAttribute("masterpumpid", lastPumpId);
		} catch (Exception e) {
			session.setAttribute("message", new Message("Pump Id Is Not Pressent Please Add Id First !!", "danger"));
			System.out.println("Exception :: " + e.getMessage());
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
	public String openStoreAndBranchNamePage(Model model, HttpSession session) {
		model.addAttribute("show", null);
		try {
			String masterIdName = "Branch Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(masterIdName);
			String lastBranchId = idMasterDto.getLastNumber();
			model.addAttribute("masterbranchid", lastBranchId);
		} catch (Exception e) {
			session.setAttribute("message", new Message("Branch Id Is Not Paressent Please Add Id First", "danger"));
			System.out.println("Exception :: " + e.getMessage());
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
	public String openDesignation(Model model, HttpSession session) throws IOException {
		model.addAttribute("show", null);
		model.addAttribute("ubmdesigList", new DesignationDto());
		try {
			List<DesignationDto> designationDtos = masterService.getDesignationsFormUbarms();
			if (!designationDtos.isEmpty()) {
				model.addAttribute("ubmdesigList", designationDtos);
			} else {
				session.setAttribute("message", new Message("Designations Not Found !!", "danger"));
				System.out.println("DesignationList Not Found !!");
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Ubarms Server Is Not Running !!", "danger"));
			System.out.println("Server Stopped !!" + e.getMessage());

		}
		model.addAttribute("title", "Master | Designation | Manintenance Management");
		return "/pages/masters/master-designation";
	}

	@GetMapping("/leakageMaster")
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
	public String openVehicleMasterDetailsPage(Model model, HttpSession session) {
		model.addAttribute("show", null);
		model.addAttribute("title", "Master | Vehicle | Manintenance Management");
		try {
			String idName = "Vehicle Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastVehicleId = idMasterDto.getLastNumber();
			model.addAttribute("vehicleMasterId", lastVehicleId);
		} catch (Exception e) {
			session.setAttribute("message",
					new Message("Vehicle Id Is Not Present !! Please Add Id First !!", "danger"));
			System.out.println("Exception :: " + e.getMessage());
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
		if (departmentDto.getDepartmentId() != "" && departmentDto.getDepartmentName() != "") {
			try {
				String idName = "Department Id";
				IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
				String lastDeptId = idMasterDto.getLastNumber();
				StringBuilder letters = new StringBuilder();
				StringBuilder numbers = new StringBuilder();
				for (int i = 0; i < lastDeptId.length(); i++) {
					char c = lastDeptId.charAt(i);
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
				String nextDeptId = lettersString + Integer.toString(number);
				idMasterDto.setLastNumber(nextDeptId);
				this.masterService.saveIdMaster(idMasterDto);
			} catch (Exception e) {
				System.out.println("something went Wrong !!" + e.getMessage());
			}

			this.masterService.saveDepartment(departmentDto);
		} else {
			return "redirect:/masters/department";
		}
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/department";
	}

	// Handler For Display Department Master History
	@GetMapping("/deparmentHistory")
	public String displayDepartmentHistory(Model model, HttpSession session) {
		List<DepartmentDto> listOfDepartment = this.masterService.findAllDepartment();
		model.addAttribute("listOfDepartment", listOfDepartment);
		model.addAttribute("show", "show");
		try {
			String idName = "Department Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String deptLastNo = idMasterDto.getLastNumber();
			model.addAttribute("maxdeptId", deptLastNo);
		} catch (Exception e) {
			session.setAttribute("message",
					new Message("Department Id Is Not Pressent Please Add Id First !!", "danger"));
			System.out.println("Exception :: " + e.getMessage());
		}
		try {
			List<DepartmentDto> departmentDtos = this.masterService.getDepartmentsFromUbarms();
			if (departmentDtos != null) {
				model.addAttribute("departmentList", departmentDtos);
			}
		} catch (Exception e) {
			System.out.println("Exception :: " + e.getMessage());
		}

		model.addAttribute("title", "Master | Department | Manintenance Management");
		return "/pages/masters/master-department";
	}

	// Handler For Save Department Id Master Data
	@PostMapping("/saveDepartmentId")
	public String saveDepartmentIdMaster(DepartmentIdMasterDto deptIdMasterDto, HttpSession session) {
		deptIdMasterDto.setDeptLastId(deptIdMasterDto.getDeptId());
		this.masterService.saveDepartmentIdMaster(deptIdMasterDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/departmentId";
	}

	// Handler For Display Department Id Master History
	@GetMapping("/departmentIdHistory")
	public String displayMasterDepartmentIdHistory(Model model) {

		List<String> list = this.masterService.findAllDepartmentIdMaster().stream()
				.map((idName) -> idName.getMasterIdName()).collect(Collectors.toList());

		// list.forEach(System.out::println);

		// For Budget Id
		boolean isExist = list.stream().filter(o -> o.contains("Budget Id")).findFirst().isPresent();
		if (isExist) {
			List<DepartmentIdMasterDto> listOfBudgetId = this.masterService.getByMasterIdName("Budget Id");
			model.addAttribute("listOfBudgetId", listOfBudgetId);
		}

		// For Out Material Issued Id
		boolean isExist1 = list.stream().filter(o -> o.contains("Out Matl Issue Id")).findFirst().isPresent();
		if (isExist1) {
			List<DepartmentIdMasterDto> listOfOutmatlIsueId = this.masterService.getByMasterIdName("Out Matl Issue Id");
			model.addAttribute("listOfOutmatlIsueId", listOfOutmatlIsueId);
		}

		// For Out Spare Material Issue Id
		boolean isExist2 = list.stream().filter(o -> o.contains("Out Spr Matl Issue Id")).findFirst().isPresent();
		if (isExist2) {
			List<DepartmentIdMasterDto> listOfOutSparematlIsueId = this.masterService
					.getByMasterIdName("Out Spr Matl Issue Id");
			model.addAttribute("listOfOutSparematlIsueId", listOfOutSparematlIsueId);
		}

		// For Out Tools Material Issue Id
		boolean isExist3 = list.stream().filter(o -> o.contains("Out Tools Matl Issue Id")).findFirst().isPresent();
		if (isExist3) {
			List<DepartmentIdMasterDto> listOfOutToolsmatlIsueId = this.masterService
					.getByMasterIdName("Out Tools Matl Issue Id");
			model.addAttribute("listOfOutToolsmatlIsueId", listOfOutToolsmatlIsueId);
		}

		// For Sequence Id
		boolean isExist4 = list.stream().filter(o -> o.contains("Sequence Id")).findFirst().isPresent();
		if (isExist4) {
			List<DepartmentIdMasterDto> listOfSequenceId = this.masterService.getByMasterIdName("Sequence Id");
			model.addAttribute("listOfSequenceId", listOfSequenceId);
		}

		// For Stock Rejection/Damage Material Return
		boolean isExist5 = list.stream().filter(o -> o.contains("Stk Rej/Dmg Matl Rtn Id")).findFirst().isPresent();
		if (isExist5) {
			List<DepartmentIdMasterDto> listOfStkRejDmgMatlRtnId = this.masterService
					.getByMasterIdName("Stk Rej/Dmg Matl Rtn Id");
			model.addAttribute("listOfStkRejDmgMatlRtnId", listOfStkRejDmgMatlRtnId);
		}

		// For Stock Rejection/Damage Spare Return
		boolean isExist6 = list.stream().filter(o -> o.contains("Stk Rej/Dmg Spr Rtn Id")).findFirst().isPresent();
		if (isExist6) {
			List<DepartmentIdMasterDto> listOfStkRejSpareRtn = this.masterService
					.getByMasterIdName("Stk Rej/Dmg Spr Rtn Id");
			model.addAttribute("listOfStkRejSpareRtn", listOfStkRejSpareRtn);
		}

		// For Stock Rejection/Damage Tools Return
		boolean isExist7 = list.stream().filter(o -> o.contains("Stk Rej/Dmg Tools Rtn Id")).findFirst().isPresent();
		if (isExist7) {
			List<DepartmentIdMasterDto> listOfStkRejToolsRtn = this.masterService
					.getByMasterIdName("Stk Rej/Dmg Tools Rtn Id");
			model.addAttribute("listOfStkRejToolsRtn", listOfStkRejToolsRtn);
		}

		// For Stock Material Return Id
		boolean isExist8 = list.stream().filter(o -> o.contains("Stock Matl Rtn Id")).findFirst().isPresent();
		if (isExist8) {
			List<DepartmentIdMasterDto> listOfstkMtlRtn = this.masterService.getByMasterIdName("Stock Matl Rtn Id");
			model.addAttribute("listOfstkMtlRtn", listOfstkMtlRtn);
		}

		// For Stock Spares Return Id
		boolean isExist9 = list.stream().filter(o -> o.contains("Stock Spares Rtn Id")).findFirst().isPresent();
		if (isExist9) {
			List<DepartmentIdMasterDto> listOfstkSprRtn = this.masterService.getByMasterIdName("Stock Spares Rtn Id");
			model.addAttribute("listOfstkSprRtn", listOfstkSprRtn);
		}

		// For Stock Tools Return Id
		boolean isExist10 = list.stream().filter(o -> o.contains("Stock Tools Rtn Id")).findFirst().isPresent();
		if (isExist10) {
			List<DepartmentIdMasterDto> listOfStkTlsRtn = this.masterService.getByMasterIdName("Stock Tools Rtn Id");
			model.addAttribute("listOfStkTlsRtn", listOfStkTlsRtn);
		}

		// For Indent Id
		boolean isExist11 = list.stream().filter(o -> o.contains("Indent Id")).findFirst().isPresent();
		if (isExist11) {
			List<DepartmentIdMasterDto> listOfindet = this.masterService.getByMasterIdName("Indent Id");
			model.addAttribute("listOfindet", listOfindet);
		}

		// For Work Order Cancel Id
		boolean isExist12 = list.stream().filter(o -> o.contains("Work Order Cnsl Id")).findFirst().isPresent();
		if (isExist12) {
			List<DepartmentIdMasterDto> listOfWorkOrderCencel = this.masterService
					.getByMasterIdName("Work Order Cnsl Id");
			model.addAttribute("listOfWorkOrderCencel", listOfWorkOrderCencel);
		}

		// For Work Order Id
		boolean isExist13 = list.stream().filter(o -> o.contains("Work Order Id")).findFirst().isPresent();
		if (isExist13) {
			List<DepartmentIdMasterDto> listOfWorkOrder = this.masterService.getByMasterIdName("Work Order Id");
			model.addAttribute("listOfWorkOrder", listOfWorkOrder);
		}

		model.addAttribute("title", "Master | DepartmentId | Manintenance Management");
		return "/pages/masters/master-departmentid-history";
	}

	// Handler For Submitting IdMaster Data
	@PostMapping("/saveIdMaster")
	public String saveIdMaster(IdMasterDto idDto, HttpSession session) {
		idDto.setLastNumber(idDto.getStatNumber());
		this.masterService.saveIdMaster(idDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/idmasterhistory";
	}

	// Handler For Showing All IdMaster Data
	@GetMapping("/idmasterhistory")
	public String showIdMasterHistory(Model model) {
		List<IdMasterDto> listOfIdMasters = this.masterService.findAllIdMaster();
		model.addAttribute("listOfIdMasters", listOfIdMasters);
		model.addAttribute("idMasterDto", new IdMasterDto());
		model.addAttribute("title", "Master | ID | Manintenance Management");
		return "/pages/masters/master-id-history";
	}

	// Handler For Submitting Category Master Data
	@PostMapping("/savecategorydata")
	public String saveCategoryMaster(CategoryDto categoryDto, HttpSession session) {
		if (this.masterService.isCategoryExists(categoryDto.getCategoryName())) {
			session.setAttribute("message", new Message("Category Name Already Exist !!", "warning"));
		} else {
			try {
				String idName = "Categaroy Id";
				IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
				String lastCategoryId = idMasterDto.getLastNumber();
				StringBuilder letters = new StringBuilder();
				StringBuilder numbers = new StringBuilder();
				for (int i = 0; i < lastCategoryId.length(); i++) {
					char c = lastCategoryId.charAt(i);
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
				String nextCategoryId = lettersString + Integer.toString(number);
				idMasterDto.setLastNumber(nextCategoryId);
				this.masterService.saveIdMaster(idMasterDto);
			} catch (Exception e) {
				System.out.println("something went Wrong !!" + e.getMessage());
			}
			this.masterService.saveCategory(categoryDto);
			session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		}
		return "redirect:/masters/category";
	}

	// Handler For Showing Category Master Data
	@GetMapping("/categoryhistory")
	public String showCategoryMasterHistory(Model model, HttpSession session) {
		List<CategoryDto> listOfCategoryDtos = this.masterService.findAllCategory();
		model.addAttribute("listOfCategory", listOfCategoryDtos);
		model.addAttribute("show", "show");
		try {
			List<BrandMasterDto> brandMasterDtos = this.masterService.getAllBrandMasters();
			model.addAttribute("brandList", brandMasterDtos);
			String idName = "Categaroy Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastNo = idMasterDto.getLastNumber();
			model.addAttribute("maxcategoryid", lastNo);
		} catch (Exception e) {
			session.setAttribute("message",
					new Message("Category Id Is Not Pressent Please Add Id First !!", "danger"));
			System.out.println("Something Wrong !!" + e.getMessage());
		}
		model.addAttribute("title", "Master | Category | Manintenance Management");
		return "/pages/masters/master-category";
	}

	// Handler For Submitting Distribution Location Data
	@PostMapping("/savedislocation")
	public String saveDistributionLocation(DistributionLocationDto disLocationDto, HttpSession session) {
		if (this.masterService.isisDistributionLocationExists(disLocationDto.getDistlocation())) {
			session.setAttribute("message", new Message("Distribution Location Already Exist !!", "warning"));
		} else {
			this.masterService.saveDistributionLocation(disLocationDto);
			session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		}
		return "redirect:/masters/dislocation";
	}

	// Handler For Showing Distribution Location History
	@GetMapping("/dislocationhistory")
	public String showDistibutionLocationHistory(Model model) {
		List<DistributionLocationDto> listOfDistLocations = this.masterService.findAllDistributionLocation();
		model.addAttribute("listOfDistLocations", listOfDistLocations);
		List<String> divisions = this.masterService.getDistinctDivisions();
		model.addAttribute("listOfDivisions", divisions);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Distribution Location | Manintenance Management");
		return "/pages/masters/distribution-location";
	}

	// Handler For Submitting Distribution Schedule Data
	@PostMapping("/savedisschedule")
	public String saveDistributionSchedule(DistributionScheduleDto disScheduleDto, HttpSession session) {
		if (this.masterService.isisDistributionScheduleExists(disScheduleDto.getDistSchedule())) {
			session.setAttribute("message", new Message("Distribution Schedule Already Exist !!", "warning"));
		} else {
			this.masterService.saveDisSchedule(disScheduleDto);
			session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		}
		return "redirect:/masters/schedule";
	}

	// Handler For showing Distribution Schedule Data
	@GetMapping("/disschedulehistory")
	public String showDistributionScheduleHistory(Model model) {
		List<DistributionScheduleDto> listOfDisScheduleDtos = this.masterService.findAllDisSchedule();
		model.addAttribute("listOfdisschedule", listOfDisScheduleDtos);
		List<String> divisionList = this.masterService.getDistinctDivisions();
		model.addAttribute("divisionList", divisionList);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Distribution Schedule | Manintenance Management");
		return "/pages/masters/distribution-schedule";
	}

	// Handler For Submitting Division Subdivision Data
	@PostMapping("/savedivsubdiv")
	public String saveDivisionSubdivision(DivisionSubdivisionDto divSubdivdto, HttpSession session) {
		if (this.masterService.isSubDivisionExists(divSubdivdto.getSubdivision())) {
			session.setAttribute("message", new Message("SubDivision Name Already Exist !!", "warning"));
		} else if (this.masterService.isServiceStationExists(divSubdivdto.getServiceStation())) {
			session.setAttribute("message", new Message("Service Station Name Already Exist !!", "warning"));
		} else {
			this.masterService.saveDivisionSubdivision(divSubdivdto);
			session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		}
		return "redirect:/masters/divsubdivision";
	}

	// Handler For showing Division Subdivision History Data
	@GetMapping("/divsubdivhistory")
	public String showDivisonSubdivisionHistory(Model model) {
		List<DivisionSubdivisionDto> listOfDivSubdiv = this.masterService.findAllDivSubdiv();
		model.addAttribute("listOfDivSubdiv", listOfDivSubdiv);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | DivSubDiv | Manintenance Management");
		return "/pages/masters/division-subdivision";
	}

	// Handler For Submitting Dma-Ward Data
	@PostMapping("/savedmaward")
	public String saveDmaWard(DmaWardDto dmaWardDto, HttpSession session) {
		if (this.masterService.isDmaNumberExists(dmaWardDto.getDmaNumber())) {
			session.setAttribute("message", new Message("Dma Number Already Exists !!", "warning"));
		} else if (this.masterService.isWardNumberExists(dmaWardDto.getWardNumber())) {
			session.setAttribute("message", new Message("Ward Number Already Exists !!", "warning"));
		} else {
			this.masterService.saveDmaWard(dmaWardDto);
			session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		}
		return "redirect:/masters/dmaward";
	}

	// Handler For showing Dma-Ward History Data
	@GetMapping("/dmawardhistory")
	public String showDmaWardHistory(Model model) {
		List<DmaWardDto> listOfDmaWard = this.masterService.getAllDmaWard();
		model.addAttribute("listOfDmaWard", listOfDmaWard);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Dma-Ward | Manintenance Management");
		return "/pages/masters/dma-ward";
	}

	// Handler For Submitting Emplyee Performance Data
	@PostMapping("/saveEmpPerform")
	public String saveEmplyeePerformance(EmployeePerformanceDto empPerformDto, HttpSession session) {
		if (this.masterService.isExistsEmpPerformanceSts(empPerformDto.getPerformStatus())) {
			session.setAttribute("message", new Message("Employee Performance Status Already Exist !!", "warning"));
		} else {
			this.masterService.saveEmplyeePerformmance(empPerformDto);
			session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		}
		return "redirect:/masters/empperformance";
	}

	// Handler For showing Employee Performance History Data
	@GetMapping("/empperformhistory")
	public String showEmplyeePerformance(Model model) {
		List<EmployeePerformanceDto> listOfEmpPerformDtos = this.masterService.getAllEmpPerformance();
		model.addAttribute("listOfEmpPerform", listOfEmpPerformDtos);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Employee | Manintenance Management");
		return "/pages/masters/employee-performance";
	}

	// Handler For Submitting HSN Code Data
	@PostMapping("/savehsncode")
	public String saveHsnCode(HsnCodeDto hsnCodeDto, HttpSession session) {
		if (this.masterService.isHsnCodeExists(hsnCodeDto.getHsnCode())) {
			session.setAttribute("message", new Message("Hsn Code Already Exists !!", "warning"));
		} else if (this.masterService.isCategoryExistsInHsnCode(hsnCodeDto.getCategory().getCategoryName())) {
			session.setAttribute("message", new Message("Category Alreday Added !!", "warning"));
		} else {
			this.masterService.saveHsnCode(hsnCodeDto);
			session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		}
		return "redirect:/masters/hsncode";
	}

	// Handler For showing Employee Performance History Data
	@GetMapping("/hsncodehistory")
	public String showHsnCodeHistory(Model model) {
		List<HsnCodeDto> listOfHsnCode = this.masterService.findAllHsnCode();
		List<CategoryDto> listOfCategoryDtos = this.masterService.findAllCategory();
		model.addAttribute("listOfCategory", listOfCategoryDtos);
		model.addAttribute("listOfHsnCode", listOfHsnCode);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | HSN Code | Manintenance Management");
		return "/pages/masters/hsncode";
	}

	// Handler For Submitting MaintanceFrequency Data
	@PostMapping("/savemaintenfreq")
	public String saveMaintanceFrequency(MaintanceFrequencyDto maintenFreqDto, HttpSession session) {
		this.masterService.saveMaintanceFrequency(maintenFreqDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/maintenfrequency";
	}

	// Handler For showing MaintanceFrequency History Data
	@GetMapping("/maintanfreqhistory")
	public String showMaintanceFrequencyHistory(Model model) {
		List<MaintanceFrequencyDto> listOfMaintanFreq = this.masterService.getAllMaintanceFrequency();
		model.addAttribute("listOfMaintenFreq", listOfMaintanFreq);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | MaintenFrequency | Manintenance Management");
		return "/pages/masters/maintenance-frequency";
	}

	// Handler For Submitting MaintanceActivety Data
	@PostMapping("/saveMaintenactive")
	public String saveMaintanceActivety(MaintenanceActivitiesDto maintenActivDto, HttpSession session) {
		if (this.masterService.isMaintenanceActivityExists(maintenActivDto.getMaintenActivity())) {
			session.setAttribute("message", new Message("Maintenance Activity Already Exist !!", "warning"));
		}else {
			this.masterService.saveMaintenActivity(maintenActivDto);			
			session.setAttribute("message", new Message("Data Successfully Added !!", "success"));
		}		
		return "redirect:/masters/maintenactivity";
	}

	// Handler For showing MaintanceActivety History Data
	@GetMapping("/maintenactivehistory")
	public String showMaintanceActivetyHistory(Model model) {
		List<MaintenanceActivitiesDto> listOfMaintenActive = this.masterService.findAllMaintnActve();
		model.addAttribute("listOfMaintenActive", listOfMaintenActive);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | MaintenFrequency | Manintenance Management");
		return "/pages/masters/maintenance-activities";
	}

	// Handler For Submitting MaintancePerformance Data
	@PostMapping("/savemaintenperform")
	public String saveMaintancePerformance(MaintenancePerformanceDto maintenPerformDto, HttpSession session) {
		this.masterService.saveMaintenPerform(maintenPerformDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/maintenperformance";
	}

	// Handler For showing MaintancePerformance History Data
	@GetMapping("/maintenperformhistory")
	public String showMaintancePerformanceHistory(Model model) {
		List<MaintenancePerformanceDto> listOfMaintenPerform = this.masterService.getAllMaintenPerform();
		model.addAttribute("listOfMaintenPerform", listOfMaintenPerform);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | MaintenActivities | Manintenance Management");
		return "/pages/masters/maintenance-performance";
	}

	// Handler For Submitting MaintancePerformance Data
	@PostMapping("/savemaintentype")
	public String saveMaintenanceType(MaintenanceTypeDto maintenTypeDto, HttpSession session) {
		this.masterService.saveMaintenanceType(maintenTypeDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/maintentype";
	}

	// Handler For showing Maintenance Type History
	@GetMapping("/maintentypehistory")
	public String showMaintenanceTypeHistory(Model model) {
		List<MaintenanceTypeDto> listOfMaintenType = this.masterService.findAllMaintenanceType();
		model.addAttribute("listOfMaintenType", listOfMaintenType);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | MaintenType | Manintenance Management");
		return "/pages/masters/maintenance-type";
	}

	// Handler For Submitting Item Master Data
	@PostMapping("/saveItemdata")
	public String saveItemMaster(ItemMasterDto itemMasterDto, HttpSession session) {
		try {
			String idName = "Item Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastItemId = idMasterDto.getLastNumber();
			StringBuilder letters = new StringBuilder();
			StringBuilder numbers = new StringBuilder();
			for (int i = 0; i < lastItemId.length(); i++) {
				char c = lastItemId.charAt(i);
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
			String nextlastItemId = lettersString + Integer.toString(number);
			idMasterDto.setLastNumber(nextlastItemId);
			this.masterService.saveIdMaster(idMasterDto);
		} catch (Exception e) {
			System.out.println("something went Wrong !!" + e.getMessage());
		}

		this.masterService.saveItemmaster(itemMasterDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/masteritem";
	}

	// Handler For showing Item Master History
	@GetMapping("/itemhistory")
	public String showItemMasterHistory(Model model, HttpSession session) {
		List<ItemMasterDto> listOfItems = this.masterService.getAllItems();
		model.addAttribute("listOfItems", listOfItems);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Item | Manintenance Management");
		try {
			List<CategoryDto> categoryDtos = this.masterService.findAllCategory();
			model.addAttribute("categoryList", categoryDtos);
			String idName = "Item Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastItemId = idMasterDto.getLastNumber();
			model.addAttribute("masterItemId", lastItemId);
		} catch (Exception e) {
			session.setAttribute("message", new Message("Master Item Id Is Not Present Add Id First !!", "danger"));
			System.out.println("Something went Wrong " + e.getMessage());
		}
		return "/pages/masters/master-item";
	}

	// Handler For Submitting Meter Type Data
	@PostMapping("/savemetertype")
	public String submitMeterTypeData(MeterTypeDto meterTypeDto, HttpSession session) {
		meterTypeDto.setMeterType(meterTypeDto.getMeterType().toUpperCase());
		this.masterService.saveMeterType(meterTypeDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/metertype";
	}

	// Handler For Showing Meter Type History
	@GetMapping("/metertypehistory")
	public String showMeterTypeHistory(Model model) {
		List<MeterTypeDto> listOfMeterTypes = this.masterService.getAllMeterType();
		model.addAttribute("listOfMeterTypes", listOfMeterTypes);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Meter Type | Manintenance Management");
		return "/pages/masters/meter-type";
	}

	// Handler For Submitting Meter manufacture Data
	@PostMapping("/savemetermanufact")
	public String saveMeterManuFacture(MeterManufactureDto meterManufactDto, HttpSession session) {

		if (meterManufactDto.getMeterType() != "") {
			try {
				String idName = "Meter Id";
				IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
				String lastMeterId = idMasterDto.getLastNumber();
				StringBuilder letters = new StringBuilder();
				StringBuilder numbers = new StringBuilder();
				for (int i = 0; i < lastMeterId.length(); i++) {
					char c = lastMeterId.charAt(i);
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
				String nextlastMeterId = lettersString + Integer.toString(number);
				idMasterDto.setLastNumber(nextlastMeterId);
				this.masterService.saveIdMaster(idMasterDto);
			} catch (Exception e) {
				System.out.println("something went Wrong !!" + e.getMessage());
			}
			this.masterService.saveMeterManufact(meterManufactDto);

		} else {
			session.setAttribute("message", new Message("Data Unsaved !! Something Went Wrong !!", "warning"));
			return "redirect:/masters/metermanufacture";
		}
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/metermanufacture";
	}

	// Handler for Display MeterManufacture History
	@GetMapping("/metermanufacthistory")
	public String displayMeterManufactHistory(Model model, HttpSession session) {
		List<MeterManufactureDto> listOfManufact = this.masterService.findAllMeterManufact();
		model.addAttribute("listOfManufact", listOfManufact);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Meter Manufacture | Manintenance Management");
		try {
			List<MeterTypeDto> meterTypeDtos = this.masterService.getAllMeterType();
			model.addAttribute("meterTypes", meterTypeDtos);
			String idName = "Meter Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastMeterId = idMasterDto.getLastNumber();
			model.addAttribute("meterIdNo", lastMeterId);
		} catch (Exception e) {
			session.setAttribute("message", new Message("Meter Id Is Not Pressent Please Add Id First !!", "danger"));
			System.out.println("Exception ::" + e.getMessage());
		}
		return "/pages/masters/meter-manufacture";
	}

	// Handler For Saving Pipe Manufacture data
	@PostMapping("/savepipemanufact")
	public String savePaipeManufacture(PipeManufactureDto pipeManufactDto, HttpSession session) {
		this.masterService.savePipeManufacture(pipeManufactDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/pipemanufacture";
	}

	// Handler for Display PipeManuFacture History
	@GetMapping("/pipemanufacthistory")
	public String showPipeManufactureHistory(Model model) {
		List<PipeManufactureDto> listOfPipeManufact = this.masterService.findAllPipeManufact();
		model.addAttribute("listOfPipeManufact", listOfPipeManufact);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Pipe | Manintenance Management");
		return "/pages/masters/pipe-manufacture";
	}

	// Handler For Submit PressureType Data
	@PostMapping("/savepressure")
	public String submitPressureType(PressureTypeDto pressureDto, HttpSession session) {
		this.masterService.savePressureType(pressureDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/pressuretype";
	}

	// Handler For Display Pressure Type History
	@GetMapping("/pressurehistory")
	public String showPressureTypeHistory(Model model) {
		List<PressureTypeDto> listOfPressure = this.masterService.getAllPressureType();
		model.addAttribute("listOfPressure", listOfPressure);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Pressure | Manintenance Management");
		return "/pages/masters/pressure-type";
	}

	// Handler for Submitting Pump master data
	@PostMapping("/savepumps")
	public String savePumpMasterData(PumpMasterDto pumpMasterDto, HttpSession session) {
		try {
			String idName = "Pump Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastPumpId = idMasterDto.getLastNumber();
			StringBuilder letters = new StringBuilder();
			StringBuilder numbers = new StringBuilder();
			for (int i = 0; i < lastPumpId.length(); i++) {
				char c = lastPumpId.charAt(i);
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
			String nextlastPumpId = lettersString + Integer.toString(number);
			idMasterDto.setLastNumber(nextlastPumpId);
			this.masterService.saveIdMaster(idMasterDto);
		} catch (Exception e) {
			System.out.println("something went Wrong !!" + e.getMessage());
		}
		this.masterService.savePumpMaster(pumpMasterDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/masterpumps";
	}

	// Handler For Display pump Master Data
	@GetMapping("/pumpshistory")
	public String displayPumpHistory(Model model, HttpSession session) {
		List<PumpMasterDto> listOfPumps = this.masterService.getAllPumpMaster();
		model.addAttribute("listOfPumps", listOfPumps);
		model.addAttribute("show", "show");
		try {
			String idName = "Pump Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastPumpId = idMasterDto.getLastNumber();
			model.addAttribute("masterpumpid", lastPumpId);
		} catch (Exception e) {
			session.setAttribute("message", new Message("Pump Id Is Not Pressent Please Add Id First !!", "danger"));
			System.out.println("Exception :: " + e.getMessage());
		}
		model.addAttribute("title", "Master | Pumps | Manintenance Management");
		return "/pages/masters/master-pumps";
	}

	// Handler for Submitting Safety Precaution data
	@PostMapping("/savesafetyprecaus")
	public String saveSaftyPrecautionData(SaftyPrecautionsDto precautionsDto, HttpSession session) {
		this.masterService.saveSaftyPrecus(precautionsDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/saftyprecaution";
	}

	// Handler For Display Safety Precaution Data
	@GetMapping("/safetyhistory")
	public String showSafetyPrecautionHistory(Model model) {
		List<SaftyPrecautionsDto> listOfSafetyPrecus = this.masterService.findAllSaftyPrecus();
		model.addAttribute("safetyPrecaus", listOfSafetyPrecus);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Safety Precaution | Manintenance Management");
		return "/pages/masters/safty-precution";
	}

	// Handler For save SurviceArea Data
	@PostMapping("/savesericarea")
	public String saveServiceArea(ServiceAreaDto serviceAreaDto, HttpSession session) {
		this.masterService.saveSaerviceArea(serviceAreaDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/servicearea";
	}

	// Handler for Display ServiceArea History
	@GetMapping("/serviceareahistory")
	public String displayServiceAreaHiatory(Model model) {
		List<ServiceAreaDto> listOfServiceArea = this.masterService.findAllServiceArea();
		model.addAttribute("listOfServiceArea", listOfServiceArea);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Service Area | Manintenance Management");
		return "/pages/masters/service-area";
	}

	// Handler For submitting Service Progress Data
	@PostMapping("/saveServiceprogress")
	public String submitServiceProgress(ServiceProgressDto serviceProgressDto, HttpSession session) {
		this.masterService.saveServiceProgress(serviceProgressDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/serviceprogtype";
	}

	// Handler For Display Service Progress Data
	@GetMapping("/serviceProgrechistory")
	public String displayServiceProgressHistory(Model model) {
		List<ServiceProgressDto> listOfServiceProgrec = this.masterService.findAllServiceProgress();
		model.addAttribute("listOfServiceProgrss", listOfServiceProgrec);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Service Progress | Manintenance Management");
		return "/pages/masters/service-progresstype";
	}

	// Handler For Submit Service Provider Data
	@PostMapping("/saveservcprovider")
	public String submitServiceProvider(ServiceProviderDto serviceProviderDto, HttpSession session) {
		this.masterService.saveServiceProvider(serviceProviderDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/serviceprovider";
	}

	// Handler For Display Service Provider History Data
	@GetMapping("/serviceprovhistory")
	public String displayServiceProviderHistory(Model model) {
		List<ServiceProviderDto> listOfServiceProvider = this.masterService.getAllServiceProvider();
		model.addAttribute("listOfServiceProvider", listOfServiceProvider);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Service Provider | Manintenance Management");
		return "/pages/masters/service-provider";
	}

	// Hander For SaveSpareParts Or Equipments Data
	@PostMapping("/saveequipments")
	public String submitSparePartsEquipments(SpareEquipmentDto spareEquipmentDto, HttpSession session) {
		this.masterService.saveSpareEquipment(spareEquipmentDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/sparepart";
	}

	// Handler For Display SaprePaerts Or Equipment history
	@GetMapping("/partsequipmenthistory")
	public String displaySpareEquipMentHistory(Model model) {
		List<SpareEquipmentDto> listOfEquipments = this.masterService.getAllSpareEquipmens();
		model.addAttribute("listOfEquipments", listOfEquipments);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Equipments | Manintenance Management");
		return "/pages/masters/sparepart-equipment";
	}

	// Handler For Submit Store/Branch Data
	@PostMapping("/savestorebranch")
	public String saveStoreBranch(StoreBranchDto storeBranchDto, HttpSession session) {
		try {
			String idName = "Branch Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastBranchId = idMasterDto.getLastNumber();
			StringBuilder letters = new StringBuilder();
			StringBuilder numbers = new StringBuilder();
			for (int i = 0; i < lastBranchId.length(); i++) {
				char c = lastBranchId.charAt(i);
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
			String nextBranchId = lettersString + Integer.toString(number);
			idMasterDto.setLastNumber(nextBranchId);
			this.masterService.saveIdMaster(idMasterDto);
		} catch (Exception e) {
			System.out.println("something went Wrong !!" + e.getMessage());
		}
		this.masterService.saveStoreBranch(storeBranchDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/storebranch";
	}

	// Handler For Display Store/branch History
	@GetMapping("/storebranchistory")
	public String displayStoreBranchHistory(Model model, HttpSession session) {
		List<StoreBranchDto> listOfStoreBranch = this.masterService.findAllStoreBranch();
		model.addAttribute("listOfStoreBranch", listOfStoreBranch);
		model.addAttribute("show", "show");
		try {
			String masterIdName = "Branch Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(masterIdName);
			String lastBranchId = idMasterDto.getLastNumber();
			model.addAttribute("masterbranchid", lastBranchId);
		} catch (Exception e) {
			session.setAttribute("message", new Message("Branch Id Is Not Paressent Please Add Id First", "danger"));
			System.out.println("Exception :: " + e.getMessage());
		}
		model.addAttribute("title", "Master | Store/Branch | Manintenance Management");
		return "/pages/masters/store-branch";
	}

	// Handler For Save Leakage Type Data
	@PostMapping("/saveleakageType")
	public String saveLaeakageType(LeakageTypeDto leakageTypeDto, HttpSession session) {
		if (this.masterService.isLeakageTypeExists(leakageTypeDto.getLeakageType())) {
			session.setAttribute("message", new Message("Leakage Type Already Present !!", "warning"));
		} else {
			this.masterService.saveLeakageType(leakageTypeDto);
			session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		}
		return "redirect:/masters/leakageMaster";
	}

	// Handler For Display Leakage type
	@GetMapping("/leakageHistory")
	public String displayLeakageTypeHistory(Model model) {
		List<LeakageTypeDto> listOfLeakageType = this.masterService.getAllLeakageType();
		model.addAttribute("listOfLikageType", listOfLeakageType);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Leakage | Manintenance Management");
		return "/pages/masters/leakage-type";
	}

	// Handler For Save TaskStatus Data
	@PostMapping("/saveTaskStatus")
	public String submitTaskStatus(TaskStatusDto taskStatusDto, HttpSession session) {
		this.masterService.saveTaskStatus(taskStatusDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/taskstatus";
	}

	// Handler For Show Task Satus History
	@GetMapping("/taskStatusHistory")
	public String showTaskStatusHistory(Model model) {
		List<TaskStatusDto> listOfTaskStatus = this.masterService.findAllTaskStatus();
		model.addAttribute("listOfTaskStatus", listOfTaskStatus);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Task | Manintenance Management");
		return "/pages/masters/task-status";
	}

	// Handler For save Tax Master data
	@PostMapping("/savetaxmaster")
	public String submitTaxMaster(TaxMasterDto taxMasterDto, HttpSession session) {
		this.masterService.saveTaxMaster(taxMasterDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/mastertax";
	}

	// Handler For Display Tax Master history
	@GetMapping("/taxMasterHistory")
	public String showTaxMasterHistory(Model model) {
		List<TaxMasterDto> listOfTaxMaster = this.masterService.getAllTaxMaster();
		model.addAttribute("listOfTaxs", listOfTaxMaster);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Tax | Manintenance Management");
		return "/pages/masters/master-tax";
	}

	// Handler For Submit Unit Measure Data
	@PostMapping("/saveUnitMeasure")
	public String saveUnitMeasure(UnitMeasureDto unitMeasureDto, HttpSession session) {
		this.masterService.saveUnitMeasure(unitMeasureDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/unitmeasure";
	}

	// Handler for Display Unit Measure History
	@GetMapping("/unitMeasureHistory")
	public String displayUnitMeasureHistory(Model model) {
		List<UnitMeasureDto> listOfunitMeasure = this.masterService.getAllUnitMeasure();
		model.addAttribute("listOfUnitMeasure", listOfunitMeasure);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Unit | Manintenance Management");
		return "/pages/masters/unit-measure";
	}

	// Handler For submit Vehicle Master Data
	@PostMapping("/saveVehicleDtls")
	public String submitVehicleMaster(VehicleDtlsDto vehicleDtlsDto, HttpSession session) {
		try {
			String idName = "Vehicle Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastVehicleId = idMasterDto.getLastNumber();
			StringBuilder letters = new StringBuilder();
			StringBuilder numbers = new StringBuilder();
			for (int i = 0; i < lastVehicleId.length(); i++) {
				char c = lastVehicleId.charAt(i);
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
			String nextVehicleId = lettersString + Integer.toString(number);
			idMasterDto.setLastNumber(nextVehicleId);
			this.masterService.saveIdMaster(idMasterDto);
		} catch (Exception e) {
			System.out.println("something went Wrong !!" + e.getMessage());
		}
		this.masterService.saveVDtls(vehicleDtlsDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/mastervehicle";
	}

	// Handler For Display Vehicle Master History
	@GetMapping("/vehicleDtlsHistory")
	public String displayVehicleMasterHistory(Model model, HttpSession session) {
		List<VehicleDtlsDto> listOfVehicle = this.masterService.findAllVehicleDtls();
		model.addAttribute("listOfVehicle", listOfVehicle);
		model.addAttribute("show", "show");
		try {
			String idName = "Vehicle Id";
			IdMasterDto idMasterDto = this.masterService.getIdMasterByMasterIdName(idName);
			String lastVehicleId = idMasterDto.getLastNumber();
			model.addAttribute("vehicleMasterId", lastVehicleId);
		} catch (Exception e) {
			session.setAttribute("message",
					new Message("Vehicle Id Is Not Present !! Please Add Id First !!", "danger"));
			System.out.println("Exception :: " + e.getMessage());
		}
		model.addAttribute("title", "Master | Vehicle | Manintenance Management");
		return "/pages/masters/master-vehicle";
	}

	// Handler For save Work Priority Data
	@PostMapping("/saveWorkPriority")
	public String saveWorkPriority(WorkPriorityDto workPriorityDto, HttpSession session) {
		this.masterService.saveWorkPriority(workPriorityDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/workpriority";
	}

	// Display Work Priority History
	@GetMapping("/workPriorityHistory")
	public String displayWorkPriority(Model model) {
		List<WorkPriorityDto> listOfWorkPriority = this.masterService.findAllWorkPriority();
		model.addAttribute("listOfWorkPriority", listOfWorkPriority);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Work Priority | Manintenance Management");
		return "/pages/masters/work-priority";
	}

	// Handler For Save Work Status Data
	@PostMapping("/saveWorkStatus")
	public String saveWorkStatus(WorkStatusDto workStatusDto, HttpSession session) {
		this.masterService.saveWorkStatus(workStatusDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/workstatus";
	}

	// Handler For Display Work Status History
	@GetMapping("/workStatusHistory")
	public String displayWorkStatusHistory(Model model) {
		List<WorkStatusDto> listOfWorkStatus = this.masterService.getAllWorkStatus();
		model.addAttribute("listOfWorkStatus", listOfWorkStatus);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Work Status | Manintenance Management");
		return "/pages/masters/work-status";
	}

	// Handler For Save TeamCode Data
	@PostMapping("/saveTeamcode")
	public String saveTeamCode(TeamCodeDto teamCodeDto, HttpSession session) {
		this.masterService.saveTeamCode(teamCodeDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/teamcode";
	}

	// Handler For Display TeamCode History
	@GetMapping("/teamcodeHistory")
	public String showTeamCodeHistory(Model model) {
		List<TeamCodeDto> listOfTeamCode = this.masterService.getAllTeamCode();
		model.addAttribute("listOfTeamCode", listOfTeamCode);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Teamcode | Manintenance Management");
		return "/pages/masters/temcode";
	}

	// Handler For save waterSource Data
	@PostMapping("/saveWaterSource")
	public String saveWaterSource(WaterSourceDto waterSourceDto, HttpSession session) {
		this.masterService.saveWaterSource(waterSourceDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/watersouce";
	}

	// Handler For Display Water Source History
	@GetMapping("/waterSourceHistory")
	public String showWaterSourceHistory(Model model) {
		List<WaterSourceDto> listOfWaterSource = this.masterService.findAllWaterSource();
		model.addAttribute("listOfWaterSource", listOfWaterSource);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | WaterSource | Manintenance Management");
		return "/pages/masters/water-source";
	}

	// Handler For Get Id Master Data for Update
	@GetMapping("/getMasterIdData/{masterId}")
	public String getDataByMasterId(@PathVariable("masterId") Long masterId, Model model) {
		IdMasterDto idMasterDtos = this.masterService.getByMasterId(masterId);
		model.addAttribute("idMasterDto", idMasterDtos);
		return "/pages/masters/master-id-update";
	}

	// Handler For Update Master Id Data
	@PostMapping("/upadeMasterId")
	public String updateMasterIdData(@ModelAttribute IdMasterDto idMasterDto, HttpSession session) {
		this.masterService.saveIdMaster(idMasterDto);
		session.setAttribute("message", new Message("Data Updated Successfully !!", "Warning"));
		return "redirect:/masters/idmasterhistory";
	}

	// Handler For get Department Id Master Data By MasterId Name
	@ResponseBody
	@GetMapping("/getDepartmentIds/{masterIdName}")
	public ResponseEntity<List<DepartmentIdMasterDto>> getByMasterIdName(
			@PathVariable("masterIdName") String masterIdName) {
		List<DepartmentIdMasterDto> departmentIdMasterDtos = masterService.getByMasterIdName(masterIdName);
		return new ResponseEntity<List<DepartmentIdMasterDto>>(departmentIdMasterDtos, HttpStatus.OK);
	}

	// Handler For get Department Id Master Data By MasterId
	@ResponseBody
	@GetMapping("/getDeptMasterIdData/{depMasterId}")
	public ResponseEntity<DepartmentIdMasterDto> getDepartmentIdByMasterdeptId(
			@PathVariable("depMasterId") Long depMasterId) {
		DepartmentIdMasterDto departmentIdMasterDto = this.masterService.getDeptIdMasterByDepMasterId(depMasterId);
		return new ResponseEntity<DepartmentIdMasterDto>(departmentIdMasterDto, HttpStatus.OK);
	}

	// Handler For Update Department Master IdData
	@PostMapping("/updateDeptIdMaster")
	public String updateDepartmentIdMaster(@ModelAttribute DepartmentIdMasterDto deptIdMasterDto, HttpSession session) {
		this.masterService.saveDepartmentIdMaster(deptIdMasterDto);
		session.setAttribute("message", new Message("Data Updated Successfully !!", "Warning"));
		return "redirect:/masters/departmentIdHistory";
	}

	// Handler For save Designation Master Data
	@PostMapping("/saveDesignation")
	public String saveDesignationmaster(DesignationDto designationDto, HttpSession session) {
		String desc = designationDto.getDesignation();
		designationDto.setDesignation(desc.toUpperCase());
		this.masterService.saveDesignation(designationDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/designation";
	}

	// Handler For Display Designation Master History
	@GetMapping("/desigHistory")
	public String displayDesignationMasterHistory(Model model) {
		try {
			List<DesignationDto> listOfDesignation = this.masterService.getAllDesignations();
			model.addAttribute("listOfDesignation", listOfDesignation);
			model.addAttribute("show", "show");
			List<DesignationDto> designationDtos = this.masterService.getDesignationsFormUbarms();
			if (!designationDtos.isEmpty()) {
				model.addAttribute("ubmdesigList", designationDtos);
			}
		} catch (Exception e) {
			System.out.println("Designation Not Found !!" + e.getMessage());
		}

		model.addAttribute("title", "Master | Designation | Manintenance Management");
		return "/pages/masters/master-designation";
	}

	// Handler For Submit Supplier Details Data
	@PostMapping("/saveSupplier")
	public String submitSupplierDetails(SupplierDtlsDto supplierDtlsDto, HttpSession session) {
		supplierDtlsDto.setState(adminService.getState(supplierDtlsDto.getState()));
		this.masterService.saveSupplierDtls(supplierDtlsDto);
		session.setAttribute("message", new Message("Data Saved Successfully !!", "success"));
		return "redirect:/masters/supplierdetails";
	}

	// Handler For Display Supplier Details History
	@GetMapping("/supplierHistory")
	public String displaySupplierDetailshistory(Model model) {
		List<SupplierDtlsDto> listOfSupplierDtls = this.masterService.getAllSupplierDtls();
		model.addAttribute("listOfSupplierDtls", listOfSupplierDtls);
		model.addAttribute("show", "show");
		model.addAttribute("states", adminService.getAllStates());
		model.addAttribute("title", "Master | Supplier | Manintenance Management");
		return "/pages/masters/supplier-details";
	}

	// Handler For Save Brand Master Data
	@PostMapping("/saveBrand-master")
	public String saveBrandMaster(BrandMasterDto brandMasterDto, HttpSession session) {
		this.masterService.isBrandNameExists(brandMasterDto.getBrandName());
		if (!this.masterService.isBrandNameExists(brandMasterDto.getBrandName())) {
			this.masterService.saveBrandMaster(brandMasterDto);
			session.setAttribute("message", new Message("Data Saved Successfully !! ", "success"));
		} else {
			session.setAttribute("message", new Message("Brand Name Alrady Exist !! ", "warning"));
			return "redirect:/masters/brand-master";
		}

		return "redirect:/masters/brand-master";
	}

	// Handler For Display Brand Master History
	@GetMapping("/brandMaster-history")
	public String displayBrandMasterHistory(Model model) {
		List<BrandMasterDto> listOfBrandMaster = this.masterService.getAllBrandMasters();
		model.addAttribute("listOfBrands", listOfBrandMaster);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Brand | Manintenance Management");
		return "/pages/masters/master-brand";
	}

	// Delete Department Master
	@RequestMapping("/deleteDeptMaster/{depMasterId}")
	public String deleteDepartmentMaster(@PathVariable Long depMasterId) {
		this.masterService.deleteDepartmentMaster(depMasterId);
		return "redirect:/masters/deparmentHistory";
	}

	// Delete Category By CategoryId
	@RequestMapping("/deleteCategory/{catid}")
	public String deleteCategory(@PathVariable("catid") Long catid, HttpSession session) {
		try {
			this.masterService.deleteCategory(catid);
		} catch (Exception e) {
			session.setAttribute("message", new Message(
					"Category Having Hsn Code !! You Can't Delete !! If You Want To Remove 1St Delete HsnCode !!",
					"danger"));
			System.out.println("You Can Not Delete !!" + e.getMessage());
		}
		return "redirect:/masters/categoryhistory";
	}

	// Delete Brand Master
	@RequestMapping("/deleteBrand/{brandMasterId}")
	public String deleteBrandMaster(@PathVariable Long brandMasterId) {
		this.masterService.deleteBrandMaster(brandMasterId);
		return "redirect:/masters/brandMaster-history";
	}

	// Delete Distribution Location
	@RequestMapping("/delteDistLocation/{disLocId}")
	public String deleteDistributionLocation(@PathVariable Long disLocId) {
		this.masterService.deleteDistributeLocation(disLocId);
		return "redirect:/masters/dislocationhistory";
	}

	// Delete Distribution Schedule
	@RequestMapping("/deleteDistSchedule/{disScheduleId}")
	public String deleteDistributionSchedule(@PathVariable Long disScheduleId) {
		this.masterService.deleteDistrbSchedule(disScheduleId);
		return "redirect:/masters/disschedulehistory";
	}

	// Delete Division SubDivision
	@RequestMapping("/deleteDivSubdiv/{divsubId}")
	public String deleteDivisionSubdivision(@PathVariable Long divsubId) {
		this.masterService.deleteDivSubDiv(divsubId);
		return "redirect:/masters/divsubdivhistory";
	}

	// Delete Dma Ward
	@RequestMapping("/deleteDmaWard/{dmaWardId}")
	public String deleteDmaWard(@PathVariable Long dmaWardId) {
		this.masterService.deleteDmaWard(dmaWardId);
		return "redirect:/masters/dmawardhistory";
	}

	// Delete Employee Performance
	@RequestMapping("/deleteEmpPerform/{empPerformId}")
	public String deleteEmployeePerformance(@PathVariable Long empPerformId) {
		this.masterService.deleteEmpPerformance(empPerformId);
		return "redirect:/masters/empperformhistory";
	}

	// Delete HsnCode Master Data
	@RequestMapping("/deleteHnsCode/{hsnCodeId}")
	public String deleteHsnCode(@PathVariable Long hsnCodeId) {
		this.masterService.deleteHsnCode(hsnCodeId);
		return "redirect:/masters/hsncodehistory";
	}

	// Delete Maintains Frequency
	@RequestMapping("/deleteMaintenFrequency/{maintanFrequId}")
	public String deleteMaintainsFrequency(@PathVariable Long maintanFrequId) {
		this.masterService.deleteMaintenFrequency(maintanFrequId);
		return "redirect:/masters/maintanfreqhistory";
	}

	// Delete Maintains Activities
	@RequestMapping("/deleteMaintenActive/{maintenActiveId}")
	public String deleteMaintainsActivity(@PathVariable Long maintenActiveId) {
		this.masterService.deleteMaintenActivity(maintenActiveId);
		return "redirect:/masters/maintenactivehistory";
	}

	// Delete maintains Performance
	@RequestMapping("/deleteMaintenPerform/{maintenPerformId}")
	public String deleteMaintainsPerformance(@PathVariable Long maintenPerformId) {
		this.masterService.deleteMaintainsPerformance(maintenPerformId);
		return "redirect:/masters/maintenperformhistory";
	}

	// Delete Maintains Type
	@RequestMapping("/deleteMaintenType/{maintenTypeId}")
	public String deleteMaintainsType(@PathVariable Long maintenTypeId) {
		this.masterService.deleteMaintainsType(maintenTypeId);
		return "redirect:/masters/maintentypehistory";
	}

	// Delete Item Master
	@RequestMapping("/deleteMasterItem/{itemMasterId}")
	public String deleteItemMaster(@PathVariable Long itemMasterId) {
		this.masterService.deleteMasterItem(itemMasterId);
		return "redirect:/masters/itemhistory";
	}

	// Delete MeterManufacture
	@RequestMapping("/deleteMeterManufact/{mtrmanufacId}")
	public String deleteMeterManufacture(@PathVariable Long mtrmanufacId) {
		this.masterService.deleteMeterManufacture(mtrmanufacId);
		return "redirect:/masters/metermanufacthistory";
	}

	// Delete MeterType
	@RequestMapping("/deleteMeterType/{meterTypeId}")
	public String deleteMeterType(@PathVariable Long meterTypeId) {
		this.masterService.deleteMeterType(meterTypeId);
		return "redirect:/masters/metertypehistory";
	}

	// Delete Pipe Manufacture
	@RequestMapping("/deletePipeManufact/{pipemanufId}")
	public String deletePipeManufacture(@PathVariable Long pipemanufId) {
		this.masterService.deletePipeManufacture(pipemanufId);
		return "redirect:/masters/pipemanufacthistory";
	}

	// Delete Pressure Type
	@RequestMapping("/deletePressureType/{pressureId}")
	public String deletePressureType(@PathVariable Long pressureId) {
		this.masterService.deletePressureType(pressureId);
		return "redirect:/masters/pressurehistory";
	}

	// Delete PumpMaster
	@RequestMapping("/deletePumpMaster/{pumpMasterId}")
	public String deletePumpMaster(@PathVariable Long pumpMasterId) {
		this.masterService.deletePumpMaster(pumpMasterId);
		return "redirect:/masters/pumpshistory";
	}

	// Delete Safety Precautions
	@RequestMapping("/deleteSafetyPrecous/{saftyprecId}")
	public String deleteSafetyPrecautions(@PathVariable Long saftyprecId) {
		this.masterService.deleteSaftyPrecason(saftyprecId);
		return "redirect:/masters/safetyhistory";
	}

	// Delete Service Area
	@RequestMapping("/deleteServiceArea/{sericAreaId}")
	public String deleteServiceArea(@PathVariable Long sericAreaId) {
		this.masterService.deleteServiceArea(sericAreaId);
		return "redirect:/masters/serviceareahistory";
	}

	// Delete Service Progress Type
	@RequestMapping("/deleteServiceProgress/{servcProgressId}")
	public String deleteServiceProgressType(@PathVariable Long servcProgressId) {
		this.masterService.deleteServiceProgressType(servcProgressId);
		return "redirect:/masters/serviceProgrechistory";
	}

	// Delete Service Provider
	@RequestMapping("/deleteServiceProvider/{servProvId}")
	public String deleteServiceProvider(@PathVariable Long servProvId) {
		this.masterService.deleteServiceProvider(servProvId);
		return "redirect:/masters/serviceprovhistory";
	}

	// Delete Spare Parts And Equipments
	@RequestMapping("/deleteSpareEquipment/{spareequiId}")
	public String deleteSparePartsEqupment(@PathVariable Long spareequiId) {
		this.masterService.deleteSpareEquipment(spareequiId);
		return "redirect:/masters/partsequipmenthistory";
	}

	// Delete Store Branch Master
	@RequestMapping("/deleteStoreBranch/{strbraNameId}")
	public String deleteStoreBranch(@PathVariable Long strbraNameId) {
		this.masterService.deleteStoreBranch(strbraNameId);
		return "redirect:/masters/storebranchistory";
	}

	// Delete Supplier Details
	@RequestMapping("/deleteSupplierDtls/{suplyDtlsId}")
	public String deleteSupplierDetails(@PathVariable Long suplyDtlsId) {
		this.masterService.deleteSupplierDetails(suplyDtlsId);
		return "redirect:/masters/supplierHistory";
	}

	// Delete Designation Master
	@RequestMapping("/deleteDesignation/{desigId}")
	public String deleteDesignationMaster(@PathVariable Long desigId) {
		this.masterService.deleteDesignations(desigId);
		return "redirect:/masters/desigHistory";
	}

	// Delete Leakage Type
	@RequestMapping("/deleteLeakageType/{leakageId}")
	public String deleteLeakageType(@PathVariable Long leakageId) {
		this.masterService.deleteLeakageType(leakageId);
		return "redirect:/masters/leakageHistory";
	}

	// Delete Task Status
	@RequestMapping("/deleteTaskStatus/{taskstsId}")
	public String deleteTaskStatus(@PathVariable Long taskstsId) {
		this.masterService.deleteTaskStatus(taskstsId);
		return "redirect:/masters/taskStatusHistory";
	}

	// Delete Tax Master
	@RequestMapping("/deleteTaxmaster/{taxMasterId}")
	public String deleteTaxMaster(@PathVariable Long taxMasterId) {
		this.masterService.deleteTaxMaster(taxMasterId);
		return "redirect:/masters/taxMasterHistory";
	}

	// Delete Unit Of Measure
	@RequestMapping("/deleteUnitMeasure/{unitMeasureId}")
	public String deleteUnitOfMeasure(@PathVariable Long unitMeasureId) {
		this.masterService.deleteUnitOfMeasure(unitMeasureId);
		return "redirect:/masters/unitMeasureHistory";
	}

	// Delete Vehicle Details
	@RequestMapping("/deleteVehicleDtls/{vehicleDtlsId}")
	public String deleteVehicleDetails(@PathVariable Long vehicleDtlsId) {
		this.masterService.deleteVehicleDetails(vehicleDtlsId);
		return "redirect:/masters/vehicleDtlsHistory";
	}

	// Delete Work Priority
	@RequestMapping("/deleteWorkPriority/{workPrioId}")
	public String deleteWorkPriority(@PathVariable Long workPrioId) {
		this.masterService.deleteWorkPriority(workPrioId);
		return "redirect:/masters/workPriorityHistory";
	}

	// Delete Work Status
	@RequestMapping("/deleteWorkStatus/{workStsId}")
	public String deleteWorkStatus(@PathVariable Long workStsId) {
		this.masterService.deleteWorkStatus(workStsId);
		return "redirect:/masters/workStatusHistory";
	}

	// Delete Team Code
	@RequestMapping("/deleteTeamCode/{teamCodeId}")
	public String deleteTeamCode(@PathVariable Long teamCodeId) {
		this.masterService.deleteTeamcode(teamCodeId);
		return "redirect:/masters/teamcodeHistory";
	}

	// Delete Water Source
	@RequestMapping("/deleteWaterSource/{wateSourceId}")
	public String deleteWaterSource(@PathVariable Long wateSourceId) {
		this.masterService.deleteWaterCource(wateSourceId);
		return "redirect:/masters/waterSourceHistory";
	}

	// Get SubDivision By Divisions
	@ResponseBody
	@GetMapping("/get/subdivision/{division}")
	public ResponseEntity<List<DivisionSubdivisionDto>> getSubDivByDivision(@PathVariable String division) {
		List<DivisionSubdivisionDto> divSubDivDtos = this.masterService.getSubDivisionListByDivision(division);
		return new ResponseEntity<List<DivisionSubdivisionDto>>(divSubDivDtos, HttpStatus.OK);
	}

	// Get Distribution Location By Sub-Divisions
	@ResponseBody
	@GetMapping("/get/distribution/location/{subDivision}")
	public ResponseEntity<List<DistributionLocationDto>> getDistributionLocationBySubDivision(
			@PathVariable String subDivision) {
		List<DistributionLocationDto> distLocation = this.masterService.getDistributLocationBysubDivision(subDivision);
		return new ResponseEntity<List<DistributionLocationDto>>(distLocation, HttpStatus.OK);
	}

	// Get HsnCode By Category
	@ResponseBody
	@GetMapping("/get/hsnCode/{categoryId}")
	public ResponseEntity<HsnCodeDto> getHsnCodeByCategory(@PathVariable Long categoryId) {
		HsnCodeDto hsnCodeDto = masterService.getHsnCodeDtoByCategory(categoryId);
		return new ResponseEntity<HsnCodeDto>(hsnCodeDto, HttpStatus.OK);
	}

	// Get Item List By Category Name
	@ResponseBody
	@GetMapping("/get/items/{categoryId}")
	public ResponseEntity<List<ItemMasterDto>> getItemsByCategory(@PathVariable Long categoryId) {
		List<ItemMasterDto> itemMasterDtos = this.masterService.getItemListByCategoryId(categoryId);
		return new ResponseEntity<List<ItemMasterDto>>(itemMasterDtos, HttpStatus.OK);
	}

	// Get Item List By Category Name
	@ResponseBody
	@GetMapping("/get/stocktype/{itemId}")
	public ResponseEntity<ItemMasterDto> getItemsByItemId(@PathVariable Long itemId) {
		ItemMasterDto itemMasterDto = this.masterService.getItemByItemId(itemId);
		return new ResponseEntity<ItemMasterDto>(itemMasterDto, HttpStatus.OK);
	}

	// Get List Of VehicleDtls By VehicleType
	@ResponseBody
	@GetMapping("/get/vehicleNo/{vehicleType}")
	public ResponseEntity<List<VehicleDtlsDto>> getListOfVehicleDtlsByVehicleType(@PathVariable String vehicleType) {
		List<VehicleDtlsDto> vehicleDtlsDtos = this.masterService.getVehiclesByVehicleType(vehicleType);
		return new ResponseEntity<List<VehicleDtlsDto>>(vehicleDtlsDtos, HttpStatus.OK);
	}

	// Get Driver Details By VehicleId
	@ResponseBody
	@GetMapping("get/vehicle/driver/{vehicleId}")
	public ResponseEntity<VehicleDtlsDto> getDriverDetailsByVehicleId(@PathVariable Long vehicleId) {
		VehicleDtlsDto vehicleDtlsDto = this.masterService.getVehicleDtlsByVehicleDtlsId(vehicleId);
		return new ResponseEntity<VehicleDtlsDto>(vehicleDtlsDto, HttpStatus.OK);
	}

	// Open Master Employee Category
	@GetMapping("/empcategory")
	public String openMasterEmplyeeCategory(Model model) {
		model.addAttribute("show", null);
		List<DepartmentDto> departmentDtos = this.masterService.findAllDepartment();
		model.addAttribute("allDepartment", departmentDtos);
		model.addAttribute("title", "Master | Employee | Manintenance Management");
		return "/pages/masters/master-employee-category";
	}

	// Save Master Employee Category
	@PostMapping("/save/empcategory")
	public String saveEmployeeCategory(EmployeeCategoryDto employeeCategoryDto, HttpSession session) {
		this.masterService.saveEmployeeMaster(employeeCategoryDto);
		session.setAttribute("message", new Message("Data Successfully Saved !!", "success"));
		return "redirect:/masters/empcategory";
	}

	// Get all Employee Master Category
	@GetMapping("/empcategory/history")
	public String displayAllEmployeeCategory(Model model) {
		List<EmployeeCategoryDto> employeeCategoryDtos = this.masterService.getAllEmployeeCategory();
		List<DepartmentDto> departmentDtos = this.masterService.findAllDepartment();
		model.addAttribute("allDepartment", departmentDtos);
		model.addAttribute("listOfEmpCategory", employeeCategoryDtos);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Master | Employee | Manintenance Management");
		return "/pages/masters/master-employee-category";
	}

	// Delete Employee Master Category By EmployeeMasterId
	@RequestMapping("/deleteEmpCategory/{empCategoryId}")
	public String deleteEmployeeMasterCategory(@PathVariable Long empCategoryId) {
		this.masterService.deleteEmployeeCategory(empCategoryId);
		return "redirect:/masters/empcategory/history";
	}
}
