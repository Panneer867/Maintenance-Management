package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.EmployeeMaster;

@Repository
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Long>{

	  @Query("from EmployeeMaster a order by a.employeeId DESC") 
	  public List<EmployeeMaster> findDeptWiseNoEmp();
		 
	EmployeeMaster findByEmployeeId(Long employeeId);
	
	EmployeeMaster findByEmployeeCode(String employeeCode);
	
	@Query("from EmployeeMaster a ORDER BY a.employeeCode ASC")
	List<EmployeeMaster> findAllEmployeeMaster();
	
	List<EmployeeMaster> findByDepartment(String department);
	 
	EmployeeMaster findByBranch(String branch); 

    @Query("select max(employeeCode) from EmployeeMaster")
    String getMaxEmployeeCode();
    
    @Query("SELECT COUNT(*) AS NumEmp, department FROM EmployeeMaster GROUP BY department")
    List<Object[]> getEmployeeCountByDepartment();
}
