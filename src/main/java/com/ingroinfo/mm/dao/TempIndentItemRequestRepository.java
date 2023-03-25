package com.ingroinfo.mm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempIndentItemRequest;

@Repository
public interface TempIndentItemRequestRepository extends JpaRepository<TempIndentItemRequest, Long> {
	
	@Query("from TempIndentItemRequest as m where m.indentNo=:indentNo and m.complNo =:complNo")
	List<TempIndentItemRequest> findListOfAddedMateials(String indentNo, String complNo);

	List<TempIndentItemRequest> findByComplNo(String complNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempIndentItemRequest m WHERE m.complNo = :complNo")
	void deleteAddedByComplNo(String complNo);

	boolean existsByItemId(String itemId);

}
