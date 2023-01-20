package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.WaterSourceRepository;
import com.ingroinfo.mm.dto.WaterSourceDto;
import com.ingroinfo.mm.entity.WaterSource;
import com.ingroinfo.mm.service.WaterSourceService;

@Service
public class WaterSourceServiceImpl implements WaterSourceService {

	@Autowired
	private WaterSourceRepository waterSourceRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public WaterSourceDto saveWaterSource(WaterSourceDto waterSourceDto) {
		WaterSource convWaterSource = this.modelMapper.map(waterSourceDto, WaterSource.class);
		WaterSource savedWaterSource = this.waterSourceRepo.save(convWaterSource);
		return this.modelMapper.map(savedWaterSource, WaterSourceDto.class);
	}

	@Override
	public List<WaterSourceDto> findAllWaterSource() {
		List<WaterSource> waterSources = this.waterSourceRepo.findAll();
		List<WaterSourceDto> waterSourceDtos = waterSources.stream().map((waterSource) -> 
		this.modelMapper.map(waterSource, WaterSourceDto.class)).collect(Collectors.toList());
		return waterSourceDtos;
	}

	@Override
	public void deleteWaterCource(Long wateSourceId) {
	   WaterSource waterSource = this.waterSourceRepo.findById(wateSourceId).get();
	   this.waterSourceRepo.delete(waterSource);
	}

}
