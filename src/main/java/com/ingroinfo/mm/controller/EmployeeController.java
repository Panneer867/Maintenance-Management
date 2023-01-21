package com.ingroinfo.mm.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import com.ingroinfo.mm.configuration.ModelMapperConfig;
import com.ingroinfo.mm.dao.EmployeeMasterRepository;
import com.ingroinfo.mm.dto.EmployeeMasterDto;
import com.ingroinfo.mm.dto.EmployeePaymentDto;
import com.ingroinfo.mm.entity.EmployeeInspection;
import com.ingroinfo.mm.entity.EmployeeMaster;
import com.ingroinfo.mm.entity.Leave;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.EmployeeInspectService;
import com.ingroinfo.mm.service.EmployeeMasterService;
import com.ingroinfo.mm.service.LeaveService;


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
	private LeaveService leaveService;
	
	@Autowired
	public ModelMapperConfig mapper;
	
	

	@GetMapping("/master")
	public String show(Model model) {
		model.addAttribute("employee", new EmployeeMasterDto());
		model.addAttribute("employees", employeeMasterService.getAllemployeeMaster());
		return "/pages/employee_management/employee_master";
		
	}
	
	@PostMapping("/saveEmployee")
	public String employeeCreate(Model model, EmployeeMasterDto employeeMasterDto) {
	    this.employeeMasterService.saveEmployeeMaster(employeeMasterDto);
		return  "redirect:/employee/master?success";		
	}
	
	@GetMapping("/management")
	public String showemployee (Model model) {
		model.addAttribute("employees", employeeMasterService.getAllemployeeMaster());
		model.addAttribute("update", new EmployeeMasterDto());
		return "/pages/employee_management/management";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute("update") EmployeeMasterDto employeemasterDto, Model model) {
		
		EmployeeMaster employee = employeeMasterRepository.findByEmployeeId(employeemasterDto.getEmployeeId());
		
		mapper.modelMapper().map(employeemasterDto, employee);
	
		employeeMasterService.saveEmployeeMaster(employee);
		return "redirect:/employee/management?success";
	
	
	}
	

	@GetMapping("/inspection")
	public String employeeInspection(Model model) {
		List<EmployeeMasterDto> listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);
		 
		return "/pages/employee_management/employee_Inspection";
	}

	@GetMapping("/getEmployeeData/{id}")
	public String getEmployeeData(@PathVariable(value = "id") Long id, Model model) {
		
		List<EmployeeMasterDto> listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		EmployeeMaster employee = employeeMasterService.getEmployeeById(id);


		model.addAttribute("listOfEmployees", employee);
		model.addAttribute("listEmployee", listOfEmployees);
		return "/pages/employee_management/inspection_employee";
		
	}
	
	@PostMapping("/saveEmpInspect")
	public String saveEmpInspect(Model model, @ModelAttribute("employeeInspect") EmployeeInspection employeeInspect,BindingResult bindingResult) {
		
		employeeInspectService.saveInspect(employeeInspect);
		return "redirect:/employee/inspection?success";
		
	}
	
	@GetMapping("/dashboard")
	public String employeeDashboard() {
		return "/pages/employee_management/employee_dashboard";
	}

	@GetMapping("/salary-generate")
	public String salaryGenerate(Model model) {
		List<EmployeeMasterDto> listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);
		return "/pages/employee_management/salary_generate";
	}



	@GetMapping("/attendence")
	public String attendence(Model model) {
		List<EmployeeMasterDto>  listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);
		return "/pages/employee_management/attendence_tracker";
	}

	@GetMapping("/payment")
	public String payment(Model model) {
		List<EmployeeMasterDto>  listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);	
		model.addAttribute("title", "Employee Management | Payment | Maintenance Management ");
		return "/pages/employee_management/payment";
	}

	@GetMapping("/attendencehistory")
	public String attendencehistory(Model model) {
		List<EmployeeMasterDto>  listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);
		model.addAttribute("show", "show");
		return "/pages/employee_management/attendence_tracker";

	}
	
	@GetMapping("/delete/{cid}")
	public String deleteEmployee(@PathVariable(value = "cid") Long id) {
		
		this.employeeMasterService.deleteEmployeeById(id);
		return 	"redirect:/employee/management?delete";
		
	}
	
	@GetMapping("/leave")
	public String leave(Model model) {
		List<EmployeeMasterDto> listOfEmployees = this.employeeMasterService.getAllemployeeMaster();
		model.addAttribute("listOfEmployees", listOfEmployees);
		return "/pages/employee_management/leave";
	}
	
	@GetMapping("/getEmp/{employeeCode}")
	public ResponseEntity<EmployeeMasterDto> getEmplyeeMaster(@PathVariable String employeeCode){
		EmployeeMasterDto employeeMasterDto = this.employeeMasterService.getEmployeeByEmpCode(employeeCode);
		return new ResponseEntity<EmployeeMasterDto>(employeeMasterDto,HttpStatus.OK);
	}
	
	
	@PostMapping("/saveLeave")
	@ResponseBody
	public String createLeave(@ModelAttribute("saveLeave") Leave leave,BindingResult bindingResult) {
		
		leaveService.saveLeave(leave);
	  return "redirect:/employee/management";		
	}
	
	@RequestMapping("/getEmpData/{employeeId}")
	@ResponseBody
	public EmployeeMaster showDetails(@PathVariable("employeeId") Long employeeId) {
		EmployeeMaster employee=  employeeMasterService.getEmployeeById(employeeId);
		return employee;
		
	}
	
	@PostMapping("/empLeave")
	public String empLeave(Model model, @ModelAttribute("leave") Leave leave,BindingResult bindingResult) {
		leaveService.saveLeave(leave);
		return "redirect:/employee/leave";
		
	}
	
	//Save Employee Payment
	@PostMapping("/saveEmpPayment")
	public String saveEmployeePayment(EmployeePaymentDto empPaymentDto,HttpSession session) {
		this.employeeMasterService.saveEmployeePayment(empPaymentDto);
		session.setAttribute("message", new Message("Employee Payment Data Successfully Saved !!","success"));		
		return "redirect:/employee/payment";
	}
	
	
	
}
