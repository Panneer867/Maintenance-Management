package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.DivisionSubdivisionRepository;
import com.ingroinfo.mm.dto.DivisionSubdivisionDto;
import com.ingroinfo.mm.entity.DivisionSubdivision;
import com.ingroinfo.mm.service.DivisionSubdivisionService;

@Service
public class DivisionSubdivisionSeviceImpl implements DivisionSubdivisionService {
	
	@Autowired
	private DivisionSubdivisionRepository divsubdivRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public DivisionSubdivisionDto saveDivisionSubdivision(DivisionSubdivisionDto divSubDto) {
		DivisionSubdivision divSubDivs = this.modelMapper.map(divSubDto, DivisionSubdivision.class);
		DivisionSubdivision savedDivSubDiv = this.divsubdivRepo.save(divSubDivs);
		return modelMapper.map(savedDivSubDiv, DivisionSubdivisionDto.class);
	}

	@Override
	public List<DivisionSubdivisionDto> findAllDivSubdiv() {
		List<DivisionSubdivision> listOfDivSubdiv = this.divsubdivRepo.findAll();
		List<DivisionSubdivisionDto> listOfDivSubdivDto = listOfDivSubdiv.stream().map((divsubdiv) -> 
		this.modelMapper.map(divsubdiv, DivisionSubdivisionDto.class)).collect(Collectors.toList());
		return listOfDivSubdivDto;
	}

	@Override
	public void deleteDivSubDiv(Long divsubId) {
		DivisionSubdivision divisionSubdivision = this.divsubdivRepo.findById(divsubId).get();
		this.divsubdivRepo.delete(divisionSubdivision);
	}

}
