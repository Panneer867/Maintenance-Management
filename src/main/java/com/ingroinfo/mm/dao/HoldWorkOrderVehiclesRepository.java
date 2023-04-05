package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingroinfo.mm.entity.HoldWorkOrderVehicles;

public interface HoldWorkOrderVehiclesRepository extends JpaRepository<HoldWorkOrderVehicles, Long> {

	List<HoldWorkOrderVehicles> getByWorkOrder(String workOrder);

}
