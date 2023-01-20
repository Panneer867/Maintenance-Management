package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.WorkEstmationDto;



public interface WorkEstmationService {
	
	//Save Info
	WorkEstmationDto saveWorkEstmation(WorkEstmationDto workEstmationDto);
		
		//Show All
		 List<WorkEstmationDto> findAllWorkEstmation();

}
