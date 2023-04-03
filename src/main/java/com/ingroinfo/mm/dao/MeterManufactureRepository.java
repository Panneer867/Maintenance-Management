package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.MeterManufacture;

@Repository
public interface MeterManufactureRepository extends JpaRepository<MeterManufacture, Long> {

	boolean existsByMeterManufactureAndMeterType(String meterManufacture, String meterType);

}
