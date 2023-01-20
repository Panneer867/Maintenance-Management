package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.MeterTypeDto;

public interface MeterTypeService {

	//create
	MeterTypeDto saveMeterType(MeterTypeDto meterTypeDto);
	
	//Find All data
	List<MeterTypeDto> getAllMeterType();
	
	//Delete
	void deleteMeterType(Long meterTypeId);
	
}
