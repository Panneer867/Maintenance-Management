package com.ingroinfo.mm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;

@Repository
public interface TempWorkOrderVehicleRequestRepository extends JpaRepository<TempWorkOrderVehicleRequest, Long> {

	@Query("from TempWorkOrderVehicleRequest as v where v.indentNo=:indentNo and v.complNo =:complNo")
	List<TempWorkOrderVehicleRequest> findListOfAddedVehicles(String indentNo, String complNo);

	List<TempWorkOrderVehicleRequest> findByComplNo(String complNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempWorkOrderVehicleRequest v WHERE v.complNo = :complNo")
	void deleteAddedByComplNo(String complNo);
}