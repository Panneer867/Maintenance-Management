package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.WaterSourceDto;

public interface WaterSourceService {

	//create
	WaterSourceDto saveWaterSource(WaterSourceDto waterSourceDto);
	
	//find All
	List<WaterSourceDto> findAllWaterSource();
	
	//Delete
	void deleteWaterCource(Long wateSourceId);
}
