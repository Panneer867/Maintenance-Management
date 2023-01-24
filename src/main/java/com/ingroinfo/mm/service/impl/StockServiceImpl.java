package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.InwardMaterialBundleRepository;
import com.ingroinfo.mm.dao.InwardMaterialRepository;
import com.ingroinfo.mm.dao.InwardMaterialTempBundleRepository;
import com.ingroinfo.mm.dao.InwardSpareBundleRepository;
import com.ingroinfo.mm.dao.InwardSpareRepository;
import com.ingroinfo.mm.dao.InwardSpareTempBundleRepository;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.InwardMaterialBundle;
import com.ingroinfo.mm.entity.InwardMaterialTempBundle;
import com.ingroinfo.mm.entity.InwardSpare;
import com.ingroinfo.mm.entity.InwardSpareBundle;
import com.ingroinfo.mm.entity.InwardSpareTempBundle;
import com.ingroinfo.mm.service.StockService;
import com.ingroinfo.mm.entity.InwardMaterial;

@Service
public class StockServiceImpl implements StockService {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private InwardMaterialTempBundleRepository inwardMaterialTempBundleRepository;

	@Autowired
	private InwardMaterialBundleRepository inwardMaterialBundleRepository;

	@Autowired
	private InwardMaterialRepository inwardMaterialRepository;

	@Autowired
	private InwardSpareTempBundleRepository inwardSpareTempBundleRepository;

	@Autowired
	private InwardSpareBundleRepository inwardSpareBundleRepository;

	@Autowired
	private InwardSpareRepository inwardSpareRepository;

	@Override
	public void saveInwardMaterial(InwardMaterialTempBundle inwardMaterialTemp) {
		inwardMaterialTempBundleRepository.save(inwardMaterialTemp);
	}

	@Override
	public List<InwardMaterialTempBundle> getInwardTempMaterialList(String username) {
		return inwardMaterialTempBundleRepository.findByUsername(username);
	}

	@Override
	public void deleteTempBundleMaterial(Long tempBunleId) {
		inwardMaterialTempBundleRepository.deleteById(tempBunleId);
	}

	@Override
	public void saveInwardMaterials(InwardMaterial inwardMaterial) {

		InwardMaterial newInwardMaterial = inwardMaterialRepository.save(inwardMaterial);

		List<InwardMaterialTempBundle> inwardMaterialTemp = inwardMaterialTempBundleRepository
				.findByUsername(inwardMaterial.getUsername());

		for (InwardMaterialTempBundle tempInwardMaterial : inwardMaterialTemp) {
			InwardMaterialBundle inwardMaterialBundle = modelMapper.map(tempInwardMaterial, InwardMaterialBundle.class);
			inwardMaterialBundle.setInwardMaterial(newInwardMaterial);
			inwardMaterialBundleRepository.save(inwardMaterialBundle);
		}
		inwardMaterialTempBundleRepository.deleteAll();
	}

	@Override
	public List<InwardMaterialBundle> getInwardMaterialList() {
		return inwardMaterialBundleRepository.findAll();
	}

	@Override
	public List<InwardItemDto> getInwarAllMaterialList() {

		List<InwardMaterial> materials = inwardMaterialRepository.findAll();

		List<InwardItemDto> newMaterials = inwardMaterialBundleRepository.findAll().stream().map(s -> {
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

		return newMaterials;
	}

	@Override
	public void deleteBundleMaterial(Long materialId) {

		InwardMaterialBundle iI = inwardMaterialBundleRepository.findBybundleId(materialId);

		Long materialsId = iI.getInwardMaterial().getMaterialsId();

		List<InwardMaterialBundle> inwardMaterial = inwardMaterialBundleRepository.findAll().stream()
				.filter(f -> f.getInwardMaterial().equals(iI.getInwardMaterial())).collect(Collectors.toList());

		inwardMaterialBundleRepository.deleteById(materialId);

		if (inwardMaterial.size() == 1) {
			inwardMaterialRepository.deleteById(materialsId);
		}

	}

	@Override
	public void deleteAllMaterials() {
		inwardMaterialTempBundleRepository.deleteAll();
	}

	@Override
	public void saveInwardSpare(InwardSpareTempBundle inwardSpareTemp) {
		inwardSpareTempBundleRepository.save(inwardSpareTemp);
	}

	@Override
	public List<InwardSpareTempBundle> getInwardTempSpareList(String username) {
		return inwardSpareTempBundleRepository.findByUsername(username);
	}

	@Override
	public void deleteTempBundleSpare(Long tempBunleId) {
		inwardSpareTempBundleRepository.deleteById(tempBunleId);

	}

	@Override
	public void saveInwardSpares(InwardSpare inwardSpare) {
		InwardSpare newInwardSpare = inwardSpareRepository.save(inwardSpare);

		List<InwardSpareTempBundle> inwardSpareTemp = inwardSpareTempBundleRepository
				.findByUsername(newInwardSpare.getUsername());

		for (InwardSpareTempBundle tempInwardSpare : inwardSpareTemp) {
			InwardSpareBundle inwardSpareBundle = modelMapper.map(tempInwardSpare, InwardSpareBundle.class);
			inwardSpareBundle.setInwardSpare(newInwardSpare);
			inwardSpareBundleRepository.save(inwardSpareBundle);
		}
		inwardSpareTempBundleRepository.deleteAll();

	}

	@Override
	public List<InwardSpareBundle> getInwardSpareList() {
		return inwardSpareBundleRepository.findAll();
	}

	@Override
	public List<InwardItemDto> getInwardAllSpareList() {
		List<InwardSpare> spares = inwardSpareRepository.findAll();

		List<InwardItemDto> newSpares = inwardSpareBundleRepository.findAll().stream().map(s -> {
			InwardItemDto iIdto = new InwardItemDto();
			for (InwardSpare spare : spares) {
				if (s.getInwardSpare().equals(spare)) {
					iIdto.setSupplierName(spare.getSupplierName());
					iIdto.setItemId(s.getItemId());
					iIdto.setItemName(s.getItemName());
					iIdto.setAliasName(s.getAliasName());
					iIdto.setSpareImage(s.getSpareImage());
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
					iIdto.setSupplierName(spare.getSupplierName());
					iIdto.setSuppliedOn(spare.getSuppliedOn());
					iIdto.setGstType(spare.getGstType());
					iIdto.setIgst(String.valueOf(spare.getIgst()));
					iIdto.setSgst(String.valueOf(spare.getSgst()));
					iIdto.setCgst(String.valueOf(spare.getCgst()));
					iIdto.setSubTotal(String.valueOf(spare.getSubTotal()));
					iIdto.setGrandTotal(String.valueOf(spare.getGrandTotal()));
					iIdto.setInvoiceNo(spare.getInvoiceNo());
					iIdto.setReceivedBy(spare.getReceivedBy());
					iIdto.setReceivedDate(spare.getReceivedDate());
					iIdto.setUsername(spare.getUsername());
					iIdto.setBundleId(s.getBundleId());
				}
			}
			return iIdto;
		}).collect(Collectors.toList());

		return newSpares;
	}

	@Override
	public void deleteBundleSpare(Long spareId) {

		InwardSpareBundle iI = inwardSpareBundleRepository.findBybundleId(spareId);

		Long sparesId = iI.getInwardSpare().getSparesId();

		List<InwardSpareBundle> inwardSpare = inwardSpareBundleRepository.findAll().stream()
				.filter(f -> f.getInwardSpare().equals(iI.getInwardSpare())).collect(Collectors.toList());

		inwardSpareBundleRepository.deleteById(spareId);

		if (inwardSpare.size() == 1) {
			inwardSpareRepository.deleteById(sparesId);
		}

	}

	@Override
	public void deleteAllSpares() {
		inwardSpareTempBundleRepository.deleteAll();

	}

}
