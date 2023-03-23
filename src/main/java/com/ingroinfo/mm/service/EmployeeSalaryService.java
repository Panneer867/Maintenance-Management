package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.EmployeeSalaryDto;

public interface EmployeeSalaryService {
	
	// Save Employee Salary
    EmployeeSalaryDto saveEmployeeSalary(EmployeeSalaryDto employeeSalaryDto);
    
   // get All Employee Master
 	List<EmployeeSalaryDto> getAllemployeeSalary();
}
