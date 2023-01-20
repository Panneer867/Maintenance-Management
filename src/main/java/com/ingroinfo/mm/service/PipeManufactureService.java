package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.PipeManufactureDto;

public interface PipeManufactureService {

	//save 
	PipeManufactureDto savePipeManufacture(PipeManufactureDto pipemanufacDto);
	
	//find All Data
	List<PipeManufactureDto> findAllPipeManufact();
	
	//Delete
	void deletePipeManufacture(Long pipemanufId);
}
