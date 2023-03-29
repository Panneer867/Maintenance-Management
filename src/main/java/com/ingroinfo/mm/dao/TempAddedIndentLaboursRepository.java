package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ingroinfo.mm.entity.TempAddedIndentLabours;

@Repository
public interface TempAddedIndentLaboursRepository extends JpaRepository<TempAddedIndentLabours, Long> {

	List<TempAddedIndentLabours> getByIndentNoAndComplNo(String indentNo, String complNo);

	boolean existsByEmpCategory(String empCategory);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempAddedIndentLabours l WHERE l.complNo = :complNo")
	void deleteByComplNo(String complNo);

}
