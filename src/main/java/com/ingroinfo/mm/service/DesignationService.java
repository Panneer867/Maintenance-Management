package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.DesignationDto;

public interface DesignationService {

	//save Data
	DesignationDto saveDesignation(DesignationDto designationDto);
	
	//find All
	List<DesignationDto> getAllDesignations();
	
	//Delete
	void deleteDesignations(Long desigId);
}
