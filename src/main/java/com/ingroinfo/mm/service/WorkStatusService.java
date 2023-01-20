package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.WorkStatusDto;

public interface WorkStatusService {

	//save
	WorkStatusDto saveWorkStatus(WorkStatusDto workStatusDto);
	
	//Find All
	List<WorkStatusDto> getAllWorkStatus();
	
	//Delete
	void deleteWorkStatus(Long workStsId);
}
