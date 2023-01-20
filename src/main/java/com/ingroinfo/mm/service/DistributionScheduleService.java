package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.DistributionScheduleDto;

public interface DistributionScheduleService {

	//save Data
	DistributionScheduleDto saveDisSchedule(DistributionScheduleDto disSchedule);
	
	//Find All Data
	List<DistributionScheduleDto> findAllDisSchedule();
	
	//Delete
	void deleteDistrbSchedule(Long disScheduleId);
}
