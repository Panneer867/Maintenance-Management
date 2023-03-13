package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.ItemMasterRepository;
import com.ingroinfo.mm.dto.ItemMasterDto;
import com.ingroinfo.mm.entity.ItemMaster;
import com.ingroinfo.mm.service.ItemMasterService;

@Service
public class ItemMasterServiceImpl implements ItemMasterService {

	@Autowired
	ItemMasterRepository itemMasterRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public ItemMasterDto saveItemmaster(ItemMasterDto itemDto) {
		itemDto.setCategoryName(itemDto.getCategory().getCategoryName());
		ItemMaster convItemMaster = this.modelMapper.map(itemDto, ItemMaster.class);
		ItemMaster savedItemMaster = this.itemMasterRepo.save(convItemMaster);
		return this.modelMapper.map(savedItemMaster, ItemMasterDto.class);
	}

	@Override
	public List<ItemMasterDto> getAllItems() {
		List<ItemMaster> itemMasters = this.itemMasterRepo.findAll();
		List<ItemMasterDto> itemMasterDtos = itemMasters.stream()
				.map((itemMaster) -> this.modelMapper.map(itemMaster, ItemMasterDto.class))
				.collect(Collectors.toList());
		return itemMasterDtos;
	}

	@Override
	public void deleteMasterItem(Long itemMasterId) {
		ItemMaster itemMaster = this.itemMasterRepo.findById(itemMasterId).get();
		this.itemMasterRepo.delete(itemMaster);
	}

	@Override
	public String getMaxItemId() {
		String maxItemId = this.itemMasterRepo.getMaxItemId();
		return maxItemId;
	}

	@Override
	public List<ItemMasterDto> getItemListByCategoryId(Long categoryId) {
		List<ItemMaster> itemMasters = this.itemMasterRepo.findItemsByCategoryId(categoryId);
		return itemMasters.stream().map((items) -> this.modelMapper.map(items, ItemMasterDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ItemMasterDto getItemByItemId(Long itemId) {	
		ItemMaster itemMaster = this.itemMasterRepo.findById(itemId).get();
		return this.modelMapper.map(itemMaster, ItemMasterDto.class);
	}

	
	@Override
	public List<ItemMasterDto> getAllItemNames(String stockType) {
		List<ItemMasterDto> itemMasterDtos = this.itemMasterRepo.findByStockType(stockType).stream().map((itemMaster) -> 
		this.modelMapper.map(itemMaster, ItemMasterDto.class)).collect(Collectors.toList());
		return itemMasterDtos;
	}
}
