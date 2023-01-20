package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.MaintenanceActivitiesDto;

public interface MaintenanceActivitiesService {

	//save Data
	MaintenanceActivitiesDto saveMaintenActivity(MaintenanceActivitiesDto maintenActivDto);
	
	//find All Data
	List<MaintenanceActivitiesDto> findAllMaintnActve();
	
	//Delete
	void deleteMaintenActivity(Long maintenActiveId);
}
