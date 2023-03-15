package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.ItemMasterDto;

public interface ItemMasterService {

	// save Data
	ItemMasterDto saveItemmaster(ItemMasterDto itemDto);

	// find All Data
	List<ItemMasterDto> getAllItems();

	// Delete
	void deleteMasterItem(Long itemMasterId);

	// Get Item List By Category Id
	List<ItemMasterDto> getItemListByCategoryId(Long categoryId);

	// Get Item List By Item Id
	ItemMasterDto getItemByItemId(Long itemId);

	// Get Item List By Stock Type
	List<ItemMasterDto> getAllItemNames(String stockType);

}
