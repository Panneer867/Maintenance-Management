package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.MaintenanceActivities;

@Repository
public interface MaintenanceActivitiesRepository extends JpaRepository<MaintenanceActivities, Long> {

	boolean existsByMaintenActivity(String maintenActivity);

}
