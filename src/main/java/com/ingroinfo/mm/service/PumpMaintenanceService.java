package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.PumpMaintenanceInspectionDto;
import com.ingroinfo.mm.dto.PumpMaintenanceUpdatedDto;
import com.ingroinfo.mm.dto.PumpMaintenanceDto;

public interface PumpMaintenanceService {

	//save Pump Index Data
	PumpMaintenanceDto savePumpMaintenance(PumpMaintenanceDto pumpMaintenDto);
	
	//Save Pump Maintenance Updated Data
	PumpMaintenanceUpdatedDto savePumpMaintenanceUpdated(PumpMaintenanceUpdatedDto pumpDto);

	//find All Pump Maintenance Updated Data
	List<PumpMaintenanceUpdatedDto> getAllPumpMaintenanceUpdated();

	//Get Pump Maintenance Updated Data By WorkOrder Number
	PumpMaintenanceUpdatedDto getPumpMaintenanceUpdatedByWorkorderNo(String workOrderNo);

	//Save Pump Maintenance Inspection Data
	PumpMaintenanceInspectionDto savePumpInspectionData(PumpMaintenanceInspectionDto pumpInspectionDto);
	
	
}
