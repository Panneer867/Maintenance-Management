package com.ingroinfo.mm.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ingroinfo.mm.dto.DesignationDto;

public interface DesignationService {

	//save Data
	DesignationDto saveDesignation(DesignationDto designationDto);
	
	//find All
	List<DesignationDto> getAllDesignations();
	
	//Delete
	void deleteDesignations(Long desigId);
	
	//Get All Designations From UBARMS
	List<DesignationDto> getDesignationsFormUbarms() throws JsonMappingException, JsonProcessingException, IOException;
}
