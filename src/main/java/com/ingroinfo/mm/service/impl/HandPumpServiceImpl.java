package com.ingroinfo.mm.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.HandPumpRepository;
import com.ingroinfo.mm.dto.HandPumpDto;
import com.ingroinfo.mm.entity.HandPump;
import com.ingroinfo.mm.service.HandPumpService;

@Service
public class HandPumpServiceImpl implements HandPumpService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	HandPumpRepository handPumpRepo;
	
	@Override
	public HandPumpDto saveHandPump(HandPumpDto handPumpDto) {
		HandPump handPump =this.modelMapper.map(handPumpDto, HandPump.class);
		HandPump saveHandPump = this.handPumpRepo.save(handPump);
		return this.modelMapper.map(saveHandPump, HandPumpDto.class);
	}

}
