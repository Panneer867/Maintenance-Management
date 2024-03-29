package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String string);

}
