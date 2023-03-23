package com.ingroinfo.mm.service.impl;

import java.util.Date;
import java.util.List;
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
	public void updateEmployeeLeave(EmployeeLeave empLeave) {
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

	
	@Override
	public EmployeeMasterDto getEmployeeById(Long employeeId) {
		EmployeeMaster employeeMaster = employeeMasterRepository.findById(employeeId).get();
		return this.modelMapper.map(employeeMaster, EmployeeMasterDto.class);
		
	}

	@Override
	public EmployeeLeave getEmpLeaveById(Long empLeaveId) {
	
	   EmployeeLeave employeeLeave  = employeeLeaveRepository.findById(empLeaveId).get();
		return employeeLeave;
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
		List<EmployeeMaster> employees = this.employeeMasterRepository.findAllEmployeeMaster();
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
	public List<EmployeeMasterDto> getEmployeeCodeByDept(String department) {
		List<EmployeeMaster> employeeCodes   = employeeMasterRepository.findByDepartment(department);
		List<EmployeeMasterDto> employeeMasterDto = employeeCodes.stream().map((employeeCode) -> 
		modelMapper.map(employeeCode, EmployeeMasterDto.class)).collect(Collectors.toList());
		return employeeMasterDto;
	}


	
	  @Override 
	  public List<EmployeeMasterDto> getNoOfEmpByDept() {
	  List<EmployeeMaster> employeeMaster = employeeMasterRepository.findDeptWiseNoEmp();
	  List<EmployeeMasterDto> employeeMasterDto = employeeMaster.stream().map((employee) ->
	  modelMapper.map(employee,EmployeeMasterDto.class)).collect(Collectors.toList()); 
	  return employeeMasterDto;
	  }
	 

	
	
	@Override
	public EmployeeLeaveDto getEmployeeLeaveById(Long empLeaveId) {
	EmployeeLeave employeeLeave =	this.employeeLeaveRepository.findById(empLeaveId).get();		
		return this.modelMapper.map(employeeLeave, EmployeeLeaveDto.class);
	}

	

	
}
