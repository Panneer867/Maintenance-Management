package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.entity.InwardItem;

public interface MaterialService {

	void saveInwardItem(InwardItem inwardItem);

	List<InwardItem> getInwardItemList();

}
