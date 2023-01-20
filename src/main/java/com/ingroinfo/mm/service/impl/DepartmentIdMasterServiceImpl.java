package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.DepartmentIdMasterRepository;
import com.ingroinfo.mm.dto.DepartmentIdMasterDto;
import com.ingroinfo.mm.entity.DepartmentIdMaster;
import com.ingroinfo.mm.service.DepartmentIdMasterService;

@Service
public class DepartmentIdMasterServiceImpl implements DepartmentIdMasterService {

	@Autowired
	private DepartmentIdMasterRepository deptIdMasterRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public DepartmentIdMasterDto saveDepartmentIdMaster(DepartmentIdMasterDto deptIdMasterDto) {
		DepartmentIdMaster convDepartmentIdMaster = this.modelMapper.map(deptIdMasterDto, DepartmentIdMaster.class);
		DepartmentIdMaster saveDepartmentIdMaster = this.deptIdMasterRepo.save(convDepartmentIdMaster);
		return this.modelMapper.map(saveDepartmentIdMaster, DepartmentIdMasterDto.class);
	}

	@Override
	public List<DepartmentIdMasterDto> findAllDepartmentIdMaster() {
		List<DepartmentIdMaster> departmentIdMasters = this.deptIdMasterRepo.findAll();
		List<DepartmentIdMasterDto> departmentIdMasterDtos = departmentIdMasters.stream().map((departmentId) -> 
		this.modelMapper.map(departmentId, DepartmentIdMasterDto.class)).collect(Collectors.toList());
		return departmentIdMasterDtos;
	}
	
	@Override
	public void deleteDepartmentMaster(Long depMasterId) {
		DepartmentIdMaster departmentIdMaster = this.deptIdMasterRepo.findById(depMasterId).orElseThrow();	
		this.deptIdMasterRepo.delete(departmentIdMaster);
	}

	@Override
	public List<DepartmentIdMasterDto> getByMasterIdName(String masterIdName) {
	     List<DepartmentIdMaster> deptDepartmentIdMasters = this.deptIdMasterRepo.findByMasterIdName(masterIdName);
	     List<DepartmentIdMasterDto> departmentIdMasterDtos = deptDepartmentIdMasters.stream().map((deptMasterId) -> 
	     this.modelMapper.map(deptMasterId, DepartmentIdMasterDto.class)).collect(Collectors.toList());
		 return departmentIdMasterDtos;
	}

	@Override
	public DepartmentIdMasterDto getDeptIdMasterByDepMasterId(Long depMasterId) {
		DepartmentIdMaster departmentIdMaster = this.deptIdMasterRepo.findById(depMasterId).get();
		return this.modelMapper.map(departmentIdMaster, DepartmentIdMasterDto.class);
	}

	

}
