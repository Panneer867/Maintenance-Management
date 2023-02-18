package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.EmployeeMaster;

@Repository
public interface EmployeeMasterRepository extends JpaRepository <EmployeeMaster, Long>{

	EmployeeMaster findByEmployeeId(Long employeeId);
	
	EmployeeMaster findByEmployeeCode(String employeeCode);
	
	/*
	 * @Query("from EmployeeMaster e where e.department =:department") public
	 * List<EmployeeMaster> findEmpCodeByDept(String department);
	 */
	  
	List<EmployeeMaster> findByDepartment(String department);
	  

    @Query("select max(employeeCode) from EmployeeMaster")
    String getMaxEmployeeCode();
}
