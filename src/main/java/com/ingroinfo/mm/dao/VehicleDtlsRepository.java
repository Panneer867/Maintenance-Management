package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.VehicleDtls;

@Repository
public interface VehicleDtlsRepository extends JpaRepository<VehicleDtls, Long> {

	@Query("select max(vehicleId) from VehicleDtls")
	String getMaxVehicleId();
}
