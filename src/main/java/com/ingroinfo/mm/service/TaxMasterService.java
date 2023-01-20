package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.TaxMasterDto;

public interface TaxMasterService {

	//save Data
	TaxMasterDto saveTaxMaster(TaxMasterDto taxMasterDto);
	
	//get All Data
	List<TaxMasterDto> getAllTaxMaster();
	
	//Delete
	void deleteTaxMaster(Long taxMasterId);
}
