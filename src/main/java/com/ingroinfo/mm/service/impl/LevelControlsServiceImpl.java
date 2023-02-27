package com.ingroinfo.mm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.mm.dao.LevelControlsRepository;
import com.ingroinfo.mm.entity.LevelControls;
import com.ingroinfo.mm.service.LevelContolsService;

@Service
public class LevelControlsServiceImpl implements LevelContolsService {
	
	@Autowired
	private LevelControlsRepository levelControlsRepository;

	@Override
	public void saveLevelControls(LevelControls levelControls) {
		
		levelControlsRepository.save(levelControls);
	}

}
