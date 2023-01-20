package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.AssestEntryDto;

public interface AssestEntryService {
	
	// Save info
	AssestEntryDto saveAssestEntry(AssestEntryDto assestEntry);

	//Show All 
	List<AssestEntryDto> findAllAssestEntry();
}
