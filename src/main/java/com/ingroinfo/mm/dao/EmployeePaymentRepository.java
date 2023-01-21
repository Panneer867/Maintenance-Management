package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.EmployeePayment;

@Repository
public interface EmployeePaymentRepository extends JpaRepository<EmployeePayment, Long> {

}
