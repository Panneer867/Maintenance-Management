package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.PumpMaintenanceDto;

public interface PumpMaintenanceService {

	//save Pump Index Data
	PumpMaintenanceDto savePumpMaintenance(PumpMaintenanceDto pumpMaintenDto);
	
	//get All Data
	List<PumpMaintenanceDto> getAllMaintenance();
	
	//get Data by Pump Maintenance Id
	PumpMaintenanceDto getPumpMaintenByPumpMaintenId(Long pumpMaintenId);

	//Delete Added Pump Material Data From List
	boolean deleteMateialById(Long pumMaterialId);
	
	
}
