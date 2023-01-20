package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.EmployeePerformRepostory;
import com.ingroinfo.mm.dto.EmployeePerformanceDto;
import com.ingroinfo.mm.entity.EmployeePerformance;
import com.ingroinfo.mm.service.EmployeePerformService;

@Service
public class EmployeePerformServiceImpl implements EmployeePerformService {

	@Autowired
	private EmployeePerformRepostory empPerformRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public EmployeePerformanceDto saveEmplyeePerformmance(EmployeePerformanceDto empPerformance) {
	    EmployeePerformance convEmpPerform = this.modelMapper.map(empPerformance, EmployeePerformance.class);
	    EmployeePerformance savedEmpPerform = this.empPerformRepo.save(convEmpPerform);
		return modelMapper.map(savedEmpPerform, EmployeePerformanceDto.class);
	}

	@Override
	public List<EmployeePerformanceDto> getAllEmpPerformance() {
		List<EmployeePerformance> empPerforms = this.empPerformRepo.findAll();
		List<EmployeePerformanceDto> empPerformDtos = empPerforms.stream().map((empPerform) -> 
		this.modelMapper.map(empPerform, EmployeePerformanceDto.class)).collect(Collectors.toList());
		return empPerformDtos;
	}

	@Override
	public void deleteEmpPerformance(Long empPerformId) {
		EmployeePerformance employeePerformance = this.empPerformRepo.findById(empPerformId).get();
		this.empPerformRepo.delete(employeePerformance);
	}

}
