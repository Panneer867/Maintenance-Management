package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.PressureType;

@Repository
public interface PressureTypeRepository extends JpaRepository<PressureType, Long> {

	boolean existsByPressureType(String pressureType);

}
