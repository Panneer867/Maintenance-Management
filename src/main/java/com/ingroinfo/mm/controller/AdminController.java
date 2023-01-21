package com.ingroinfo.mm.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ingroinfo.mm.entity.Role;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.configuration.ModelMapperConfig;
import com.ingroinfo.mm.dto.BranchDto;
import com.ingroinfo.mm.dto.CompanyDto;
import com.ingroinfo.mm.dto.UserDto;
import com.ingroinfo.mm.dto.UserRolesDto;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	public ModelMapperConfig mapper;

	@Autowired
	private AdminService adminService;

	private static final ModelMapper modelMapper = new ModelMapper();

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("/home")
	@PreAuthorize("hasAuthority('ADMIN_HOME')")
	public String adminHome(Model model) {
		model.addAttribute("title", "Admin | Maintenance Mangement");
		return "/pages/admin/home";
	}

	@GetMapping("/company/create")
	@PreAuthorize("hasAuthority('CREATE_COMPANY')")
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
			return "redirect:/admin/company/create";
		}

		if (adminService.companyUsernameExists(companyDto.getUsername())) {
			session.setAttribute("message",
					new Message("Username is already associated with another account !", "danger"));
			return "redirect:/admin/company/create";
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
		if (company.getEnableApp().length() == 0) {
			company.setEnableApp("off");
		}
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
		return "redirect:/admin/company/create";
	}

	@GetMapping("/company/list")
	@PreAuthorize("hasAuthority('COMPANY_MANAGEMENT')")
	public String companyList(Model model) {
		model.addAttribute("companies", adminService.getAllCompanies());

		return "/pages/admin/company_list";
	}

	@GetMapping("/company/edit/{id}")
	@PreAuthorize("hasAuthority('EDIT_COMPANY')")
	public String companyEdit(@PathVariable Long id, Model model) {

		model.addAttribute("companyDetails", adminService.getCompanyById(id));
		model.addAttribute("company", new CompanyDto());
		model.addAttribute("user", adminService.getUserByEmail(adminService.getCompanyById(id).getEmail()));

		return "/pages/admin/edit_company";
	}

	@GetMapping("/company/view/{id}")
	@PreAuthorize("hasAuthority('VIEW_COMPANY')")
	public String companyView(@PathVariable Long id, Model model) {

		model.addAttribute("companyDetails", adminService.getCompanyById(id));
		model.addAttribute("user", adminService.getUserByEmail(adminService.getCompanyById(id).getEmail()));

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

		Company company = adminService.getCompanyById(companyDto.getCompanyId());
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
		adminService.updateUserDetailsForCompany(companyDto);
		session.setAttribute("message", new Message("Company has been successfully updated!!", "success"));

		return "redirect:/admin/company/list";
	}

	@PostMapping("/company/edit/logo")
	public String companyLogo(@RequestParam("logo") MultipartFile file, @RequestParam String companyId,
			HttpSession session) throws IOException {

		Company company = adminService.getCompanyById(Long.parseLong(companyId));

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
	@PreAuthorize("hasAuthority('DELETE_COMPANY')")
	public String deleteCompany(@RequestParam("id") Long companyId, HttpSession session) {

		adminService.deleteCompanyById(companyId);
		session.setAttribute("message", new Message("Company has been deleted successfully !!", "success"));

		return "redirect:/admin/company/list";
	}

	@GetMapping("/branch/create")
	@PreAuthorize("hasAuthority('CREATE_BRANCH')")
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

			return "redirect:/admin/branch/create";
		}
		if (adminService.branchUsernameExists(branchDto.getUsername())) {
			session.setAttribute("message",
					new Message("Username is already associated with another account !", "danger"));

			return "redirect:/admin/branch/create";
		}

		Company company = adminService.getCompanyById(branchDto.getCompanyId());

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

			return "redirect:/admin/branch/create";
		}

		return "redirect:/admin/branch/create";
	}

	@GetMapping("/branch/list")
	@PreAuthorize("hasAuthority('BRANCH_MANAGEMENT')")
	public String branchList(Model model) {
		model.addAttribute("branches", adminService.getAllBranches());
		model.addAttribute("states", adminService.getAllStates());
		model.addAttribute("companies", adminService.getAllCompanies());
		return "/pages/admin/branch_list";
	}

	@GetMapping("/branch/delete")
	@PreAuthorize("hasAuthority('DELETE_BRANCH')")
	public String deleteBranch(@RequestParam("id") Long branchId, HttpSession session) {
		adminService.deleteBranch(branchId);
		session.setAttribute("message", new Message("Branch has been deleted successfully !!", "success"));

		return "redirect:/admin/branch/list";

	}

	@GetMapping("/branch/view/{id}")
	@PreAuthorize("hasAuthority('VIEW_BRANCH')")
	public String branchView(@PathVariable Long id, Model model) {
		model.addAttribute("branchDetails", adminService.getBranchById(id));
		model.addAttribute("user", adminService.getUserByEmail(adminService.getBranchById(id).getEmail()));

		return "/pages/admin/view_branch";
	}

	@GetMapping("/branch/edit/{id}")
	@PreAuthorize("hasAuthority('EDIT_BRANCH')")
	public String branchEdit(@PathVariable Long id, Model model) {

		model.addAttribute("branchDetails", adminService.getBranchById(id));
		model.addAttribute("branch", new BranchDto());
		model.addAttribute("companies", adminService.getAllCompanies());
		model.addAttribute("user", adminService.getUserByEmail(adminService.getBranchById(id).getEmail()));

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
		Branch branch = adminService.getBranchById(branchDto.getBranchId());
		mapper.modelMapper().map(branchDto, branch);
		adminService.saveBranch(branch);
		adminService.updateUserDetailsForBranch(branchDto);
		session.setAttribute("message", new Message("Branch has been successfully updated!!", "success"));

		return "redirect:/admin/branch/list";
	}

	@GetMapping("/user/create")
	@PreAuthorize("hasAuthority('CREATE_USER')")
	public String createUser(Model model) {

		model.addAttribute("user", new UserDto());
		model.addAttribute("branches", adminService.getAllBranches());
		model.addAttribute("companies", adminService.getAllCompanies());
		model.addAttribute("roles", adminService.getAllRoles());

		return "/pages/admin/create_user";
	}

	@PostMapping("/user/register")
	public String createUser(@ModelAttribute("user") UserDto userDto, HttpSession session, Principal principal) {

		if (adminService.userUsernameExists(userDto.getUsername())) {
			session.setAttribute("message",
					new Message("Username is already associated with another account !", "danger"));
			return "redirect:/admin/user/create";
		}

		if (adminService.userEmailExists(userDto.getEmail())) {
			session.setAttribute("message",
					new Message("Email is already associated with another account !", "danger"));
			return "redirect:/admin/user/create";
		}

		User user = modelMapper.map(userDto, User.class);
		user.setCompany(adminService.getCompanyById(userDto.getCompanyId()));
		user.setBranch(adminService.getBranchById(userDto.getBranchId()));
		adminService.registerUser(user, userDto.getRoleId());
		session.setAttribute("message", new Message("User has been successfully Created!!", "success"));

		return "redirect:/admin/user/create";
	}

	@GetMapping("/user/list")
	@PreAuthorize("hasAuthority('USER_MANAGEMENT')")
	public String userList(Model model) {

		model.addAttribute("users", adminService.getAllUsers());

		return "/pages/admin/users_list";
	}

	@GetMapping("/user/edit/{id}")
	@PreAuthorize("hasAuthority('EDIT_USER')")
	public String userEdit(@PathVariable Long id, Model model) {

		model.addAttribute("user", new UserDto());
		model.addAttribute("userDetails", adminService.getUserById(id));
		model.addAttribute("branches", adminService.getAllBranches());
		model.addAttribute("companies", adminService.getAllCompanies());
		model.addAttribute("roles", adminService.getAllRoles());
		model.addAttribute("roleId", adminService.getRoleIdByUserId(id));

		return "/pages/admin/edit_user";
	}

	@PostMapping("/user/edit/update")
	public String userUpdate(@ModelAttribute("user") UserDto userDto, BindingResult bindingResult, HttpSession session)
			throws IOException {

		if (adminService.userEmailCheck(userDto)) {
			session.setAttribute("message",
					new Message("Email is already associated with another account !", "danger"));

			return "redirect:/admin/user/edit/" + userDto.getUserId();
		}

		if (adminService.userUsernameCheck(userDto)) {
			session.setAttribute("message",
					new Message("Username is already associated with another account !", "danger"));

			return "redirect:/admin/user/edit/" + userDto.getUserId();
		}

		adminService.updateUser(userDto);
		session.setAttribute("message", new Message("User has been successfully updated!!", "success"));

		return "redirect:/admin/user/list";
	}

	@GetMapping("/user/delete")
	@PreAuthorize("hasAuthority('DELETE_USER')")
	public String deleteUser(@RequestParam("id") Long userId, HttpSession session) {

		adminService.deleteUserById(userId);
		session.setAttribute("message", new Message("User has been deleted successfully !!", "success"));

		return "redirect:/admin/user/list";

	}

	@GetMapping("/role/create")
	@PreAuthorize("hasAuthority('CREATE_ROLE')")
	public String addRoles(Model model) {

		model.addAttribute("role", new Role());

		return "/pages/admin/create_role";
	}

	@PostMapping("/role/register")
	public String addRoles(@ModelAttribute("role") Role role, HttpSession session) {

		role.setName("ROLE_" + role.getName().trim().replaceAll("\\s+", "_"));

		if (adminService.roleExists(role.getName())) {
			session.setAttribute("message", new Message("You've entered role Name already exists !", "danger"));

			return "redirect:/admin/role/create";
		}
		adminService.addRole(role);
		session.setAttribute("message", new Message("Role has been successfully created!!", "success"));

		return "redirect:/admin/role/create";
	}

	@GetMapping("/role/delete")
	@PreAuthorize("hasAuthority('DELETE_ROLE')")
	public String deleteRole(@RequestParam("id") Long roleId, HttpSession session) {

		adminService.deleteRoleById(roleId);
		session.setAttribute("message", new Message("Role has been deleted successfully !!", "success"));

		return "redirect:/admin/role/list";
	}

	@GetMapping("/role/list")
	@PreAuthorize("hasAuthority('ROLE_MANAGEMENT')")
	public String rolesList(Model model) {

		model.addAttribute("role", new Role());
		model.addAttribute("roles", adminService.getAllRoles());

		return "/pages/admin/roles_list";
	}

	@PostMapping("/role/update")
	@PreAuthorize("hasAuthority('EDIT_ROLE')")
	public String updateRoles(@ModelAttribute("role") Role role, HttpSession session) {

		role.setName("ROLE_" + role.getName().trim().replaceAll("\\s+", "_"));

		if (adminService.roleNameCheck(role.getName(), role.getId())) {
			session.setAttribute("message", new Message("You've entered role Name already exists !", "danger"));

			return "redirect:/admin/role/list";
		}

		Role oldRole = adminService.getRoleById(role.getId());
		oldRole.setName("ROLE_" + role.getName().trim().replaceAll("\\s+", "_"));
		oldRole.setDescription(role.getDescription());
		adminService.updateRole(oldRole);
		session.setAttribute("message", new Message("Role has been updated successfully !", "success"));

		return "redirect:/admin/role/list";
	}

	@GetMapping("/user/roles")
	@PreAuthorize("hasAuthority('USER_ROLES')")
	public String userRoles(Model model, Principal principal) {

		String type = adminService.getUserByUsername(principal.getName()).getUserType();

		if (type.equalsIgnoreCase("B")) {
			model.addAttribute("roles", adminService.getAllRoles());
		}
		if (type.equalsIgnoreCase("A")) {
			model.addAttribute("roles", adminService.getAllRolesOnlyWithoutAdmin());
		}
		if (type.equalsIgnoreCase("C")) {
			model.addAttribute("roles", adminService.getAllRolesWithoutAdminAndCompany());
		}

		model.addAttribute("userRoles", new UserRolesDto());

		return "/pages/admin/user_roles";
	}

	@PostMapping("/user/roles/update")
	@PreAuthorize("hasAuthority('EDIT_USER_ROLES')")
	public String updateUserRoles(@ModelAttribute("userRoles") UserRolesDto dto, HttpSession session) {

		adminService.AssignRoles(dto);
		session.setAttribute("message", new Message("User Roles has been updated successfully !", "success"));

		return "redirect:/admin/user/roles";
	}

	@GetMapping("/user/change-password")
	public String changePassword() {

		return "/pages/admin/change_password";
	}

	@GetMapping("/backup")
	@PreAuthorize("hasAuthority('BACKUP')")
	public String backup() {

		return "/pages/admin/backup";
	}

	@GetMapping("/excel/import-export")
	@PreAuthorize("hasAuthority('EXCEL_IMPEXP')")
	public String excel() {

		return "/pages/admin/excel_import_export";
	}

	@GetMapping("/device/control")
	@PreAuthorize("hasAuthority('DEVICE_CONTROL')")
	public String deviceControl() {

		return "/pages/admin/device_control";
	}

}
