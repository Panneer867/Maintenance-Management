package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.MeterManufactureRepository;
import com.ingroinfo.mm.dto.MeterManufactureDto;
import com.ingroinfo.mm.entity.MeterManufacture;
import com.ingroinfo.mm.service.MeterManufactureService;

@Service
public class MeterManufactureServiceImpl implements MeterManufactureService {

	@Autowired
	private MeterManufactureRepository meterManufactRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MeterManufactureDto saveMeterManufact(MeterManufactureDto meterManufactDto) {
		MeterManufacture convMeterManufact = this.modelMapper.map(meterManufactDto, MeterManufacture.class);
		MeterManufacture savedMeterManufact = this.meterManufactRepo.save(convMeterManufact);
		return this.modelMapper.map(savedMeterManufact, MeterManufactureDto.class);
	}

	@Override
	public List<MeterManufactureDto> findAllMeterManufact() {
		List<MeterManufacture> meterManufacts = this.meterManufactRepo.findAll();
		List<MeterManufactureDto> meterManufactDtos = meterManufacts.stream().map((meterManufact) -> 
		this.modelMapper.map(meterManufact, MeterManufactureDto.class)).collect(Collectors.toList());
		return meterManufactDtos;
	}

	@Override
	public void deleteMeterManufacture(Long mtrmanufacId) {
		MeterManufacture meterManufacture = this.meterManufactRepo.findById(mtrmanufacId).get();
		this.meterManufactRepo.delete(meterManufacture);
	}

	@Override
	public String getMaxMeterId() {
		String maxMeterId = this.meterManufactRepo.getMaxMeterId();
		return maxMeterId;
	}

}
