package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.SupplierDtlsDto;

public interface SupplierDtlsService {

	//create
	SupplierDtlsDto saveSupplierDtls(SupplierDtlsDto supplierDtlsDto);
	
	//find All
	List<SupplierDtlsDto> getAllSupplierDtls();
	
	//Delete
	void deleteSupplierDetails(Long suplyDtlsId);
}
