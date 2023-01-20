package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.PressureTypeRepository;
import com.ingroinfo.mm.dto.PressureTypeDto;
import com.ingroinfo.mm.entity.PressureType;
import com.ingroinfo.mm.service.PressureTypeService;

@Service
public class PressureTypeSeriveImpl implements PressureTypeService {

	@Autowired
	private PressureTypeRepository pressureTypeRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PressureTypeDto savePressureType(PressureTypeDto pressureDto) {
		 PressureType convPressure = this.modelMapper.map(pressureDto, PressureType.class);
		 PressureType savedPressure  = this.pressureTypeRepo.save(convPressure);
		 return this.modelMapper.map(savedPressure, PressureTypeDto.class);
	}

	@Override
	public List<PressureTypeDto> getAllPressureType() {
		List<PressureType> pressureTypes = this.pressureTypeRepo.findAll();
		List<PressureTypeDto> pressureTypeDtos = pressureTypes.stream().map((pressureType) -> 
		this.modelMapper.map(pressureType, PressureTypeDto.class)).collect(Collectors.toList());
		return pressureTypeDtos;
	}

	@Override
	public void deletePressureType(Long pressureId) {
		PressureType pressureType = this.pressureTypeRepo.findById(pressureId).get();
		this.pressureTypeRepo.delete(pressureType);
	}

}
