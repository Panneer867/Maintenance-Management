package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.DivisionSubdivision;

@Repository
public interface DivisionSubdivisionRepository extends JpaRepository<DivisionSubdivision, Long> {

	@Query("SELECT DISTINCT division FROM DivisionSubdivision")
	List<String> getDistinctDivision();

	List<DivisionSubdivision> findByDivision(String division);

	boolean existsBySubdivision(String subdivision);

	boolean existsByServiceStation(String serviceStation);

	

}
