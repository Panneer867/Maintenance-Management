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

	//Get All Vehicle Types
	List<String> getAllVehicleTypes();

	//Get Vehicle Details By VehicleType
	List<VehicleDtlsDto> getVehiclesByVehicleType(String vehicleType);

	//Get Vehicle Details By VehicleDtlsId
	VehicleDtlsDto getVehicleDtlsByVehicleDtlsId(Long vehicleId);
}
