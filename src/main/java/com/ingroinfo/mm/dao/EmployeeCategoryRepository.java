package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.EmployeeCategory;

@Repository
public interface EmployeeCategoryRepository extends JpaRepository<EmployeeCategory, Long> {

	EmployeeCategory findByDepartment(String department);

}
