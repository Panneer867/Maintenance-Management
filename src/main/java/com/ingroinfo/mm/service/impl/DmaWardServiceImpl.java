package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.DmaWardRepository;
import com.ingroinfo.mm.dto.DmaWardDto;
import com.ingroinfo.mm.entity.DmaWard;
import com.ingroinfo.mm.service.DmaWardService;

@Service
public class DmaWardServiceImpl implements DmaWardService {
	
	@Autowired
	private DmaWardRepository dmaWardRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DmaWardDto saveDmaWard(DmaWardDto dmaWardDto) {
		 DmaWard convDmaWard = this.modelMapper.map(dmaWardDto, DmaWard.class);
	     DmaWard savedDmaWard = this.dmaWardRepository.save(convDmaWard);
		 return modelMapper.map(savedDmaWard, DmaWardDto.class);
	}

	@Override
	public List<DmaWardDto> getAllDmaWard() {
		 List<DmaWard> dmaWards = this.dmaWardRepository.findAll();
		 List<DmaWardDto> dmaWardDtos = dmaWards.stream().map((dmaWard) -> 
		 this.modelMapper.map(dmaWard, DmaWardDto.class)).collect(Collectors.toList());
		return dmaWardDtos;
	}

	@Override
	public void deleteDmaWard(Long dmaWardId) {
		DmaWard dmaWard = this.dmaWardRepository.findById(dmaWardId).get();
		this.dmaWardRepository.delete(dmaWard);
	}

}
