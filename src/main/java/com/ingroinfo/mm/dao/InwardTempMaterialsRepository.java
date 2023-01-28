package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardTempMaterials;

@Repository
public interface InwardTempMaterialsRepository extends JpaRepository<InwardTempMaterials, Long> {

	List<InwardTempMaterials> findByUsername(String username);

}
