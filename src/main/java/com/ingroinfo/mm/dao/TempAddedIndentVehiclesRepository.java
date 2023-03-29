package com.ingroinfo.mm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempAddedIndentVehicles;

@Repository
public interface TempAddedIndentVehiclesRepository extends JpaRepository<TempAddedIndentVehicles, Long> {

	List<TempAddedIndentVehicles> getByIndentNoAndComplNo(String indentNo, String complNo);

	boolean existsByVehicleNo(String vehicleNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempAddedIndentVehicles v WHERE v.complNo = :complNo")
	void deleteByComplNo(String complNo);

}
