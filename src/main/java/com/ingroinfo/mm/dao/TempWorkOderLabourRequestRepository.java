package com.ingroinfo.mm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempWorkOderLabourRequest;

@Repository
public interface TempWorkOderLabourRequestRepository extends JpaRepository<TempWorkOderLabourRequest, Long> {

	@Query("from TempWorkOderLabourRequest as l where l.indentNo=:indentNo and l.complNo =:complNo")
	List<TempWorkOderLabourRequest> findListOfAddedLabors(String indentNo, String complNo);

	List<TempWorkOderLabourRequest> findByComplNo(String complNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempWorkOderLabourRequest l WHERE l.complNo = :complNo")
	void deleteAddedByComplNo(String complNo);

}
