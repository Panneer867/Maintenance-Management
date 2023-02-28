package com.ingroinfo.mm.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ingroinfo.mm.dto.DepartmentDto;

public interface DepartmentService {

	//create
	DepartmentDto saveDepartment(DepartmentDto departmentDto);
	
	//find All
	List<DepartmentDto> findAllDepartment();
	
	//Delete
	void deleteDepartmentMaster(Long depMasterId);
	
	//find Max Department Id 
	String getMaxDepartmentId();
	
	//Get Department List From UBARMS
	List<DepartmentDto> getDepartmentsFromUbarms() throws JsonMappingException, JsonProcessingException;
}
