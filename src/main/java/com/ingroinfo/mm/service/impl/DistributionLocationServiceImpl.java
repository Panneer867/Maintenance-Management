package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.DistributionLocationRepository;
import com.ingroinfo.mm.dto.DistributionLocationDto;
import com.ingroinfo.mm.entity.DistributionLocation;
import com.ingroinfo.mm.service.DistributionLocationService;

@Service
public class DistributionLocationServiceImpl implements DistributionLocationService {
	
	@Autowired
	private DistributionLocationRepository disLocationRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public DistributionLocationDto saveDistributionLocation(DistributionLocationDto disLocationDto) {
		 DistributionLocation distributionLocation = this.modelMapper.map(disLocationDto, DistributionLocation.class);
		 DistributionLocation saveDistributionLocation = this.disLocationRepo.save(distributionLocation);
		 return modelMapper.map(saveDistributionLocation, DistributionLocationDto.class);
	}

	@Override
	public List<DistributionLocationDto> findAllDistributionLocation() {
		 List<DistributionLocation> distributionLocations = this.disLocationRepo.findAll();
		 List<DistributionLocationDto> distributionLocationDtos = distributionLocations.stream().map((distributionLocation)->
		 this.modelMapper.map(distributionLocation, DistributionLocationDto.class)).collect(Collectors.toList());
		return distributionLocationDtos;
	}

}
