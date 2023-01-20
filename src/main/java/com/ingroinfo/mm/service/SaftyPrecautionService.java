package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.SaftyPrecautionsDto;

public interface SaftyPrecautionService {

	//save
	SaftyPrecautionsDto saveSaftyPrecus(SaftyPrecautionsDto saftyPrecusDto);
	
	//Find All Data
	List<SaftyPrecautionsDto> findAllSaftyPrecus();
	
	//Delete
	void deleteSaftyPrecason(Long saftyprecId);
}
