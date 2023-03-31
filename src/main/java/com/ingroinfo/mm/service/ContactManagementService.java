package com.ingroinfo.mm.service;



import java.util.List;

import com.ingroinfo.mm.dto.ContactManagementDto;
import com.ingroinfo.mm.entity.ContactManagement;

public interface ContactManagementService {
	
	// Save Data in Contact Management 
	ContactManagementDto save(ContactManagement contactManagement);
	
	//to get List of Contact Management
	List<ContactManagement>  getAllContactDetails();

}
