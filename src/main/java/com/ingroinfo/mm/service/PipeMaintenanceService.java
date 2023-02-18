package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.PipeIndexDto;
import com.ingroinfo.mm.dto.PipeMaintenanceDto;
import com.ingroinfo.mm.dto.PipeMaintenanceInspectionDto;
import com.ingroinfo.mm.dto.PipeMaintenanceUpdateDto;

public interface PipeMaintenanceService {

	
	// Save Pipe Maintenance
	PipeMaintenanceDto savePipeMaintenance(PipeMaintenanceDto pipeMaintenanceDto);
	
	//Save Pipe Index
	PipeIndexDto savePipeIndex(PipeIndexDto pipeIndexDto);
	
	//save Pipe Maintenance Update Page
	PipeMaintenanceUpdateDto savePipeMaintenanceUpdate(PipeMaintenanceUpdateDto pipeMaintenanceUpdate);
		
	//save Pipe Maintenance Inspection Page
	PipeMaintenanceInspectionDto savePipeMaintenanceInspection(PipeMaintenanceInspectionDto pipeMaintenanceInspection);

	//To Show All in Pipe Maintenance to  Maintenance Update
	List<PipeMaintenanceDto> findAllPipeMaintenance();
	
	//To Show All in Pipe Maintenance update to Maintenance Inspection
	List<PipeMaintenanceUpdateDto> findAllPipeMaintenanceUpdate();
	
	
	 //show single Data on Pipe Id
	PipeMaintenanceDto getPipeMaintenanceById(Long pipeId);
	
	//show single Data on Pipe Maintenance Update 
	PipeMaintenanceUpdateDto getPipeMaintenanceUpdateById(Long pipeUpdateId);
	
	
	
	
	
}
