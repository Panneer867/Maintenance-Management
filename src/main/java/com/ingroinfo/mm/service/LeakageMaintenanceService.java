package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.LeakageMainteUpdateDto;


public interface LeakageMaintenanceService {

	//save Data
	LeakageMainteUpdateDto saveMaintenanceType(LeakageMainteUpdateDto leakageMainteUpdateDto);
	
	//findAll Data
	List<LeakageMainteUpdateDto> findAllLeakageMainteUpdate();
	
	//Delete
	void deleteLekageMainteUpdate(Long leakageUpdateId);
}
