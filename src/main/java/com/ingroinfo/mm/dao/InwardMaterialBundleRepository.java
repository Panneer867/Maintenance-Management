package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.InwardMaterial;
import com.ingroinfo.mm.entity.InwardMaterialBundle;

@Repository
public interface InwardMaterialBundleRepository extends JpaRepository<InwardMaterialBundle, Long> {

	InwardMaterialBundle findBybundleId(Long itemId);

	List<InwardMaterialBundle> findByInwardMaterial(InwardMaterial newInwardMaterial);


}
