package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardMaterialBundles;

@Repository
public interface InwardMaterialBundlesRepository extends JpaRepository<InwardMaterialBundles, Long> {

	InwardMaterialBundles findByBundleId(Long allMaterialsId);

}
