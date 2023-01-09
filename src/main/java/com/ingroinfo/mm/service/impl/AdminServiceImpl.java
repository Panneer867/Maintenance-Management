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
import com.ingroinfo.mm.entity.Bank;
import com.ingroinfo.mm.entity.Branch;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.State;
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

	@Override
	public void registerCompany(User user) {
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
		register(user);

	}

	@Override
	public void registerBranch(User user) {
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_BRANCH")));
		register(user);
	}

	@Override
	public void registerUser(User user) {
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
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
	public User getUserByCompanyId(Long id) {
		return userRepository.findByCompany(companyRepository.findByCompanyId(id));
	}

	@Override
	public Integer getStateId(String name) {
		return stateRepository.findByName(name).getId();
	}

}
