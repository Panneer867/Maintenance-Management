package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.PumpMaintenanceUpdated;

@Repository
public interface PumpMaintenanceUpdatedRepository extends JpaRepository<PumpMaintenanceUpdated, Long> {

	PumpMaintenanceUpdated getByWorkOrderNo(String workOrderNo);

}
