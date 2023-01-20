package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.WorkStatusRepository;
import com.ingroinfo.mm.dto.WorkStatusDto;
import com.ingroinfo.mm.entity.WorkStatus;
import com.ingroinfo.mm.service.WorkStatusService;

@Service
public class WorkStatusServiceImpl implements WorkStatusService {

	@Autowired
	private WorkStatusRepository workStatusRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public WorkStatusDto saveWorkStatus(WorkStatusDto workStatusDto) {
		WorkStatus convWorkStatus = this.modelMapper.map(workStatusDto, WorkStatus.class);
		WorkStatus savedWorkStatus = this.workStatusRepo.save(convWorkStatus);
		return this.modelMapper.map(savedWorkStatus, WorkStatusDto.class);
	}

	@Override
	public List<WorkStatusDto> getAllWorkStatus() {
		List<WorkStatus> workStatuss = this.workStatusRepo.findAll();
		List<WorkStatusDto> workStatusDtos = workStatuss.stream().map((workStatus) -> 
		this.modelMapper.map(workStatus, WorkStatusDto.class)).collect(Collectors.toList());
		return workStatusDtos;
	}

	@Override
	public void deleteWorkStatus(Long workStsId) {
	  WorkStatus workStatus = this.workStatusRepo.findById(workStsId).get();
	  this.workStatusRepo.delete(workStatus);
	}

}
