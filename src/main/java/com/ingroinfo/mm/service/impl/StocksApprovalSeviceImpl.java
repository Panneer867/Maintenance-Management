package com.ingroinfo.mm.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.ApprovedWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.ApprovedWorkOrderNosRepository;
import com.ingroinfo.mm.dao.InwardApprovedMaterialsRepository;
import com.ingroinfo.mm.dao.InwardApprovedSparesRepository;
import com.ingroinfo.mm.dao.InwardApprovedToolsRepository;
import com.ingroinfo.mm.dao.InwardMaterialsRepository;
import com.ingroinfo.mm.dao.InwardRejectedMaterialsRepository;
import com.ingroinfo.mm.dao.InwardRejectedSparesRepository;
import com.ingroinfo.mm.dao.InwardRejectedToolsRepository;
import com.ingroinfo.mm.dao.InwardSparesRepository;
import com.ingroinfo.mm.dao.InwardToolsRepository;
import com.ingroinfo.mm.dao.RejectedWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.RejectedWorkOrderNosRepository;
import com.ingroinfo.mm.dao.TempWorkOrderNosRepository;
import com.ingroinfo.mm.dao.WorkOrderItemsRepository;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.entity.ApprovedWorkOrderItems;
import com.ingroinfo.mm.entity.ApprovedWorkOrderNos;
import com.ingroinfo.mm.entity.InwardApprovedMaterials;
import com.ingroinfo.mm.entity.InwardApprovedSpares;
import com.ingroinfo.mm.entity.InwardApprovedTools;
import com.ingroinfo.mm.entity.InwardMaterials;
import com.ingroinfo.mm.entity.InwardRejectedMaterials;
import com.ingroinfo.mm.entity.InwardRejectedSpares;
import com.ingroinfo.mm.entity.InwardRejectedTools;
import com.ingroinfo.mm.entity.InwardSpares;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.RejectedWorkOrderItems;
import com.ingroinfo.mm.entity.RejectedWorkOrderNos;
import com.ingroinfo.mm.entity.TempWorkOrderNos;
import com.ingroinfo.mm.entity.WorkOrderItems;
import com.ingroinfo.mm.service.StocksApprovalService;

@Service
public class StocksApprovalSeviceImpl implements StocksApprovalService {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private InwardApprovedMaterialsRepository inwardApprovedMaterialsRepository;

	@Autowired
	private InwardRejectedMaterialsRepository inwardRejectedMaterialsRepository;

	@Autowired
	private InwardMaterialsRepository inwardMaterialsRepository;

	@Autowired
	private InwardApprovedSparesRepository inwardApprovedSparesRepository;

	@Autowired
	private InwardRejectedSparesRepository inwardRejectedSparesRepository;

	@Autowired
	private InwardSparesRepository inwardSparesRepository;

	@Autowired
	private InwardApprovedToolsRepository inwardApprovedToolsRepository;

	@Autowired
	private InwardRejectedToolsRepository inwardRejectedToolsRepository;

	@Autowired
	private InwardToolsRepository inwardToolsRepository;

	@Autowired
	private WorkOrderItemsRepository workOrderItemsRepository;

	@Autowired
	private ApprovedWorkOrderItemsRepository approvedWorkOrderItemsRepository;

	@Autowired
	private ApprovedWorkOrderNosRepository approvedWorkOrderNosRepository;

	@Autowired
	private TempWorkOrderNosRepository tempWorkOrderNosRepository;

	@Autowired
	private RejectedWorkOrderItemsRepository rejectedWorkOrderItemsRepository;

	@Autowired
	private RejectedWorkOrderNosRepository rejectedWorkOrderNosRepository;

	/**************************** Materials ********************************/

	@Override
	public void saveMaterial(InwardDto inwardItemDto) {

		InwardApprovedMaterials inwardApprovedMaterials = modelMapper.map(inwardItemDto, InwardApprovedMaterials.class);
		inwardApprovedMaterials.setStockType("ML");
		InwardApprovedMaterials newApprovedMaterials = inwardApprovedMaterialsRepository.save(inwardApprovedMaterials);

		if (newApprovedMaterials != null) {
			inwardMaterialsRepository.deleteById(inwardItemDto.getMaterialId());
		}
	}

	@Override
	public void rejectMaterial(Long id) {

		InwardMaterials inwardMaterial = inwardMaterialsRepository.findByMaterialId(id);

		InwardRejectedMaterials rejectMaterial = modelMapper.map(inwardMaterial, InwardRejectedMaterials.class);

		InwardRejectedMaterials rejectedMaterial = inwardRejectedMaterialsRepository.save(rejectMaterial);

		if (rejectedMaterial != null) {
			inwardMaterialsRepository.deleteById(id);
		}
	}

	/**************************** Spares ********************************/
	@Override
	public void saveSpare(InwardDto inwardItemDto) {

		InwardApprovedSpares inwardApprovedSpares = modelMapper.map(inwardItemDto, InwardApprovedSpares.class);
		inwardApprovedSpares.setStockType("SP");
		InwardApprovedSpares newApprovedSpares = inwardApprovedSparesRepository.save(inwardApprovedSpares);

		if (newApprovedSpares != null) {
			inwardSparesRepository.deleteById(inwardItemDto.getSpareId());
		}
	}

	@Override
	public void rejectSpare(Long id) {

		InwardSpares inwardSpare = inwardSparesRepository.findBySpareId(id);

		InwardRejectedSpares rejectSpare = modelMapper.map(inwardSpare, InwardRejectedSpares.class);

		InwardRejectedSpares rejectedSpare = inwardRejectedSparesRepository.save(rejectSpare);

		if (rejectedSpare != null) {
			inwardSparesRepository.deleteById(id);
		}
	}

	/**************************** Tools ********************************/

	@Override
	public void saveTool(InwardDto inwardItemDto) {
		InwardApprovedTools inwardApprovedTools = modelMapper.map(inwardItemDto, InwardApprovedTools.class);
		inwardApprovedTools.setStockType("TE");
		InwardApprovedTools newApprovedTools = inwardApprovedToolsRepository.save(inwardApprovedTools);

		if (newApprovedTools != null) {
			inwardToolsRepository.deleteById(inwardItemDto.getToolId());
		}

	}

	@Override
	public void rejectTool(Long id) {

		InwardTools inwardTool = inwardToolsRepository.findByToolId(id);

		InwardRejectedTools rejectTool = modelMapper.map(inwardTool, InwardRejectedTools.class);

		InwardRejectedTools rejectedTools = inwardRejectedToolsRepository.save(rejectTool);

		if (rejectedTools != null) {
			inwardToolsRepository.deleteById(id);
		}
	}

	/****************************
	 * Outwards Workorders
	 ********************************/
	@Override
	public void approveOutwardStocks(Long workOrderNo) {

		TempWorkOrderNos tempWorkOrderNos = tempWorkOrderNosRepository.findByWorkOrderNo(workOrderNo);

		ApprovedWorkOrderNos approvedWorkOrderNos = modelMapper.map(tempWorkOrderNos, ApprovedWorkOrderNos.class);

		approvedWorkOrderNosRepository.save(approvedWorkOrderNos);

		List<WorkOrderItems> workOrderItems = workOrderItemsRepository.findByWorkOrderNo(workOrderNo);

		for (WorkOrderItems workOrderItem : workOrderItems) {

			ApprovedWorkOrderItems approvedWorkOrderItems = modelMapper.map(workOrderItem,
					ApprovedWorkOrderItems.class);
			approvedWorkOrderItemsRepository.save(approvedWorkOrderItems);
		}

		tempWorkOrderNosRepository.deleteById(tempWorkOrderNos.getOrderId());
	}

	@Override
	public void rejectWorkorderItems(Long workOrderNo) {
		TempWorkOrderNos tempWorkOrderNos = tempWorkOrderNosRepository.findByWorkOrderNo(workOrderNo);

		RejectedWorkOrderNos rejectedWorkOrderNos = modelMapper.map(tempWorkOrderNos, RejectedWorkOrderNos.class);

		rejectedWorkOrderNosRepository.save(rejectedWorkOrderNos);

		List<WorkOrderItems> workOrderItems = workOrderItemsRepository.findByWorkOrderNo(workOrderNo);

		for (WorkOrderItems workOrderItem : workOrderItems) {

			RejectedWorkOrderItems rejectedWorkOrderItems = modelMapper.map(workOrderItem,
					RejectedWorkOrderItems.class);
			rejectedWorkOrderItemsRepository.save(rejectedWorkOrderItems);
		}

		tempWorkOrderNosRepository.deleteById(tempWorkOrderNos.getOrderId());

	}

}
