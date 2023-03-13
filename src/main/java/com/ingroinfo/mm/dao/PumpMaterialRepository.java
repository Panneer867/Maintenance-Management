package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ingroinfo.mm.entity.PumpMaterial;

public interface PumpMaterialRepository extends JpaRepository<PumpMaterial, Long> {

	@Query("from PumpMaterial as p where p.indentType=:indentType and p.indentNo=:indentNo and p.complNo =:complNo")
	List<PumpMaterial> findByIndentTypeAndIndentNoAndComplNo(String indentType, String indentNo, String complNo);

}
