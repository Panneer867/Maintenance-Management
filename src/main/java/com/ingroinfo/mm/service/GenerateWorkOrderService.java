package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.GenerateWorkOrderDto;
import com.ingroinfo.mm.entity.GenerateLabourWork;
import com.ingroinfo.mm.entity.GenerateVehicleWork;
import com.ingroinfo.mm.entity.GenerateWorkOrder;


public interface GenerateWorkOrderService {
	
	//Save Info
	GenerateWorkOrderDto saveGenerateWorkOrder(GenerateWorkOrderDto generateWorkOrder);
	
	//Show All
	 List<GenerateWorkOrderDto> findAllGenerateWorkOrder();
	 
	 //show single Data on Id
	 GenerateWorkOrderDto getGenerateWorkOrderById(Long generateWorkId);
	 	 
	//to save All category data with bundle
	 List<GenerateWorkOrder> createBundle(List<GenerateWorkOrder> bundleList);
	 
	 
	//to save All labour data with bundle
	 List<GenerateLabourWork> genLabourBundle(List<GenerateLabourWork> genLabourList);
	 
	//to save All Vehicle data with bundle
	 List<GenerateVehicleWork> generateVehicleBundle(List<GenerateVehicleWork> genVehicleList);
	 
	 //get Generate Work Order Data By IndentNo
	 GenerateWorkOrderDto findGeneWorkOrderIndentNoById(String indentNo);


	 
}
