package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.SpareEquipment;

@Repository
public interface SpareEquipmentRepository extends JpaRepository<SpareEquipment, Long> {

}
