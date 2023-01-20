package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.DmaWardDto;

public interface DmaWardService {

	//create
	DmaWardDto saveDmaWard(DmaWardDto dmaWardDto);
	
	//GetAll Data
	List<DmaWardDto> getAllDmaWard();
	
	//Delete
	void deleteDmaWard(Long dmaWardId);
}
