package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrderVehicleRequest;

@Repository
public interface WorkOrderVehicleRequestRepository extends JpaRepository<WorkOrderVehicleRequest, Long> {

}
