package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.LeakageTypeDto;

public interface LeakageTypeService {

	//save
	LeakageTypeDto saveLeakageType(LeakageTypeDto leakageTypeDto);
	
	//Find All
	List<LeakageTypeDto> getAllLeakageType();
	
	//Delete
	void deleteLeakageType(Long leakageId);
}
