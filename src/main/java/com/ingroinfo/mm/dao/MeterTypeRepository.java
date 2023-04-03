package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.MeterType;

@Repository
public interface MeterTypeRepository extends JpaRepository<MeterType, Long> {

	boolean existsByMeterType(String meterType);

}
