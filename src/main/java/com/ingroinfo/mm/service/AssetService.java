package com.ingroinfo.mm.service;

import java.util.List;
import com.ingroinfo.mm.dto.AssetEntryDto;
import com.ingroinfo.mm.entity.Assets;

public interface AssetService {
	
	void saveAsset(AssetEntryDto assestEntry);

	List<Assets> getAllAssets();
	
	Assets getAssetById(Long assetId);
}
