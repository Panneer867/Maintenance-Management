package com.ingroinfo.mm.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.mm.dto.ContactManagementDto;
import com.ingroinfo.mm.dto.DepartmentDto;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.ContactManagement;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.ContactManagementService;
import com.ingroinfo.mm.service.MasterService;

@Controller
@RequestMapping("/contact")
public class ContactManagementController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private ContactManagementService contactManagementService;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MasterService masterService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("/management")
	@PreAuthorize("hasAuthority('CONTACT_MANAGEMENT')")
	public String contactMangement(Model model) {
		model.addAttribute("show", null);
		model.addAttribute("contact", new ContactManagementDto());
		
		List<String> divSubDivList = this.masterService.getDistinctDivisions();
		model.addAttribute("divSubDivList", divSubDivList);
	
		
		List<DepartmentDto> listOfDeptDto = this.masterService.findAllDepartment();
		model.addAttribute("listOfDepts", listOfDeptDto);
		return "/pages/contact_management";
	}

	@PostMapping("/save")
	public String saveContactMangement( @RequestParam("image") MultipartFile file, @ModelAttribute("contact") ContactManagementDto contactMangement,
			BindingResult bindingResult, HttpSession session, Principal principal) {
		String companyName = "";

		Company company = adminService.getCompanyByUsername(principal.getName());

		if (company != null) {
			companyName = company.getCompanyName();
		} else {
			companyName = "Admin";
		}

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		List<String> drives = adminService.getLocalDriveLetters();
		String drive = drives.get(0);

		String fileName = contactMangement.getName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "."
				+ tokens.get();
		String uploadDir = drive + "\\Company\\" + companyName + "\\Contact_Management\\";

		try {
			adminService.saveFile(uploadDir, fileName, file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String image = "/Company/" + companyName + "/Contact_Management/" + fileName;

		ContactManagement contactManagement = modelMapper.map(contactMangement, ContactManagement.class);

		contactManagement.setImage(image);

		this.contactManagementService.save(contactManagement);
		session.setAttribute("message", new Message("Contact Details Added Sucessfully !", "success"));
		return "redirect:/contact/management";
	}

	@GetMapping("/view")
	public String viewContact(Model model) {
		
		model.addAttribute("contact", new ContactManagementDto());
		
		List<String> divSubDivList = this.masterService.getDistinctDivisions();
		model.addAttribute("divSubDivList", divSubDivList);
	
		
		List<DepartmentDto> listOfDeptDto = this.masterService.findAllDepartment();
		model.addAttribute("listOfDepts", listOfDeptDto);
	
		model.addAttribute("contacts", contactManagementService.getAllContactDetails());
		model.addAttribute("show", "show");
		return "/pages/contact_management";
	}
}
