package com.ingroinfo.mm.service;

import java.util.List;
import java.util.Map;
import com.ingroinfo.mm.dto.EmployeeMasterDto;
import com.ingroinfo.mm.dto.EmployeeLeaveDto;
import com.ingroinfo.mm.entity.EmployeeLeave;
import com.ingroinfo.mm.entity.EmployeeMaster;

public interface EmployeeMasterService {
	
	//get Maximum Employee Code
	String getMaxEmployeeCode();
	
	//get Maximum SanSL
	int getMaxSanSlLeave(String employeeCode);
	
	//get Maximum SanCL
	int getMaxSanClLeave(String employeeCode);
	
	//get Maximum SanCL
	int getMaxSanLwpLeave(String employeeCode);
	
	// Save Employee Master
	EmployeeMasterDto saveEmployeeMaster(EmployeeMasterDto empMasterDto);

	// Delete Employee Master
	void deleteEmployeeById(Long employeeId);

	// get Employee Master By Id
	EmployeeMaster getEmployeeById(Long employeeId);

	EmployeeLeave getEmployeeLeaveById(Long empLeaveId);
	
	// Update Employee Master
	void saveEmployee(EmployeeMaster employee);
	
	// Update Employee Leave
		void saveEmployeeLeave(EmployeeLeave empLeave);

	// get All Employee Master
	List<EmployeeMasterDto> getAllemployeeMaster();

	// get Employee Master By EmpCode
	EmployeeMasterDto getEmployeeByEmpCode(String employeeCode);

	// get Employee Master By EmployeeCode
	EmployeeLeaveDto getEmpLeaveByEmpCode(String employeeCode);
	
	// Save Employee Leave
    EmployeeLeaveDto saveEmployeeLeave(EmployeeLeaveDto empLeaveDto);
	
	// Find All Employee Leave
    List<EmployeeLeaveDto> getAllEmployeeLeave();
    
	/*
	 * // get By EmpLeaveId EmployeeLeaveDto getByEmpLeaveId(Long empLeaveId);
	 */
	
	//Get Employee Leave Data For Approval By Hr Approval
	List<EmployeeLeaveDto> getEmployeeLeaveByHrApproval(String hrApproval); 
	
	// Get EmployeeCode By department
	List<EmployeeMasterDto> getEmployeeCodeByDept(String department);
	
	Map<Integer, Integer> getEmployeeLeaveMonthwise(String empCode);
	

}
