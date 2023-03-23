package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.DistributionLocation;

@Repository
public interface DistributionLocationRepository extends JpaRepository<DistributionLocation, Long> {

	boolean existsByDistlocation(String distlocation);

	List<DistributionLocation> getBySubDivision(String subDivision);

}
