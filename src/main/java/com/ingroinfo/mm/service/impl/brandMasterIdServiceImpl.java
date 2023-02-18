package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.BrandMasterRepository;
import com.ingroinfo.mm.dto.BrandMasterDto;
import com.ingroinfo.mm.entity.BrandMaster;
import com.ingroinfo.mm.service.BrandMasterService;

@Service
public class brandMasterIdServiceImpl implements BrandMasterService {
	
	@Autowired
	private BrandMasterRepository brandMasterRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BrandMasterDto saveBrandMaster(BrandMasterDto brandMasterDto) {
		BrandMaster convBrandMaster = this.modelMapper.map(brandMasterDto, BrandMaster.class);
		BrandMaster savedBrandMaster = this.brandMasterRepo.save(convBrandMaster);
		return this.modelMapper.map(savedBrandMaster, BrandMasterDto.class);
	}

	@Override
	public List<BrandMasterDto> getAllBrandMasters() {
		List<BrandMaster> brandMasters = this.brandMasterRepo.findAll();
		List<BrandMasterDto> brandMasterDtos = brandMasters.stream().map((brandMastr) -> 
		this.modelMapper.map(brandMastr, BrandMasterDto.class)).collect(Collectors.toList());
		return brandMasterDtos;
	}

	@Override
	public void deleteBrandMaster(Long brandMasterId) {
		BrandMaster brandMaster = this.brandMasterRepo.findById(brandMasterId).get();
		this.brandMasterRepo.delete(brandMaster);
	}

}
