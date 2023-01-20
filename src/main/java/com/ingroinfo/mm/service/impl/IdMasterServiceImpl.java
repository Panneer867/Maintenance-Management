package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.IdMasterRepository;
import com.ingroinfo.mm.dto.IdMasterDto;
import com.ingroinfo.mm.entity.IdMaster;
import com.ingroinfo.mm.service.IdMasterService;

@Service
public class IdMasterServiceImpl implements IdMasterService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private IdMasterRepository idRepository;

	@Override
	public IdMasterDto saveIdMaster(IdMasterDto idDto) {
		 IdMaster idMaster = this.modelMapper.map(idDto, IdMaster.class);
		 IdMaster savedIdMaster = this.idRepository.save(idMaster);
		 return modelMapper.map(savedIdMaster, IdMasterDto.class);
	}

	@Override
	public List<IdMasterDto> findAllIdMaster() {		
		List<IdMaster> idMasters = this.idRepository.findAll();
		List<IdMasterDto> idMasterDto = idMasters.stream().map((idMaster)-> 
		   this.modelMapper.map(idMaster, IdMasterDto.class)).collect(Collectors.toList());		
		return idMasterDto;
	}

	@Override
	public IdMasterDto getByMasterId(Long masterId) {
		IdMaster idMaster = this.idRepository.findById(masterId).get();
		return this.modelMapper.map(idMaster, IdMasterDto.class);
	}

	@Override
	public IdMasterDto getByMasterIdName(String masterIdName) {
		 IdMaster idMaster = this.idRepository.getByMasterIdName(masterIdName);
		 return this.modelMapper.map(idMaster, IdMasterDto.class);
	}
	

}
