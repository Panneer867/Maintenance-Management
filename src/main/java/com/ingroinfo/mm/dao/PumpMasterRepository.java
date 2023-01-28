package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.PumpMaster;

@Repository
public interface PumpMasterRepository extends JpaRepository<PumpMaster, Long> {

	@Query("select max(pumpId) from PumpMaster")
	String getMaxPumpId();
	
	PumpMaster getPumpMasterByPumpId(String pumpId);
}