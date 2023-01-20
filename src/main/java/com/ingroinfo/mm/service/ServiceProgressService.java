package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.ServiceProgressDto;

public interface ServiceProgressService {

	//create
	ServiceProgressDto saveServiceProgress(ServiceProgressDto serviceProgrssDto);
	
	//Find All
	List<ServiceProgressDto> findAllServiceProgress();
	
	//Delete
	void deleteServiceProgressType(Long servcProgressId);
}
