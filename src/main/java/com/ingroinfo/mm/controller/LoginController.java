package com.ingroinfo.mm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.service.AdminService;

@Controller
public class LoginController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("register", new User());

		return "/register";
	}

	@PostMapping("/register")
	public String addUser(Model model, @ModelAttribute("register") User user) {
		adminService.saveUser(user);

		return "redirect:/login?success";
	}
}
