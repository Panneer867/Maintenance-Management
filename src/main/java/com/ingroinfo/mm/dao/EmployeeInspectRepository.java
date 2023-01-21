package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.EmployeeInspection;

@Repository
public interface EmployeeInspectRepository extends JpaRepository<EmployeeInspection, Long> {


}
