package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.DistributionScheduleRepository;
import com.ingroinfo.mm.dto.DistributionScheduleDto;
import com.ingroinfo.mm.entity.DistributionSchedule;
import com.ingroinfo.mm.service.DistributionScheduleService;

@Service
public class DistributionScheduleServiceImpl implements DistributionScheduleService {
	
	@Autowired
	private DistributionScheduleRepository disScheduleRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DistributionScheduleDto saveDisSchedule(DistributionScheduleDto disSchedule) {
		 DistributionSchedule disSchedules = this.modelMapper.map(disSchedule, DistributionSchedule.class);
		 DistributionSchedule savedDisSchedule  = this.disScheduleRepo.save(disSchedules);
		return this.modelMapper.map(savedDisSchedule, DistributionScheduleDto.class);
	}

	@Override
	public List<DistributionScheduleDto> findAllDisSchedule() {
		List<DistributionSchedule> disSchedules = this.disScheduleRepo.findAll();
		List<DistributionScheduleDto> disScheduleDtos = disSchedules.stream().map((distributionSchedule)-> 
		this.modelMapper.map(distributionSchedule, DistributionScheduleDto.class)).collect(Collectors.toList());
		return disScheduleDtos;
	}

	@Override
	public void deleteDistrbSchedule(Long disScheduleId) {
		DistributionSchedule distributionSchedule= this.disScheduleRepo.findById(disScheduleId).get();
		this.disScheduleRepo.delete(distributionSchedule);
	}

}
