package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.SaftyPrecautionRepository;
import com.ingroinfo.mm.dto.SaftyPrecautionsDto;
import com.ingroinfo.mm.entity.SaftyPrecautions;
import com.ingroinfo.mm.service.SaftyPrecautionService;

@Service
public class SaftyPrecautionServiceImpl implements SaftyPrecautionService {

	@Autowired
	private SaftyPrecautionRepository saftyPrecusRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public SaftyPrecautionsDto saveSaftyPrecus(SaftyPrecautionsDto saftyPrecusDto) {
		SaftyPrecautions convPrecaution = this.modelMapper.map(saftyPrecusDto, SaftyPrecautions.class);
		SaftyPrecautions savedPrecaution = this.saftyPrecusRepo.save(convPrecaution);
		return modelMapper.map(savedPrecaution, SaftyPrecautionsDto.class);
	}

	@Override
	public List<SaftyPrecautionsDto> findAllSaftyPrecus() {
		List<SaftyPrecautions> saftyPrecautions = this.saftyPrecusRepo.findAll();
		List<SaftyPrecautionsDto> precautionDtos = saftyPrecautions.stream().map((precaution) -> 
		this.modelMapper.map(precaution, SaftyPrecautionsDto.class)).collect(Collectors.toList());
		return precautionDtos;
	}

	@Override
	public void deleteSaftyPrecason(Long saftyprecId) {
		 SaftyPrecautions saftyPrecautions = this.saftyPrecusRepo.findById(saftyprecId).get();
		 this.saftyPrecusRepo.delete(saftyPrecautions);
	}

}
