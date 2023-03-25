package com.ingroinfo.mm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;

@Repository
public interface TempWorkOrderItemRequestRepository extends JpaRepository<TempWorkOrderItemRequest, Long> {

	@Query("from TempWorkOrderItemRequest i where i.complNo=:complNo and i.indentNo=:indentNo")
	List<TempWorkOrderItemRequest> findByComplNo(String complNo, String indentNo);
	
	@Transactional
    @Modifying
    @Query("DELETE FROM TempWorkOrderItemRequest i WHERE i.complNo = :complNo")
	void deleteAllDataByComplNo(String complNo);

}
