package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.SupplierDtlsRepository;
import com.ingroinfo.mm.dto.SupplierDtlsDto;
import com.ingroinfo.mm.entity.SupplierDtls;
import com.ingroinfo.mm.service.SupplierDtlsService;

@Service
public class SupplierDtlsServiceImpl implements SupplierDtlsService {

	@Autowired
	SupplierDtlsRepository supplierDtlsRepo;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public SupplierDtlsDto saveSupplierDtls(SupplierDtlsDto supplierDtlsDto) {
		SupplierDtls convSupplierDtls = this.modelMapper.map(supplierDtlsDto, SupplierDtls.class);
		SupplierDtls savedSupplierDtls = this.supplierDtlsRepo.save(convSupplierDtls);
		return this.modelMapper.map(savedSupplierDtls, SupplierDtlsDto.class);
	}

	@Override
	public List<SupplierDtlsDto> getAllSupplierDtls() {
		List<SupplierDtls> supplierDtlss = this.supplierDtlsRepo.findAll();
		List<SupplierDtlsDto> supplierDtlsDtos = supplierDtlss.stream().map((supplier)-> 
		this.modelMapper.map(supplier, SupplierDtlsDto.class)).collect(Collectors.toList());
		return supplierDtlsDtos;
	}

	@Override
	public void deleteSupplierDetails(Long suplyDtlsId) {
		 SupplierDtls supplierDtls = this.supplierDtlsRepo.findById(suplyDtlsId).get();
		 this.supplierDtlsRepo.delete(supplierDtls);
	}

}
