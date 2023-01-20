package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.CancelWorkOrderRepository;
import com.ingroinfo.mm.dto.CancelWorkOrderDto;
import com.ingroinfo.mm.entity.CancelWorkOrder;
import com.ingroinfo.mm.service.CancelWorkOrderService;




@Service
public class CancelWorkOrderServiceImpl implements CancelWorkOrderService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private CancelWorkOrderRepository cancelRepository;
	
	
	@Override
	public CancelWorkOrderDto saveCancelWorkOrder(CancelWorkOrderDto cancelWorkOrder) {
		 
		CancelWorkOrder cancelWorkOrders = this.modelMapper.map(cancelWorkOrder, CancelWorkOrder.class);
	    CancelWorkOrder savedCancel = this.cancelRepository.save(cancelWorkOrders);
	    return this.modelMapper.map(savedCancel, CancelWorkOrderDto.class);
	}

	@Override
	public List<CancelWorkOrderDto> findAllCancelWorkOrder() {
		List<CancelWorkOrder> idCancels = this.cancelRepository.findAll();
		List<CancelWorkOrderDto> idCancelDto = idCancels.stream().map((idCancel)-> 
		   this.modelMapper.map(idCancel, CancelWorkOrderDto.class)).collect(Collectors.toList());		
		return idCancelDto;
	}

}
