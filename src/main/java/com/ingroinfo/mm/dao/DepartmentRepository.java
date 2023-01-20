package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query("select max(departmentId) from Department")
	String getMaxDepartmentId();
		
}
