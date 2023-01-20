package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.ServiceAreaDto;

public interface ServiceAreaService {

	//create
	ServiceAreaDto saveSaerviceArea(ServiceAreaDto serviceAreaDto);
	
	//Find All Data
	List<ServiceAreaDto> findAllServiceArea();
	
	//Delete
	void deleteServiceArea(Long sericAreaId);
}
