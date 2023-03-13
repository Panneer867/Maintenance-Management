package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.VehicleDtlsRepository;
import com.ingroinfo.mm.dto.VehicleDtlsDto;
import com.ingroinfo.mm.entity.VehicleDtls;
import com.ingroinfo.mm.service.VehicleDtlsService;

@Service
public class VehicleDtlsSeviceImpl implements VehicleDtlsService {

	@Autowired
	private VehicleDtlsRepository vehicleDtlsRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public VehicleDtlsDto saveVDtls(VehicleDtlsDto vehicleDtlsDto) {
		VehicleDtls conVehicleDtls = this.modelMapper.map(vehicleDtlsDto, VehicleDtls.class);
		VehicleDtls savedVehicleDtls = this.vehicleDtlsRepo.save(conVehicleDtls);
		return this.modelMapper.map(savedVehicleDtls, VehicleDtlsDto.class);
	}

	@Override
	public List<VehicleDtlsDto> findAllVehicleDtls() {
		List<VehicleDtls> vehicleDtlss = this.vehicleDtlsRepo.findAll();
		List<VehicleDtlsDto> vehicleDtlsDtos = vehicleDtlss.stream()
				.map((vehicle) -> this.modelMapper.map(vehicle, VehicleDtlsDto.class)).collect(Collectors.toList());
		return vehicleDtlsDtos;
	}

	@Override
	public void deleteVehicleDetails(Long vehicleDtlsId) {
		VehicleDtls vehicleDtls = this.vehicleDtlsRepo.findById(vehicleDtlsId).get();
		this.vehicleDtlsRepo.delete(vehicleDtls);
	}

	@Override
	public String getMaxVehicleId() {
		String maxVehicleId = this.vehicleDtlsRepo.getMaxVehicleId();
		return maxVehicleId;
	}

	@Override
	public List<String> getAllVehicleTypes() {
		List<String> vehicleTypes = this.vehicleDtlsRepo.getAllVehicleTypes();
		return vehicleTypes;
	}

	@Override
	public List<VehicleDtlsDto> getVehiclesByVehicleType(String vehicleType) {
		List<VehicleDtls> vehicleDtls = this.vehicleDtlsRepo.findByVehicleType(vehicleType);
		return vehicleDtls.stream().map((vehicle) -> this.modelMapper.map(vehicle, VehicleDtlsDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public VehicleDtlsDto getVehicleDtlsByVehicleDtlsId(Long vehicleId) {
		VehicleDtls vehicleDtls = this.vehicleDtlsRepo.findById(vehicleId).get();
		return this.modelMapper.map(vehicleDtls, VehicleDtlsDto.class);
	}

}
