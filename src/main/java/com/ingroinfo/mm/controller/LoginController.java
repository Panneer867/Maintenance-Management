package com.ingroinfo.mm.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.dto.CompanyDto;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.BackupService;

@Controller
public class LoginController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private AdminService adminService;

	@Autowired
	private BackupService backupService;

	@GetMapping("/login")
	public String login() {

		return "/login";
	}

	@GetMapping("/register/company")
	public String createCompany(Model model) {
		model.addAttribute("company", new CompanyDto());
		model.addAttribute("states", adminService.getAllStates());
		return "/company";
	}

	@PostMapping("/register/company")
	public String createCompany(@RequestParam("logo") MultipartFile file,
			@ModelAttribute("company") CompanyDto companyDto, BindingResult bindingResult, HttpSession session) {

		if (adminService.companyEmailExists(companyDto.getEmail())) {
			session.setAttribute("message",
					new Message("Email is already associated with another account !", "danger"));
			return "redirect:/register/company";
		}

		if (adminService.companyUsernameExists(companyDto.getUsername())) {
			session.setAttribute("message",
					new Message("Username is already associated with another account !", "danger"));
			return "redirect:/register/company";
		}

		Company company = modelMapper.map(companyDto, Company.class);
		User user = modelMapper.map(companyDto, User.class);
		String drive = backupService.getLocalDriveLetters().get(0);
		Optional<String> fileExtension = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		String fileName = company.getCompanyName() + "_" + ThreadLocalRandom.current().nextInt(1, 1000) + "."
				+ fileExtension.get();
		String uploadDir = drive + "\\Company\\" + company.getCompanyName() + "\\logo";
		company.setPath(drive + "\\Company\\" + company.getCompanyName());
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

		return "redirect:/login?success";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {

		return "/access_denied";
	}

	@GetMapping("/server-error")
	public String serverError() {

		return "/server_error";
	}

}
