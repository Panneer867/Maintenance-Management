package com.ingroinfo.mm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.IndentLabourRequest;

@Repository
public interface IndentLabourRequestRepository extends JpaRepository<IndentLabourRequest, Long> {

	@Query("from IndentLabourRequest l where l.complNo=:complNo and l.indentNo=:indentNo")
	List<IndentLabourRequest> findByComplNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM IndentLabourRequest l WHERE l.complNo = :complNo")
	void deleteAllByComplNo(String complNo);

}
