package com.ingroinfo.mm.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.entity.Bank;

public interface AdminService {
	
	void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;
	
	List<Object[]> getAllStates();

	List<Object[]> getCitiesByState(Integer id);

	List<Bank> getAllBanks();

	void registerCompany(User user);

	void registerBranch(User user);

	void registerUser(User user);
	
	void saveCompany(Company company);
	
}
