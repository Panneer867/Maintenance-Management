package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.IndentApprovedItemsRepository;
import com.ingroinfo.mm.dao.IndentApprovedLaboursRepository;
import com.ingroinfo.mm.dao.IndentApprovedVehiclesRepository;
import com.ingroinfo.mm.dao.TempWorkOrderItemRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderLabourRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderVehicleRequestRepository;
import com.ingroinfo.mm.dao.WapWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.WapWorkOrderLaboursRepository;
import com.ingroinfo.mm.dao.WapWorkOrderVehiclesRepository;
import com.ingroinfo.mm.dto.WapWorkOrderItemsDto;
import com.ingroinfo.mm.entity.IndentApprovedItems;
import com.ingroinfo.mm.entity.IndentApprovedLabours;
import com.ingroinfo.mm.entity.IndentApprovedVehicles;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;
import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;
import com.ingroinfo.mm.entity.WapWorkOrderItems;
import com.ingroinfo.mm.service.WorkOrderService;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TempWorkOrderItemRequestRepository tempWorkOrderItemRequestRepo;
	@Autowired
	private TempWorkOrderLabourRequestRepository tempWorkOrderLabourRequestRepo;
	@Autowired
	private TempWorkOrderVehicleRequestRepository tempWorkOrderVehicleRequestRepo;
	@Autowired
	private IndentApprovedItemsRepository indentApprovedItemsRepo;
	@Autowired
	private IndentApprovedLaboursRepository indentApprovedLaboursRepo;
	@Autowired
	private IndentApprovedVehiclesRepository indentApprovedVehiclesRepo;
	@Autowired
	private WapWorkOrderItemsRepository wapWorkOrderItemsRepo;
	@Autowired
	private WapWorkOrderVehiclesRepository wapWorkOrderVehiclesRepo;
	@Autowired
	private WapWorkOrderLaboursRepository wapWorkOrderLaboursRepo;

	@Override
	public List<TempWorkOrderItemRequest> getTempWorkOrderItemsByComplNoAndIndentNo(String complNo, String indentNo) {
		return tempWorkOrderItemRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
	}

	@Override
	public List<TempWorkOrderLabourRequest> getTempWorkOrderLaborsByComplNoAndIndentNo(String complNo,
			String indentNo) {
		return tempWorkOrderLabourRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
	}

	@Override
	public List<TempWorkOrderVehicleRequest> getTempWorkOrderVehiclesByComplNoAndIndentNo(String complNo,
			String indentNo) {
		return tempWorkOrderVehicleRequestRepo.getByComplNoAndIndentNo(complNo, indentNo);
	}

	@Override
	public List<IndentApprovedItems> getApprovedIndentItemsByComplNoAndIndentNo(String complNo, String indentNo) {
		return this.indentApprovedItemsRepo.getByComplNoAndIndentNo(complNo, indentNo);
	}

	@Override
	public List<IndentApprovedLabours> getApprovedIndentLaborsByComplNoAndIndentNo(String complNo, String indentNo) {
		return this.indentApprovedLaboursRepo.getByComplNoAndIndentNo(complNo, indentNo);
	}

	@Override
	public List<IndentApprovedVehicles> getApprovedIndentVehiclesByComplNoAndIndentNo(String complNo, String indentNo) {
		return this.indentApprovedVehiclesRepo.getByComplNoAndIndentNo(complNo, indentNo);
	}

	@Override
	public List<IndentApprovedLabours> getApprovedLabourIndentByApprovedSts(String approvedSts) {
		return this.indentApprovedLaboursRepo.getByApprovedSts(approvedSts);
	}

	@Override
	public List<IndentApprovedVehicles> getApprovedVehicleIndentByApprovedSts(String approvedSts) {
		return this.indentApprovedVehiclesRepo.getByApprovedSts(approvedSts);
	}

	@Override
	public void saveAllTempWorkOrderLabours(List<TempWorkOrderLabourRequest> tempWorkOrderLabours) {
		tempWorkOrderLabourRequestRepo.saveAll(tempWorkOrderLabours);
	}

	@Override
	public void saveAllApprovedIndentLabours(List<IndentApprovedLabours> approvedIndentLabours) {
		this.indentApprovedLaboursRepo.saveAll(approvedIndentLabours);
	}

	@Override
	public void saveAllTempWorkOrderVehicles(List<TempWorkOrderVehicleRequest> tempWorkOrderVehicles) {
		this.tempWorkOrderVehicleRequestRepo.saveAll(tempWorkOrderVehicles);
	}

	@Override
	public void saveAllApprovedIndentVehicles(List<IndentApprovedVehicles> approvedIndentVehicles) {
		this.indentApprovedVehiclesRepo.saveAll(approvedIndentVehicles);
	}

	@Override
	public void saveAllWapWorkOrderItems(List<WapWorkOrderItemsDto> wapWorkOrderItemsDtos) {
		List<WapWorkOrderItems> wapWorkItems = wapWorkOrderItemsDtos.stream()
				.map((wapWorkItem) -> this.modelMapper.map(wapWorkItem, WapWorkOrderItems.class))
				.collect(Collectors.toList());
		this.wapWorkOrderItemsRepo.saveAll(wapWorkItems);
	}

	@Override
	public void deleteTempWorkOrderItemRequestByComplNo(String complNo) {
		this.tempWorkOrderItemRequestRepo.deleteByComplNo(complNo);
	}

}
