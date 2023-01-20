package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.LeakageTypeRepository;
import com.ingroinfo.mm.dto.LeakageTypeDto;
import com.ingroinfo.mm.entity.LeakageType;
import com.ingroinfo.mm.service.LeakageTypeService;

@Service
public class LeakageTypeServiceImpl implements LeakageTypeService {

	@Autowired
	private LeakageTypeRepository leakageTypeRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public LeakageTypeDto saveLeakageType(LeakageTypeDto leakageTypeDto) {
		LeakageType convLeakageType = this.modelMapper.map(leakageTypeDto, LeakageType.class);
		LeakageType savedLeakageType = this.leakageTypeRepository.save(convLeakageType);
		return this.modelMapper.map(savedLeakageType, LeakageTypeDto.class);
	}

	@Override
	public List<LeakageTypeDto> getAllLeakageType() {
		List<LeakageType> leakageTypes = this.leakageTypeRepository.findAll();
		List<LeakageTypeDto> leakageTypeDtos = leakageTypes.stream().map((leakageType) -> 
		this.modelMapper.map(leakageType, LeakageTypeDto.class)).collect(Collectors.toList());
		return leakageTypeDtos;
	}

	@Override
	public void deleteLeakageType(Long leakageId) {
		 LeakageType leakageType = this.leakageTypeRepository.findById(leakageId).get();
		 this.leakageTypeRepository.delete(leakageType);
	}

}
