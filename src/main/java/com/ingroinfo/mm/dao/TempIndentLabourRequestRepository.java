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

	@Query("from TempIndentLabourRequest as l where l.indentNo=:indentNo and l.complNo =:complNo")
	List<TempIndentLabourRequest> findListOfAddedLabors(String indentNo, String complNo);

	List<TempIndentLabourRequest> findByComplNo(String complNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempIndentLabourRequest l WHERE l.complNo = :complNo")
	void deleteAddedByComplNo(String complNo);

	boolean existsByEmpCategory(String empCategory);
}
