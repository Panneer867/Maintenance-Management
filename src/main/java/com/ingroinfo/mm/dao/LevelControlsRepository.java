package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.LevelControls;

@Repository
public interface LevelControlsRepository extends JpaRepository<LevelControls, Long> {


	
}
