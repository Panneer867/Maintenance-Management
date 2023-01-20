package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.UnitMeasureRepository;
import com.ingroinfo.mm.dto.UnitMeasureDto;
import com.ingroinfo.mm.entity.UnitMeasure;
import com.ingroinfo.mm.service.UnitMeasureService;

@Service
public class UnitMeasureServiceImpl implements UnitMeasureService {

	@Autowired
	private UnitMeasureRepository unitMeasureRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UnitMeasureDto saveUnitMeasure(UnitMeasureDto unitMeasureDto) {
		UnitMeasure convUnitMeasure = this.modelMapper.map(unitMeasureDto, UnitMeasure.class);
		UnitMeasure savedUnitMeasure = this.unitMeasureRepo.save(convUnitMeasure);
		return this.modelMapper.map(savedUnitMeasure, UnitMeasureDto.class);
	}

	@Override
	public List<UnitMeasureDto> getAllUnitMeasure() {
	    List<UnitMeasure> unitMeasures = this.unitMeasureRepo.findAll();
	    List<UnitMeasureDto> unitMeasureDtos = unitMeasures.stream().map((unitMeasure) -> 
	    this.modelMapper.map(unitMeasure, UnitMeasureDto.class)).collect(Collectors.toList());
		return unitMeasureDtos;
	}

	@Override
	public void deleteUnitOfMeasure(Long unitMeasureId) {
		 UnitMeasure unitMeasure = this.unitMeasureRepo.findById(unitMeasureId).get();
		 this.unitMeasureRepo.delete(unitMeasure);
	}

}
