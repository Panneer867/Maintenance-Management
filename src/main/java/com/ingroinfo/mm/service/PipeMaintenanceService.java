package com.ingroinfo.mm.service;

import java.util.List;


import com.ingroinfo.mm.dto.PipeMaintenanceDto;
import com.ingroinfo.mm.dto.PipeMaintenanceInspectionDto;
import com.ingroinfo.mm.dto.PipeMaintenanceUpdateDto;

public interface PipeMaintenanceService {

	
	// Save info
	PipeMaintenanceDto savePipeMaintenance(PipeMaintenanceDto pipeMaintenanceDto);

	//To Show All in Pipe Maintenance to  Maintenance Update
	List<PipeMaintenanceDto> findAllPipeMaintenance();
	
	//To Show All in Pipe Maintenance update to Maintenance Inspection
	List<PipeMaintenanceUpdateDto> findAllPipeMaintenanceUpdate();
	
	
	 //show single Data on Pipe Id
	PipeMaintenanceDto getPipeMaintenanceById(Long pipeId);
	
	//show single Data on Pipe Maintenance Update 
	PipeMaintenanceUpdateDto getPipeMaintenanceUpdateById(Long pipeUpdateId);
	
	//save Pipe Maintenance Update Page
	PipeMaintenanceUpdateDto savePipeMaintenanceUpdate(PipeMaintenanceUpdateDto pipeMaintenanceUpdate);
	
	//save Pipe Maintenance Inspection Page
	PipeMaintenanceInspectionDto savePipeMaintenanceInspection(PipeMaintenanceInspectionDto pipeMaintenanceInspection);
	
}
