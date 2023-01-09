package com.ingroinfo.mm.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.entity.Bank;
import com.ingroinfo.mm.entity.Branch;

public interface AdminService {
	
	void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;
	
	List<Object[]> getAllStates();

	List<Object[]> getCitiesByState(Integer id);

	List<Bank> getAllBanks();
	
	String getState(String stateId);

	void registerCompany(User user);

	void registerBranch(User user);

	void registerUser(User user);
	
	Company saveCompany(Company company);

	boolean companyEmailExists(String email);

	List<Company> getAllCompanies();

	void deleteCompany(Long companyId);

	boolean branchEmailExists(String email);

	Branch saveBranch(Branch branch);

	Company getCompany(Long id);

	List<Branch> getAllBranches();

	void deleteBranch(Long branchId);

	

	

	
}
