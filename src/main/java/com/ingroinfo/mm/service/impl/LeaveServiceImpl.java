package com.ingroinfo.mm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.LeaveRepository;
import com.ingroinfo.mm.entity.Leave;
import com.ingroinfo.mm.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService{

	@Autowired
	private LeaveRepository leaveRepository;
	
	@Override
	public Leave saveLeave(Leave leave) {
	
		return leaveRepository.save(leave);
	}
	

}
