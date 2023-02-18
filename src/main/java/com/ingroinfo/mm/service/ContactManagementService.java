package com.ingroinfo.mm.service;



import java.util.List;

import com.ingroinfo.mm.dto.ContactManagementDto;

public interface ContactManagementService {
	
	// Save Data in Contact Management 
	ContactManagementDto saveContactMangement(ContactManagementDto contactMangementDto);
	
	//to get List of Contact Management
	List<ContactManagementDto>  findAllContactManagement();

}
