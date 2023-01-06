package com.ingroinfo.mm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.InwardItemRepository;
import com.ingroinfo.mm.entity.InwardItem;
import com.ingroinfo.mm.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private InwardItemRepository inwardItemRepo;

	@Override
	public void saveInwardItem(InwardItem inwardItem) {
		inwardItemRepo.save(inwardItem);
	}

	@Override
	public List<InwardItem> getInwardItemList() {
		return inwardItemRepo.findAll();
	}

}
