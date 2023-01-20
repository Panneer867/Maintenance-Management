package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.ServiceProviderDto;

public interface ServiceProviderService {

	//save Data
	ServiceProviderDto saveServiceProvider(ServiceProviderDto serviceProviderDto);
	
	//find All Data
	List<ServiceProviderDto> getAllServiceProvider();
	
	//Delete
	void deleteServiceProvider(Long servProvId);
}
