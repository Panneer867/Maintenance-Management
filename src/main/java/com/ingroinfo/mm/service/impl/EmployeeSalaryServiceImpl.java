package com.ingroinfo.mm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.EmployeeMasterRepository;
import com.ingroinfo.mm.dao.EmployeeSalaryRepository;
import com.ingroinfo.mm.dto.EmployeeSalaryDto;
import com.ingroinfo.mm.entity.EmployeeMaster;
import com.ingroinfo.mm.entity.EmployeeSalary;
import com.ingroinfo.mm.service.EmployeeSalaryService;

@Service
public class EmployeeSalaryServiceImpl implements EmployeeSalaryService{

@Autowired
private EmployeeSalaryRepository employeeSalaryRepository;
@Autowired
private EmployeeMasterRepository employeeMasterRepository;
@Autowired
private ModelMapper modelMapper;
	@Override
	public EmployeeSalaryDto saveEmployeeSalary(EmployeeSalaryDto employeeSalaryDto) {
			EmployeeMaster employeeMaster =	employeeMasterRepository.findByEmployeeCode(employeeSalaryDto.getEmployeeId());
			employeeSalaryDto.setCreateDate(new Date());
			EmployeeSalary convEmployeeSalary =  this.modelMapper.map(employeeSalaryDto, EmployeeSalary.class);
	      
			convEmployeeSalary.setEmployeeMaster(employeeMaster);
			EmployeeSalary savedEmployeeSalary    =  this.employeeSalaryRepository.save(convEmployeeSalary);
			return this.modelMapper.map(savedEmployeeSalary, EmployeeSalaryDto.class);
	}
	
	@Override
	public List<EmployeeSalaryDto> getAllemployeeSalary() {
		   List<EmployeeSalary>  employees  = this.employeeSalaryRepository.findAll();
		   List<EmployeeSalaryDto> employeeSalaryDto = employees.stream().map((employee) ->
		   this.modelMapper.map(employee, EmployeeSalaryDto.class)).collect(Collectors.toList());
		   return employeeSalaryDto;
	}

	

}
