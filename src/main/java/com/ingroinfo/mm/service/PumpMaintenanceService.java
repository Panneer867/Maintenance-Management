package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.PumpMaintenanceDto;

public interface PumpMaintenanceService {

	//save Data
	PumpMaintenanceDto savePumpMaintenance(PumpMaintenanceDto pumpMaintenDto);
	
	//get All Data
	List<PumpMaintenanceDto> getAllMaintenance();
	
	//get Data by Pump Maintenance Id
	PumpMaintenanceDto getPumpMaintenByPumpMaintenId(Long pumpMaintenId);
	
	//get All Data By Pump Maintenance Status
	//List<PumpMaintenanceDto> getAllMaintenByPumpMaintenSts(String pumpMaintenSts);
}
