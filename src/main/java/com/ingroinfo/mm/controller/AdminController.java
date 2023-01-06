package com.ingroinfo.mm.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.MaterialService;
import com.ingroinfo.mm.configuration.ModelMapperConfig;
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

		return "/pages/admin/create_company";
	}

	@PostMapping("/company/register")
	public String createCompany(@RequestParam("logo") MultipartFile file,
			@ModelAttribute("company") CompanyDto companyDto, HttpSession session) throws IOException {

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		Company company = modelMapper.map(companyDto, Company.class);
		User user = modelMapper.map(companyDto, User.class);

		String fileName = company.getCompanyName() + tokens.get();
		String uploadDir = "C:\\Company\\" + company.getCompanyName() + "\\Logo";
		company.setPath("C:\\Company\\" + company.getCompanyName());
		company.setLogo(fileName);

		adminService.saveFile(uploadDir, fileName, file);
		adminService.saveCompany(company);
		adminService.registerCompany(user);
		session.setAttribute("message", new Message("Company has been created successfully !!", "success"));
		return "redirect:/admin/account/company";
	}

	@GetMapping("/account/company/list")
	public String companyList(Model model) {
		return "/pages/admin/company_list";
	}

	@GetMapping("/account/branch")
	public String createBranch(Model model) {
		return "/pages/admin/create_branch";
	}

	@GetMapping("/account/branch/list")
	public String branchList(Model model) {
		return "/pages/admin/branch_list";
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
