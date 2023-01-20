package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.EmployeePerformance;

@Repository
public interface EmployeePerformRepostory extends JpaRepository<EmployeePerformance, Long> {

}
