package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.DesignationRepository;
import com.ingroinfo.mm.dto.DesignationDto;
import com.ingroinfo.mm.entity.Designation;
import com.ingroinfo.mm.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	private DesignationRepository designationRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public DesignationDto saveDesignation(DesignationDto designationDto) {
		Designation convDesignation = this.modelMapper.map(designationDto, Designation.class);
		Designation savedDesignation = this.designationRepo.save(convDesignation);
		return this.modelMapper.map(savedDesignation, DesignationDto.class);
	}

	@Override
	public List<DesignationDto> getAllDesignations() {
		List<Designation> designations = this.designationRepo.findAll();
		List<DesignationDto> designationDtos = designations.stream().map((designation) -> 
		this.modelMapper.map(designation, DesignationDto.class)).collect(Collectors.toList());
		return designationDtos;
	}

	@Override
	public void deleteDesignations(Long desigId) {
		 Designation designation = this.designationRepo.findById(desigId).get();
		 this.designationRepo.delete(designation);
	}

}
