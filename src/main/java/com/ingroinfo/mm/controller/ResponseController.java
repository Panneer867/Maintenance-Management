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
import com.ingroinfo.mm.dto.UserRolesDto;
import com.ingroinfo.mm.entity.HsnCode;
import com.ingroinfo.mm.entity.Role;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.HsnCodeService;


@RestController
@RequestMapping("/get")
public class ResponseController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private HsnCodeService hsnCodeService;
	
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
		return adminService.getUserRoles(id);
	}
	
	@GetMapping("/item/hsn/{id}")
	public HsnCode getItemHsn(@PathVariable Long id) {
		return hsnCodeService.getHsnById(id);
	}

}
