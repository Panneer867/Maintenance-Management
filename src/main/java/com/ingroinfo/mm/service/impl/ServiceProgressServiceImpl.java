package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.ServiceProgressRepository;
import com.ingroinfo.mm.dto.ServiceProgressDto;
import com.ingroinfo.mm.entity.ServiceProgress;
import com.ingroinfo.mm.service.ServiceProgressService;

@Service
public class ServiceProgressServiceImpl implements ServiceProgressService {

	@Autowired
	private ServiceProgressRepository serviceProgressRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ServiceProgressDto saveServiceProgress(ServiceProgressDto serviceProgrssDto) {
		ServiceProgress convServiceProgrec = this.modelMapper.map(serviceProgrssDto, ServiceProgress.class);
		ServiceProgress savedServiceProgrec = this.serviceProgressRepo.save(convServiceProgrec);
		return this.modelMapper.map(savedServiceProgrec, ServiceProgressDto.class);
	}

	@Override
	public List<ServiceProgressDto> findAllServiceProgress() {
		List<ServiceProgress> serviceProgresses = this.serviceProgressRepo.findAll();
		List<ServiceProgressDto> serviceProgressDtos = serviceProgresses.stream().map((serviceProgress) -> 
		this.modelMapper.map(serviceProgress, ServiceProgressDto.class)).collect(Collectors.toList());
		return serviceProgressDtos;
	}

	@Override
	public void deleteServiceProgressType(Long servcProgressId) {
		 ServiceProgress serviceProgress = this.serviceProgressRepo.findById(servcProgressId).get();
		 this.serviceProgressRepo.delete(serviceProgress);
	}

}
