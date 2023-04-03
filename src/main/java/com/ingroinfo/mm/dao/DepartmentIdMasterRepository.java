package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.DepartmentIdMaster;

@Repository
public interface DepartmentIdMasterRepository extends JpaRepository<DepartmentIdMaster, Long> {

	public List<DepartmentIdMaster> findByMasterIdName(String masterIdName);
	
	@Query("from DepartmentIdMaster d where d.masterIdName=:masterIdName and d.deptName=:deptName")
	public DepartmentIdMaster getByDeptIdNameAndDeptName(String masterIdName,String deptName);

	public boolean existsByDeptNameAndMasterIdName(String masterIdName, String deptName);

	public boolean existsByDeptIdAndMasterIdName(String masterIdName, String deptId);
		
}
