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
			
	List<TempIndentItemRequest> getByComplNoAndIndentNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempIndentItemRequest m WHERE m.complNo = :complNo")
	void deleteAllByComplNo(String complNo);


}
