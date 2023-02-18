package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.ContactManagementRepository;
import com.ingroinfo.mm.dto.ContactManagementDto;
import com.ingroinfo.mm.entity.ContactManagement;
import com.ingroinfo.mm.service.ContactManagementService;



@Service
public class ContactManagementServiceImp implements ContactManagementService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ContactManagementRepository contactMangeRepo;
	
	@Override
	public ContactManagementDto saveContactMangement(ContactManagementDto contactMangementDto) {
		ContactManagement contactMangement = this.modelMapper.map(contactMangementDto, ContactManagement.class);
		ContactManagement saveContactMangement	= this.contactMangeRepo.save(contactMangement);
		return this.modelMapper.map(saveContactMangement, ContactManagementDto.class);
	}

	@Override
	public List<ContactManagementDto> findAllContactManagement() {
       List<ContactManagement> contManagtList = this.contactMangeRepo.findAll();
       List<ContactManagementDto> contactManagementDtos = contManagtList.stream().map((contManag)->
       			this.modelMapper.map(contManag, ContactManagementDto.class)).collect(Collectors.toList());
		return contactManagementDtos;
	}

}
