package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.InwardBundleItemRepository;
import com.ingroinfo.mm.dao.InwardMaterialRepository;
import com.ingroinfo.mm.dao.InwardTempBundleItemRepository;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.InwardItem;
import com.ingroinfo.mm.entity.InwardItemTemp;
import com.ingroinfo.mm.entity.InwardMaterial;
import com.ingroinfo.mm.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private InwardTempBundleItemRepository inwardTempItemRepo;

	@Autowired
	private InwardBundleItemRepository inwardItemRepo;

	@Autowired
	private InwardMaterialRepository inwardMaterialRepo;

	@Override
	public void saveInwardItem(InwardItemTemp inwardItemTemp) {
		inwardTempItemRepo.save(inwardItemTemp);
	}

	@Override
	public List<InwardItemTemp> getInwardTempItemList(String username) {
		return inwardTempItemRepo.findByUsername(username);
	}

	@Override
	public void deleteTempBundleItem(Long tempBunleId) {
		inwardTempItemRepo.deleteById(tempBunleId);

	}

	@Override
	public void saveInwardMaterial(InwardMaterial inwardMaterial) {

		InwardMaterial newInwardMaterial = inwardMaterialRepo.save(inwardMaterial);

		List<InwardItemTemp> inwardItemTemp = inwardTempItemRepo.findByUsername(inwardMaterial.getUsername());

		for (InwardItemTemp tempInwardItem : inwardItemTemp) {
			InwardItem inwardItem = modelMapper.map(tempInwardItem, InwardItem.class);
			inwardItem.setInwardMaterial(newInwardMaterial);
			inwardItemRepo.save(inwardItem);
		}
		inwardTempItemRepo.deleteAll();
	}

	@Override
	public void deleteAllItems() {
		inwardTempItemRepo.deleteAll();

	}

	@Override
	public List<InwardItem> getInwardItemList() {
		return inwardItemRepo.findAll();
	}

	@Override
	public List<InwardItemDto> getInwarAlldItemList() {
		List<InwardMaterial> materials = inwardMaterialRepo.findAll();

		List<InwardItemDto> newItems = inwardItemRepo.findAll().stream().map(s -> {
			InwardItemDto iIdto = new InwardItemDto();
			for (InwardMaterial material : materials) {
				if (s.getInwardMaterial().equals(material)) {
					iIdto.setSupplierName(material.getSupplierName());
					iIdto.setItemId(s.getItemId());
					iIdto.setItemName(s.getItemName());
					iIdto.setAliasName(s.getAliasName());
					iIdto.setMaterialImage(s.getMaterialImage());
					iIdto.setImagePath(s.getImagePath());
					iIdto.setCategoryName(s.getCategoryName());
					iIdto.setBrand(s.getBrand());
					iIdto.setHsnCode(s.getHsnCode());
					iIdto.setUnitOfMeasure(s.getUnitOfMeasure());
					iIdto.setTotalQuantity(String.valueOf(s.getTotalQuantity()));
					iIdto.setTotalAmount(String.valueOf(s.getTotalAmount()));
					iIdto.setCostRate(String.valueOf(s.getCostRate()));
					iIdto.setMrp(String.valueOf(s.getMrp()));
					iIdto.setEntryDate(s.getEntryDate());
					iIdto.setDescription(s.getDescription());
					iIdto.setDateCreated(s.getDateCreated());
					iIdto.setLastUpdated(s.getLastUpdated());
					iIdto.setSupplierName(material.getSupplierName());
					iIdto.setSuppliedOn(material.getSuppliedOn());
					iIdto.setGstType(material.getGstType());
					iIdto.setIgst(String.valueOf(material.getIgst()));
					iIdto.setSgst(String.valueOf(material.getSgst()));
					iIdto.setCgst(String.valueOf(material.getCgst()));
					iIdto.setSubTotal(String.valueOf(material.getSubTotal()));
					iIdto.setGrandTotal(String.valueOf(material.getGrandTotal()));
					iIdto.setInvoiceNo(material.getInvoiceNo());
					iIdto.setReceivedBy(material.getReceivedBy());
					iIdto.setReceivedDate(material.getReceivedDate());
					iIdto.setUsername(material.getUsername());
					iIdto.setBundleId(s.getBundleId());
				}
			}
			return iIdto;
		}).collect(Collectors.toList());

		return newItems;
	}

	@Override
	public void deleteBundleItem(Long itemId) {

		InwardItem iI = inwardItemRepo.findBybundleId(itemId);

		Long materialsId = iI.getInwardMaterial().getMaterialsId();

		List<InwardItem> inwardItem = inwardItemRepo.findAll().stream()
				.filter(f -> f.getInwardMaterial().equals(iI.getInwardMaterial())).collect(Collectors.toList());

		inwardItemRepo.deleteById(itemId);

		if (inwardItem.size() == 1) {
			inwardMaterialRepo.deleteById(materialsId);
		}

	}

}
