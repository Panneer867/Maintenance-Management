package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.HsnCodeRepository;
import com.ingroinfo.mm.dto.HsnCodeDto;
import com.ingroinfo.mm.entity.HsnCode;
import com.ingroinfo.mm.service.HsnCodeService;

@Service
public class HsnCodeServiceImpl implements HsnCodeService {

	@Autowired
	private HsnCodeRepository hsnCodeRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public HsnCodeDto saveHsnCode(HsnCodeDto hsnCodeDto) {
		 HsnCode conHsnCode = this.modelMapper.map(hsnCodeDto, HsnCode.class);
		 HsnCode savedHsnCode = this.hsnCodeRepo.save(conHsnCode);
		 return this.modelMapper.map(savedHsnCode, HsnCodeDto.class);
	}

	@Override
	public List<HsnCodeDto> findAllHsnCode() {
		List<HsnCode> hsnCodes = this.hsnCodeRepo.findAll();
		List<HsnCodeDto> hsnCodeDtos = hsnCodes.stream().map((hsnCode) -> 
		this.modelMapper.map(hsnCode, HsnCodeDto.class)).collect(Collectors.toList());
		return hsnCodeDtos;
	}

	@Override
	public void deleteHsnCode(Long hsnCodeId) {
		HsnCode hsnCode = this.hsnCodeRepo.findById(hsnCodeId).get();
		this.hsnCodeRepo.delete(hsnCode);
	}

	@Override
	public HsnCodeDto getHsnCodeByCategory(String category) {
		HsnCode hsnCode = this.hsnCodeRepo.findByCategory(category);
		return this.modelMapper.map(hsnCode, HsnCodeDto.class);
	}

}
