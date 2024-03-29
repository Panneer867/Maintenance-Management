package com.ingroinfo.mm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingroinfo.mm.dao.EmployeeMasterRepository;
import com.ingroinfo.mm.dao.ItemMasterRepository;
import com.ingroinfo.mm.dto.UserRolesDto;
import com.ingroinfo.mm.entity.EmployeeMaster;
import com.ingroinfo.mm.entity.ItemMaster;
import com.ingroinfo.mm.entity.Role;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.UserRolesService;

@RestController
@RequestMapping("/get")
public class ResponseController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserRolesService userRolesService;

	@Autowired
	private EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	private ItemMasterRepository itemMasterRepository;

	@GetMapping("/city")
	public String getCities(@RequestParam Integer stateId) {

		String json = null;
		List<Object[]> list = adminService.getCitiesByState(stateId);
		try {
			json = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@GetMapping("/user/{id}")
	public User getUsers(@PathVariable Long id) {
		return adminService.getUserById(id);

	}

	@GetMapping("/role/{id}")
	public Role getRoles(@PathVariable Long id) {
		Role role = adminService.getRoleById(id);
		role.setName(role.getName().replace("ROLE_", ""));
		return role;
	}

	@GetMapping("/user/roles/{id}")
	public UserRolesDto getUserRoles(@PathVariable Long id) {
		return userRolesService.getUserRoles(id);
	}

	@GetMapping("/item/details/{id}")
	public ItemMaster getItemDetails(@PathVariable Long id) {
		return itemMasterRepository.findById(id).get();
	}

	@GetMapping("/test")
	public EmployeeMaster getEmployees(@RequestParam Long id) {
		return employeeMasterRepository.findByEmployeeId(id);
	}
}
