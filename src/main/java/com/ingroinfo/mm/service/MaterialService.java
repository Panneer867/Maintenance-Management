package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.InwardItem;
import com.ingroinfo.mm.entity.InwardItemTemp;
import com.ingroinfo.mm.entity.InwardMaterial;

public interface MaterialService {

	void saveInwardItem(InwardItemTemp inwardItemTemp);

	List<InwardItemTemp> getInwardTempItemList(String username);

	void deleteTempBundleItem(Long tempBunleId);
	
	void saveInwardMaterial(InwardMaterial inwardMaterial);

	void deleteAllItems();

	List<InwardItem> getInwardItemList();

	List<InwardItemDto> getInwarAlldItemList();

	void deleteBundleItem(Long itemId);

}
