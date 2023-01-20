package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.StoreBranchDto;

public interface StoreBranchService {

	//Save Data
	StoreBranchDto saveStoreBranch(StoreBranchDto storeBranchDto);
	
	//Find All
	List<StoreBranchDto> findAllStoreBranch();
	
	//Delete
	void deleteStoreBranch(Long strbraNameId);
	
	//Get Maximum Store Branch Id
	String getMaxStoreBranchId();
}
