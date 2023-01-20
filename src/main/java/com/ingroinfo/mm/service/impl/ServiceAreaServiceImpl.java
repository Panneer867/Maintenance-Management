package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.ServiceAreaRepository;
import com.ingroinfo.mm.dto.ServiceAreaDto;
import com.ingroinfo.mm.entity.ServiceArea;
import com.ingroinfo.mm.service.ServiceAreaService;

@Service
public class ServiceAreaServiceImpl implements ServiceAreaService {

	@Autowired
	private ServiceAreaRepository serviceAreaRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ServiceAreaDto saveSaerviceArea(ServiceAreaDto serviceAreaDto) {
		ServiceArea convServiceArea = this.modelMapper.map(serviceAreaDto, ServiceArea.class);
		ServiceArea savedServiceArea = this.serviceAreaRepo.save(convServiceArea);
		return this.modelMapper.map(savedServiceArea, ServiceAreaDto.class);
	}

	@Override
	public List<ServiceAreaDto> findAllServiceArea() {
		List<ServiceArea> serviceAreas = this.serviceAreaRepo.findAll();
		List<ServiceAreaDto> serviceAreaDtos = serviceAreas.stream().map((serviceArea) -> 
		this.modelMapper.map(serviceArea, ServiceAreaDto.class)).collect(Collectors.toList());
		return serviceAreaDtos;
	}

	@Override
	public void deleteServiceArea(Long sericAreaId) {
		 ServiceArea serviceArea = this.serviceAreaRepo.findById(sericAreaId).get();
		 this.serviceAreaRepo.delete(serviceArea);
	}

}
