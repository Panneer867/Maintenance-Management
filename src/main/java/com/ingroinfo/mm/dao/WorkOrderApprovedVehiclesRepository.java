package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrderApprovedVehicles;

@Repository
public interface WorkOrderApprovedVehiclesRepository extends JpaRepository<WorkOrderApprovedVehicles, Long> {

	List<WorkOrderApprovedVehicles> findByComplNoAndIndentNo(String complNo, String indentNo);

}
