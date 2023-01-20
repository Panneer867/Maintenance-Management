package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.HoldWorkOrderDto;



public interface HoldWorkOrderService {
	
	//Save Info
	HoldWorkOrderDto saveHoldWorkOrder(HoldWorkOrderDto holdWorkOrder);
	
	//Show All
	 List<HoldWorkOrderDto> findAllHoldWorkOrder();

}
