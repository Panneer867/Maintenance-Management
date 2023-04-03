package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.MaintenancePerformance;

@Repository
public interface MaintenancePerformanceRepository extends JpaRepository<MaintenancePerformance, Long> {

	boolean existsByMaintenPerformType(String maintenPerformType);

}
