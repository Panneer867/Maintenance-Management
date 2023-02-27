package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardApprovedMaterials;

@Repository
public interface InwardApprovedMaterialsRepository extends JpaRepository<InwardApprovedMaterials, Long> {

	InwardApprovedMaterials findByApprovedMaterialId(Long id);

	List<InwardApprovedMaterials> findByItemId(Long itemId);


}
