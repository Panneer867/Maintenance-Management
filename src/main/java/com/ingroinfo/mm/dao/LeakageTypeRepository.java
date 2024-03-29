package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.LeakageType;

@Repository
public interface LeakageTypeRepository extends JpaRepository<LeakageType, Long> {

	boolean existsByLeakageType(String leakageType);

}
