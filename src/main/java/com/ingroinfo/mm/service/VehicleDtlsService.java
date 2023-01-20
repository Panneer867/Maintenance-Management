package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.VehicleDtlsDto;

public interface VehicleDtlsService {

	//create
	VehicleDtlsDto saveVDtls(VehicleDtlsDto vehicleDtlsDto);
	
	//find All
	List<VehicleDtlsDto> findAllVehicleDtls();
	
	//delete
	void deleteVehicleDetails(Long vehicleDtlsId);
	
	//get Max Vehicle Id
	String getMaxVehicleId();
}
