package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.CancelWorkOrderVehicles;

@Repository
public interface CancelWorkOrderVehiclesRepository extends JpaRepository<CancelWorkOrderVehicles, Long> {

	List<CancelWorkOrderVehicles> getByWorkOrder(String workOrder);

}
