package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.EmployeeInspectRepository;
import com.ingroinfo.mm.dao.EmployeeMasterRepository;
import com.ingroinfo.mm.dao.EmployeePaymentRepository;
import com.ingroinfo.mm.dto.EmployeeMasterDto;
import com.ingroinfo.mm.dto.EmployeePaymentDto;
import com.ingroinfo.mm.entity.EmployeeInspection;
import com.ingroinfo.mm.entity.EmployeeMaster;
import com.ingroinfo.mm.entity.EmployeePayment;
import com.ingroinfo.mm.service.EmployeeMasterService;



@Service
public class EmployeeMasterServiceImpl implements EmployeeMasterService {
	
	@Autowired
	private ModelMapper modelMapper;	
    @Autowired
	private EmployeeMasterRepository employeeMasterRepository ;   
    @Autowired
    private EmployeeInspectRepository employeeInspectRepository;
    @Autowired
    private EmployeePaymentRepository empPaymentRepo;
    


@Override
public void deleteEmployeeById(Long employeeId) {
	// TODO Auto-generated method stub
	employeeMasterRepository.deleteById(employeeId);
}





@Override
public void saveEmployeeInspect(EmployeeInspection employeeInspect) {
	// TODO Auto-generated method stub
	employeeInspectRepository.save(employeeInspect);
}


@Override
public void saveEmployeeMaster(EmployeeMaster employee) {
	// TODO Auto-generated method stub
	employeeMasterRepository.save(employee);
}


@Override
public List<EmployeeMasterDto> getAllemployeeMaster() {
	// TODO Auto-generated method stub
	
	List<EmployeeMaster> employeeList= employeeMasterRepository.findAll();
	
	List<EmployeeMasterDto> employeemasterDto =
			  employeeList.stream().map((employeeMaster) ->{
				  EmployeeMasterDto newEmployee = new EmployeeMasterDto();
			  newEmployee.setEmployeeId(employeeMaster.getEmployeeId());
			  newEmployee.setEmployeeCode(employeeMaster.getEmployeeCode());
			  newEmployee.setEmpName(employeeMaster.getEmpName());
			  newEmployee.setFatherName(employeeMaster.getFatherName());
			  newEmployee.setHouseNo(employeeMaster.getHouseNo());
			  newEmployee.setAddress(employeeMaster.getAddress());
			  newEmployee.setAadharNo(employeeMaster.getAadharNo());
			  newEmployee.setBankAccNo(employeeMaster.getBankAccNo());
			  newEmployee.setBankName(employeeMaster.getBankName());
			  newEmployee.setContactNo(employeeMaster.getContactNo());
			  newEmployee.setDesignation(employeeMaster.getDesignation());
			  newEmployee.setDepartment(employeeMaster.getDepartment());
			  newEmployee.setDlNo(employeeMaster.getDlNo());
			  newEmployee.setPanNO(employeeMaster.getPanNO());
			  newEmployee.setPassportNo(employeeMaster.getPassportNo());
			  newEmployee.setPfNo(employeeMaster.getPfNo());
			  newEmployee.setEsiNumber(employeeMaster.getEsiNumber());
			  newEmployee.setIfscCode(employeeMaster.getIfscCode());
			  newEmployee.setRefContactNo(employeeMaster.getRefContactNo());
			  newEmployee.setDateOfJoin(employeeMaster.getDateOfJoin());
			  newEmployee.setBloodGroup(employeeMaster.getBloodGroup());
			  newEmployee.setGender(employeeMaster.getGender());
			  newEmployee.setBranch(employeeMaster.getBranch());
			  newEmployee.setCompany(employeeMaster.getCompany());
			  newEmployee.setCity(employeeMaster.getCity());
			  newEmployee.setDateOfBirth(employeeMaster.getDateOfBirth());
			  newEmployee.setEndDate(employeeMaster.getEndDate());
			  newEmployee.setEmpStatus(employeeMaster.getEmpStatus());
			  newEmployee.setEmptype(employeeMaster.getEmptype());
			  newEmployee.setCl(employeeMaster.getCl());
			  newEmployee.setLwp(employeeMaster.getLwp());
			  newEmployee.setSl(employeeMaster.getSl());
			  newEmployee.setMaritalstatus(employeeMaster.getMaritalstatus());
			  newEmployee.setTotalLeave(employeeMaster.getTotalLeave());
			  newEmployee.setState(employeeMaster.getState());
			  newEmployee.setPersonName(employeeMaster.getPersonName());
			  newEmployee.setQualification(employeeMaster.getQualification());
			  newEmployee.setPinCode(employeeMaster.getPinCode());
		
			  return newEmployee; }).collect(Collectors.toList());
			  
			  return employeemasterDto; 
}


@Override
public void updateEmployee(EmployeeMaster employee) {
	// TODO Auto-generated method stub
	employeeMasterRepository.save(employee);
}

@Override
public EmployeeMaster getEmployeeById(Long employeeId) {

	Optional<EmployeeMaster> optional = employeeMasterRepository.findById(employeeId);
	EmployeeMaster employee = null;
	if (optional.isPresent()) {
		employee = optional.get();
	} else {
		throw new RuntimeException(" Employee not found for id :: " + employeeId);
	}
	return employee;
}


@Override
public EmployeeMasterDto getEmployeeByEmpCode(String employeeCode) {
	EmployeeMaster employee = this.employeeMasterRepository.findByEmployeeCode(employeeCode);
	return this.modelMapper.map(employee, EmployeeMasterDto.class);
}


@Override
public EmployeePaymentDto saveEmployeePayment(EmployeePaymentDto empPaymentDto) {
	EmployeePayment convEmployeePayment = this.modelMapper.map(empPaymentDto, EmployeePayment.class);
	EmployeePayment savedEmployeePayment = this.empPaymentRepo.save(convEmployeePayment);
	return this.modelMapper.map(savedEmployeePayment, EmployeePaymentDto.class);
}


@Override
public EmployeeMasterDto saveEmployeeMaster(EmployeeMasterDto empMasterDto) {
	    EmployeeMaster convEmployeeMaster  =  this.modelMapper.map(empMasterDto, EmployeeMaster.class);
	    EmployeeMaster savedEmployeeMaster =  this.employeeMasterRepository.save(convEmployeeMaster);
	    return this.modelMapper.map(savedEmployeeMaster, EmployeeMasterDto.class);
}


}
 


