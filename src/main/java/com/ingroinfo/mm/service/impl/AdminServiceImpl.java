package com.ingroinfo.mm.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.dao.BankRepository;
import com.ingroinfo.mm.dao.BranchRepository;
import com.ingroinfo.mm.dao.CompanyRepository;
import com.ingroinfo.mm.dao.RoleRepository;
import com.ingroinfo.mm.dao.StateRepository;
import com.ingroinfo.mm.dao.UserRepository;
import com.ingroinfo.mm.dto.BranchDto;
import com.ingroinfo.mm.dto.CompanyDto;
import com.ingroinfo.mm.dto.UserDto;
import com.ingroinfo.mm.dto.UserRolesDto;
import com.ingroinfo.mm.entity.Bank;
import com.ingroinfo.mm.entity.Branch;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.Role;
import com.ingroinfo.mm.entity.State;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.entity.UserRole;
import com.ingroinfo.mm.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

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

	private void register(User user) {
		user.setPassword(getEncodedPassword(user.getPassword()));
		userRepository.save(user);
	}

	private void userRoleEntry(Long roleId) {

		User admin = userRepository.findByUserType("A");
		Long adminUserId = admin.getUserId();

		try {

			String checkSql = "SELECT * FROM USERS_ROLES WHERE USER_ID = " + adminUserId + " AND ROLE_ID= " + roleId;
			int count = jdbcTemplate.update(checkSql);

			if (count == 0) {

				String sql = "INSERT INTO USERS_ROLES (USER_ID,ROLE_ID) VALUES (?, ?)";
				jdbcTemplate.update(sql, adminUserId, roleId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerCompany(User user) {

		String roleName = "ROLE_" + user.getName().toUpperCase() + "_COMPANY";
		String description = "This is " + user.getName() + "company role";
		Role role = roleRepository.findByName(roleName);

		if (role == null) {
			role = new Role(roleName, description);
			roleRepository.save(role);
		}

		userRoleEntry(role.getId());

		user.setRoles(Arrays.asList(role));
		user.setBranch(null);
		user.setUserType("C");
		register(user);

	}

	@Override
	public void registerBranch(User user) {

		String roleName = "ROLE_" + user.getName().toUpperCase() + "_BRANCH";
		String description = "This is " + user.getName() + "branch role";
		Role role = roleRepository.findByName(roleName);

		if (role == null) {
			role = new Role(roleName, description);
			roleRepository.save(role);
		}

		userRoleEntry(role.getId());

		user.setRoles(Arrays.asList(role));
		user.setUserType("B");
		register(user);

		try {

			User admin = userRepository.findByUserType("C");
			String sql = "INSERT INTO USERS_ROLES (USER_ID,ROLE_ID) VALUES (?, ?)";
			jdbcTemplate.update(sql, admin.getUserId(), role.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void registerUser(User user, Long roleId) {

		Role userRole = roleRepository.findById(roleId).get();
		user.setRoles(Arrays.asList(userRole));
		user.setUserType("U");
		register(user);

		userRoleEntry(roleId);

		User companyUserDetail = userRepository.findAll().stream()
				.filter(o -> o.getEmail().equalsIgnoreCase(user.getCompany().getEmail())).findFirst().get();
		User branchUserDetail = userRepository.findAll().stream()
				.filter(o -> o.getEmail().equalsIgnoreCase(user.getBranch().getEmail())).findFirst().get();

		Long companyId = companyUserDetail.getUserId();
		Long branchId = branchUserDetail.getUserId();

		try {

			String checkCompanySql = "SELECT * FROM USERS_ROLES WHERE USER_ID = " + companyId + " AND ROLE_ID= "
					+ roleId;
			String checkBranchSql = "SELECT * FROM USERS_ROLES WHERE USER_ID = " + branchId + " AND ROLE_ID= " + roleId;
			String sql = "INSERT INTO USERS_ROLES (USER_ID,ROLE_ID) VALUES (?, ?)";
			int count1 = jdbcTemplate.update(checkCompanySql);
			int count2 = jdbcTemplate.update(checkBranchSql);

			if (count1 == 0) {
				jdbcTemplate.update(sql, companyId, roleId);
			}

			if (count2 == 0) {
				jdbcTemplate.update(sql, branchId, roleId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public void deleteCompanyById(Long companyId) {

		Company company = companyRepository.findByCompanyId(companyId);

		User user = userRepository.findAll().stream().filter(o -> o.getEmail().equalsIgnoreCase(company.getEmail()))
				.findFirst().get();

		Collection<Role> roles = userRepository.findByUserId(user.getUserId()).getRoles();
		List<Long> roleId = roles.stream().map(role -> role.getId()).collect(Collectors.toList());

		for (int i = 0; i < roleId.size(); i++) {
			deleteRoleById(roleId.get(i));
		}

		try {

			String sql = "DELETE FROM USERS_ROLES WHERE USER_ID= ? AND ROLE_ID = ?";
			jdbcTemplate.update(sql, user.getUserId(), roleId);

		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public Company getCompanyById(Long id) {
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

		Branch branch = branchRepository.findByBranchId(branchId);

		User user = userRepository.findAll().stream().filter(o -> o.getEmail().equals(branch.getEmail())).findFirst()
				.get();

		Collection<Role> roles = userRepository.findByUserId(user.getUserId()).getRoles();
		List<Long> roleId = roles.stream().map(role -> role.getId()).collect(Collectors.toList());

		for (int i = 0; i < roleId.size(); i++) {
			deleteRoleById(roleId.get(i));
		}

		try {

			String sql = "DELETE FROM USERS_ROLES WHERE USER_ID= ? AND ROLE_ID = ?";
			jdbcTemplate.update(sql, user.getUserId(), roleId);

		} catch (Exception e) {
			e.printStackTrace();
		}

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
	public void updateUserDetailsForCompany(CompanyDto companyDto) {

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
	public Branch getBranchById(Long id) {
		return branchRepository.findByBranchId(id);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean branchEmailCheck(BranchDto branchDto) {

		boolean isExistsUser = userRepository.findAll().stream()
				.filter(x -> !branchRepository.findByBranchId(branchDto.getBranchId()).equals(x.getBranch()))
				.collect(Collectors.toList()).stream().filter(o -> o.getEmail().equals(branchDto.getEmail()))
				.findFirst().isPresent();

		boolean isExistsBranch = branchRepository.findAll().stream()
				.filter(x -> !branchDto.getBranchId().equals(x.getBranchId())).collect(Collectors.toList()).stream()
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
	public void updateUserDetailsForBranch(BranchDto branchDto) {
		User user = userRepository.findByEmail(branchDto.getEmail());
		user.setEmail(branchDto.getEmail());
		user.setMobile(branchDto.getMobile());
		user.setName(branchDto.getBranchName());
		user.setUsername(branchDto.getUsername());
		user.setRemarks(branchDto.getRemarks());

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
	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findByUserId(id);
	}

	@Override
	public List<Role> getAllRolesOnlyWithoutAdmin() {
		return roleRepository.findAll().stream()
				.map(r -> new Role(r.getId(), r.getName().replace("ROLE_", ""), r.getDescription(), r.getDateCreated(),
						r.getLastUpdated()))
				.collect(Collectors.toList()).stream().filter(o -> !o.getName().equals("ADMIN"))
				.collect(Collectors.toList());
	}

	@Override
	public List<Role> getAllRolesWithoutAdminAndCompany() {
		return roleRepository.findAll().stream()
				.map(r -> new Role(r.getId(), r.getName().replace("ROLE_", ""), r.getDescription(), r.getDateCreated(),
						r.getLastUpdated()))
				.collect(Collectors.toList()).stream()
				.filter(o -> !o.getName().equals("ADMIN") && !o.getName().contains("COMPANY"))
				.collect(Collectors.toList());
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll().stream()
				.map(r -> new Role(r.getId(), r.getName().replace("ROLE_", ""), r.getDescription(), r.getDateCreated(),
						r.getLastUpdated()))
				.collect(Collectors.toList()).stream().filter(o -> !o.getName().equals("ADMIN")
						&& !o.getName().contains("COMPANY") && !o.getName().contains("BRANCH"))
				.collect(Collectors.toList());
	}

	@Override
	public void addRole(Role role) {

		roleRepository.save(role);
		Role newRole = roleRepository.findByName(role.getName());

		userRoleEntry(newRole.getId());
	}

	@Override
	public void updateRole(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void deleteRoleById(Long roleId) {
		User admin = userRepository.findByUsername("Admin");
		String sql = "DELETE FROM USERS_ROLES WHERE USER_ID= ? AND ROLE_ID = ?";
		jdbcTemplate.update(sql, admin.getUserId(), roleId);

		roleRepository.deleteById(roleId);
	}

	@Override
	public Role getRoleById(Long roleId) {
		return roleRepository.findById(roleId).get();
	}

	@Override
	public boolean roleExists(String roleName) {

		return roleRepository.findByName(roleName) != null;
	}

	@Override
	public boolean roleNameCheck(String roleName, Long id) {

		return roleRepository.findAll().stream().filter(x -> !id.equals(x.getId())).collect(Collectors.toList())
				.stream().filter(o -> o.getName().equals(roleName)).findFirst().isPresent();
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

	private void deleteRole(Long roleId, String PageNumber) {
		try {
			String no[] = PageNumber.split("N");
			long pageNo = Long.parseLong(no[0]);

			String sql = "SELECT * FROM ROLE_PRIVILEGES WHERE ROLE_ID = " + roleId + " AND PAGE_NO =" + pageNo;

			int count = jdbcTemplate.update(sql);

			if (count > 0) {
				String sql2 = "DELETE FROM ROLE_PRIVILEGES WHERE ROLE_ID= ? AND PAGE_NO = ?";
				jdbcTemplate.update(sql2, roleId, pageNo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createRole(Long roleId, String PageNumber) {
		try {
			long pageNo = Long.parseLong(PageNumber);

			String sql = "SELECT * FROM ROLE_PRIVILEGES WHERE ROLE_ID = " + roleId + " AND PAGE_NO =" + pageNo;

			int count = jdbcTemplate.update(sql);

			if (count == 0) {

				String sql1 = "INSERT INTO ROLE_PRIVILEGES (ROLE_ID,PAGE_NO) VALUES (?, ?)";
				jdbcTemplate.update(sql1, roleId, pageNo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void AssignRoles(UserRolesDto dto) {

		Long roleId = dto.getRoleId();

		if (dto.getAdminpage().contains("N")) {
			deleteRole(roleId, dto.getAdminpage());
		} else {
			createRole(roleId, dto.getAdminpage());
		}
		if (dto.getCompanyManagement().contains("N")) {
			deleteRole(roleId, dto.getCompanyManagement());
		} else {
			createRole(roleId, dto.getCompanyManagement());
		}
		if (dto.getCreateCompany().contains("N")) {
			deleteRole(roleId, dto.getCreateCompany());
		} else {
			createRole(roleId, dto.getCreateCompany());
		}
		if (dto.getEditCompany().contains("N")) {
			deleteRole(roleId, dto.getEditCompany());
		} else {
			createRole(roleId, dto.getEditCompany());
		}
		if (dto.getViewCompany().contains("N")) {
			deleteRole(roleId, dto.getViewCompany());
		} else {
			createRole(roleId, dto.getViewCompany());
		}
		if (dto.getDeleteCompany().contains("N")) {
			deleteRole(roleId, dto.getDeleteCompany());
		} else {
			createRole(roleId, dto.getDeleteCompany());
		}

	}

	@Override
	public UserRolesDto getUserRoles(Long roleId) {

		UserRolesDto pages = new UserRolesDto();

		try {
			String sql = "SELECT * FROM ROLE_PRIVILEGES WHERE ROLE_ID = " + roleId + " ORDER BY PAGE_NO";
			int count = jdbcTemplate.update(sql);

			if (count > 0) {
				List<UserRole> userRoles = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserRole.class));

				for (int i = 0; i < userRoles.size(); i++) {

					int pageNo = userRoles.get(i).getPageNo();

					if (pageNo == 100) {
						pages.setAdminpage(String.valueOf(pageNo));
					} else if (pageNo == 101) {
						pages.setCompanyManagement(String.valueOf(pageNo));
					} else if (pageNo == 102) {
						pages.setCreateCompany(String.valueOf(pageNo));
					} else if (pageNo == 103) {
						pages.setEditCompany(String.valueOf(pageNo));
					} else if (pageNo == 104) {
						pages.setViewCompany(String.valueOf(pageNo));
					} else if (pageNo == 105) {
						pages.setDeleteCompany(String.valueOf(pageNo));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pages;
	}

	@Override
	public boolean userEmailCheck(UserDto userDto) {

		return userRepository.findAll().stream().filter(x -> !userDto.getUserId().equals(x.getUserId()))
				.collect(Collectors.toList()).stream().filter(o -> o.getEmail().equals(userDto.getEmail())).findFirst()
				.isPresent();
	}

	@Override
	public boolean userUsernameCheck(UserDto userDto) {
		return userRepository.findAll().stream().filter(x -> !userDto.getUserId().equals(x.getUserId()))
				.collect(Collectors.toList()).stream().filter(o -> o.getUsername().equals(userDto.getUsername()))
				.findFirst().isPresent();
	}

	@Override
	public void updateUser(UserDto userDto) {

		User user = getUserById(userDto.getUserId());

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setMobile(userDto.getMobile());
		user.setRemarks(userDto.getRemarks());
		user.setUsername(userDto.getUsername());

		if (userDto.getPassword().length() == 0) {
			user.setPassword(user.getPassword());
			userRepository.save(user);
		} else {
			user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
			userRepository.save(user);
		}
	}

	@Override
	public Long getRoleIdByUserId(Long id) {

		Collection<Role> roles = userRepository.findByUserId(id).getRoles();
		List<Long> roleId = roles.stream().map(role -> role.getId()).collect(Collectors.toList());

		return roleId.get(0);
	}

}
