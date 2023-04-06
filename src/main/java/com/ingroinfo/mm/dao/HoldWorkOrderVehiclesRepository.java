package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.HoldWorkOrderVehicles;

@Repository
public interface HoldWorkOrderVehiclesRepository extends JpaRepository<HoldWorkOrderVehicles, Long> {

	List<HoldWorkOrderVehicles> getByWorkOrder(String workOrder);

}
