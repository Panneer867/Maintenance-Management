package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.PumpMaintenanceInspection;

@Repository
public interface PumpMaintenanceInspectionRepository extends JpaRepository<PumpMaintenanceInspection, Long> {

}
