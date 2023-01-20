package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.TaxMasterRepository;
import com.ingroinfo.mm.dto.TaxMasterDto;
import com.ingroinfo.mm.entity.TaxMaster;
import com.ingroinfo.mm.service.TaxMasterService;

@Service
public class TaxMasterServiceImpl implements TaxMasterService {

	@Autowired
	private TaxMasterRepository taxMasterRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public TaxMasterDto saveTaxMaster(TaxMasterDto taxMasterDto) {
		TaxMaster convTaxMaster = this.modelMapper.map(taxMasterDto, TaxMaster.class);
		this.taxMasterRepository.save(convTaxMaster);
		return this.modelMapper.map(convTaxMaster, TaxMasterDto.class);
	}

	@Override
	public List<TaxMasterDto> getAllTaxMaster() {
		List<TaxMaster> taxMasters = this.taxMasterRepository.findAll();
		List<TaxMasterDto> taxMasterDtos = taxMasters.stream().map((taxmaster) -> 
		this.modelMapper.map(taxmaster, TaxMasterDto.class)).collect(Collectors.toList());
		return taxMasterDtos;
	}

	@Override
	public void deleteTaxMaster(Long taxMasterId) {
		TaxMaster taxMaster = this.taxMasterRepository.findById(taxMasterId).get();
		this.taxMasterRepository.delete(taxMaster);
	}

}
