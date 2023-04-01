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

	List<TempWorkOrderVehicleRequest> getByComplNoAndIndentNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempWorkOrderVehicleRequest v WHERE v.complNo = :complNo")
	void deleteByComplNo(String complNo);

}
