package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.PipeManufactureRepository;
import com.ingroinfo.mm.dto.PipeManufactureDto;
import com.ingroinfo.mm.entity.PipeManufacture;
import com.ingroinfo.mm.service.PipeManufactureService;

@Service
public class PipeManufactureServiceImpl implements PipeManufactureService {

	@Autowired
	private PipeManufactureRepository pipeManufactRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PipeManufactureDto savePipeManufacture(PipeManufactureDto pipemanufacDto) {
		PipeManufacture convPipeManufact = this.modelMapper.map(pipemanufacDto, PipeManufacture.class);
		PipeManufacture savedPipeManufact = this.pipeManufactRepo.save(convPipeManufact);
		return this.modelMapper.map(savedPipeManufact, PipeManufactureDto.class);
	}

	@Override
	public List<PipeManufactureDto> findAllPipeManufact() {
		List<PipeManufacture> pipeManufacts = this.pipeManufactRepo.findAll();
		List<PipeManufactureDto> pipeManufactDtos = pipeManufacts.stream().map((pipeManufact) -> 
		this.modelMapper.map(pipeManufact, PipeManufactureDto.class)).collect(Collectors.toList());
		return pipeManufactDtos;
	}

	@Override
	public void deletePipeManufacture(Long pipemanufId) {
		PipeManufacture pipeManufacture = this.pipeManufactRepo.findById(pipemanufId).get();
		this.pipeManufactRepo.delete(pipeManufacture);
	}

}
