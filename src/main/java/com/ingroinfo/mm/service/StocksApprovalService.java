package com.ingroinfo.mm.service;

import com.ingroinfo.mm.dto.InwardDto;

public interface StocksApprovalService {

	void saveMaterial(InwardDto inwardItemDto);

	void rejectMaterial(Long id);

	void saveSpare(InwardDto inwardItemDto);

	void rejectSpare(Long id);
	
	void saveTool(InwardDto inwardItemDto);

	void rejectTool(Long id);

	void approveOutwardStocks(Long workOrderNo);

	void rejectWorkorderItems(Long workOrderNo);

}
