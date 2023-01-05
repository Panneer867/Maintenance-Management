package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.IdMasterDto;

public interface IdMasterService {

	//save Data
    IdMasterDto saveIdMaster(IdMasterDto idDto);
    
    //FindAll
    List<IdMasterDto> findAllIdMaster();
	
}
