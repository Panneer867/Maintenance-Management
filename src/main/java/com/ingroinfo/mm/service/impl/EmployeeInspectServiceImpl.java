package com.ingroinfo.mm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.EmployeeInspectRepository;
import com.ingroinfo.mm.entity.EmployeeInspection;
import com.ingroinfo.mm.service.EmployeeInspectService;

@Service
public class EmployeeInspectServiceImpl implements EmployeeInspectService{
	
	@Autowired
	private EmployeeInspectRepository employeeInspectRepository;
	


	@Override
	public void saveInspect(EmployeeInspection employeeInspect) {
		// TODO Auto-generated method stub
		employeeInspectRepository.save(employeeInspect);
	}

}
