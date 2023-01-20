package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.PressureTypeDto;

public interface PressureTypeService {

	//create
	PressureTypeDto savePressureType(PressureTypeDto pressureDto);
	
	//Get All Data
	List<PressureTypeDto> getAllPressureType();
	
	//Delete
	void deletePressureType(Long pressureId);
}
