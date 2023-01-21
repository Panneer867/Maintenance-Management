package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.HsnCodeDto;
import com.ingroinfo.mm.entity.HsnCode;

public interface HsnCodeService {

	//create
	HsnCodeDto saveHsnCode(HsnCodeDto hsnCodeDto);
	
	//findAll Data
	List<HsnCodeDto> findAllHsnCode();
	
	//Delete
	void deleteHsnCode(Long hsnCodeId);

	HsnCode getHsnById(Long id);
}
