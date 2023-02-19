package com.ingroinfo.mm.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.configuration.ModelMapperConfig;
import com.ingroinfo.mm.dao.EmployeeLeaveRepository;
import com.ingroinfo.mm.dao.EmployeeMasterRepository;
import com.ingroinfo.mm.dto.EmployeeLeaveDto;
import com.ingroinfo.mm.dto.EmployeeMasterDto;
import com.ingroinfo.mm.dto.EmployeeSalaryDto;
import com.ingroinfo.mm.dto.IdMasterDto;
import com.ingroinfo.mm.entity.EmployeeInspection;
import com.ingroinfo.mm.entity.EmployeeLeave;
import com.ingroinfo.mm.entity.EmployeeMaster;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.DepartmentService;
import com.ingroinfo.mm.service.EmployeeInspectService;
import com.ingroinfo.mm.service.EmployeeMasterService;
import com.ingroinfo.mm.service.EmployeeSalaryService;
import com.ingroinfo.mm.service.IdMasterService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeMasterService employeeMasterService;
	@Autowired
	private EmployeeInspectService employeeInspectService;
	@Autowired
	private EmployeeMasterRepository employeeMasterRepository;
	@Autowired
	private EmployeeLeaveRepository employeeLeaveRepository;
	@Autowired
	private EmployeeSalaryService employeeSalaryService;
	@Autowired
	private AdminService adminService;
	@Autowired
	public ModelMapperConfig mapper;
	@Autowired
	private DepartmentService departService;
	@Autowired
	private IdMasterService idMasterService;

	@GetMapping("/dashboard")
	public String employeeDashboard() {
		return "/pages/employee_management/employee_dashboard";
	}

	@GetMapping("/master")
	public String show(Model model, HttpSession session) {

		model.addAttribute("show", null);
		model.addAttribute("title", "Employee | EmployeeMaster | Manintenance Management");
		try {
			String maxEmpCode = this.employeeMasterRepository.getMaxEmployeeCode();
			if (maxEmpCode != null) {
				int nextempId = Integer.parseInt(maxEmpCode) + 1;
				model.addAttribute("empId", nextempId + "");
			} else {
				String idName = "Employee Id";
				IdMasterDto idMasterDto = this.idMasterService.getByMasterIdName(idName);
				String employeeId = idMasterDto.getStatNumber();
				model.addAttribute("empId", employeeId);
			}
		} catch (Exception e) {
			session.setAttribute("message", new Message("Employee Id Is Not Pressent Please Add Id First !!", "info"));
			System.out.println("Exception :: " + e.getMessage());
		}
		model.addAttribute("DeptList", departService.findAllDepartment());
		model.addAttribute("employee", new EmployeeMasterDto());
		model.addAttribute("employees", employeeMasterService.getAllemployeeMaster());
		model.addAttribute("states", adminService.getAllStates());
		model.addAttribute("banks",adminService.getAllBanks());
		return "/pages/employee_management/employee_master";
	}

	@GetMapping("/management")
	public String showemployee(Model model) {
		model.addAttribute("employees", employeeMasterService.getAllemployeeMaster());
		model.addAttribute("update", new EmployeeMasterDto());
		return "/pages/employee_management/management";
	}
	
	

	@GetMapping("/attendenceTracker")
	public String attendence(Model model) {
		List<EmployeeMasterDto> listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);
		return "/pages/employee_management/attendence_tracker";
	}

	@GetMapping("/salary-generate")
	public String salaryGenerate(Model model) {
		model.addAttribute("deptList", departService.findAllDepartment());
		return "/pages/employee_management/salary_generate";
	}

	@GetMapping("/salaryManagement")
	public String showEmpSalary(Model model) {
		model.addAttribute("employee",employeeSalaryService.getAllemployeeSalary());
		return "/pages/employee_management/salary_management";
		
	}
	@GetMapping("/inspection")
	public String employeeInspection(Model model) {
		List<EmployeeMasterDto> listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);
		model.addAttribute("employeeData", new EmployeeMasterDto());
		return "/pages/employee_management/employee_Inspection";
	}

	@GetMapping("/leave")
	public String leave(Model model) {
		List<EmployeeMasterDto> listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);
		return "/pages/employee_management/leave";
	}

	

	// Submitting Employee Master Data
	@PostMapping("/saveEmployee")
	public String employeeCreate(@RequestParam("employeeImage") MultipartFile file, Model model,
			@ModelAttribute("employee") EmployeeMasterDto employeeMasterDto, BindingResult bindingResult,
			HttpSession session) throws IOException {
		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		String fileName = employeeMasterDto.getEmpName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "."
				+ tokens.get();
		String uploadDir = "C:/Company/" + employeeMasterDto.getDepartment() + "/Employees/";
		employeeMasterDto.setEmployeeImage(fileName);
		adminService.saveFile(uploadDir, fileName, file);
		employeeMasterDto.setState(adminService.getState(employeeMasterDto.getState()));
		this.employeeMasterService.saveEmployeeMaster(employeeMasterDto);
		session.setAttribute("message", new Message("Employee Master Data Successfully Saved !!", "success"));
		return "redirect:/employee/master";
	}

	// Updating Employee Master Data
	@PostMapping("/update")
	public String update(@ModelAttribute("update") EmployeeMasterDto employeemasterDto, Model model, HttpSession session) {
		EmployeeMaster employee = employeeMasterRepository.findByEmployeeId(employeemasterDto.getEmployeeId());
		mapper.modelMapper().map(employeemasterDto, employee);
		employeeMasterService.saveEmployee(employee);
		session.setAttribute("message", new Message("Employee Master Data Successfully Updated !!", "success"));
		return "redirect:/employee/management";
	}

	// Showing Employee Data By ID
	@GetMapping("/getEmployeeData/{id}")
	public String getEmployeeData(@PathVariable(value = "id") Long id, Model model) {
		List<EmployeeMasterDto> listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		EmployeeMaster employee = employeeMasterService.getEmployeeById(id);
		model.addAttribute("employeeData", employee);
		model.addAttribute("listOfEmployees", listOfEmployees);
		return "/pages/employee_management/employee_inspection";
	}

	// Showing Employee Data By EmpCode
	@GetMapping("/getEmp/{employeeCode}")
	public ResponseEntity<EmployeeMasterDto> getEmplyeeMaster(@PathVariable String employeeCode) {
		EmployeeMasterDto employeeMasterDto = this.employeeMasterService.getEmployeeByEmpCode(employeeCode);
		return new ResponseEntity<EmployeeMasterDto>(employeeMasterDto, HttpStatus.OK);
	}

	// Showing Employee Leave Data By EmpCode
	@GetMapping("/getleaveData/{employeeCode}")
	public ResponseEntity<EmployeeLeaveDto> getEmployeeLeave(@PathVariable("employeeCode") String employeecode) {
		EmployeeLeaveDto employeeLeaveDto = this.employeeMasterService.getEmpLeaveByEmpCode(employeecode);
		return new ResponseEntity<EmployeeLeaveDto>(employeeLeaveDto, HttpStatus.OK);
	}
	

	// Showing Employee Data By ID
	@RequestMapping("/getEmpData/{employeeId}")
	@ResponseBody
	public EmployeeMaster showDetails(@PathVariable("employeeId") Long employeeId) {
		EmployeeMaster employee = employeeMasterService.getEmployeeById(employeeId);
		return employee;
	}

	// Showing Employee Data By ID
	@RequestMapping("/getEmpLeaveData/{empLeaveId}")
	@ResponseBody
	public EmployeeLeave showEmployeeLeave(@PathVariable("empLeaveId") Long empLeaveId) {
		EmployeeLeave employeeLeave = employeeMasterService.getEmployeeLeaveById(empLeaveId);
		return employeeLeave;
	}

	// Delete Employee Data By ID
	@GetMapping("/delete/{cid}")
	public String deleteEmployee(@PathVariable(value = "cid") Long id, HttpSession session) {
		this.employeeMasterService.deleteEmployeeById(id);
		session.setAttribute("message", new Message("Employee Master Data Successfully Deleted !!", "danger"));
		return "redirect:/employee/management";
	}

	// Submitting Employee Inspection Data
	@PostMapping("/saveEmpInspect")
	public String saveEmpInspect(@ModelAttribute("inspect") EmployeeInspection employeeInspect, HttpSession session) {
		if(employeeInspect.getEmpCode() != "") {
			employeeInspectService.saveInspect(employeeInspect);
			session.setAttribute("message", new Message("Employee Inspection Data Successfully Saved !!", "success"));
		}else {
		session.setAttribute("message", new Message("Click On The Employee Id ", "warning"));
		}
		return "redirect:/employee/inspection";
	}

	@GetMapping("/attendencehistory")
	public String attendencehistory(Model model) {
		List<EmployeeMasterDto> listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);
		model.addAttribute("show", "show");
		return "/pages/employee_management/attendence_tracker";
	}

	// Submitting Employee Leave Data
	@PostMapping("/saveEmpLeave")
	public String saveEmployeeLeave(EmployeeLeaveDto empLeaveDto, HttpSession session) {
		if (empLeaveDto.getDeptHead().equals("Yes")) {
			empLeaveDto.setHrApproval("NO");
			empLeaveDto.setSacnSickLeave(0);
			empLeaveDto.setSacnCausalLeave(0);
			empLeaveDto.setSacnLwp(0);
			this.employeeMasterService.saveEmployeeLeave(empLeaveDto);
			session.setAttribute("message", new Message("Employee Leave Data Successfully Saved !!", "success"));
		} else {
			session.setAttribute("message", new Message("Dept Head Not permision for Leave !!", "danger"));
		}
		return "redirect:/employee/leave";
	}


	@PostMapping("/saveEmpSalary")
	public String saveEmployeeSalary(EmployeeSalaryDto employeeSalaryDto,HttpSession session) {
		if(employeeSalaryDto.getEmployeeId() != "null") {
		this.employeeSalaryService.saveEmployeeSalary(employeeSalaryDto);
		session.setAttribute("message", new Message("Employee Salary Data Successfully Saved !!", "success"));
	}
	else {
		session.setAttribute("message", new Message("Employee Salary Data Successfully nit saved !!", "warrnings"));
	}
		return "redirect:/employee/salary-generate";
		
	}
	// Save Approval leave Data
	@PostMapping("/saveApprvlLeave")
	public String savedApprvlLeave(@ModelAttribute("leaveUpdate") EmployeeLeaveDto employeeLeaveDto,
			HttpSession session) {
		if(employeeLeaveDto.getEmployeeCode() != "") {
		EmployeeLeave employeeLeave = employeeLeaveRepository.findByEmpLeaveId(employeeLeaveDto.getEmpLeaveId());
		mapper.modelMapper().map(employeeLeaveDto, employeeLeave);
	
		if (employeeLeaveDto.getHrApproval().equals("YES")) {
			if (employeeLeaveDto.getLeaveType().equals("SL")) {
				// find previous sl data
				// add previous sl data + present Data
				// set the data to dto and store

				int sl = employeeLeaveRepository.getSenctionLeaveByLeaveType(employeeLeave.getEmployeeCode());
				int sl1 = employeeLeave.getNoOfLeave();

				employeeLeave.setSacnSickLeave(sl + sl1);
			} else if (employeeLeaveDto.getLeaveType().equals("CL")) {

				int sl = employeeLeaveRepository.getSancationCLByLeaveType(employeeLeave.getEmployeeCode());
				int sl1 = employeeLeave.getNoOfLeave();
				employeeLeave.setSacnCausalLeave(sl + sl1);

			} else if (employeeLeaveDto.getLeaveType().equals("LWP")) {
				int sl = employeeLeaveRepository.getSancationLwpByLeaveType(employeeLeave.getEmployeeCode());
				int sl1 = employeeLeave.getNoOfLeave();
				employeeLeave.setSacnLwp(sl + sl1);
			}

			employeeMasterService.saveEmployeeLeave(employeeLeave);
			session.setAttribute("message", new Message("Employee Leave Data Successfully Saved !!", "success"));
		} else {
			session.setAttribute("message", new Message("HR Approval Not permision for Leave !!", "danger"));
		}
		}
		else {
			session.setAttribute("message", new Message("Click On The Employee Id ", "warning"));
		}

		return "redirect:/approval";
	}

	@GetMapping("/apprvlLeave/{empLeaveId}")
	public String ShowApproval(@PathVariable long empLeaveId, Model model) {

		/*
		 * String keyword = "NO"; List<EmployeeLeaveDto> listOfEmployees =
		 * this.employeeMasterService.getEmployeeLeaveByHrApproval(keyword);
		 * model.addAttribute("listOfEmployees", listOfEmployees);
		 */

		EmployeeLeave empLeaveDto = this.employeeMasterService.getEmployeeLeaveById(empLeaveId);
		model.addAttribute("leaveData", empLeaveDto);

		int sanSL = employeeLeaveRepository.getSenctionLeaveByLeaveType(empLeaveDto.getEmployeeCode());
		empLeaveDto.setSacnSickLeave(sanSL);

		int sanCL = employeeLeaveRepository.getSancationCLByLeaveType(empLeaveDto.getEmployeeCode());
		empLeaveDto.setSacnCausalLeave(sanCL);

		int sanLwp = employeeLeaveRepository.getSancationLwpByLeaveType(empLeaveDto.getEmployeeCode());
		empLeaveDto.setSacnLwp(sanLwp);

		String employeeFather = empLeaveDto.getEmployeeMaster().getFatherName();
		String empDesignation = empLeaveDto.getEmployeeMaster().getDesignation();
		String empHouseNo = empLeaveDto.getEmployeeMaster().getHouseNo();
		String empdateofJoin = empLeaveDto.getEmployeeMaster().getDateOfJoin();
		String address = empLeaveDto.getEmployeeMaster().getAddress();
		String refConNo = empLeaveDto.getEmployeeMaster().getRefContactNo();
		String conNo = empLeaveDto.getEmployeeMaster().getContactNo();
		model.addAttribute("empFather", employeeFather);
		model.addAttribute("empdesign", empDesignation);
		model.addAttribute("empHouseNo", empHouseNo);
		model.addAttribute("empdateofJoin", empdateofJoin);
		model.addAttribute("empAddress", address);
		model.addAttribute("refConNo", refConNo);
		model.addAttribute("conNo", conNo);
		return "/pages/hr_approvals";
	}

	@GetMapping("/total-leaves/{empCode}")
	public @ResponseBody EmployeeLeaveDto getTotalLeaves(@PathVariable("empCode") String empCode, Model model) {

		LocalDate currentDate = LocalDate.now();
		int month = currentDate.getMonthValue();

		EmployeeLeaveDto e = new EmployeeLeaveDto();

		List<EmployeeLeave> employeeLeaves = employeeLeaveRepository.findByEmployeeCode(empCode);
		
		if (employeeLeaves.size() != 0) {

			List<EmployeeLeave> employeeLeaves2 = employeeLeaves.stream().filter(d -> {
				Calendar cal = Calendar.getInstance();
				cal.setTime(d.getLastUpdateDate());
				int leaveMonth = cal.get(Calendar.MONTH) + 1;
				//int days = cal.get(Calendar.DAY_OF_MONTH);
				return leaveMonth == month;
			}).collect(Collectors.toList());

			// maxSanSickLeave
			OptionalInt maxSanSL = employeeLeaves2.stream().mapToInt(d -> d.getSacnSickLeave()).max();
			int maxSanSickLeave = maxSanSL.getAsInt();
			// maxSanCausalLeave
			OptionalInt maxSanCL = employeeLeaves2.stream().mapToInt(d -> d.getSacnCausalLeave()).max();
			int maxSanCausalLeave = maxSanCL.getAsInt();
			// maxSanLwpLeave
			OptionalInt maxSanLwp = employeeLeaves2.stream().mapToInt(d -> d.getSacnLwp()).max();
			int maxSanLwpLeave = maxSanLwp.getAsInt();

			e.setSacnSickLeave(maxSanSickLeave);
			e.setSacnCausalLeave(maxSanCausalLeave);
			e.setSacnLwp(maxSanLwpLeave);
		}else {
		e.setSacnSickLeave(0);
		e.setSacnCausalLeave(0);
		e.setSacnLwp(0);
		}
		return e;
	}

	@ResponseBody
	@GetMapping("/getEmployeeCodeByDept/{department}")
	public ResponseEntity<List<EmployeeMasterDto>> getEmployeeCodeByDept(
			@PathVariable("department") String department) {

		List<EmployeeMasterDto> employeeMasterDto = employeeMasterService.getEmployeeCodeByDept(department);
		return new ResponseEntity<List<EmployeeMasterDto>>(employeeMasterDto, HttpStatus.OK);

	}
	
	

}
