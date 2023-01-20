package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.HoldWorkOrderRepository;
import com.ingroinfo.mm.dto.HoldWorkOrderDto;
import com.ingroinfo.mm.entity.HoldWorkOrder;
import com.ingroinfo.mm.service.HoldWorkOrderService;




@Service
public class HoldWorkOrderServiceImpl implements HoldWorkOrderService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private HoldWorkOrderRepository holdRepository;

	@Override
	public HoldWorkOrderDto saveHoldWorkOrder(HoldWorkOrderDto holdWorkOrder) {
	        HoldWorkOrder holdWorkOrders = this.modelMapper.map(holdWorkOrder, HoldWorkOrder.class);
	        HoldWorkOrder savedhold = this.holdRepository.save(holdWorkOrders);
		return this.modelMapper.map(savedhold, HoldWorkOrderDto.class);
	}

	@Override
	public List<HoldWorkOrderDto> findAllHoldWorkOrder() {
		List<HoldWorkOrder> idHolds = this.holdRepository.findAll();
		List<HoldWorkOrderDto>idHoldDto = idHolds.stream().map((idHold)-> this.modelMapper.map(idHold, HoldWorkOrderDto.class)).collect(Collectors.toList());
		return idHoldDto;
	}
}
