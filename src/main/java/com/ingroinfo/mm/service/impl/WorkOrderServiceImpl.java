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
import com.ingroinfo.mm.dao.WorkOrderApprovedItemsRepository;
import com.ingroinfo.mm.dao.WorkOrderApprovedLaboursRepository;
import com.ingroinfo.mm.dao.WorkOrderApprovedVehiclesRepository;
import com.ingroinfo.mm.dto.CancelWorkOrderItemsDto;
import com.ingroinfo.mm.dto.CancelWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.CancelWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.HoldWorkOrderItemsDto;
import com.ingroinfo.mm.dto.HoldWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.HoldWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.WapWorkOrderItemsDto;
import com.ingroinfo.mm.dto.WapWorkOrderLaboursDto;
import com.ingroinfo.mm.dto.WapWorkOrderVehiclesDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedItemsDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedLaboursDto;
import com.ingroinfo.mm.dto.WorkOrderApprovedVehiclesDto;
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
import com.ingroinfo.mm.entity.WorkOrderApprovedItems;
import com.ingroinfo.mm.entity.WorkOrderApprovedLabours;
import com.ingroinfo.mm.entity.WorkOrderApprovedVehicles;
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
	@Autowired
	private WorkOrderApprovedItemsRepository workOrderApprovedItemsRepo;
	@Autowired
	private WorkOrderApprovedLaboursRepository workOrderApprovedLaboursRepo;
	@Autowired
	private WorkOrderApprovedVehiclesRepository workOrderApprovedVehiclesRepo;

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
	public List<IndentApprovedItems> getApprovedItemsIndentByApprovedSts(String approvedSts) {
		return this.indentApprovedItemsRepo.getByApprovedSts(approvedSts);
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
	public void saveAllTempWorkOrderItems(List<TempWorkOrderItemRequest> tempWorkOrderItems) {
		this.tempWorkOrderItemRequestRepo.saveAll(tempWorkOrderItems);
	}

	@Override
	public void saveAllTempWorkOrderLabours(List<TempWorkOrderLabourRequest> tempWorkOrderLabours) {
		this.tempWorkOrderLabourRequestRepo.saveAll(tempWorkOrderLabours);
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

	@Override
	public List<WapWorkOrderItemsDto> getWapWorkOrderItemsByComplNoAndIndentNo(String complNo, String indentNo) {
		List<WapWorkOrderItems> wapWorkOrderItems = this.wapWorkOrderItemsRepo.getByComplNoAndIndentNo(complNo,
				indentNo);
		return wapWorkOrderItems.stream()
				.map((workorderItemDto) -> modelMapper.map(workorderItemDto, WapWorkOrderItemsDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<WapWorkOrderLaboursDto> getWapWorkOrderLaboursByComplNoAndIndentNo(String complNo, String indentNo) {
		List<WapWorkOrderLabours> wapWorkOrderLabours = this.wapWorkOrderLaboursRepo.getByComplNoAndIndentNo(complNo,
				indentNo);
		return wapWorkOrderLabours.stream()
				.map((workOrderLabourDto) -> modelMapper.map(workOrderLabourDto, WapWorkOrderLaboursDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<WapWorkOrderVehiclesDto> getWapWorkOrderVehiclesByComplNoAndIndentNo(String complNo, String indentNo) {
		List<WapWorkOrderVehicles> wapWorkOrderVehicles = this.wapWorkOrderVehiclesRepo.getByComplNoAndIndentNo(complNo,
				indentNo);
		return wapWorkOrderVehicles.stream()
				.map((workorderVehicleDto) -> modelMapper.map(workorderVehicleDto, WapWorkOrderVehiclesDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void saveAllApprovedWorkOrderItems(List<WorkOrderApprovedItemsDto> workOrderApprovedItemsDtos) {
		List<WorkOrderApprovedItems> workOrderApprovedItems = workOrderApprovedItemsDtos.stream()
				.map((workOrderItems) -> modelMapper.map(workOrderItems, WorkOrderApprovedItems.class))
				.collect(Collectors.toList());
		this.workOrderApprovedItemsRepo.saveAll(workOrderApprovedItems);
	}

	@Override
	public void saveAllApprovedWorkOrderLabours(List<WorkOrderApprovedLaboursDto> workOrderApprovedLaboursDtos) {
		List<WorkOrderApprovedLabours> workOrderApprovedLabours = workOrderApprovedLaboursDtos.stream()
				.map((workOrderLabours) -> modelMapper.map(workOrderLabours, WorkOrderApprovedLabours.class))
				.collect(Collectors.toList());
		this.workOrderApprovedLaboursRepo.saveAll(workOrderApprovedLabours);
	}

	@Override
	public void saveAllApprovedWorkOrderVehicles(List<WorkOrderApprovedVehiclesDto> workOrderApprovedVehiclesDtos) {
		List<WorkOrderApprovedVehicles> workOrderApprovedVehicles = workOrderApprovedVehiclesDtos.stream()
				.map((workOrderVehicles) -> modelMapper.map(workOrderVehicles, WorkOrderApprovedVehicles.class))
				.collect(Collectors.toList());
		this.workOrderApprovedVehiclesRepo.saveAll(workOrderApprovedVehicles);
	}

	@Override
	public void deleteAllWapWorkorderItemsByComplNo(String complNo) {
		this.wapWorkOrderItemsRepo.deleteByComplNo(complNo);
	}

	@Override
	public void deleteAllWapWorkorderLaboursByComplNo(String complNo) {
		this.wapWorkOrderLaboursRepo.deleteByComplNo(complNo);
	}

	@Override
	public void deleteAllWapWorkorderVehiclesByComplNo(String complNo) {
		this.wapWorkOrderVehiclesRepo.deleteByComplNo(complNo);
	}

	@Override
	public List<WorkOrderApprovedItemsDto> getApprovedWorkOrderItemsByWorkorderNo(String workOrder) {
		List<WorkOrderApprovedItems> workOrderApprovedItems = this.workOrderApprovedItemsRepo.getByWorkOrder(workOrder);
		return workOrderApprovedItems.stream().map((approvedWorkOrderItemdto) -> this.modelMapper
				.map(approvedWorkOrderItemdto, WorkOrderApprovedItemsDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<WorkOrderApprovedLaboursDto> getApprovedWorkOrderLaboursByWorkorderNo(String workOrder) {
		List<WorkOrderApprovedLabours> workOrderApprovedLabours = this.workOrderApprovedLaboursRepo
				.getByWorkOrder(workOrder);
		return workOrderApprovedLabours.stream().map((approvedWorkOrderLabourdto) -> this.modelMapper
				.map(approvedWorkOrderLabourdto, WorkOrderApprovedLaboursDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<WorkOrderApprovedVehiclesDto> getApprovedWorkOrderVehiclesByWorkorderNo(String workOrder) {
		List<WorkOrderApprovedVehicles> workOrderApprovedVehicles = this.workOrderApprovedVehiclesRepo
				.getByWorkOrder(workOrder);
		return workOrderApprovedVehicles.stream().map((approvedWorkOrderVehicledto) -> this.modelMapper
				.map(approvedWorkOrderVehicledto, WorkOrderApprovedVehiclesDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<HoldWorkOrderItemsDto> getHoldWorkOrderItemsByWorkorderNo(String workOrder) {
		List<HoldWorkOrderItems> holdWorkOrderItems = this.holdWorkOrderItemsRepo.getByWorkOrder(workOrder);
		return holdWorkOrderItems.stream()
				.map((holdWorkOrderItemdto) -> this.modelMapper.map(holdWorkOrderItemdto, HoldWorkOrderItemsDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<HoldWorkOrderLaboursDto> getHoldWorkOrderLaboursByWorkorderNo(String workOrder) {
		List<HoldWorkOrderLabours> holdWorkOrderLabours = this.holdWorkOrderLaboursRepo.getByWorkOrder(workOrder);
		return holdWorkOrderLabours.stream().map(
				(holdWorkOrderLabordto) -> this.modelMapper.map(holdWorkOrderLabordto, HoldWorkOrderLaboursDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<HoldWorkOrderVehiclesDto> getHoldWorkOrderVehiclesByWorkorderNo(String workOrder) {
		List<HoldWorkOrderVehicles> holdWorkOrderVehicles = this.holdWorkOrderVehiclesRepo.getByWorkOrder(workOrder);
		return holdWorkOrderVehicles.stream().map((holdWorkorderVehicleDto) -> this.modelMapper
				.map(holdWorkorderVehicleDto, HoldWorkOrderVehiclesDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<CancelWorkOrderItemsDto> getCanceledWorkOrderItemsByWorkorderNo(String workOrder) {
		List<CancelWorkOrderItems> cancelWorkOrderItems = this.cancelWorkOrderItemsRepo.getByWorkOrder(workOrder);
		return cancelWorkOrderItems.stream().map((cancelWorkorderVehicleDto) -> this.modelMapper
				.map(cancelWorkorderVehicleDto, CancelWorkOrderItemsDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<CancelWorkOrderLaboursDto> getCanceledWorkOrderLaboursByWorkorderNo(String workOrder) {
		List<CancelWorkOrderLabours> cancelWorkOrderLabours = this.cancelWorkOrderLaboursRepo.getByWorkOrder(workOrder);
		return cancelWorkOrderLabours.stream().map((cancelWorkorderVehicleDto) -> this.modelMapper
				.map(cancelWorkorderVehicleDto, CancelWorkOrderLaboursDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<CancelWorkOrderVehiclesDto> getCanceledWorkOrderVehiclesByWorkorderNo(String workOrder) {
		List<CancelWorkOrderVehicles> cancelWorkOrderVehiclescs = this.cancelWorkOrderVehiclesRepo
				.getByWorkOrder(workOrder);
		return cancelWorkOrderVehiclescs.stream().map((cancelWorkorderVehicleDto) -> this.modelMapper
				.map(cancelWorkorderVehicleDto, CancelWorkOrderVehiclesDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<WorkOrderApprovedItemsDto> getApprovedWorkOrderItemsByComplNoAndIndentNo(String complNo, String indentNo) {
		List<WorkOrderApprovedItems> workOrderApprovedItems = this.workOrderApprovedItemsRepo
				.getByComplNoAndIndentNo(complNo, indentNo);
		return workOrderApprovedItems.stream()
				.map((workOrderItem) -> this.modelMapper.map(workOrderItem, WorkOrderApprovedItemsDto.class))
				.collect(Collectors.toList());
	}

}
