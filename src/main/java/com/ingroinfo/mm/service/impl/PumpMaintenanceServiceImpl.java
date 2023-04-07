package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.PumpMaintenanceInspectionRepository;
import com.ingroinfo.mm.dao.PumpMaintenanceUpdatedRepository;
import com.ingroinfo.mm.dao.PumpMaintenanceRepository;
import com.ingroinfo.mm.dto.PumpMaintenanceInspectionDto;
import com.ingroinfo.mm.dto.PumpMaintenanceUpdatedDto;
import com.ingroinfo.mm.dto.PumpMaintenanceDto;
import com.ingroinfo.mm.entity.PumpMaintenanceInspection;
import com.ingroinfo.mm.entity.PumpMaintenanceUpdated;
import com.ingroinfo.mm.entity.PumpMaintenance;
import com.ingroinfo.mm.service.PumpMaintenanceService;

@Service
public class PumpMaintenanceServiceImpl implements PumpMaintenanceService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PumpMaintenanceRepository pumpMaintenRepo;
	@Autowired
	private PumpMaintenanceUpdatedRepository pumpMaintenainceUpdatedRepo;
	@Autowired
	private PumpMaintenanceInspectionRepository pumpMaintenainceInspectionRepo;

	@Override
	public PumpMaintenanceDto savePumpMaintenance(PumpMaintenanceDto pumpMaintenDto) {
		PumpMaintenance convPumpMainten = this.modelMapper.map(pumpMaintenDto, PumpMaintenance.class);
		PumpMaintenance savedPumpMainten = this.pumpMaintenRepo.save(convPumpMainten);
		return this.modelMapper.map(savedPumpMainten, PumpMaintenanceDto.class);
	}

	@Override
	public PumpMaintenanceUpdatedDto savePumpMaintenanceUpdated(PumpMaintenanceUpdatedDto pumpDto) {
		PumpMaintenanceUpdated savedPumpUpdated = this.pumpMaintenainceUpdatedRepo.save(modelMapper.map(pumpDto, PumpMaintenanceUpdated.class));
		return this.modelMapper.map(savedPumpUpdated, PumpMaintenanceUpdatedDto.class);
	}

	@Override
	public List<PumpMaintenanceUpdatedDto> getAllPumpMaintenanceUpdated() {
		List<PumpMaintenanceUpdated> pumpMaintenainceUpdateds = this.pumpMaintenainceUpdatedRepo.findAll();
		return pumpMaintenainceUpdateds.stream()
				.map((updatedPumps) -> this.modelMapper.map(updatedPumps, PumpMaintenanceUpdatedDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PumpMaintenanceUpdatedDto getPumpMaintenanceUpdatedByWorkorderNo(String workOrderNo) {
		PumpMaintenanceUpdated pumpMaintenainceUpdated = this.pumpMaintenainceUpdatedRepo.getByWorkOrderNo(workOrderNo);
		return this.modelMapper.map(pumpMaintenainceUpdated, PumpMaintenanceUpdatedDto.class);
	}

	@Override
	public PumpMaintenanceInspectionDto savePumpInspectionData(PumpMaintenanceInspectionDto pumpInspectionDto) {
		PumpMaintenanceInspection convertedInspection = this.modelMapper.map(pumpInspectionDto, PumpMaintenanceInspection.class);
		PumpMaintenanceInspection savedPumpInspections = this.pumpMaintenainceInspectionRepo.save(convertedInspection);
		return this.modelMapper.map(savedPumpInspections, PumpMaintenanceInspectionDto.class);
	}

}
