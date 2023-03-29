package com.ingroinfo.mm.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempIndentVehicleRequest;

@Repository
public interface TempIndentVehicleRequestRepository extends JpaRepository<TempIndentVehicleRequest, Long> {

	List<TempIndentVehicleRequest> getByComplNoAndIndentNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempIndentVehicleRequest v WHERE v.complNo = :complNo")
	void deleteAllByComplNo(String complNo);

}
