package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.PipeIndexRepository;
import com.ingroinfo.mm.dao.PipeMaintenanceInspectionRepository;
import com.ingroinfo.mm.dao.PipeMaintenanceRepository;
import com.ingroinfo.mm.dao.PipeMaintenanceUpdateRepository;
import com.ingroinfo.mm.dto.PipeIndexDto;
import com.ingroinfo.mm.dto.PipeMaintenanceDto;
import com.ingroinfo.mm.dto.PipeMaintenanceInspectionDto;
import com.ingroinfo.mm.dto.PipeMaintenanceUpdateDto;
import com.ingroinfo.mm.entity.PipeIndex;
import com.ingroinfo.mm.entity.PipeMaintenance;
import com.ingroinfo.mm.entity.PipeMaintenanceInspection;
import com.ingroinfo.mm.entity.PipeMaintenanceUpdate;
import com.ingroinfo.mm.service.PipeMaintenanceService;

@Service
public class PipeMaintenanceServiceImp implements PipeMaintenanceService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private  PipeMaintenanceRepository pipeMaintenanceRepo;
	
	@Autowired
	private PipeIndexRepository pipeIndexRepo; 
	
	@Autowired
	private PipeMaintenanceUpdateRepository pipeMaintenanceUpdateRepo;
	
	@Autowired
	private PipeMaintenanceInspectionRepository pipeMaintenanceInspectionRepo;
	
	//save Pipe Maintenance
	@Override
	public PipeMaintenanceDto savePipeMaintenance(PipeMaintenanceDto pipeMaintenance) {
		PipeMaintenance pMaintenance = this.modelMapper.map(pipeMaintenance, PipeMaintenance.class);
	    PipeMaintenance savePipeMaintenance	= this.pipeMaintenanceRepo.save(pMaintenance);
		return this.modelMapper.map(savePipeMaintenance, PipeMaintenanceDto.class);
	}
	
	
	//show All data in Pipe Maintenance List to Maintenance Update
	@Override
	public List<PipeMaintenanceDto> findAllPipeMaintenance() {
		
		List<PipeMaintenance> idpipeMaintenances = this.pipeMaintenanceRepo.findAll();
		List<PipeMaintenanceDto> iPipeMaintenanceDtos = idpipeMaintenances.stream().map((idpipeMaintenance)-> 
		this.modelMapper.map(idpipeMaintenance, PipeMaintenanceDto.class)).collect(Collectors.toList());
		return iPipeMaintenanceDtos;
	}
	
	//show All data in Pipe Maintenance Update to Maintenance  Inspection
	@Override
	public List<PipeMaintenanceUpdateDto> findAllPipeMaintenanceUpdate() {

	  List<PipeMaintenanceUpdate> pipeMaintenanceUpdateList = this.pipeMaintenanceUpdateRepo.findAll();
	  List<PipeMaintenanceUpdateDto> pipeMaintenanceUpdateDtos = pipeMaintenanceUpdateList.stream().map((pipeMaintenanceUpdate)-> 
	     this.modelMapper.map(pipeMaintenanceUpdate,PipeMaintenanceUpdateDto.class)).collect(Collectors.toList());
		
		return pipeMaintenanceUpdateDtos;
	}

    //Display single data By Pipe Maintenance Id 
	@Override
	public PipeMaintenanceDto getPipeMaintenanceById(Long pipeId) {
	   PipeMaintenance pipeMaintenance = this.pipeMaintenanceRepo.findById(pipeId).get();
		return this.modelMapper.map(pipeMaintenance, PipeMaintenanceDto.class);
	}
	
	// Display data Pipe Maintenance Update Id
	@Override
	public PipeMaintenanceUpdateDto getPipeMaintenanceUpdateById(Long pipeUpdateId) {
		PipeMaintenanceUpdate pipeMaintenanceUpdate = this.pipeMaintenanceUpdateRepo.findById(pipeUpdateId).get();
		return this.modelMapper.map(pipeMaintenanceUpdate, PipeMaintenanceUpdateDto.class);
	}

	// save Pipe Maintenance Update
	@Override
	public PipeMaintenanceUpdateDto savePipeMaintenanceUpdate(PipeMaintenanceUpdateDto pipeMaintenanceUpdate) {
		PipeMaintenanceUpdate pMaintenanceUpdate = this.modelMapper.map(pipeMaintenanceUpdate, PipeMaintenanceUpdate.class);
		PipeMaintenanceUpdate savepPipeMaintenanceUpdate = this.pipeMaintenanceUpdateRepo.save(pMaintenanceUpdate);
		return this.modelMapper.map(savepPipeMaintenanceUpdate, PipeMaintenanceUpdateDto.class);
	}

	//save Pipe Maintenance Inspection
	@Override
	public PipeMaintenanceInspectionDto savePipeMaintenanceInspection(PipeMaintenanceInspectionDto pipeMaintenanceInspection) {
	   PipeMaintenanceInspection pMaintenanceInspection	= this.modelMapper.map(pipeMaintenanceInspection, PipeMaintenanceInspection.class);
		this.pipeMaintenanceInspectionRepo.save(pMaintenanceInspection);
	   return this.modelMapper.map(pMaintenanceInspection, PipeMaintenanceInspectionDto.class);
	}


	@Override
	public PipeIndexDto savePipeIndex(PipeIndexDto pipeIndexDto) {
		PipeIndex pipeIndex = this.modelMapper.map(pipeIndexDto, PipeIndex.class);
		this.pipeIndexRepo.save(pipeIndex);
		return this.modelMapper.map(pipeIndex, PipeIndexDto.class);
	}





	

}
