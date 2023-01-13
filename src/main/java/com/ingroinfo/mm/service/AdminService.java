package com.ingroinfo.mm.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.Privilege;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.dto.BranchDto;
import com.ingroinfo.mm.dto.CompanyDto;
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

	List<BranchDto> getAllBranches();

	void deleteBranch(Long branchId);

	boolean companyEmailCheck(CompanyDto companyDto);

	User getUserByEmail(String email);

	void updateUserCompany(CompanyDto companyDto);

	boolean companyUsernameExists(String username);

	boolean companyUsernameCheck(CompanyDto companyDto);

	boolean branchAllowed(Company company);

	boolean branchUsernameExists(String username);

	Branch getBranch(Long id);

	User getUser(String email);

	boolean branchEmailCheck(BranchDto branchDto);

	boolean branchUsernameCheck(BranchDto branchDto);

	void updateUserBranch(BranchDto branchDto);

	boolean userEmailExists(String email);

	boolean userUsernameExists(String username);

	List<Privilege> getAllRoles();

	void addRole(Privilege role);

}
