package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.WorkOrderItemsRequestDto;

public interface WorkOrderItemsRequestService {
	
    //Save all Item Requested Data
	List<WorkOrderItemsRequestDto> saveAllPumpIndent(List<WorkOrderItemsRequestDto> wOrderItemsRequestDtos);
}
