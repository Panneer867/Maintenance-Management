package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.EmployeeMasterDto;
import com.ingroinfo.mm.dto.EmployeePaymentDto;
import com.ingroinfo.mm.entity.EmployeeInspection;
import com.ingroinfo.mm.entity.EmployeeMaster;



public interface EmployeeMasterService {
	

	
	
	
	
	void deleteEmployeeById(Long employeeId);
	
	EmployeeMaster getEmployeeById(Long employeeId);
	
	void saveEmployeeInspect(EmployeeInspection employeeInspection);


    void saveEmployeeMaster(EmployeeMaster employee);
   
    List<EmployeeMasterDto> getAllemployeeMaster();

     void  updateEmployee(EmployeeMaster employee);
     
     EmployeeMasterDto getEmployeeByEmpCode(String employeeCode);
     
     //Save Employee Payment
     EmployeePaymentDto saveEmployeePayment(EmployeePaymentDto empPaymentDto);
     
	 // Save Employee Master
     EmployeeMasterDto saveEmployeeMaster(EmployeeMasterDto empMasterDto);
}
