package com.ingroinfo.mm.service;

import com.ingroinfo.mm.dto.UserRolesDto;

public interface UserRolesService {
	
	void AssignRoles(UserRolesDto dto);
	
	UserRolesDto getUserRoles(Long roleId);

}
