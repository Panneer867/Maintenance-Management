package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.DistributionLocationDto;

public interface DistributionLocationService {

	//Save Data
	DistributionLocationDto saveDistributionLocation(DistributionLocationDto disLocationDto);
	
	//FindAll
	List<DistributionLocationDto> findAllDistributionLocation();
	
	//Delete
	void deleteDistributeLocation(Long disLocId);
}
