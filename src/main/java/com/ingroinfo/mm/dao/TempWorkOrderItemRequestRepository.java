package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;

@Repository
public interface TempWorkOrderItemRequestRepository extends JpaRepository<TempWorkOrderItemRequest, Long> {

	List<TempWorkOrderItemRequest> getByComplNoAndIndentNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempWorkOrderItemRequest m WHERE m.complNo = :complNo")
	void deleteByComplNo(String complNo);

}
