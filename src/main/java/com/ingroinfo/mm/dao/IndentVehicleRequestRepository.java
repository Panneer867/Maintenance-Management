package com.ingroinfo.mm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.IndentVehicleRequest;

@Repository
public interface IndentVehicleRequestRepository extends JpaRepository<IndentVehicleRequest, Long> {

	@Query("from IndentVehicleRequest v where v.complNo=:complNo and v.indentNo=:indentNo")
	List<IndentVehicleRequest> findByComplNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM IndentVehicleRequest v WHERE v.complNo = :complNo")
	void deleteAllByComplNo(String complNo);

}
