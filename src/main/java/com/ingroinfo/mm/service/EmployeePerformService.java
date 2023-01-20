package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.EmployeePerformanceDto;

public interface EmployeePerformService {

	//save Data
	EmployeePerformanceDto saveEmplyeePerformmance(EmployeePerformanceDto empPerformance);
	
	//Find All Data
	List<EmployeePerformanceDto> getAllEmpPerformance();
	
	//Delete
	void deleteEmpPerformance(Long empPerformId);
}
