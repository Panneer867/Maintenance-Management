package com.ingroinfo.mm.service.impl;

import java.util.List;
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
	public List<AssestEntry> findAllAssestEntry() {
		
		return this.assestEntryRepository.findAll();
	}

	@Override
	public AssestEntryDto getAssestEntryById(Long assestEntryId) {
		AssestEntry assestEntry =	this.assestEntryRepository.findById(assestEntryId).get();
		return this.modelMapper.map(assestEntry, AssestEntryDto.class);
	}

}
