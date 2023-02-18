package com.ingroinfo.mm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.EmployeeLeaveRepository;
import com.ingroinfo.mm.dao.EmployeeMasterRepository;
import com.ingroinfo.mm.dto.EmployeeLeaveDto;
import com.ingroinfo.mm.dto.EmployeeMasterDto;
import com.ingroinfo.mm.entity.EmployeeLeave;
import com.ingroinfo.mm.entity.EmployeeMaster;
import com.ingroinfo.mm.service.EmployeeMasterService;

@Service
public class EmployeeMasterServiceImpl implements EmployeeMasterService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EmployeeMasterRepository employeeMasterRepository;	
	@Autowired
	private EmployeeLeaveRepository employeeLeaveRepository;

	@Override
	public void deleteEmployeeById(Long employeeId) {
		employeeMasterRepository.deleteById(employeeId);
	}

	@Override
	public void saveEmployee(EmployeeMaster employee) {
		employeeMasterRepository.save(employee);
	}
	
	@Override
	public void saveEmployeeLeave(EmployeeLeave empLeave) {
		// TODO Auto-generated method stub
		employeeLeaveRepository.save(empLeave);
	}

	@Override
	public String getMaxEmployeeCode() {
		String maxEmployeeCode = this.employeeMasterRepository.getMaxEmployeeCode();
		return maxEmployeeCode;
	}

	@Override
	public int getMaxSanSlLeave(String employeeCode) {
		   int maxSanSL = this.employeeLeaveRepository.getSenctionLeaveByLeaveType(employeeCode);
		return maxSanSL;
	}

	@Override
	public int getMaxSanClLeave(String employeeCode) {
	int maxSanCL = this.employeeLeaveRepository.getSancationCLByLeaveType(employeeCode);
		return maxSanCL;
	}
	
	@Override
	public int getMaxSanLwpLeave(String employeeCode) {
	int maxSanLwp =	this.employeeLeaveRepository.getSancationLwpByLeaveType(employeeCode);
		return maxSanLwp;
	}

	
	/*
	 * @Override public List<EmployeeMasterDto> getAllemployeeMaster() {
	 * List<EmployeeMaster> employeeList = employeeMasterRepository.findAll();
	 * 
	 * List<EmployeeMasterDto> employeemasterDto =
	 * employeeList.stream().map((employeeMaster) -> { EmployeeMasterDto newEmployee
	 * = new EmployeeMasterDto();
	 * newEmployee.setEmployeeId(employeeMaster.getEmployeeId());
	 * newEmployee.setEmployeeCode(employeeMaster.getEmployeeCode());
	 * newEmployee.setEmpName(employeeMaster.getEmpName());
	 * newEmployee.setEmployeeImage(employeeMaster.getEmployeeImage());
	 * newEmployee.setFatherName(employeeMaster.getFatherName());
	 * newEmployee.setHouseNo(employeeMaster.getHouseNo());
	 * newEmployee.setAddress(employeeMaster.getAddress());
	 * newEmployee.setAadharNo(employeeMaster.getAadharNo());
	 * newEmployee.setBankAccNo(employeeMaster.getBankAccNo());
	 * newEmployee.setBankName(employeeMaster.getBankName());
	 * newEmployee.setContactNo(employeeMaster.getContactNo());
	 * newEmployee.setDesignation(employeeMaster.getDesignation());
	 * newEmployee.setDepartment(employeeMaster.getDepartment());
	 * newEmployee.setDlNo(employeeMaster.getDlNo());
	 * newEmployee.setPanNO(employeeMaster.getPanNO());
	 * newEmployee.setPassportNo(employeeMaster.getPassportNo());
	 * newEmployee.setPfNo(employeeMaster.getPfNo());
	 * newEmployee.setEsiNumber(employeeMaster.getEsiNumber());
	 * newEmployee.setIfscCode(employeeMaster.getIfscCode());
	 * newEmployee.setRefContactNo(employeeMaster.getRefContactNo());
	 * newEmployee.setDateOfJoin(employeeMaster.getDateOfJoin());
	 * newEmployee.setBloodGroup(employeeMaster.getBloodGroup());
	 * newEmployee.setGender(employeeMaster.getGender());
	 * newEmployee.setBranch(employeeMaster.getBranch());
	 * newEmployee.setCompany(employeeMaster.getCompany());
	 * newEmployee.setCity(employeeMaster.getCity());
	 * newEmployee.setDateOfBirth(employeeMaster.getDateOfBirth());
	 * newEmployee.setEndDate(employeeMaster.getEndDate());
	 * newEmployee.setEmpStatus(employeeMaster.getEmpStatus());
	 * newEmployee.setEmptype(employeeMaster.getEmptype());
	 * newEmployee.setCl(employeeMaster.getCl());
	 * newEmployee.setLwp(employeeMaster.getLwp());
	 * newEmployee.setSl(employeeMaster.getSl());
	 * newEmployee.setMaritalstatus(employeeMaster.getMaritalstatus());
	 * newEmployee.setTotalLeave(employeeMaster.getTotalLeave());
	 * newEmployee.setState(employeeMaster.getState());
	 * newEmployee.setPersonName(employeeMaster.getPersonName());
	 * newEmployee.setQualification(employeeMaster.getQualification());
	 * newEmployee.setPinCode(employeeMaster.getPinCode());
	 * newEmployee.setState(employeeMaster.getState()); return newEmployee;
	 * }).collect(Collectors.toList());
	 * 
	 * return employeemasterDto; }
	 */
	
	@Override
	public EmployeeMaster getEmployeeById(Long employeeId) {
		Optional<EmployeeMaster> optional = employeeMasterRepository.findById(employeeId);
		EmployeeMaster employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + employeeId);
		}
		return employee;
	}

	@Override
	public EmployeeLeave getEmployeeLeaveById(Long empLeaveId) {
	
	   Optional<EmployeeLeave> optional  = employeeLeaveRepository.findById(empLeaveId);
	   EmployeeLeave employee = null;
	   if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + empLeaveId);
		}
		return employee;
	}

	
	
	
	@Override
	public EmployeeMasterDto getEmployeeByEmpCode(String employeeCode) {
		EmployeeMaster employee = this.employeeMasterRepository.findByEmployeeCode(employeeCode);
		return this.modelMapper.map(employee, EmployeeMasterDto.class);
	}
	
	@Override
	public EmployeeLeaveDto getEmpLeaveByEmpCode(String employeeCode) {
		EmployeeLeave employeeLeave = this.employeeLeaveRepository.findleaveByEmployeeCode(employeeCode);
		return this.modelMapper.map(employeeLeave, EmployeeLeaveDto.class);
	}

	@Override
	public EmployeeMasterDto saveEmployeeMaster(EmployeeMasterDto empMasterDto) {
		EmployeeMaster convEmployeeMaster = this.modelMapper.map(empMasterDto, EmployeeMaster.class);
		EmployeeMaster savedEmployeeMaster = this.employeeMasterRepository.save(convEmployeeMaster);
		return this.modelMapper.map(savedEmployeeMaster, EmployeeMasterDto.class);
	}

	@Override
	public EmployeeLeaveDto saveEmployeeLeave(EmployeeLeaveDto empLeaveDto) {
		EmployeeMaster employeeMaster = employeeMasterRepository.findByEmployeeCode(empLeaveDto.getEmployeeCode());
		empLeaveDto.setCreateDate(new Date());
	EmployeeLeave convEmployeeLeave	= this.modelMapper.map(empLeaveDto, EmployeeLeave.class);
	convEmployeeLeave.setEmployeeMaster(employeeMaster);
	EmployeeLeave savedEmployeeLeave= this.employeeLeaveRepository.save(convEmployeeLeave);
		return this.modelMapper.map(savedEmployeeLeave, EmployeeLeaveDto.class);
	}

	@Override
	public List<EmployeeLeaveDto> getAllEmployeeLeave() {
	List<EmployeeLeave>	employees   =  this.employeeLeaveRepository.findAll();
	List<EmployeeLeaveDto> empLeaveDto	=	employees.stream().map((employee) -> 
       this.modelMapper.map(employee, EmployeeLeaveDto.class)).collect(Collectors.toList());
		return empLeaveDto;
	}

	@Override
	public List<EmployeeMasterDto> getAllemployeeMaster() {
		List<EmployeeMaster> employees = this.employeeMasterRepository.findAll();
		List<EmployeeMasterDto> employeemasterDto = employees.stream().map((employee) -> 
		this.modelMapper.map(employee, EmployeeMasterDto.class)).collect(Collectors.toList());
		return employeemasterDto;
	}
	
	@Override
	public List<EmployeeLeaveDto> getEmployeeLeaveByHrApproval(String hrApproval) {
		List<EmployeeLeave> employeeLeaves = employeeLeaveRepository.findByHrApproval(hrApproval);
		List<EmployeeLeaveDto> employeeLeaveDtos = employeeLeaves.stream().map((employeeLeave) ->
		modelMapper.map(employeeLeave, EmployeeLeaveDto.class)).collect(Collectors.toList());
		return employeeLeaveDtos;
	}


	@Override
	public Map<Integer, Integer> getEmployeeLeaveMonthwise(String empCode) {
	
		List<EmployeeLeave> employeeLeaves =  employeeLeaveRepository.findByEmployeeCode(empCode);
		Map<Integer, Integer> leaves = new HashMap<Integer, Integer>();	
		int totalLeaves = 0;
		for (EmployeeLeave EmployeeLeave : employeeLeaves) {
			for (int i = 1; i <= 12; i++) {
				String smonth = EmployeeLeave.getLeaveDate().substring(5, 7);
				int month = Integer.parseInt(smonth);
				if (i == month) {
					if(EmployeeLeave.getHrApproval().equals("YES"))
					{
					totalLeaves = totalLeaves + EmployeeLeave.getSacnSickLeave();					
					leaves.put(month, totalLeaves);
					}
				}
			}
		}
		return leaves;
	}

	@Override
	public List<EmployeeMasterDto> getEmployeeCodeByDept(String department) {
		List<EmployeeMaster> employeeCodes   = employeeMasterRepository.findByDepartment(department);
		List<EmployeeMasterDto> employeeMasterDto = employeeCodes.stream().map((employeeCode) -> 
		modelMapper.map(employeeCode, EmployeeMasterDto.class)).collect(Collectors.toList());
		return employeeMasterDto;
	}

	
}
