package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	boolean existsByDepartmentName(String departmentName);
	
}
