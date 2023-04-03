package com.ingroinfo.mm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.MeterReplaceRepository;
import com.ingroinfo.mm.entity.MeterReplace;
import com.ingroinfo.mm.service.MeterService;

@Service
public class MeterServiceImpl implements MeterService {

	@Autowired
	private MeterReplaceRepository meterReplaceRepository;
	
	@Override
	public void save(MeterReplace meterReplace) {
		meterReplaceRepository.save(meterReplace);
	}

}
