package com.ingroinfo.mm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.IndentItemRequest;

@Repository
public interface IndentItemRequestRepository extends JpaRepository<IndentItemRequest, Long> {

	@Query("from IndentItemRequest i where i.complNo=:complNo and i.indentNo=:indentNo")
	List<IndentItemRequest> findByComplNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM IndentItemRequest i WHERE i.complNo = :complNo")
	void deleteAllByComplNo(String complNo);

}
