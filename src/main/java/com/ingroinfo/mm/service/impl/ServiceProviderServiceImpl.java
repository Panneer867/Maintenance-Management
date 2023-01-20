package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.ServiceProviderRepository;
import com.ingroinfo.mm.dto.ServiceProviderDto;
import com.ingroinfo.mm.entity.ServiceProvider;
import com.ingroinfo.mm.service.ServiceProviderService;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {

	@Autowired
	private ServiceProviderRepository serviceProviderRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ServiceProviderDto saveServiceProvider(ServiceProviderDto serviceProviderDto) {
		ServiceProvider convserviceProvider = this.modelMapper.map(serviceProviderDto, ServiceProvider.class);
		ServiceProvider savedServiceProvider = this.serviceProviderRepo.save(convserviceProvider);
		return this.modelMapper.map(savedServiceProvider, ServiceProviderDto.class);
	}

	@Override
	public List<ServiceProviderDto> getAllServiceProvider() {
		List<ServiceProvider> serviceProviders = this.serviceProviderRepo.findAll();
		List<ServiceProviderDto> serviceProviderDtos = serviceProviders.stream().map((serviceProvider) -> 
		this.modelMapper.map(serviceProvider, ServiceProviderDto.class)).collect(Collectors.toList());
		return serviceProviderDtos;
	}

	@Override
	public void deleteServiceProvider(Long servProvId) {
		 ServiceProvider serviceProvider = this.serviceProviderRepo.findById(servProvId).get();
		 this.serviceProviderRepo.delete(serviceProvider);
	}

}
