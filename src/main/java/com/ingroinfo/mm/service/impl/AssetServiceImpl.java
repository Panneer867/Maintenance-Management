package com.ingroinfo.mm.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.AssetRepository;
import com.ingroinfo.mm.dto.AssetEntryDto;
import com.ingroinfo.mm.entity.Assets;
import com.ingroinfo.mm.service.AssetService;


@Service
public class AssetServiceImpl implements AssetService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AssetRepository assetRepository;
	
	@Override
	public void saveAsset(AssetEntryDto assestEntry) {
		Assets assets = modelMapper.map(assestEntry, Assets.class);
		assetRepository.save(assets);
	}

	@Override
	public List<Assets> getAllAssets() {		
		return assetRepository.findAll();
	}

	@Override
	public Assets getAssetById(Long assetId) {		
		return assetRepository.findById(assetId).get();
	}

}
