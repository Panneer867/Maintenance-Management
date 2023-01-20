package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.UnitMeasureDto;

public interface UnitMeasureService {

	//save Data
	UnitMeasureDto saveUnitMeasure(UnitMeasureDto unitMeasureDto);
	
	//find All 
	List<UnitMeasureDto> getAllUnitMeasure();
	
	//Delete 
	void deleteUnitOfMeasure(Long unitMeasureId);
}
