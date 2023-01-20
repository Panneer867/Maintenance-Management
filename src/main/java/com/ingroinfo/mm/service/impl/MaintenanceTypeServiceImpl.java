package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.MaintenanceTypeRepository;
import com.ingroinfo.mm.dto.MaintenanceTypeDto;
import com.ingroinfo.mm.entity.MaintenanceType;
import com.ingroinfo.mm.service.MaintenanceTypeService;

@Service
public class MaintenanceTypeServiceImpl implements MaintenanceTypeService {

	@Autowired
	private MaintenanceTypeRepository maintenTypeRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MaintenanceTypeDto saveMaintenanceType(MaintenanceTypeDto maintenTypeDto) {
		MaintenanceType convMaintenType = this.modelMapper.map(maintenTypeDto, MaintenanceType.class);
		MaintenanceType savedMaintenType = this.maintenTypeRepo.save(convMaintenType);
		return this.modelMapper.map(savedMaintenType, MaintenanceTypeDto.class);
	}

	@Override
	public List<MaintenanceTypeDto> findAllMaintenanceType() {
		List<MaintenanceType> maintenTypes = this.maintenTypeRepo.findAll();
		List<MaintenanceTypeDto> maintenTypeDtos = maintenTypes.stream().map((maintenType) -> 
		this.modelMapper.map(maintenType, MaintenanceTypeDto.class)).collect(Collectors.toList());
		return maintenTypeDtos;
	}

	@Override
	public void deleteMaintainsType(Long maintenTypeId) {
		 MaintenanceType maintenanceType = this.maintenTypeRepo.findById(maintenTypeId).get();
		 this.maintenTypeRepo.delete(maintenanceType);
	}

}
