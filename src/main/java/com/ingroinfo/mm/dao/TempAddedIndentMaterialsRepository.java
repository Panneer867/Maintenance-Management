package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ingroinfo.mm.entity.TempAddedIndentMaterials;

@Repository
public interface TempAddedIndentMaterialsRepository extends JpaRepository<TempAddedIndentMaterials, Long> {

	List<TempAddedIndentMaterials> getByIndentNoAndComplNo(String indentNo, String complNo);

	boolean existsByItemIdAndComplNo(String itemId,String complNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempAddedIndentMaterials i WHERE i.complNo = :complNo")
	void deleteByComplNo(String complNo);

}
