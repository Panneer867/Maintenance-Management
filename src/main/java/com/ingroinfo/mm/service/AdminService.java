package com.ingroinfo.mm.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.Role;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.dto.BranchDto;
import com.ingroinfo.mm.dto.CompanyDto;
import com.ingroinfo.mm.dto.UserDto;
import com.ingroinfo.mm.dto.UserRolesDto;
import com.ingroinfo.mm.entity.Bank;
import com.ingroinfo.mm.entity.Branch;

public interface AdminService {

	void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;

	List<Object[]> getAllStates();

	List<Object[]> getCitiesByState(Integer id);

	List<Bank> getAllBanks();

	List<User> getAllUsers();

	String getState(String stateId);

	void registerCompany(User user);

	void registerBranch(User user);

	void registerUser(User user, Long roleId);

	Company saveCompany(Company company);

	boolean companyEmailExists(String email);

	List<Company> getAllCompanies();

	void deleteCompanyById(Long companyId);

	boolean branchEmailExists(String email);

	Branch saveBranch(Branch branch);

	Company getCompanyById(Long id);

	List<BranchDto> getAllBranches();

	void deleteBranch(Long branchId);

	boolean companyEmailCheck(CompanyDto companyDto);

	User getUserByEmail(String email);

	User getUserByUsername(String username);

	void updateUserDetailsForCompany(CompanyDto companyDto);

	boolean companyUsernameExists(String username);

	boolean companyUsernameCheck(CompanyDto companyDto);

	boolean branchAllowed(Company company);

	boolean branchUsernameExists(String username);

	Branch getBranchById(Long id);

	boolean branchEmailCheck(BranchDto branchDto);

	boolean branchUsernameCheck(BranchDto branchDto);

	void updateUserDetailsForBranch(BranchDto branchDto);

	boolean userEmailExists(String email);

	boolean userUsernameExists(String username);

	void deleteUserById(Long userId);

	List<Role> getAllRoles();

	List<Role> getAllRolesOnlyWithoutAdmin();

	List<Role> getAllRolesWithoutAdminAndCompany();

	Role getRoleById(Long roleId);

	void addRole(Role role);

	void deleteRoleById(Long roleId);

	boolean roleExists(String roleName);

	boolean roleNameCheck(String roleName, Long id);

	void updateRole(Role privilege);

	void AssignRoles(UserRolesDto dto);

	UserRolesDto getUserRoles(Long roleId);

	User getUserById(Long id);

	boolean userEmailCheck(UserDto userDto);

	boolean userUsernameCheck(UserDto userDto);

	void updateUser(UserDto userDto);

	Long getRoleIdByUserId(Long id);

	Company getCompanyByUsername(String name);

}
