package com.ingroinfo.mm.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.PipeIndexRepository;
import com.ingroinfo.mm.dto.PipeIndexDto;
import com.ingroinfo.mm.entity.PipeIndex;
import com.ingroinfo.mm.service.PipeMaintenanceService;

@Service
public class PipeMaintenanceServiceImp implements PipeMaintenanceService {

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	private PipeIndexRepository pipeIndexRepo; 	
	
	@Override
	public PipeIndexDto savePipeIndex(PipeIndexDto pipeIndexDto) {
		PipeIndex pipeIndex = this.modelMapper.map(pipeIndexDto, PipeIndex.class);
		this.pipeIndexRepo.save(pipeIndex);
		return this.modelMapper.map(pipeIndex, PipeIndexDto.class);
	}

}
