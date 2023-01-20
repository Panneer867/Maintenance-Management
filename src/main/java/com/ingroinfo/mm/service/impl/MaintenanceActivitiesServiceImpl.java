package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.MaintenanceActivitiesRepository;
import com.ingroinfo.mm.dto.MaintenanceActivitiesDto;
import com.ingroinfo.mm.entity.MaintenanceActivities;
import com.ingroinfo.mm.service.MaintenanceActivitiesService;

@Service
public class MaintenanceActivitiesServiceImpl implements MaintenanceActivitiesService {

	@Autowired
	private MaintenanceActivitiesRepository maintenActiveRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MaintenanceActivitiesDto saveMaintenActivity(MaintenanceActivitiesDto maintenActivDto) {
	    MaintenanceActivities convMaintenActive = this.modelMapper.map(maintenActivDto, MaintenanceActivities.class);
	    MaintenanceActivities savedMaintenActive = this.maintenActiveRepo.save(convMaintenActive);
		return modelMapper.map(savedMaintenActive, MaintenanceActivitiesDto.class);
	}

	@Override
	public List<MaintenanceActivitiesDto> findAllMaintnActve() {
		List<MaintenanceActivities> maintenActives = this.maintenActiveRepo.findAll();
		List<MaintenanceActivitiesDto> maintenActiveDtos = maintenActives.stream().map((maintenActive) -> 
		this.modelMapper.map(maintenActive, MaintenanceActivitiesDto.class)).collect(Collectors.toList());
		return maintenActiveDtos;
	}

	@Override
	public void deleteMaintenActivity(Long maintenActiveId) {
		MaintenanceActivities maintenanceActivities = this.maintenActiveRepo.findById(maintenActiveId).get();
		this.maintenActiveRepo.delete(maintenanceActivities);
	}

}
