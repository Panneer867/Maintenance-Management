package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.ItemMasterDto;

public interface ItemMasterService {

	//save Data
	ItemMasterDto saveItemmaster(ItemMasterDto itemDto);
	
	//find All Data
	List<ItemMasterDto> getAllItems();
	
	//Delete
	void deleteMasterItem(Long itemMasterId);
	
	//get Max Item Id
	String getMaxItemId();

	//Get Item List By Category Name
	List<ItemMasterDto> getItemListByCategory(String category);
	
}
