package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingroinfo.mm.entity.Assets;

public interface AssetRepository extends JpaRepository<Assets,Long>{
	

}
