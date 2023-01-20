package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.CancelWorkOrderDto;


public interface CancelWorkOrderService {
	
	//Save Info
	CancelWorkOrderDto saveCancelWorkOrder(CancelWorkOrderDto cancelWorkOrder);
	
	//Show All
	 List<CancelWorkOrderDto> findAllCancelWorkOrder();

}
