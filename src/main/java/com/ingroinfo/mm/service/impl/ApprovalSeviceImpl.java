package com.ingroinfo.mm.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.MaterialApprovalRepository;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.InwardMaterialApproved;
import com.ingroinfo.mm.service.ApprovalService;
import com.ingroinfo.mm.service.StockService;

@Service
public class ApprovalSeviceImpl implements ApprovalService {
	
	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private MaterialApprovalRepository materialApprovalRepository;
	
	@Autowired
	private StockService stockService;

	@Override
	public void saveMaterial(InwardItemDto inwardItemDto) {
		
		InwardMaterialApproved inwardMaterialApproved = modelMapper.map(inwardItemDto, InwardMaterialApproved.class);

		materialApprovalRepository.save(inwardMaterialApproved);
		
		stockService.deleteBundleMaterial(inwardItemDto.getBundleId());
	}

}
