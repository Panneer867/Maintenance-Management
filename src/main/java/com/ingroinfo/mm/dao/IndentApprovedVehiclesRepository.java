package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.IndentApprovedVehicles;

@Repository
public interface IndentApprovedVehiclesRepository extends JpaRepository<IndentApprovedVehicles, Long> {

	List<IndentApprovedVehicles> getByComplNoAndIndentNo(String complNo, String indentNo);

	List<IndentApprovedVehicles> getByApprovedSts(String approvedSts);

}
