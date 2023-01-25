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
import com.ingroinfo.mm.dao.InwardToolsBundleRepository;
import com.ingroinfo.mm.dao.InwardToolsRepository;
import com.ingroinfo.mm.dao.InwardToolsTempBundleRepository;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.InwardMaterialBundle;
import com.ingroinfo.mm.entity.InwardMaterialTempBundle;
import com.ingroinfo.mm.entity.InwardSpare;
import com.ingroinfo.mm.entity.InwardSpareBundle;
import com.ingroinfo.mm.entity.InwardSpareTempBundle;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.InwardToolsBundle;
import com.ingroinfo.mm.entity.InwardToolsTempBundle;
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

	@Autowired
	private InwardToolsTempBundleRepository inwardToolsTempBundleRepository;

	@Autowired
	private InwardToolsBundleRepository inwardToolsBundleRepository;

	@Autowired
	private InwardToolsRepository inwardToolsRepository;

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

		List<InwardMaterialBundle> listOfMaterials = inwardMaterialBundleRepository
				.findByInwardMaterial(newInwardMaterial);

		newInwardMaterial.setNoOfMaterials(listOfMaterials.size());

		inwardMaterialRepository.save(inwardMaterial);

	}

	@Override
	public InwardMaterialBundle getMaterialById(Long bundleId) {
		return inwardMaterialBundleRepository.findBybundleId(bundleId);
	}

	@Override
	public List<InwardMaterialBundle> getInwardMaterialList() {
		return inwardMaterialBundleRepository.findAll();
	}

	@Override
	public List<InwardItemDto> getBundledMaterialsById(Long bundleId) {

		InwardMaterial inwardMaterial = inwardMaterialRepository.findByAllMaterialsId(bundleId);

		List<InwardItemDto> newMaterials = inwardMaterialBundleRepository.findByInwardMaterial(inwardMaterial).stream()
				.map(s -> {

					InwardItemDto iIdto = new InwardItemDto();

					iIdto.setSupplierName(inwardMaterial.getSupplierName());
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
					iIdto.setSupplierName(inwardMaterial.getSupplierName());
					iIdto.setSuppliedOn(inwardMaterial.getSuppliedOn());
					iIdto.setGstType(inwardMaterial.getGstType());
					iIdto.setIgst(String.valueOf(inwardMaterial.getIgst()));
					iIdto.setSgst(String.valueOf(inwardMaterial.getSgst()));
					iIdto.setCgst(String.valueOf(inwardMaterial.getCgst()));
					iIdto.setSubTotal(String.valueOf(inwardMaterial.getSubTotal()));
					iIdto.setGrandTotal(String.valueOf(inwardMaterial.getGrandTotal()));
					iIdto.setInvoiceNo(inwardMaterial.getInvoiceNo());
					iIdto.setReceivedBy(inwardMaterial.getReceivedBy());
					iIdto.setReceivedDate(inwardMaterial.getReceivedDate());
					iIdto.setUsername(inwardMaterial.getUsername());
					iIdto.setBundleId(s.getBundleId());

					return iIdto;
				}).collect(Collectors.toList());

		return newMaterials;
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
	public boolean deleteBundleMaterial(Long materialId) {

		boolean deletedAll = false;

		InwardMaterialBundle iI = inwardMaterialBundleRepository.findBybundleId(materialId);

		double subTotal = (iI.getInwardMaterial().getSubTotal() - iI.getTotalAmount());

		double gstVal = (subTotal / 100) * iI.getInwardMaterial().getIgst();

		InwardMaterial inwardMaterial = inwardMaterialRepository
				.findByAllMaterialsId(iI.getInwardMaterial().getAllMaterialsId());

		inwardMaterial.setSubTotal(subTotal);
		inwardMaterial.setGrandTotal(subTotal + gstVal);
		
		Long materialsId = iI.getInwardMaterial().getAllMaterialsId();

		List<InwardMaterialBundle> inwardMaterials = inwardMaterialBundleRepository.findAll().stream()
				.filter(f -> f.getInwardMaterial().equals(iI.getInwardMaterial())).collect(Collectors.toList());

		inwardMaterialBundleRepository.deleteById(materialId);

		List<InwardMaterialBundle> inwardMaterialsCount = inwardMaterialBundleRepository.findAll().stream()
				.filter(f -> f.getInwardMaterial().equals(iI.getInwardMaterial())).collect(Collectors.toList());
		
		inwardMaterial.setNoOfMaterials(inwardMaterialsCount.size());
		inwardMaterialRepository.save(inwardMaterial);

		if (inwardMaterials.size() == 1) {
			inwardMaterialRepository.deleteById(materialsId);
			deletedAll = true;
		}
		return deletedAll;
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

		List<InwardSpareBundle> listOfSpares = inwardSpareBundleRepository.findByInwardSpare(newInwardSpare);

		newInwardSpare.setNoOfSpares(listOfSpares.size());

		inwardSpareRepository.save(newInwardSpare);

	}

	@Override
	public List<InwardSpareBundle> getInwardSpareList() {
		return inwardSpareBundleRepository.findAll();
	}

	@Override
	public List<InwardSpareBundle> getBundledSparesById(Long bundleId) {

		InwardSpare inwardSpare = inwardSpareRepository.findByAllSparesId(bundleId);

		return inwardSpareBundleRepository.findByInwardSpare(inwardSpare);
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

		double subTotal = (iI.getInwardSpare().getSubTotal() - iI.getTotalAmount());

		double gstVal = (subTotal / 100) * iI.getInwardSpare().getIgst();

		InwardSpare inwardSpare = inwardSpareRepository.findByAllSparesId(iI.getInwardSpare().getAllSparesId());

		inwardSpare.setSubTotal(subTotal);
		inwardSpare.setGrandTotal(subTotal + gstVal);

		Long sparesId = iI.getInwardSpare().getAllSparesId();

		List<InwardSpareBundle> inwardSpares = inwardSpareBundleRepository.findAll().stream()
				.filter(f -> f.getInwardSpare().equals(iI.getInwardSpare())).collect(Collectors.toList());

		inwardSpareBundleRepository.deleteById(spareId);

		inwardSpareRepository.save(inwardSpare);

		if (inwardSpares.size() == 1) {
			inwardSpareRepository.deleteById(sparesId);
		}
	}

	@Override
	public void deleteAllSpares() {
		inwardSpareTempBundleRepository.deleteAll();
	}

	@Override
	public void saveInwardTools(InwardToolsTempBundle inwardToolsTemp) {
		inwardToolsTempBundleRepository.save(inwardToolsTemp);
	}

	@Override
	public List<InwardToolsTempBundle> getInwardTempToolsList(String username) {
		return inwardToolsTempBundleRepository.findByUsername(username);
	}

	@Override
	public void deleteTempBundleTools(Long tempBunleId) {

		inwardToolsTempBundleRepository.deleteById(tempBunleId);
	}

	@Override
	public void saveInwardAllTools(InwardTools inwardTools) {

		InwardTools newInwardTools = inwardToolsRepository.save(inwardTools);

		List<InwardToolsTempBundle> inwardToolsTemp = inwardToolsTempBundleRepository
				.findByUsername(newInwardTools.getUsername());

		for (InwardToolsTempBundle tempInwardTools : inwardToolsTemp) {
			InwardToolsBundle inwardToolsBundle = modelMapper.map(tempInwardTools, InwardToolsBundle.class);
			inwardToolsBundle.setInwardTools(newInwardTools);
			inwardToolsBundleRepository.save(inwardToolsBundle);
		}
		inwardToolsTempBundleRepository.deleteAll();

		List<InwardToolsBundle> listOfTools = inwardToolsBundleRepository.findByInwardTools(newInwardTools);

		newInwardTools.setNoOfTools(listOfTools.size());

		inwardToolsRepository.save(newInwardTools);
	}

	@Override
	public List<InwardToolsBundle> getInwardToolsList() {
		return inwardToolsBundleRepository.findAll();
	}

	@Override
	public List<InwardToolsBundle> getBundledToolsById(Long bundleId) {

		InwardTools inwardTools = inwardToolsRepository.findByAllToolsId(bundleId);

		return inwardToolsBundleRepository.findByInwardTools(inwardTools);
	}

	@Override
	public List<InwardItemDto> getInwardAllToolsList() {

		List<InwardTools> tools = inwardToolsRepository.findAll();

		List<InwardItemDto> newTools = inwardToolsBundleRepository.findAll().stream().map(s -> {
			InwardItemDto iIdto = new InwardItemDto();
			for (InwardTools tool : tools) {
				if (s.getInwardTools().equals(tool)) {
					iIdto.setSupplierName(tool.getSupplierName());
					iIdto.setItemId(s.getItemId());
					iIdto.setItemName(s.getItemName());
					iIdto.setAliasName(s.getAliasName());
					iIdto.setToolsImage(s.getToolsImage());
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
					iIdto.setSupplierName(tool.getSupplierName());
					iIdto.setSuppliedOn(tool.getSuppliedOn());
					iIdto.setGstType(tool.getGstType());
					iIdto.setIgst(String.valueOf(tool.getIgst()));
					iIdto.setSgst(String.valueOf(tool.getSgst()));
					iIdto.setCgst(String.valueOf(tool.getCgst()));
					iIdto.setSubTotal(String.valueOf(tool.getSubTotal()));
					iIdto.setGrandTotal(String.valueOf(tool.getGrandTotal()));
					iIdto.setInvoiceNo(tool.getInvoiceNo());
					iIdto.setReceivedBy(tool.getReceivedBy());
					iIdto.setReceivedDate(tool.getReceivedDate());
					iIdto.setUsername(tool.getUsername());
					iIdto.setBundleId(s.getBundleId());
				}
			}
			return iIdto;
		}).collect(Collectors.toList());

		return newTools;
	}

	@Override
	public void deleteBundleTools(Long toolsId) {

		InwardToolsBundle iI = inwardToolsBundleRepository.findBybundleId(toolsId);

		double subTotal = (iI.getInwardTools().getSubTotal() - iI.getTotalAmount());

		double gstVal = (subTotal / 100) * iI.getInwardTools().getIgst();

		InwardTools inwardTools = inwardToolsRepository.findByAllToolsId(iI.getInwardTools().getAllToolsId());

		inwardTools.setSubTotal(subTotal);
		inwardTools.setGrandTotal(subTotal + gstVal);

		Long lastToolsId = iI.getInwardTools().getAllToolsId();

		List<InwardToolsBundle> allInwardTools = inwardToolsBundleRepository.findAll().stream()
				.filter(f -> f.getInwardTools().equals(iI.getInwardTools())).collect(Collectors.toList());

		inwardToolsBundleRepository.deleteById(toolsId);

		inwardToolsRepository.save(inwardTools);

		if (allInwardTools.size() == 1) {
			inwardToolsRepository.deleteById(lastToolsId);
		}

	}

	@Override
	public void deleteAllTools() {
		inwardToolsTempBundleRepository.deleteAll();
	}

	@Override
	public List<InwardMaterial> getMaterialsBundlesList() {

		return inwardMaterialRepository.findAll();
	}

	@Override
	public List<InwardSpare> getSparesBundlesList() {

		return inwardSpareRepository.findAll();
	}

	@Override
	public List<InwardTools> getToolsBundlesList() {

		return inwardToolsRepository.findAll();
	}

}
