package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.MaintenancePerformanceDto;

public interface MaintenancePerformService {

	//save Data
	MaintenancePerformanceDto saveMaintenPerform(MaintenancePerformanceDto maintenPerformDto);
	
	//find All Data
	List<MaintenancePerformanceDto> getAllMaintenPerform();
	
	//Delete
	void deleteMaintainsPerformance(Long maintenPerformId);
}
