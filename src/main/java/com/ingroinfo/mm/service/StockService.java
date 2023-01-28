package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.InwardMaterials;
import com.ingroinfo.mm.entity.InwardTempMaterials;
import com.ingroinfo.mm.entity.InwardSpare;
import com.ingroinfo.mm.entity.InwardSpareBundle;
import com.ingroinfo.mm.entity.InwardSpareTempBundle;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.InwardToolsBundle;
import com.ingroinfo.mm.entity.InwardToolsTempBundle;
import com.ingroinfo.mm.entity.InwardMaterialBundles;

public interface StockService {

	void saveInwardMaterial(InwardTempMaterials inwardMaterialTemp);

	List<InwardTempMaterials> getInwardTempMaterialList(String username);

	void deleteTempBundleMaterial(Long tempBunleId);
	
	void saveInwardMaterials(InwardMaterialBundles inwardMaterial);

	List<InwardMaterials> getInwardMaterialList();
	
	List<InwardItemDto> getBundledMaterialsById(Long bundleId);

	List<InwardItemDto> getInwarAllMaterialList();
	
	List<InwardMaterialBundles> getMaterialsBundlesList();
	
	InwardMaterials getMaterialById(Long bundleId);

	boolean deleteBundleMaterial(Long materialId);
	
	void deleteAllMaterials();
	
	void saveInwardSpare(InwardSpareTempBundle inwardSpareTemp);

	List<InwardSpareTempBundle> getInwardTempSpareList(String username);

	void deleteTempBundleSpare(Long tempBunleId);
	
	void saveInwardSpares(InwardSpare inwardSpare);

	List<InwardSpareBundle> getInwardSpareList();
	
	List<InwardItemDto> getBundledSparesById(Long bundleId);

	List<InwardItemDto> getInwardAllSpareList();
	
	List<InwardSpare> getSparesBundlesList();

	boolean deleteBundleSpare(Long spareId);
	
	void deleteAllSpares();
	
	void saveInwardTools(InwardToolsTempBundle inwardToolsTemp);

	List<InwardToolsTempBundle> getInwardTempToolsList(String username);

	void deleteTempBundleTools(Long tempBunleId);
	
	void saveInwardAllTools(InwardTools inwardTools);

	List<InwardToolsBundle> getInwardToolsList();
	
	List<InwardItemDto> getBundledToolsById(Long bundleId);

	List<InwardItemDto> getInwardAllToolsList();
	
	List<InwardTools> getToolsBundlesList();

	boolean deleteBundleTools(Long toolsId);
	
	void deleteAllTools();

	InwardSpareBundle getSpareById(Long bundleId);

	InwardToolsBundle getToolsById(Long bundleId);

	

	

	

}
