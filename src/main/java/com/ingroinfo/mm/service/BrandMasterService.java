package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.BrandMasterDto;

public interface BrandMasterService {

	//save Data
	BrandMasterDto saveBrandMaster(BrandMasterDto brandMasterDto);
	
	//Get All Data
	List<BrandMasterDto> getAllBrandMasters();
	
	//Delete
	void deleteBrandMaster(Long brandMasterId);
}
