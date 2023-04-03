package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ServiceArea;

@Repository
public interface ServiceAreaRepository extends JpaRepository<ServiceArea, Long> {

	boolean existsByServiceArea(String serviceArea);

}
