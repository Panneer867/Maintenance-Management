package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.AssestEntryRepository;
import com.ingroinfo.mm.dto.AssestEntryDto;
import com.ingroinfo.mm.entity.AssestEntry;
import com.ingroinfo.mm.service.AssestEntryService;


@Service
public class AssestEntryServiceImpl implements AssestEntryService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AssestEntryRepository assestEntryRepository;
	
	@Override
	public AssestEntryDto saveAssestEntry(AssestEntryDto assestEntry) {
		AssestEntry assestEntrys =this.modelMapper.map(assestEntry, AssestEntry.class);
		AssestEntry saveAssestEntry = this.assestEntryRepository.save(assestEntrys);
		return this.modelMapper.map(saveAssestEntry, AssestEntryDto.class);
	}

	@Override
	public List<AssestEntryDto> findAllAssestEntry() {
		List<AssestEntry> idAssestEntrys = this.assestEntryRepository.findAll();
		List<AssestEntryDto> idAssestDto = idAssestEntrys.stream().map((idAssest)->
		this.modelMapper.map(idAssestEntrys, AssestEntryDto.class)).collect(Collectors.toList());
		return idAssestDto;
	}

}
