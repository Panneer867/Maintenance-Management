package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.InwardMaterialBundle;
import com.ingroinfo.mm.entity.InwardMaterialTempBundle;
import com.ingroinfo.mm.entity.InwardSpare;
import com.ingroinfo.mm.entity.InwardSpareBundle;
import com.ingroinfo.mm.entity.InwardSpareTempBundle;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.InwardToolsBundle;
import com.ingroinfo.mm.entity.InwardToolsTempBundle;
import com.ingroinfo.mm.entity.InwardMaterial;

public interface StockService {

	void saveInwardMaterial(InwardMaterialTempBundle inwardMaterialTemp);

	List<InwardMaterialTempBundle> getInwardTempMaterialList(String username);

	void deleteTempBundleMaterial(Long tempBunleId);
	
	void saveInwardMaterials(InwardMaterial inwardMaterial);

	List<InwardMaterialBundle> getInwardMaterialList();

	List<InwardItemDto> getInwarAllMaterialList();

	void deleteBundleMaterial(Long materialId);
	
	void deleteAllMaterials();
	
	void saveInwardSpare(InwardSpareTempBundle inwardSpareTemp);

	List<InwardSpareTempBundle> getInwardTempSpareList(String username);

	void deleteTempBundleSpare(Long tempBunleId);
	
	void saveInwardSpares(InwardSpare inwardSpare);

	List<InwardSpareBundle> getInwardSpareList();

	List<InwardItemDto> getInwardAllSpareList();

	void deleteBundleSpare(Long spareId);
	
	void deleteAllSpares();
	
	void saveInwardTools(InwardToolsTempBundle inwardToolsTemp);

	List<InwardToolsTempBundle> getInwardTempToolsList(String username);

	void deleteTempBundleTools(Long tempBunleId);
	
	void saveInwardAllTools(InwardTools inwardTools);

	List<InwardToolsBundle> getInwardToolsList();

	List<InwardItemDto> getInwardAllToolsList();

	void deleteBundleTools(Long toolsId);
	
	void deleteAllTools();

}
