package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.InwardMaterialsRepository;
import com.ingroinfo.mm.dao.InwardMaterialBundlesRepository;
import com.ingroinfo.mm.dao.InwardTempMaterialsRepository;
import com.ingroinfo.mm.dao.InwardSpareBundleRepository;
import com.ingroinfo.mm.dao.InwardSpareRepository;
import com.ingroinfo.mm.dao.InwardSpareTempBundleRepository;
import com.ingroinfo.mm.dao.InwardToolsBundleRepository;
import com.ingroinfo.mm.dao.InwardToolsRepository;
import com.ingroinfo.mm.dao.InwardToolsTempBundleRepository;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.InwardMaterials;
import com.ingroinfo.mm.entity.InwardTempMaterials;
import com.ingroinfo.mm.entity.InwardSpare;
import com.ingroinfo.mm.entity.InwardSpareBundle;
import com.ingroinfo.mm.entity.InwardSpareTempBundle;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.InwardToolsBundle;
import com.ingroinfo.mm.entity.InwardToolsTempBundle;
import com.ingroinfo.mm.service.StockService;
import com.ingroinfo.mm.entity.InwardMaterialBundles;

@Service
public class StockServiceImpl implements StockService {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private InwardTempMaterialsRepository inwardMaterialTempBundleRepository;

	@Autowired
	private InwardMaterialsRepository inwardMaterialsRepository;

	@Autowired
	private InwardMaterialBundlesRepository inwardMaterialBundlesRepository;

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
	public void saveInwardMaterial(InwardTempMaterials inwardMaterialTemp) {
		inwardMaterialTempBundleRepository.save(inwardMaterialTemp);
	}

	@Override
	public List<InwardTempMaterials> getInwardTempMaterialList(String username) {
		return inwardMaterialTempBundleRepository.findByUsername(username);
	}

	@Override
	public void deleteTempBundleMaterial(Long tempBunleId) {
		inwardMaterialTempBundleRepository.deleteById(tempBunleId);
	}

	@Override
	public void saveInwardMaterials(InwardMaterialBundles inwardMaterial) {

		InwardMaterialBundles newInwardMaterial = inwardMaterialBundlesRepository.save(inwardMaterial);

		List<InwardTempMaterials> inwardMaterialTemp = inwardMaterialTempBundleRepository
				.findByUsername(inwardMaterial.getUsername());

		for (InwardTempMaterials tempInwardMaterial : inwardMaterialTemp) {

			InwardMaterials inwardMaterialBundle = modelMapper.map(tempInwardMaterial, InwardMaterials.class);
			inwardMaterialBundle.setMaterialBundle(newInwardMaterial);
			inwardMaterialsRepository.save(inwardMaterialBundle);

		}
		inwardMaterialTempBundleRepository.deleteAll();

		List<InwardMaterials> listOfMaterials = inwardMaterialsRepository.findByMaterialBundle(newInwardMaterial);

		newInwardMaterial.setNoOfMaterials(listOfMaterials.size());

		inwardMaterialBundlesRepository.save(inwardMaterial);

	}

	@Override
	public InwardMaterials getMaterialById(Long bundleId) {
		return inwardMaterialsRepository.findByMaterialId(bundleId);
	}

	@Override
	public List<InwardMaterials> getInwardMaterialList() {
		return inwardMaterialsRepository.findAll();
	}

	@Override
	public List<InwardItemDto> getBundledMaterialsById(Long bundleId) {

		InwardMaterialBundles inwardMaterial = inwardMaterialBundlesRepository.findByBundleId(bundleId);

		List<InwardItemDto> newMaterials = inwardMaterialsRepository.findByMaterialBundle(inwardMaterial).stream()
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
					iIdto.setTotalQuantity(s.getTotalQuantity());
					iIdto.setTotalAmount(s.getTotalAmount());
					iIdto.setCostRate(s.getCostRate());
					iIdto.setMrp(s.getMrp());
					iIdto.setEntryDate(s.getEntryDate());
					iIdto.setDescription(s.getDescription());
					iIdto.setDateCreated(s.getDateCreated());
					iIdto.setLastUpdated(s.getLastUpdated());
					iIdto.setSupplierName(inwardMaterial.getSupplierName());
					iIdto.setSuppliedOn(inwardMaterial.getSuppliedOn());
					iIdto.setGstType(inwardMaterial.getGstType());
					iIdto.setIgst(inwardMaterial.getIgst());
					iIdto.setSgst(inwardMaterial.getSgst());
					iIdto.setCgst(inwardMaterial.getCgst());
					iIdto.setSubTotal(inwardMaterial.getSubTotal());
					iIdto.setGrandTotal(inwardMaterial.getGrandTotal());
					iIdto.setInvoiceNo(inwardMaterial.getInvoiceNo());
					iIdto.setReceivedBy(inwardMaterial.getReceivedBy());
					iIdto.setReceivedDate(inwardMaterial.getReceivedDate());
					iIdto.setUsername(inwardMaterial.getUsername());
					iIdto.setMaterialId(s.getMaterialId());
					iIdto.setBundleId(s.getMaterialBundle().getBundleId());

					return iIdto;
				}).collect(Collectors.toList());

		return newMaterials;
	}

	@Override
	public List<InwardItemDto> getInwarAllMaterialList() {

		List<InwardMaterialBundles> materials = inwardMaterialBundlesRepository.findAll();

		List<InwardItemDto> newMaterials = inwardMaterialsRepository.findAll().stream().map(s -> {
			InwardItemDto iIdto = new InwardItemDto();
			for (InwardMaterialBundles material : materials) {
				if (s.getMaterialBundle().equals(material)) {
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
					iIdto.setTotalQuantity(s.getTotalQuantity());
					iIdto.setTotalAmount(s.getTotalAmount());
					iIdto.setCostRate(s.getCostRate());
					iIdto.setMrp(s.getMrp());
					iIdto.setEntryDate(s.getEntryDate());
					iIdto.setDescription(s.getDescription());
					iIdto.setDateCreated(s.getDateCreated());
					iIdto.setLastUpdated(s.getLastUpdated());
					iIdto.setSupplierName(material.getSupplierName());
					iIdto.setSuppliedOn(material.getSuppliedOn());
					iIdto.setGstType(material.getGstType());
					iIdto.setIgst(material.getIgst());
					iIdto.setSgst(material.getSgst());
					iIdto.setCgst(material.getCgst());
					iIdto.setSubTotal(material.getSubTotal());
					iIdto.setGrandTotal(material.getGrandTotal());
					iIdto.setInvoiceNo(material.getInvoiceNo());
					iIdto.setReceivedBy(material.getReceivedBy());
					iIdto.setReceivedDate(material.getReceivedDate());
					iIdto.setUsername(material.getUsername());
					iIdto.setMaterialId(s.getMaterialId());
					iIdto.setBundleId(s.getMaterialBundle().getBundleId());
				}
			}
			return iIdto;
		}).collect(Collectors.toList());

		return newMaterials;
	}

	@Override
	public boolean deleteBundleMaterial(Long materialId) {

		boolean deletedAll = false;

		InwardMaterials iI = inwardMaterialsRepository.findByMaterialId(materialId);

		double subTotal = (iI.getMaterialBundle().getSubTotal() - iI.getTotalAmount());

		double gstVal = (subTotal / 100) * iI.getMaterialBundle().getIgst();

		InwardMaterialBundles inwardMaterial = inwardMaterialBundlesRepository
				.findByBundleId(iI.getMaterialBundle().getBundleId());

		inwardMaterial.setSubTotal(subTotal);
		inwardMaterial.setGrandTotal(subTotal + gstVal);

		Long materialsId = iI.getMaterialBundle().getBundleId();

		List<InwardMaterials> inwardMaterials = inwardMaterialsRepository.findAll().stream()
				.filter(f -> f.getMaterialBundle().equals(iI.getMaterialBundle())).collect(Collectors.toList());

		inwardMaterialsRepository.deleteById(materialId);

		List<InwardMaterials> inwardMaterialsCount = inwardMaterialsRepository.findAll().stream()
				.filter(f -> f.getMaterialBundle().equals(iI.getMaterialBundle())).collect(Collectors.toList());

		inwardMaterial.setNoOfMaterials(inwardMaterialsCount.size());
		inwardMaterialBundlesRepository.save(inwardMaterial);

		if (inwardMaterials.size() == 1) {
			inwardMaterialBundlesRepository.deleteById(materialsId);
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
	public InwardSpareBundle getSpareById(Long bundleId) {
		return inwardSpareBundleRepository.findBybundleId(bundleId);
	}

	@Override
	public List<InwardSpareBundle> getInwardSpareList() {
		return inwardSpareBundleRepository.findAll();
	}

	@Override
	public List<InwardItemDto> getBundledSparesById(Long bundleId) {

		InwardSpare inwardSpare = inwardSpareRepository.findByAllSparesId(bundleId);

		List<InwardItemDto> newSpares = inwardSpareBundleRepository.findByInwardSpare(inwardSpare).stream().map(s -> {

			InwardItemDto iIdto = new InwardItemDto();

			iIdto.setSupplierName(inwardSpare.getSupplierName());
			iIdto.setItemId(s.getItemId());
			iIdto.setItemName(s.getItemName());
			iIdto.setAliasName(s.getAliasName());
			iIdto.setSpareImage(s.getSpareImage());
			iIdto.setImagePath(s.getImagePath());
			iIdto.setCategoryName(s.getCategoryName());
			iIdto.setBrand(s.getBrand());
			iIdto.setHsnCode(s.getHsnCode());
			iIdto.setUnitOfMeasure(s.getUnitOfMeasure());
			iIdto.setTotalQuantity(s.getTotalQuantity());
			iIdto.setTotalAmount(s.getTotalAmount());
			iIdto.setCostRate(s.getCostRate());
			iIdto.setMrp(s.getMrp());
			iIdto.setEntryDate(s.getEntryDate());
			iIdto.setDescription(s.getDescription());
			iIdto.setDateCreated(s.getDateCreated());
			iIdto.setLastUpdated(s.getLastUpdated());
			iIdto.setSupplierName(inwardSpare.getSupplierName());
			iIdto.setSuppliedOn(inwardSpare.getSuppliedOn());
			iIdto.setGstType(inwardSpare.getGstType());
			iIdto.setIgst(inwardSpare.getIgst());
			iIdto.setSgst(inwardSpare.getSgst());
			iIdto.setCgst(inwardSpare.getCgst());
			iIdto.setSubTotal(inwardSpare.getSubTotal());
			iIdto.setGrandTotal(inwardSpare.getGrandTotal());
			iIdto.setInvoiceNo(inwardSpare.getInvoiceNo());
			iIdto.setReceivedBy(inwardSpare.getReceivedBy());
			iIdto.setReceivedDate(inwardSpare.getReceivedDate());
			iIdto.setUsername(inwardSpare.getUsername());
			iIdto.setBundleId(s.getBundleId());

			return iIdto;
		}).collect(Collectors.toList());

		return newSpares;
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
					iIdto.setTotalQuantity(s.getTotalQuantity());
					iIdto.setTotalAmount(s.getTotalAmount());
					iIdto.setCostRate(s.getCostRate());
					iIdto.setMrp(s.getMrp());
					iIdto.setEntryDate(s.getEntryDate());
					iIdto.setDescription(s.getDescription());
					iIdto.setDateCreated(s.getDateCreated());
					iIdto.setLastUpdated(s.getLastUpdated());
					iIdto.setSupplierName(spare.getSupplierName());
					iIdto.setSuppliedOn(spare.getSuppliedOn());
					iIdto.setGstType(spare.getGstType());
					iIdto.setIgst(spare.getIgst());
					iIdto.setSgst(spare.getSgst());
					iIdto.setCgst(spare.getCgst());
					iIdto.setSubTotal(spare.getSubTotal());
					iIdto.setGrandTotal(spare.getGrandTotal());
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
	public boolean deleteBundleSpare(Long spareId) {

		boolean deletedAll = false;

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

		List<InwardSpareBundle> inwardSparesCount = inwardSpareBundleRepository.findAll().stream()
				.filter(f -> f.getInwardSpare().equals(iI.getInwardSpare())).collect(Collectors.toList());

		inwardSpare.setNoOfSpares(inwardSparesCount.size());

		inwardSpareRepository.save(inwardSpare);

		if (inwardSpares.size() == 1) {
			inwardSpareRepository.deleteById(sparesId);
			deletedAll = true;
		}
		return deletedAll;
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
	public InwardToolsBundle getToolsById(Long bundleId) {
		return inwardToolsBundleRepository.findBybundleId(bundleId);
	}

	@Override
	public List<InwardToolsBundle> getInwardToolsList() {
		return inwardToolsBundleRepository.findAll();
	}

	@Override
	public List<InwardItemDto> getBundledToolsById(Long bundleId) {

		InwardTools inwardTools = inwardToolsRepository.findByAllToolsId(bundleId);

		List<InwardItemDto> newTools = inwardToolsBundleRepository.findByInwardTools(inwardTools).stream().map(s -> {

			InwardItemDto iIdto = new InwardItemDto();

			iIdto.setSupplierName(inwardTools.getSupplierName());
			iIdto.setItemId(s.getItemId());
			iIdto.setItemName(s.getItemName());
			iIdto.setAliasName(s.getAliasName());
			iIdto.setToolsImage(s.getToolsImage());
			iIdto.setImagePath(s.getImagePath());
			iIdto.setCategoryName(s.getCategoryName());
			iIdto.setBrand(s.getBrand());
			iIdto.setHsnCode(s.getHsnCode());
			iIdto.setUnitOfMeasure(s.getUnitOfMeasure());
			iIdto.setTotalQuantity(s.getTotalQuantity());
			iIdto.setTotalAmount(s.getTotalAmount());
			iIdto.setCostRate(s.getCostRate());
			iIdto.setMrp(s.getMrp());
			iIdto.setEntryDate(s.getEntryDate());
			iIdto.setDescription(s.getDescription());
			iIdto.setDateCreated(s.getDateCreated());
			iIdto.setLastUpdated(s.getLastUpdated());
			iIdto.setSupplierName(inwardTools.getSupplierName());
			iIdto.setSuppliedOn(inwardTools.getSuppliedOn());
			iIdto.setGstType(inwardTools.getGstType());
			iIdto.setIgst(inwardTools.getIgst());
			iIdto.setSgst(inwardTools.getSgst());
			iIdto.setCgst(inwardTools.getCgst());
			iIdto.setSubTotal(inwardTools.getSubTotal());
			iIdto.setGrandTotal(inwardTools.getGrandTotal());
			iIdto.setInvoiceNo(inwardTools.getInvoiceNo());
			iIdto.setReceivedBy(inwardTools.getReceivedBy());
			iIdto.setReceivedDate(inwardTools.getReceivedDate());
			iIdto.setUsername(inwardTools.getUsername());
			iIdto.setBundleId(s.getBundleId());

			return iIdto;
		}).collect(Collectors.toList());

		return newTools;
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
					iIdto.setTotalQuantity(s.getTotalQuantity());
					iIdto.setTotalAmount(s.getTotalAmount());
					iIdto.setCostRate(s.getCostRate());
					iIdto.setMrp(s.getMrp());
					iIdto.setEntryDate(s.getEntryDate());
					iIdto.setDescription(s.getDescription());
					iIdto.setDateCreated(s.getDateCreated());
					iIdto.setLastUpdated(s.getLastUpdated());
					iIdto.setSupplierName(tool.getSupplierName());
					iIdto.setSuppliedOn(tool.getSuppliedOn());
					iIdto.setGstType(tool.getGstType());
					iIdto.setIgst(tool.getIgst());
					iIdto.setSgst(tool.getSgst());
					iIdto.setCgst(tool.getCgst());
					iIdto.setSubTotal(tool.getSubTotal());
					iIdto.setGrandTotal(tool.getGrandTotal());
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
	public boolean deleteBundleTools(Long toolsId) {

		boolean deletedAll = false;

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

		List<InwardToolsBundle> inwardToolsCount = inwardToolsBundleRepository.findAll().stream()
				.filter(f -> f.getInwardTools().equals(iI.getInwardTools())).collect(Collectors.toList());

		inwardTools.setNoOfTools(inwardToolsCount.size());

		inwardToolsRepository.save(inwardTools);

		if (allInwardTools.size() == 1) {
			inwardToolsRepository.deleteById(lastToolsId);
			deletedAll = true;
		}
		return deletedAll;
	}

	@Override
	public void deleteAllTools() {
		inwardToolsTempBundleRepository.deleteAll();
	}

	@Override
	public List<InwardMaterialBundles> getMaterialsBundlesList() {

		return inwardMaterialBundlesRepository.findAll();
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
