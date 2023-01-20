package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.MeterTypeRepository;
import com.ingroinfo.mm.dto.MeterTypeDto;
import com.ingroinfo.mm.entity.MeterType;
import com.ingroinfo.mm.service.MeterTypeService;

@Service
public class MeterTypeServiceImpl implements MeterTypeService {

	@Autowired
	private MeterTypeRepository meterTypeRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MeterTypeDto saveMeterType(MeterTypeDto meterTypeDto) {
		MeterType convMeterType = this.modelMapper.map(meterTypeDto, MeterType.class);
		MeterType savedMeterType = this.meterTypeRepo.save(convMeterType);
		return this.modelMapper.map(savedMeterType, MeterTypeDto.class);
	}

	@Override
	public List<MeterTypeDto> getAllMeterType() {
		List<MeterType> meterTypes = this.meterTypeRepo.findAll();
		List<MeterTypeDto> meterTypeDtos = meterTypes.stream().map((meterType) -> 
		this.modelMapper.map(meterType, MeterTypeDto.class)).collect(Collectors.toList());
		return meterTypeDtos;
	}

	@Override
	public void deleteMeterType(Long meterTypeId) {
		MeterType meterType = this.meterTypeRepo.findById(meterTypeId).get();
		this.meterTypeRepo.delete(meterType);
	}

}
