package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.PumpMasterRepository;
import com.ingroinfo.mm.dto.PumpMasterDto;
import com.ingroinfo.mm.entity.PumpMaster;
import com.ingroinfo.mm.service.PumpMasterService;

@Service
public class PumpMasterServiceImpl implements PumpMasterService {

	@Autowired
	private PumpMasterRepository pumpMasterRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PumpMasterDto savePumpMaster(PumpMasterDto pumpDto) {
		PumpMaster convPumpMaster = this.modelMapper.map(pumpDto, PumpMaster.class);
		PumpMaster savedPumps = this.pumpMasterRepo.save(convPumpMaster);
		return this.modelMapper.map(savedPumps, PumpMasterDto.class);
	}

	@Override
	public List<PumpMasterDto> getAllPumpMaster() {
	    List<PumpMaster> pumpMasters = this.pumpMasterRepo.findAll();
	    List<PumpMasterDto> pumpMasterDtos = pumpMasters.stream().map((pumpMaster) -> 
	    this.modelMapper.map(pumpMaster, PumpMasterDto.class)).collect(Collectors.toList());
		return pumpMasterDtos;
	}

	@Override
	public void deletePumpMaster(Long pumpMasterId) {
		PumpMaster pumpMaster = this.pumpMasterRepo.findById(pumpMasterId).get();
		this.pumpMasterRepo.delete(pumpMaster);
	}
	
	@Override
	public PumpMasterDto getPumpDataByPumpId(String pumpId) {
		PumpMaster pumpMaster  = this.pumpMasterRepo.getPumpMasterByPumpId(pumpId);
		return this.modelMapper.map(pumpMaster, PumpMasterDto.class);
	}

}
