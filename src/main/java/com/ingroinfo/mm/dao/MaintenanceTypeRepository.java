package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.MaintenanceType;

@Repository
public interface MaintenanceTypeRepository extends JpaRepository<MaintenanceType, Long> {

	boolean existsByMaintenTypeStatus(String maintenTypeStatus);


}
