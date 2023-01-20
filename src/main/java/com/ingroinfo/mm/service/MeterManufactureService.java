package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.MeterManufactureDto;

public interface MeterManufactureService {

	//save 
	MeterManufactureDto saveMeterManufact(MeterManufactureDto meterManufactDto);
	
	//Get All Data
	List<MeterManufactureDto> findAllMeterManufact();
	
	//Delete
	void deleteMeterManufacture(Long mtrmanufacId);
	
	//get Max Meter Id
	String getMaxMeterId();
}
