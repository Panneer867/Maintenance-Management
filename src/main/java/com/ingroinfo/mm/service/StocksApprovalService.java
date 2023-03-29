package com.ingroinfo.mm.service;

import com.ingroinfo.mm.dto.InwardDto;

public interface StocksApprovalService {

	void saveMaterial(InwardDto inwardItemDto);

	void rejectMaterial(Long id, String username);

	void saveSpare(InwardDto inwardItemDto);

	void rejectSpare(Long id, String username);
	
	void saveTool(InwardDto inwardItemDto);

	void rejectTool(Long id, String username);

	void approveOutwardStocks(Long stockOrderNo, String username);

	void rejectStockorderItems(Long stockOrderNo, String username);

	void approveReturnItem(Long id, String username);

	void rejectReturnItem(Long id, String username);

}
