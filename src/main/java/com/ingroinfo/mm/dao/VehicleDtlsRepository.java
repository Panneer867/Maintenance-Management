package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.VehicleDtls;

@Repository
public interface VehicleDtlsRepository extends JpaRepository<VehicleDtls, Long> {

	@Query("SELECT DISTINCT vehicleType FROM VehicleDtls")
	List<String> getAllVehicleTypes();

	List<VehicleDtls> findByVehicleType(String vehicleType);

	boolean existsByVehicleNo(String vehicleNo);

	boolean existsByRcNumber(String rcNumber);

	boolean existsByInsurancNo(String insurancNo);

}
