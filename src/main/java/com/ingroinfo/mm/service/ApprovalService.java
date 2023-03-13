package com.ingroinfo.mm.service;

import com.ingroinfo.mm.dto.InwardDto;

public interface ApprovalService {

	void saveMaterial(InwardDto inwardItemDto);

	void rejectMaterial(Long id);

	void saveSpare(InwardDto inwardItemDto);

	void rejectSpare(Long id);
	
	void saveTool(InwardDto inwardItemDto);

	void rejectTool(Long id);

}
