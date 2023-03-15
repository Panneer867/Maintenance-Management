package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.PumpMaintenanceDto;
import com.ingroinfo.mm.dto.PumpMaterialDto;

public interface PumpMaintenanceService {

	//save Pump Index Data
	PumpMaintenanceDto savePumpMaintenance(PumpMaintenanceDto pumpMaintenDto);
	
	//get All Data
	List<PumpMaintenanceDto> getAllMaintenance();
	
	//get Data by Pump Maintenance Id
	PumpMaintenanceDto getPumpMaintenByPumpMaintenId(Long pumpMaintenId);

	//Add Pump Material Data
	PumpMaterialDto addPumpMaterial(PumpMaterialDto pumpMaterialDto);

	//Get List Of Added Data
	List<PumpMaterialDto> findListOfAddedPumpData(String indentType, String indentNo, String complNo);

	//Delete Added Pump Material Data From List
	boolean deleteMateialById(Long pumMaterialId);
	
	//Get Pump Material Added List By Complain Number
	List<PumpMaterialDto> getPumpIndentAddedDataByComplNo(String complNo);

	//Delete All Added Materials By Complain No
	void deleteAllAddedMaterialByComplNo(String complNo);
	
}
