package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.InwardMaterialBundles;
import com.ingroinfo.mm.entity.InwardMaterials;

@Repository
public interface InwardMaterialsRepository extends JpaRepository<InwardMaterials, Long> {

	InwardMaterials findByMaterialId(Long itemId);

	List<InwardMaterials> findByMaterialBundle(InwardMaterialBundles newInwardMaterial);


}
