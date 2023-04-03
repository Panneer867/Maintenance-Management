package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.CancelWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.CancelWorkOrderLaboursRepository;
import com.ingroinfo.mm.dao.CancelWorkOrderVehiclesRepository;
import com.ingroinfo.mm.dao.HoldWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.HoldWorkOrderLaboursRepository;
import com.ingroinfo.mm.dao.HoldWorkOrderVehiclesRepository;
import com.ingroinfo.mm.dao.IndentApprovedItemsRepository;
import com.ingroinfo.mm.dao.IndentApprovedLaboursRepository;
import com.ingroinfo.mm.dao.IndentApprovedVehiclesRepository;
import com.ingroinfo.mm.dao.TempWorkOrderItemRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderLabourRequestRepository;
import com.ingroinfo.mm.dao.TempWorkOrderVehicleRequestRepository;
import com.ingroinfo.mm.dao.WapWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.WapWorkOrderLaboursRepository;
import com.ingroinfo.mm.dao.WapWorkOrderVehiclesRepository;
import com.ingroinfo.mm.dto.CancelWorkOrderItemsDto;
import com.ingroinfo.mm.dto.CancelWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.CancelWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.HoldWorkOrderItemsDto;
import com.ingroinfo.mm.dto.HoldWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.HoldWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.WapWorkOrderItemsDto;
import com.ingroinfo.mm.dto.WapWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.WapWorkOrderVehiclesDto;
import com.ingroinfo.mm.entity.CancelWorkOrderItems;
import com.ingroinfo.mm.entity.CancelWorkOrderLabours;
import com.ingroinfo.mm.entity.CancelWorkOrderVehicles;
import com.ingroinfo.mm.entity.HoldWorkOrderItems;
import com.ingroinfo.mm.entity.HoldWorkOrderLabours;
import com.ingroinfo.mm.entity.HoldWorkOrderVehicles;
import com.ingroinfo.mm.entity.IndentApprovedItems;
import com.ingroinfo.mm.entity.IndentApprovedLabours;
import com.ingroinfo.mm.entity.IndentApprovedVehicles;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;
import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;
import com.ingroinfo.mm.entity.WapWorkOrderItems;
import com.ingroinfo.mm.entity.WapWorkOrderLabours;
import com.ingroinfo.mm.entity.WapWorkOrderVehicles;
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
	@Autowired
	private HoldWorkOrderItemsRepository holdWorkOrderItemsRepo;
	@Autowired
	private HoldWorkOrderLaboursRepository holdWorkOrderLaboursRepo;
	@Autowired
	private HoldWorkOrderVehiclesRepository holdWorkOrderVehiclesRepo;
	@Autowired
	private CancelWorkOrderItemsRepository cancelWorkOrderItemsRepo;
	@Autowired
	private CancelWorkOrderLaboursRepository cancelWorkOrderLaboursRepo;
	@Autowired
	private CancelWorkOrderVehiclesRepository cancelWorkOrderVehiclesRepo;

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
	public void saveAllWapWorkOrderLabours(List<WapWorkOrderLaboursDto> wapWorkOrderLaboursDtos) {
		List<WapWorkOrderLabours> wapWorkOrderLabours = wapWorkOrderLaboursDtos.stream()
				.map((wapWorkLabour) -> modelMapper.map(wapWorkLabour, WapWorkOrderLabours.class))
				.collect(Collectors.toList());
		this.wapWorkOrderLaboursRepo.saveAll(wapWorkOrderLabours);
	}

	@Override
	public void saveAllWapWorkOrderVehicles(List<WapWorkOrderVehiclesDto> wapWorkOrderVehicleDtos) {
		List<WapWorkOrderVehicles> wapWorkOrderLabours = wapWorkOrderVehicleDtos.stream()
				.map((wapWorkVehicle) -> modelMapper.map(wapWorkVehicle, WapWorkOrderVehicles.class))
				.collect(Collectors.toList());
		this.wapWorkOrderVehiclesRepo.saveAll(wapWorkOrderLabours);
	}

	@Override
	public void deleteTempWorkOrderItemRequestByComplNo(String complNo) {
		this.tempWorkOrderItemRequestRepo.deleteByComplNo(complNo);
	}

	@Override
	public void deleteTempWorkOrderLaboursRequestByComplNo(String complNo) {
		this.tempWorkOrderLabourRequestRepo.deleteByComplNo(complNo);
	}

	@Override
	public void deleteTempWorkOrderVehicleRequestByComplNo(String complNo) {
		this.tempWorkOrderVehicleRequestRepo.deleteByComplNo(complNo);
	}

	@Override
	public void saveAllApprovedIndentItems(List<IndentApprovedItems> indentApprovedItems) {
		this.indentApprovedItemsRepo.saveAll(indentApprovedItems);
	}

	@Override
	public void saveAllHoldWorkOrderItems(List<HoldWorkOrderItemsDto> holdWorkOrderItemsDtos) {
		List<HoldWorkOrderItems> holdWorkOrderItems = holdWorkOrderItemsDtos.stream()
				.map((holdWorkOrderItem) -> modelMapper.map(holdWorkOrderItem, HoldWorkOrderItems.class))
				.collect(Collectors.toList());
		this.holdWorkOrderItemsRepo.saveAll(holdWorkOrderItems);
	}

	@Override
	public void saveAllHoldWorkOrderLabours(List<HoldWorkOrderLaboursDto> holdWorkOrderLaboursDtos) {
		List<HoldWorkOrderLabours> holdWorkOrderLabours = holdWorkOrderLaboursDtos.stream()
				.map((holdWorkOrderlabor) -> modelMapper.map(holdWorkOrderlabor, HoldWorkOrderLabours.class))
				.collect(Collectors.toList());
		this.holdWorkOrderLaboursRepo.saveAll(holdWorkOrderLabours);
	}

	@Override
	public void saveAllHoldWorkOrderVehicles(List<HoldWorkOrderVehiclesDto> holdWorkOrderVehiclesDtos) {
		List<HoldWorkOrderVehicles> holdWorkOrderVehicles = holdWorkOrderVehiclesDtos.stream()
				.map((holdWorkOrdervehicle) -> modelMapper.map(holdWorkOrdervehicle, HoldWorkOrderVehicles.class))
				.collect(Collectors.toList());
		this.holdWorkOrderVehiclesRepo.saveAll(holdWorkOrderVehicles);
	}

	@Override
	public void saveAllCancelWorkOrderItems(List<CancelWorkOrderItemsDto> cancelWorkOrderItemsDtos) {
		List<CancelWorkOrderItems> cancelWorkOrderItems = cancelWorkOrderItemsDtos.stream()
				.map((cancelWorkOrderlabor) -> modelMapper.map(cancelWorkOrderlabor, CancelWorkOrderItems.class))
				.collect(Collectors.toList());
		this.cancelWorkOrderItemsRepo.saveAll(cancelWorkOrderItems);
	}

	@Override
	public void saveAllCancelWorkOrderLabours(List<CancelWorkOrderLaboursDto> cancelWorkOrderLaboursDtos) {
		List<CancelWorkOrderLabours> cancelWorkOrderLabours = cancelWorkOrderLaboursDtos.stream()
				.map((cancelWorkOrderlabor) -> modelMapper.map(cancelWorkOrderlabor, CancelWorkOrderLabours.class))
				.collect(Collectors.toList());
		this.cancelWorkOrderLaboursRepo.saveAll(cancelWorkOrderLabours);
	}

	@Override
	public void saveAllCancelWorkOrderVehicles(List<CancelWorkOrderVehiclesDto> cancelWorkOrderVehiclesDtos) {
		List<CancelWorkOrderVehicles> cancelWorkOrderLabours = cancelWorkOrderVehiclesDtos.stream()
				.map((cancelWorkOrderlabor) -> modelMapper.map(cancelWorkOrderlabor, CancelWorkOrderVehicles.class))
				.collect(Collectors.toList());
		this.cancelWorkOrderVehiclesRepo.saveAll(cancelWorkOrderLabours);
	}

}
