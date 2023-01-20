package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.PumpMaintenanceRepository;
import com.ingroinfo.mm.dto.PumpMaintenanceDto;
import com.ingroinfo.mm.entity.PumpMaintenance;
import com.ingroinfo.mm.service.PumpMaintenanceService;

@Service
public class PumpMaintenanceServiceImpl implements PumpMaintenanceService {

	@Autowired
	private PumpMaintenanceRepository pumpMaintenRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PumpMaintenanceDto savePumpMaintenance(PumpMaintenanceDto pumpMaintenDto) {
		PumpMaintenance convPumpMainten = this.modelMapper.map(pumpMaintenDto, PumpMaintenance.class);
		PumpMaintenance savedPumpMainten = this.pumpMaintenRepo.save(convPumpMainten);
		return this.modelMapper.map(savedPumpMainten, PumpMaintenanceDto.class);
	}

	@Override
	public List<PumpMaintenanceDto> getAllMaintenance() {
		List<PumpMaintenance> pumpMaintenances = this.pumpMaintenRepo.findAll();
		List<PumpMaintenanceDto> pumpMaintenanceDtos = pumpMaintenances.stream().map((pumpmainten) -> 
		this.modelMapper.map(pumpmainten, PumpMaintenanceDto.class)).collect(Collectors.toList());
		return pumpMaintenanceDtos;
	}

	@Override
	public PumpMaintenanceDto getPumpMaintenByPumpMaintenId(Long pumpMaintenId) {
		PumpMaintenance pumpMaintenance = this.pumpMaintenRepo.findById(pumpMaintenId).get();
		return this.modelMapper.map(pumpMaintenance, PumpMaintenanceDto.class);
	}


}
