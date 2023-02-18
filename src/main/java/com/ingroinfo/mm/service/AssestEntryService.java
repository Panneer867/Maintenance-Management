package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.AssestEntryDto;
import com.ingroinfo.mm.entity.AssestEntry;

public interface AssestEntryService {
	
	// Save info
	AssestEntryDto saveAssestEntry(AssestEntryDto assestEntry);

	//Show All 
	List<AssestEntry> findAllAssestEntry();
	
	// get the data By Id
	AssestEntryDto getAssestEntryById(Long assestEntryId);
}
