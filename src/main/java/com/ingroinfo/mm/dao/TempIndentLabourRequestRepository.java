package com.ingroinfo.mm.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempIndentLabourRequest;

@Repository
public interface TempIndentLabourRequestRepository extends JpaRepository<TempIndentLabourRequest, Long> {

	List<TempIndentLabourRequest> getByComplNoAndIndentNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempIndentLabourRequest l WHERE l.complNo = :complNo")
	void deleteAllByComplNo(String complNo);

}
