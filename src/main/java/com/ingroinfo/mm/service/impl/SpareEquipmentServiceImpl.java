package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.SpareEquipmentRepository;
import com.ingroinfo.mm.dto.SpareEquipmentDto;
import com.ingroinfo.mm.entity.SpareEquipment;
import com.ingroinfo.mm.service.SpareEquipmentService;

@Service
public class SpareEquipmentServiceImpl implements SpareEquipmentService {

	@Autowired
	private SpareEquipmentRepository spareEquipRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public SpareEquipmentDto saveSpareEquipment(SpareEquipmentDto spareEquipDto) {
		SpareEquipment convEquipment = this.modelMapper.map(spareEquipDto, SpareEquipment.class);
		SpareEquipment savedEquipment = this.spareEquipRepo.save(convEquipment);
		return this.modelMapper.map(savedEquipment, SpareEquipmentDto.class);
	}

	@Override
	public List<SpareEquipmentDto> getAllSpareEquipmens() {
		List<SpareEquipment> spareEquipments = this.spareEquipRepo.findAll();
		List<SpareEquipmentDto> spareEquipmentDtos = spareEquipments.stream().map((spareEquipment) -> 
		this.modelMapper.map(spareEquipment, SpareEquipmentDto.class)).collect(Collectors.toList());
		return spareEquipmentDtos;
	}

	@Override
	public void deleteSpareEquipment(Long spareequiId) {
	   SpareEquipment spareEquipment = this.spareEquipRepo.findById(spareequiId).get();
	   this.spareEquipRepo.delete(spareEquipment);
	}

}
