package com.ingroinfo.mm.helper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.ingroinfo.mm.dao.PrivilegeRepository;
import com.ingroinfo.mm.dao.RoleRepository;
import com.ingroinfo.mm.entity.Privilege;
import com.ingroinfo.mm.entity.Role;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;

		Privilege companyPrivilege = createPrivilegeIfNotFound("COMPANY_PRIVILEGE");
		Privilege branchPrivilege = createPrivilegeIfNotFound("BRANCH_PRIVILEGE");
		Privilege userPrivilege = createPrivilegeIfNotFound("USER_PRIVILEGE");

		List<Privilege> ownerPrivileges = Arrays.asList(companyPrivilege, branchPrivilege, userPrivilege);
		createRoleIfNotFound("ROLE_ADMIN", ownerPrivileges);
		createRoleIfNotFound("ROLE_COMPANY", Arrays.asList(companyPrivilege));
		createRoleIfNotFound("ROLE_BRANCH", Arrays.asList(branchPrivilege));
		createRoleIfNotFound("ROLE_USER", Arrays.asList(userPrivilege));

		alreadySetup = true;
	}

	@Transactional
	Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

}
