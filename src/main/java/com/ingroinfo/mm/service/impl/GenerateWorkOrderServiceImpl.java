package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.GenerateLabourWorkRepository;
import com.ingroinfo.mm.dao.GenerateVehicleWorkRepository;
import com.ingroinfo.mm.dao.GenerateWorkOrderRepository;
import com.ingroinfo.mm.dto.GenerateWorkOrderDto;
import com.ingroinfo.mm.entity.GenerateLabourWork;
import com.ingroinfo.mm.entity.GenerateVehicleWork;
import com.ingroinfo.mm.entity.GenerateWorkOrder;
import com.ingroinfo.mm.service.GenerateWorkOrderService;


@Service
public class GenerateWorkOrderServiceImpl implements GenerateWorkOrderService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private GenerateWorkOrderRepository generateRepository;
	
	@Autowired
	private GenerateLabourWorkRepository genLabourWorkRepo;
	
	@Autowired
	private GenerateVehicleWorkRepository genVehicleWorkRepo;
	
	
	//Save Data 
	@Override
	public GenerateWorkOrderDto saveGenerateWorkOrder(GenerateWorkOrderDto generateWorkOrder) {
		GenerateWorkOrder generateWorkOrders = this.modelMapper.map(generateWorkOrder, GenerateWorkOrder.class);
		GenerateWorkOrder savedGenerate = this.generateRepository.save(generateWorkOrders);
		return this.modelMapper.map(savedGenerate, GenerateWorkOrderDto.class);
	}

	//Show All Data in List
	public List<GenerateWorkOrderDto> findAllGenerateWorkOrder() {
		List<GenerateWorkOrder> idGenerates = this.generateRepository.findAll();
		List<GenerateWorkOrderDto> idGeneratesDto = idGenerates.stream().map((idGenerate)-> 
		   this.modelMapper.map(idGenerate, GenerateWorkOrderDto.class)).collect(Collectors.toList());		
		return idGeneratesDto;
	}
	
	//Save Generate category Bundle In Database
	@Override
	public List<GenerateWorkOrder> createBundle(List<GenerateWorkOrder> bundleList) {
		return generateRepository.saveAll(bundleList);
	}
	
	//save Generate Labour Bundle in DB
	@Override
	public List<GenerateLabourWork> genLabourBundle(List<GenerateLabourWork> genLabourList) {
		return genLabourWorkRepo.saveAll(genLabourList);
	}
	
	//save Generate Vehicle Bundle in DB
	@Override
	public List<GenerateVehicleWork> generateVehicleBundle(List<GenerateVehicleWork> genVehicleList) {
		return genVehicleWorkRepo.saveAll(genVehicleList);
	}

	//To Get Single Data By its Id
	@Override
	public GenerateWorkOrderDto getGenerateWorkOrderById(Long generateWorkId) {
		GenerateWorkOrder generateWork  = this.generateRepository.findById(generateWorkId).get();
		return this.modelMapper.map(generateWork, GenerateWorkOrderDto.class);
	}

	 //get Generate Work Order Data  by IndentNo
	@Override
	public GenerateWorkOrderDto findGeneWorkOrderIndentNoById(String indentNo) {
		GenerateWorkOrder generateWorkOrder = this.generateRepository.findByIndentNo(indentNo);
		return this.modelMapper.map(generateWorkOrder, GenerateWorkOrderDto.class);
	}





	
}
