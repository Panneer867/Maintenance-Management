package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.PumpMaster;

@Repository
public interface PumpMasterRepository extends JpaRepository<PumpMaster, Long> {
	
	PumpMaster getPumpMasterByPumpId(String pumpId);

	boolean existsBymanufactNameAndPumpType(String manufactName, String pumpType);
}
