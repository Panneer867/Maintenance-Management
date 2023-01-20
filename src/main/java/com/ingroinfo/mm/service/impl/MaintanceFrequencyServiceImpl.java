package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.MaintanceFrequencyRepository;
import com.ingroinfo.mm.dto.MaintanceFrequencyDto;
import com.ingroinfo.mm.entity.MaintanceFrequency;
import com.ingroinfo.mm.service.MaintanceFrequencyService;

@Service
public class MaintanceFrequencyServiceImpl implements MaintanceFrequencyService {

	@Autowired
	private MaintanceFrequencyRepository maintanFrequencyRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MaintanceFrequencyDto saveMaintanceFrequency(MaintanceFrequencyDto maintanFrequency) {
		MaintanceFrequency convMaintanFreq = this.modelMapper.map(maintanFrequency, MaintanceFrequency.class);
		MaintanceFrequency savedMaintanFreq = this.maintanFrequencyRepo.save(convMaintanFreq);
		return this.modelMapper.map(savedMaintanFreq, MaintanceFrequencyDto.class);
	}

	@Override
	public List<MaintanceFrequencyDto> getAllMaintanceFrequency() {
		List<MaintanceFrequency> maintanFreqs = this.maintanFrequencyRepo.findAll();
		List<MaintanceFrequencyDto> maintanFreqDtos = maintanFreqs.stream().map((maintanFreq) -> 
		this.modelMapper.map(maintanFreq, MaintanceFrequencyDto.class)).collect(Collectors.toList());
		return maintanFreqDtos;
	}

	@Override
	public void deleteMaintenFrequency(Long maintanFrequId) {
		MaintanceFrequency maintanceFrequency = this.maintanFrequencyRepo.findById(maintanFrequId).get();
		this.maintanFrequencyRepo.delete(maintanceFrequency);
	}

}
