package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.MaintanceFrequencyDto;

public interface MaintanceFrequencyService {

	//save Data
	MaintanceFrequencyDto saveMaintanceFrequency(MaintanceFrequencyDto maintanFrequency);
	
	//Find All Data
	List<MaintanceFrequencyDto> getAllMaintanceFrequency();
	
	//Delete
	void deleteMaintenFrequency(Long maintanFrequId);
}
