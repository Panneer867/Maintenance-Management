package com.ingroinfo.mm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.UserRepository;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	@Autowired
	private UserRepository repository;

	@Override
	public void saveUser(User user) {
		user.setPassword(getEncodedPassword(user.getPassword()));
		repository.save(user);
	}

}
