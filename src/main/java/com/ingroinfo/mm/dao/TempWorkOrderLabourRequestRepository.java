package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;

@Repository
public interface TempWorkOrderLabourRequestRepository extends JpaRepository<TempWorkOrderLabourRequest, Long> {

	@Query("from TempWorkOrderLabourRequest as l where l.indentNo=:indentNo and l.complNo =:complNo")
	List<TempWorkOrderLabourRequest> findByComplNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempWorkOrderLabourRequest l WHERE l.complNo = :complNo")
	void deleteAllDataByComplNo(String complNo);

}
