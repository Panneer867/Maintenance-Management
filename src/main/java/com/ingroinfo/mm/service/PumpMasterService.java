package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.PumpMasterDto;

public interface PumpMasterService {

	//create
	PumpMasterDto savePumpMaster(PumpMasterDto pumpDto);
	
	//Find All data
	List<PumpMasterDto> getAllPumpMaster();
	
	//Delete
	void deletePumpMaster(Long pumpMasterId);	
	
	//get PumpData By pump Id
	PumpMasterDto getPumpDataByPumpId(String pumpId);
}
