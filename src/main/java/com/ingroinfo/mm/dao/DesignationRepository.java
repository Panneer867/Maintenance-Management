package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {

	boolean existsByDesignation(String designation);

}
