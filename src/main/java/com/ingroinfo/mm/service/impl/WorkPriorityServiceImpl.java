package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.WorkPriorityRepository;
import com.ingroinfo.mm.dto.WorkPriorityDto;
import com.ingroinfo.mm.entity.WorkPriority;
import com.ingroinfo.mm.service.WorkPriorityService;

@Service
public class WorkPriorityServiceImpl implements WorkPriorityService {

	@Autowired
	private WorkPriorityRepository workPriorityRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public WorkPriorityDto saveWorkPriority(WorkPriorityDto workPriorityDto) {
		WorkPriority convWorkPriority = this.modelMapper.map(workPriorityDto, WorkPriority.class);
		WorkPriority savedWorkPriority = this.workPriorityRepo.save(convWorkPriority);
		return this.modelMapper.map(savedWorkPriority, WorkPriorityDto.class);
	}

	@Override
	public List<WorkPriorityDto> findAllWorkPriority() {
		List<WorkPriority> workPriorities = this.workPriorityRepo.findAll();
		List<WorkPriorityDto> workPriorityDtos = workPriorities.stream().map((workPriority) -> 
		this.modelMapper.map(workPriority, WorkPriorityDto.class)).collect(Collectors.toList());
		return workPriorityDtos;
	}

	@Override
	public void deleteWorkPriority(Long workPrioId) {
		 WorkPriority workPriority = this.workPriorityRepo.findById(workPrioId).get();
		 this.workPriorityRepo.delete(workPriority);
	}

}
