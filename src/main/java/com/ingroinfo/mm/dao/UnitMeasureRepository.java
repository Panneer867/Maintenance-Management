package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.UnitMeasure;

@Repository
public interface UnitMeasureRepository extends JpaRepository<UnitMeasure, Long> {

}
