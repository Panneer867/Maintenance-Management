package com.ingroinfo.mm.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.dao.BankRepository;
import com.ingroinfo.mm.dao.BranchRepository;
import com.ingroinfo.mm.dao.CompanyRepository;
import com.ingroinfo.mm.dao.PrivilegeRepository;
import com.ingroinfo.mm.dao.RoleRepository;
import com.ingroinfo.mm.dao.StateRepository;
import com.ingroinfo.mm.dao.UserRepository;
import com.ingroinfo.mm.dto.BranchDto;
import com.ingroinfo.mm.dto.CompanyDto;
import com.ingroinfo.mm.entity.Bank;
import com.ingroinfo.mm.entity.Branch;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.Privilege;
import com.ingroinfo.mm.entity.Role;
import com.ingroinfo.mm.entity.State;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	private void register(User user) {
		user.setPassword(getEncodedPassword(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public void registerCompany(User user) {
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_COMPANY")));
		user.setBranch(null);
		user.setUserType("C");
		register(user);
	}

	@Override
	public void registerBranch(User user) {
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_BRANCH")));
		user.setUserType("B");
		register(user);
	}

	@Override
	public void registerUser(User user, Long roleId) {
		Role userRole = roleRepository.findByName("ROLE_USER");
		user.setRoles(Arrays.asList(userRole));
		user.setUserType("U");
		user.setUserRole(privilegeRepository.findById(roleId).get().getName());
		String sql = "INSERT INTO ROLE_PRIVILEGES (ROLE_ID, PRIVILEGE_ID) VALUES (?, ?)";
		jdbcTemplate.update(sql, userRole.getId(), roleId);
		register(user);
	}

	@Override
	public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}

	@Override
	public String getState(String stateId) {
		Optional<State> state = stateRepository.findById(Integer.parseInt(stateId));
		return state.get().getName();
	}

	@Override
	public Company saveCompany(Company company) {
		companyRepository.save(company);
		return companyRepository.findByEmail(company.getEmail());
	}

	public List<Object[]> getAllStates() {
		return stateRepository.getAllStates();
	}

	public List<Object[]> getCitiesByState(Integer id) {
		return stateRepository.getCitiesByState(id);
	}

	@Override
	public List<Bank> getAllBanks() {
		return bankRepository.findAll();
	}

	@Override
	public boolean companyEmailExists(String email) {
		return (userRepository.findByEmail(email) != null || companyRepository.findByEmail(email) != null);
	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public void deleteCompany(Long companyId) {
		companyRepository.deleteById(companyId);
	}

	@Override
	public boolean branchEmailExists(String email) {
		return (userRepository.findByEmail(email) != null || branchRepository.findByEmail(email) != null);
	}

	@Override
	public Branch saveBranch(Branch branch) {
		branchRepository.save(branch);
		return branchRepository.findByEmail(branch.getEmail());
	}

	@Override
	public Company getCompany(Long id) {
		return companyRepository.findByCompanyId(id);
	}

	@Override
	public List<BranchDto> getAllBranches() {

		List<Branch> branches = branchRepository.findAll();
		List<BranchDto> branchDto = branches.stream().map((branch) -> {
			BranchDto newBranch = new BranchDto();
			newBranch.setAddress(branch.getAddress());
			newBranch.setBranchName(branch.getBranchName());
			newBranch.setCity(branch.getCity());
			newBranch.setState(branch.getState());
			newBranch.setPincode(branch.getPincode());
			newBranch.setCompany(branch.getCompany().getCompanyName());
			newBranch.setEmail(branch.getEmail());
			newBranch.setMobile(branch.getMobile());
			newBranch.setBranchId(branch.getBranchId());
			newBranch.setDateCreated(branch.getDateCreated());
			newBranch.setLastUpdated(branch.getLastUpdated());
			return newBranch;
		}).collect(Collectors.toList());
		return branchDto;
	}

	@Override
	public void deleteBranch(Long branchId) {
		branchRepository.deleteById(branchId);
	}

	@Override
	public boolean companyEmailCheck(CompanyDto companyDto) {

		boolean isExistsUser = userRepository.findAll().stream()
				.filter(x -> !companyRepository.findByCompanyId(companyDto.getCompanyId()).equals(x.getCompany()))
				.collect(Collectors.toList()).stream().filter(o -> o.getEmail().equals(companyDto.getEmail()))
				.findFirst().isPresent();

		boolean isExistsCompany = companyRepository.findAll().stream()
				.filter(x -> !companyDto.getCompanyId().equals(x.getCompanyId())).collect(Collectors.toList()).stream()
				.filter(o -> o.getEmail().equals(companyDto.getEmail())).findFirst().isPresent();

		return isExistsUser || isExistsCompany;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void updateUserCompany(CompanyDto companyDto) {

		User user = userRepository.findByEmail(companyDto.getEmail());
		user.setEmail(companyDto.getEmail());
		user.setMobile(companyDto.getMobile());
		user.setName(companyDto.getCompanyName());
		user.setUsername(companyDto.getUsername());
		if (companyDto.getPassword().length() == 0) {
			user.setPassword(user.getPassword());
			userRepository.save(user);
		} else {
			user.setPassword(this.passwordEncoder.encode(companyDto.getPassword()));
			userRepository.save(user);
		}
	}

	@Override
	public boolean companyUsernameExists(String username) {
		return userRepository.findByUsername(username) != null;
	}

	@Override
	public boolean companyUsernameCheck(CompanyDto companyDto) {

		return userRepository.findAll().stream()
				.filter(x -> !companyRepository.findByCompanyId(companyDto.getCompanyId()).equals(x.getCompany()))
				.collect(Collectors.toList()).stream().filter(o -> o.getUsername().equals(companyDto.getUsername()))
				.findFirst().isPresent();
	}

	@Override
	public boolean branchAllowed(Company company) {

		boolean checkBranch = false;

		String branchCount = company.getNoOfBranch();
		List<Branch> branches = branchRepository.findAll();
		int count = Integer.parseInt(branchCount);
		int branchSize = branches.size();
		if (count > branchSize) {
			checkBranch = true;
		}
		return checkBranch;
	}

	@Override
	public boolean branchUsernameExists(String username) {
		return userRepository.findByUsername(username) != null;
	}

	@Override
	public Branch getBranch(Long id) {
		return branchRepository.findByBranchId(id);
	}

	@Override
	public User getUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public boolean branchEmailCheck(BranchDto branchDto) {

		boolean isExistsUser = userRepository.findAll().stream()
				.filter(x -> !branchRepository.findByBranchId(branchDto.getBranchId()).equals(x.getBranch()))
				.collect(Collectors.toList()).stream().filter(o -> o.getEmail().equals(branchDto.getEmail()))
				.findFirst().isPresent();

		boolean isExistsBranch = branchRepository.findAll().stream()
				.filter(x -> !branchDto.getCompanyId().equals(x.getBranchId())).collect(Collectors.toList()).stream()
				.filter(o -> o.getEmail().equals(branchDto.getEmail())).findFirst().isPresent();

		return isExistsUser || isExistsBranch;
	}

	@Override
	public boolean branchUsernameCheck(BranchDto branchDto) {

		return userRepository.findAll().stream()
				.filter(x -> !branchRepository.findByBranchId(branchDto.getBranchId()).equals(x.getBranch()))
				.collect(Collectors.toList()).stream().filter(o -> o.getUsername().equals(branchDto.getUsername()))
				.findFirst().isPresent();
	}

	@Override
	public void updateUserBranch(BranchDto branchDto) {
		User user = userRepository.findByEmail(branchDto.getEmail());
		user.setEmail(branchDto.getEmail());
		user.setMobile(branchDto.getMobile());
		user.setName(branchDto.getBranchName());
		user.setUsername(branchDto.getUsername());
		if (branchDto.getPassword().length() == 0) {
			user.setPassword(user.getPassword());
			userRepository.save(user);
		} else {
			user.setPassword(this.passwordEncoder.encode(branchDto.getPassword()));
			userRepository.save(user);
		}
	}

	@Override
	public boolean userEmailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	@Override
	public boolean userUsernameExists(String username) {
		return userRepository.findByUsername(username) != null;
	}

	@Override
	public List<Privilege> getAllRoles() {
		return privilegeRepository.findAll();
	}

	@Override
	public void addRole(Privilege role) {

		privilegeRepository.save(role);
		Privilege newRole = privilegeRepository.findByName(role.getName());
		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		String sql = "INSERT INTO ROLE_PRIVILEGES (ROLE_ID, PRIVILEGE_ID) VALUES (?, ?)";
		jdbcTemplate.update(sql, adminRole.getId(), newRole.getId());
	}

	@Override
	public void updateRole(Privilege privilege) {
		privilegeRepository.save(privilege);
	}

	@Override
	public void deleteRole(Long roleId) {
		privilegeRepository.deleteById(roleId);
	}

	@Override
	public Optional<Privilege> getRoleById(Long roleId) {

		return privilegeRepository.findById(roleId);
	}

	@Override
	public boolean roleExists(String roleName) {

		return privilegeRepository.findByName(roleName) != null;
	}

	@Override
	public boolean roleNameCheck(Privilege role) {

		return privilegeRepository.findAll().stream().filter(x -> !role.getId().equals(x.getId()))
				.collect(Collectors.toList()).stream().filter(o -> o.getName().equals(role.getName().trim()))
				.findFirst().isPresent();
	}

	@Override
	public List<User> getAllUsers() {
		String admin = "A";
		String branch = "B";
		String company = "C";

		return userRepository.findAll().stream().filter(x -> !admin.equalsIgnoreCase(x.getUserType()))
				.collect(Collectors.toList()).stream().filter(x -> !branch.equalsIgnoreCase(x.getUserType()))
				.collect(Collectors.toList()).stream().filter(x -> !company.equalsIgnoreCase(x.getUserType()))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);

	}

}
