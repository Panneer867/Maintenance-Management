package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.DepartmentRepository;
import com.ingroinfo.mm.dto.DepartmentDto;
import com.ingroinfo.mm.entity.Department;
import com.ingroinfo.mm.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		Department convDepartment = this.modelMapper.map(departmentDto, Department.class);
		Department savedDepartment = this.departmentRepository.save(convDepartment);
		return this.modelMapper.map(savedDepartment, DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> findAllDepartment() {
		List<Department> departments = this.departmentRepository.findAll();
		List<DepartmentDto> departmentDtos = departments.stream().map((department) -> this.modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
		return departmentDtos;
	}

	@Override
	public void deleteDepartmentMaster(Long depMasterId) {
		Department department = this.departmentRepository.findById(depMasterId).get();
		this.departmentRepository.delete(department);
	}

	@Override
	public String getMaxDepartmentId() {
	    String departmentId = this.departmentRepository.getMaxDepartmentId();
		return departmentId;
	}

	

}
