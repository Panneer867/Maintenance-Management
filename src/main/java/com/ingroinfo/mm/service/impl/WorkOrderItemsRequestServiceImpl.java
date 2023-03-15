package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.WorkOrderItemsRequestRepository;
import com.ingroinfo.mm.dto.WorkOrderItemsRequestDto;
import com.ingroinfo.mm.entity.WorkOrderItemsRequest;
import com.ingroinfo.mm.service.WorkOrderItemsRequestService;

@Service
public class WorkOrderItemsRequestServiceImpl implements WorkOrderItemsRequestService {

	@Autowired
	private WorkOrderItemsRequestRepository itemsRequestRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<WorkOrderItemsRequestDto> saveAllPumpIndent(List<WorkOrderItemsRequestDto> wOrderItemsRequestDtos) {
	    List<WorkOrderItemsRequest> workOrderItemsList = wOrderItemsRequestDtos.stream()
	            .map(workOrderDto -> this.modelMapper.map(workOrderDto, WorkOrderItemsRequest.class))
	            .collect(Collectors.toList());
	    List<WorkOrderItemsRequest> workOrderItemsRequests = this.itemsRequestRepository.saveAll(workOrderItemsList);
	    return workOrderItemsRequests.stream()
	            .map(workOrder -> this.modelMapper.map(workOrder, WorkOrderItemsRequestDto.class))
	            .collect(Collectors.toList());
	}
	

}
