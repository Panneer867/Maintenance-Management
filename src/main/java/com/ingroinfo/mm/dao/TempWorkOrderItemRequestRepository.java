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

	@Query("from TempWorkOrderItemRequest as m where m.indentNo=:indentNo and m.complNo =:complNo")
	List<TempWorkOrderItemRequest> findListOfAddedMateials(String indentNo, String complNo);

	List<TempWorkOrderItemRequest> findByComplNo(String complNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempWorkOrderItemRequest m WHERE m.complNo = :complNo")
	void deleteAddedByComplNo(String complNo);
}
