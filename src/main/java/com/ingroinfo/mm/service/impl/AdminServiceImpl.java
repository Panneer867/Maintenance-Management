package com.ingroinfo.mm.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.dao.BankRepository;
import com.ingroinfo.mm.dao.CompanyRepository;
import com.ingroinfo.mm.dao.RoleRepository;
import com.ingroinfo.mm.dao.StateRepository;
import com.ingroinfo.mm.dao.UserRepository;
import com.ingroinfo.mm.entity.Bank;
import com.ingroinfo.mm.entity.Company;
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
	public void saveCompany(Company company) {
		companyRepository.save(company);
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
	public boolean emailExists(String email) {

		return (companyRepository.findByEmail(email) != null && userRepository.findByEmail(email) != null);
	}
}
