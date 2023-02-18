package com.ingroinfo.mm.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.LeakageMaintenUpdateRepository;
import com.ingroinfo.mm.dto.LeakageMainteUpdateDto;
import com.ingroinfo.mm.entity.LeakageMaintenanceUpdate;
import com.ingroinfo.mm.service.LeakageMaintenanceService;


@Service
public class LeakageMaintenanceServiceImpl  implements LeakageMaintenanceService{

	@Autowired
    ModelMapper modelMapper;
	
	@Autowired
	LeakageMaintenUpdateRepository leakageMaintenUpdateRepo;
	
	@Override
	public LeakageMainteUpdateDto saveMaintenanceType(LeakageMainteUpdateDto leakageMainteUpdateDto) {
		LeakageMaintenanceUpdate leakageMainteUpdate = this.modelMapper.map(leakageMainteUpdateDto, LeakageMaintenanceUpdate.class);
		LeakageMaintenanceUpdate saveLeakageMainteUpdate = this.leakageMaintenUpdateRepo.save(leakageMainteUpdate);
		return this.modelMapper.map(saveLeakageMainteUpdate, LeakageMainteUpdateDto.class);
	}

	@Override
	public List<LeakageMainteUpdateDto> findAllLeakageMainteUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLekageMainteUpdate(Long leakageUpdateId) {
		// TODO Auto-generated method stub
		
	}

}
