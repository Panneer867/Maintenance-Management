package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(Long id);
	
	User findByUsername(String username);

	User findByEmail(String email);

	User findByCompany(Company company);

	User findByUserType(String userType);

}