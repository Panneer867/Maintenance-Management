package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.WorkPriorityDto;

public interface WorkPriorityService {

	//save
	WorkPriorityDto saveWorkPriority(WorkPriorityDto workPriorityDto);
	
	//find All
	List<WorkPriorityDto> findAllWorkPriority();
	
	//Delete
	void deleteWorkPriority(Long workPrioId);
}
