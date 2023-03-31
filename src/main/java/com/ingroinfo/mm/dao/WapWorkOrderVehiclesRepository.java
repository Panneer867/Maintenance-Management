package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WapWorkOrderVehicles;

@Repository
public interface WapWorkOrderVehiclesRepository extends JpaRepository<WapWorkOrderVehicles, Long> {

}
