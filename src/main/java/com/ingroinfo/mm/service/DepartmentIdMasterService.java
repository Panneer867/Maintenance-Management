package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.DepartmentIdMasterDto;

public interface DepartmentIdMasterService {

	//save Data
	DepartmentIdMasterDto saveDepartmentIdMaster(DepartmentIdMasterDto deptIdMasterDto);
	
	//find All
	List<DepartmentIdMasterDto> findAllDepartmentIdMaster();
			
	//Delete
	void deleteDepartmentMaster(Long depMasterId);
	
	//get Data by MasterIdName
	List<DepartmentIdMasterDto> getByMasterIdName(String masterIdName);
	
	//get Data by MasterId
	DepartmentIdMasterDto getDeptIdMasterByDepMasterId(Long depMasterId);
}
