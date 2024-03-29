package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.BrandMaster;

@Repository
public interface BrandMasterRepository extends JpaRepository<BrandMaster, Long> {

	boolean existsByBrandName(String brandName);
}
