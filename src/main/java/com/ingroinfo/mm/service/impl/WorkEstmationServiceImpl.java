package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.WorkEstmationRepository;
import com.ingroinfo.mm.dto.WorkEstmationDto;
import com.ingroinfo.mm.entity.WorkEstmation;
import com.ingroinfo.mm.service.WorkEstmationService;


@Service
public class WorkEstmationServiceImpl implements WorkEstmationService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private WorkEstmationRepository workEstmationRepository;
	

	@Override
	public WorkEstmationDto saveWorkEstmation(WorkEstmationDto workEstmationDto) {
		WorkEstmation workEstmations = this.modelMapper.map(workEstmationDto, WorkEstmation.class);
		WorkEstmation savedWorkEstmation = this.workEstmationRepository.save(workEstmations);
		return this.modelMapper.map(savedWorkEstmation, WorkEstmationDto.class);
	}

	@Override
	public List<WorkEstmationDto> findAllWorkEstmation() {
		List<WorkEstmation> idWorkEstmation = this.workEstmationRepository.findAll();
		List<WorkEstmationDto> ididWorkEstmationDto = idWorkEstmation.stream().map((idWorkEstmations)-> 
		   this.modelMapper.map(idWorkEstmations, WorkEstmationDto.class)).collect(Collectors.toList());		
		return ididWorkEstmationDto;
	}
	
	
}
