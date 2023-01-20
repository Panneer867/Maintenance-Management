package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.StoreBranchRepository;
import com.ingroinfo.mm.dto.StoreBranchDto;
import com.ingroinfo.mm.entity.StoreBranch;
import com.ingroinfo.mm.service.StoreBranchService;

@Service
public class StoreBranchServiceImpl implements StoreBranchService {

	@Autowired
	private StoreBranchRepository storeBranchRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public StoreBranchDto saveStoreBranch(StoreBranchDto storeBranchDto) {
		StoreBranch convBranch = this.modelMapper.map(storeBranchDto, StoreBranch.class);
		StoreBranch savedBranch = this.storeBranchRepository.save(convBranch);
		return this.modelMapper.map(savedBranch, StoreBranchDto.class);
	}

	@Override
	public List<StoreBranchDto> findAllStoreBranch() {
		List<StoreBranch> storeBranchs = this.storeBranchRepository.findAll();
		List<StoreBranchDto> storeBranchDtos = storeBranchs.stream().map((storeBranch) -> 
		this.modelMapper.map(storeBranch, StoreBranchDto.class)).collect(Collectors.toList());
		return storeBranchDtos;
	}

	@Override
	public void deleteStoreBranch(Long strbraNameId) {
		StoreBranch storeBranch = this.storeBranchRepository.findById(strbraNameId).get();
		this.storeBranchRepository.delete(storeBranch);
	}

	@Override
	public String getMaxStoreBranchId() {
		String maxStoreBranchId = this.storeBranchRepository.getMaxStoreBranchId();
		return maxStoreBranchId;
	}

}
