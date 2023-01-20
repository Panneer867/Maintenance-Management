package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.MaintenancePerformanceRepository;
import com.ingroinfo.mm.dto.MaintenancePerformanceDto;
import com.ingroinfo.mm.entity.MaintenancePerformance;
import com.ingroinfo.mm.service.MaintenancePerformService;

@Service
public class MaintenancePerformServiceImpl implements MaintenancePerformService {

	@Autowired
	private MaintenancePerformanceRepository maintenPerformRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MaintenancePerformanceDto saveMaintenPerform(MaintenancePerformanceDto maintenPerformDto) {
		MaintenancePerformance convMaintenPerform = this.modelMapper.map(maintenPerformDto, MaintenancePerformance.class);
		MaintenancePerformance savedMaintenPerform = this.maintenPerformRepo.save(convMaintenPerform);
		return this.modelMapper.map(savedMaintenPerform, MaintenancePerformanceDto.class);
	}

	@Override
	public List<MaintenancePerformanceDto> getAllMaintenPerform() {
		List<MaintenancePerformance> maintenPerforms = this.maintenPerformRepo.findAll();
		List<MaintenancePerformanceDto> maintenPerformDtos = maintenPerforms.stream().map((maintenPerform) -> 
		this.modelMapper.map(maintenPerform, MaintenancePerformanceDto.class)).collect(Collectors.toList());
		return maintenPerformDtos;
	}

	@Override
	public void deleteMaintainsPerformance(Long maintenPerformId) {
		MaintenancePerformance maintenancePerformance = this.maintenPerformRepo.findById(maintenPerformId).get();
		this.maintenPerformRepo.delete(maintenancePerformance);
	}

}
