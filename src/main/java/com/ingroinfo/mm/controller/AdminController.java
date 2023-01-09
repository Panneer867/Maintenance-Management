package com.ingroinfo.mm.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.entity.Branch;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.dto.BranchDto;
import com.ingroinfo.mm.dto.CompanyDto;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	private static final ModelMapper modelMapper = new ModelMapper();

	@GetMapping("/home")
	public String adminHome(Model model) {

		model.addAttribute("title", "Admin | Maintenance Mangement");
		return "/pages/admin/home";
	}

	@GetMapping("/account/company")
	public String createCompany(Model model) {
		model.addAttribute("title", "New Company | Maintenance Mangement");
		model.addAttribute("company", new CompanyDto());
		model.addAttribute("states", adminService.getAllStates());
		return "/pages/admin/create_company";
	}

	@PostMapping("/company/register")
	public String createCompany(@RequestParam("logo") MultipartFile file,
			@ModelAttribute("company") CompanyDto companyDto, BindingResult bindingResult, HttpSession session) {

		if (adminService.companyEmailExists(companyDto.getEmail())) {
			session.setAttribute("message",
					new Message("Email is already associated with another account !", "danger"));
			return "redirect:/admin/account/company";
		}

		Company company = modelMapper.map(companyDto, Company.class);
		User user = modelMapper.map(companyDto, User.class);

		Optional<String> fileExtension = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		String fileName = company.getCompanyName() + "." + fileExtension.get();
		String uploadDir = "C:\\Company\\" + company.getCompanyName() + "\\logo";
		company.setPath("C:\\Company\\" + company.getCompanyName());
		company.setLogo(fileName);
		company.setState(adminService.getState(companyDto.getState()));
		user.setName(company.getCompanyName());

		try {
			adminService.saveFile(uploadDir, fileName, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Company newCompany = adminService.saveCompany(company);
		user.setCompany(newCompany);
		adminService.registerCompany(user);
		session.setAttribute("message", new Message("Company has been created successfully !!", "success"));

		return "redirect:/admin/account/company";
	}

	@GetMapping("/account/company/list")
	public String companyList(Model model) {

		model.addAttribute("company", adminService.getAllCompanies());
		return "/pages/admin/company_list";
	}

	@GetMapping("/account/company/delete")
	public String deleteCompany(@RequestParam("id") Long companyId, HttpSession session) {

		adminService.deleteCompany(companyId);
		session.setAttribute("message", new Message("Company has been deleted successfully !!", "success"));
		return "redirect:/admin/account/company/list";

	}

	@GetMapping("/account/branch")
	public String createBranch(Model model) {
		model.addAttribute("title", "New Branch | Maintenance Mangement");
		model.addAttribute("branch", new BranchDto());
		model.addAttribute("states", adminService.getAllStates());
		model.addAttribute("companies", adminService.getAllCompanies());
		return "/pages/admin/create_branch";
	}

	@PostMapping("/branch/register")
	public String createBranch(@ModelAttribute("branch") BranchDto branchDto, HttpSession session,
			Principal principal) {

		if (adminService.branchEmailExists(branchDto.getEmail())) {
			session.setAttribute("message",
					new Message("Email is already associated with another account !", "danger"));
			return "redirect:/admin/account/branch";
		}
		
		Company company = adminService.getCompany(branchDto.getCompanyId());
		Branch branch = modelMapper.map(branchDto, Branch.class);
		User user = modelMapper.map(branchDto, User.class);

		branch.setState(adminService.getState(branchDto.getState()));
		branch.setCompany(company);
		Branch newBranch = adminService.saveBranch(branch);
		user.setBranch(newBranch);
		user.setCompany(company);
		user.setName(branchDto.getBranchName());
		adminService.registerBranch(user);
		session.setAttribute("message", new Message("Branch has been created successfully !!", "success"));

		return "redirect:/admin/account/branch";
	}

	@GetMapping("/account/branch/list")
	public String branchList(Model model) {
		model.addAttribute("company", adminService.getAllBranches());
		model.addAttribute("states", adminService.getAllStates());
		model.addAttribute("company", adminService.getAllCompanies());
		return "/pages/admin/branch_list";
	}
	
	
	@GetMapping("/account/branch/delete")
	public String deleteBranch(@RequestParam("id") Long branchId, HttpSession session) {

		adminService.deleteBranch(branchId);
		session.setAttribute("message", new Message("Branch has been deleted successfully !!", "success"));
		return "redirect:/admin/account/branch/list";

	}

	@GetMapping("/user")
	public String createUser(Model model) {
		return "/pages/admin/create_user";
	}

	@GetMapping("/user/list")
	public String userList() {
		return "/pages/admin/users_list";
	}

	@GetMapping("/user/role")
	public String userRoles() {
		return "/pages/admin/user_roles";
	}

	@GetMapping("/user/role/master")
	public String roleMaster() {
		return "/pages/admin/roles_master";
	}

	@GetMapping("/user/change-password")
	public String changePassword() {
		return "/pages/admin/change_password";
	}

	@GetMapping("/backup")
	public String backup() {
		return "/pages/admin/backup";
	}

	@GetMapping("/excel/import-export")
	public String excel() {
		return "/pages/admin/excel_import_export";
	}

	@GetMapping("/device/control")
	public String deviceControl() {
		return "/pages/admin/device_control";
	}

}
