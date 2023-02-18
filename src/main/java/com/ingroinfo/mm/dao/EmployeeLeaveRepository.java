
package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.EmployeeLeave;
@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long> {

	EmployeeLeave findByEmpLeaveId(Long empLeaveId);
	
	@Query("from EmployeeLeave e where e.employeeCode =:employeeCode")
	EmployeeLeave findleaveByEmployeeCode(String employeeCode);

	@Query("from EmployeeLeave a where a.hrApproval =:hrApproval  ORDER BY a.leaveDate ASC")
	List<EmployeeLeave> findByHrApproval(String hrApproval);
	
	@Query("select max(sacnSickLeave) from EmployeeLeave")
     int getMaxScanSL();

	@Query("select max(e.sacnSickLeave) from EmployeeLeave e where e.employeeCode =:employeeCode")
	 int getSenctionLeaveByLeaveType(String employeeCode);
	
	@Query("select max(e.sacnCausalLeave) from EmployeeLeave e where  e.employeeCode =:employeeCode")
	 int getSancationCLByLeaveType(String employeeCode);
	
	@Query("select max(e.sacnLwp) from EmployeeLeave e where e.employeeCode =:employeeCode")
	 int getSancationLwpByLeaveType(String employeeCode);
	
	List<EmployeeLeave> findByEmployeeCode(String empCode);
}
