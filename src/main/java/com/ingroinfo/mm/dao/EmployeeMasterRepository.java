package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.EmployeeMaster;
@Repository
public interface EmployeeMasterRepository extends JpaRepository <EmployeeMaster, Long>{

	EmployeeMaster findByEmployeeId(Long employeeId);
	
	EmployeeMaster findByEmployeeCode(String employeeCode);
}
