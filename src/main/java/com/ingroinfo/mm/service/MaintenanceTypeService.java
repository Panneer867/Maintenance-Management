package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.MaintenanceTypeDto;

public interface MaintenanceTypeService {

	//create Data
	MaintenanceTypeDto saveMaintenanceType(MaintenanceTypeDto maintenTypeDto);
	
	//findAll Data
	List<MaintenanceTypeDto> findAllMaintenanceType();
	
	//Delete
	void deleteMaintainsType(Long maintenTypeId);
}
