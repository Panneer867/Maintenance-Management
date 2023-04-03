package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.IdMaster;

@Repository
public interface IdMasterRepository extends JpaRepository<IdMaster, Long> {
	
	IdMaster getByMasterIdName(String masterIdName);

	boolean existsByMasterIdName(String masterIdName);
}
