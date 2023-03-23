package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.DistributionSchedule;

@Repository
public interface DistributionScheduleRepository extends JpaRepository<DistributionSchedule, Long> {

	boolean existsByDistSchedule(String distSchedule);

}
