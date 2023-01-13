package com.ingroinfo.mm.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.entity.Branch;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.Privilege;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.configuration.ModelMapperConfig;
import com.ingroinfo.mm.dto.BranchDto;
import com.ingroinfo.mm.dto.CompanyDto;
import com.ingroinfo.mm.dto.UserDto;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	public ModelMapperConfig mapper;

	@Autowired
	private AdminService adminService;

	private static final ModelMapper modelMapper = new ModelMapper();

	@GetMapping("/home")
	public String adminHome(Model model) {
		model.addAttribute("title", "Admin | Maintenance Mangement");
		return "/pages/admin/home";
	}

	@GetMapping("/company")
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
			return "redirect:/admin/company";
		}

		if (adminService.companyUsernameExists(companyDto.getUsername())) {
			session.setAttribute("message",
					new Message("Username is already associated with another account !", "danger"));
			return "redirect:/admin/company";
		}

		Company company = modelMapper.map(companyDto, Company.class);
		User user = modelMapper.map(companyDto, User.class);
		Optional<String> fileExtension = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));
		String fileName = company.getCompanyName() + "_" + ThreadLocalRandom.current().nextInt(1, 10000) + "."
				+ fileExtension.get();
		String uploadDir = "C:\\Company\\" + company.getCompanyName() + "\\logo";
		company.setPath("C:\\Company\\" + company.getCompanyName());
		company.setLogo(fileName);
		company.setState(adminService.getState(companyDto.getState()));
		user.setName(company.getCompanyName());

		if (companyDto.getNoOfBranch().length() == 0) {
			company.setNoOfBranch("0");
		}
		try {
			adminService.saveFile(uploadDir, fileName, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Company newCompany = adminService.saveCompany(company);
		user.setCompany(newCompany);
		adminService.registerCompany(user);
		session.setAttribute("message", new Message("Company has been created successfully !!", "success"));
		return "redirect:/admin/company";
	}

	@GetMapping("/company/list")
	public String companyList(Model model) {
		model.addAttribute("companies", adminService.getAllCompanies());
		return "/pages/admin/company_list";
	}

	@GetMapping("/company/edit/{id}")
	public String companyEdit(@PathVariable Long id, Model model, HttpSession session) {
		model.addAttribute("companyDetails", adminService.getCompany(id));
		model.addAttribute("company", new CompanyDto());
		model.addAttribute("user", adminService.getUser(adminService.getCompany(id).getEmail()));
		return "/pages/admin/edit_company";
	}

	@GetMapping("/company/view/{id}")
	public String companyView(@PathVariable Long id, Model model, HttpSession session) {
		model.addAttribute("companyDetails", adminService.getCompany(id));
		model.addAttribute("user", adminService.getUser(adminService.getCompany(id).getEmail()));
		return "/pages/admin/view_company";
	}

	@PostMapping("/company/edit/update")
	public String companyUpdate(@ModelAttribute("company") CompanyDto companyDto, BindingResult bindingResult,
			HttpSession session, Principal principal) throws IOException {
		if (adminService.companyEmailCheck(companyDto)) {
			session.setAttribute("message",
					new Message("Email is already associated with another account !", "danger"));
			return "redirect:/admin/company/edit/" + companyDto.getCompanyId();
		}

		if (adminService.companyUsernameCheck(companyDto)) {
			session.setAttribute("message",
					new Message("Username is already associated with another account !", "danger"));
			return "redirect:/admin/company/edit/" + companyDto.getCompanyId();
		}
		Company company = adminService.getCompany(companyDto.getCompanyId());
		String oldfolder = "C:\\Company\\" + company.getCompanyName() + "\\";
		File files = new File(oldfolder);
		mapper.modelMapper().map(companyDto, company);
		String folder = "C:\\Company\\" + companyDto.getCompanyName() + "\\";
		if (!oldfolder.equalsIgnoreCase(folder)) {
			File rename = new File(folder);
			files.renameTo(rename);
			company.setPath(folder);
		}
		adminService.saveCompany(company);
		adminService.updateUserCompany(companyDto);
		session.setAttribute("message", new Message("Company has been successfully updated!!", "success"));
		return "redirect:/admin/company/list";
	}

	@PostMapping("/company/edit/logo")
	public String companyLogo(@RequestParam("logo") MultipartFile file, @RequestParam String companyId,
			HttpSession session) throws IOException {
		Company company = adminService.getCompany(Long.parseLong(companyId));
		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));
		String fileName = company.getCompanyName() + "_" + ThreadLocalRandom.current().nextInt(1, 10000) + "."
				+ tokens.get();
		String uploadDir = "C:\\Company\\" + company.getCompanyName() + "\\logo";
		company.setLogo(fileName);
		adminService.saveFile(uploadDir, fileName, file);
		adminService.saveCompany(company);
		session.setAttribute("message", new Message("Logo has been successfully Updated !", "success"));
		return "redirect:/admin/company/edit/" + companyId;
	}

	@GetMapping("/company/delete")
	public String deleteCompany(@RequestParam("id") Long companyId, HttpSession session) {
		adminService.deleteCompany(companyId);
		session.setAttribute("message", new Message("Company has been deleted successfully !!", "success"));
		return "redirect:/admin/company/list";
	}

	@GetMapping("/branch")
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
			return "redirect:/admin/branch";
		}
		if (adminService.branchUsernameExists(branchDto.getUsername())) {
			session.setAttribute("message",
					new Message("Username is already associated with another account !", "danger"));
			return "redirect:/admin/branch";
		}

		Company company = adminService.getCompany(branchDto.getCompanyId());

		if (adminService.branchAllowed(company)) {
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

		} else {
			session.setAttribute("message",
					new Message("Only " + company.getNoOfBranch() + " no of branches are allowed !", "danger"));
			return "redirect:/admin/branch";
		}

		return "redirect:/admin/branch";
	}

	@GetMapping("/branch/list")
	public String branchList(Model model) {
		model.addAttribute("branches", adminService.getAllBranches());
		model.addAttribute("states", adminService.getAllStates());
		model.addAttribute("companies", adminService.getAllCompanies());
		return "/pages/admin/branch_list";
	}

	@GetMapping("/branch/delete")
	public String deleteBranch(@RequestParam("id") Long branchId, HttpSession session) {
		adminService.deleteBranch(branchId);
		session.setAttribute("message", new Message("Branch has been deleted successfully !!", "success"));
		return "redirect:/admin/branch/list";

	}

	@GetMapping("/branch/view/{id}")
	public String branchView(@PathVariable Long id, Model model, HttpSession session) {
		model.addAttribute("branchDetails", adminService.getBranch(id));
		model.addAttribute("user", adminService.getUser(adminService.getBranch(id).getEmail()));
		return "/pages/admin/view_branch";
	}

	@GetMapping("/branch/edit/{id}")
	public String branchEdit(@PathVariable Long id, Model model, HttpSession session) {
		model.addAttribute("branchDetails", adminService.getBranch(id));
		model.addAttribute("branch", new BranchDto());
		model.addAttribute("companies", adminService.getAllCompanies());
		model.addAttribute("user", adminService.getUser(adminService.getBranch(id).getEmail()));
		return "/pages/admin/edit_branch";
	}

	@PostMapping("/branch/edit/update")
	public String brandUpdate(@ModelAttribute("branch") BranchDto branchDto, BindingResult bindingResult,
			HttpSession session) throws IOException {
		if (adminService.branchEmailCheck(branchDto)) {
			session.setAttribute("message",
					new Message("Email is already associated with another account !", "danger"));
			return "redirect:/admin/branch/edit/" + branchDto.getBranchId();
		}

		if (adminService.branchUsernameCheck(branchDto)) {
			session.setAttribute("message",
					new Message("Username is already associated with another account !", "danger"));
			return "redirect:/admin/branch/edit/" + branchDto.getBranchId();
		}
		Branch branch = adminService.getBranch(branchDto.getBranchId());
		mapper.modelMapper().map(branchDto, branch);
		adminService.saveBranch(branch);
		adminService.updateUserBranch(branchDto);
		session.setAttribute("message", new Message("Branch has been successfully updated!!", "success"));
		return "redirect:/admin/branch/list";
	}

	@GetMapping("/user")
	public String createUser(Model model) {
		model.addAttribute("user", new UserDto());
		model.addAttribute("branches", adminService.getAllBranches());
		model.addAttribute("companies", adminService.getAllCompanies());
		model.addAttribute("roles", adminService.getAllRoles());
		return "/pages/admin/create_user";
	}
	
	@PostMapping("/user/register")
	public String createUser(@ModelAttribute("user") UserDto userDto, HttpSession session,
			Principal principal) {
		if (adminService.userEmailExists(userDto.getEmail())) {
			session.setAttribute("message",
					new Message("Email is already associated with another account !", "danger"));
			return "redirect:/admin/user";
		}
		if (adminService.userUsernameExists(userDto.getUsername())) {
			session.setAttribute("message",
					new Message("Username is already associated with another account !", "danger"));
			return "redirect:/admin/user";
		}

		User user = modelMapper.map(userDto, User.class);
		user.setCompany(adminService.getCompany(userDto.getCompanyId()));
		user.setBranch(adminService.getBranch(userDto.getBranchId()));
		adminService.registerUser(user);
		session.setAttribute("message", new Message("User has been successfully Created!!", "success"));
		return "redirect:/admin/user";
	}

	@GetMapping("/user/list")
	public String userList() {
		return "/pages/admin/users_list";
	}

	@GetMapping("/role")
	public String userRoles(Model model) {
		model.addAttribute("role", new Privilege());
		return "/pages/admin/roles_master";
	}
	
	@PostMapping("/role/add")
	public String addRoles(@ModelAttribute("role") Privilege role, HttpSession session) {
		adminService.addRole(role);
		session.setAttribute("message", new Message("Role has been successfully Created!!", "success"));
		return "redirect:/admin/role";
	}

	@GetMapping("/user/role/master")
	public String roleMaster() {
		return "/pages/admin/user_roles";
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
