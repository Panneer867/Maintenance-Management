package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.SpareEquipmentDto;

public interface SpareEquipmentService {

	//create
	SpareEquipmentDto saveSpareEquipment(SpareEquipmentDto spareEquipDto);
	
	//Show All Data
	List<SpareEquipmentDto> getAllSpareEquipmens();
	
	//Delete
	void deleteSpareEquipment(Long spareequiId);
}
