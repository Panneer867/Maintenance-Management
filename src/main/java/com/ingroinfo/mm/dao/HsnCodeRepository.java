package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.HsnCode;

@Repository
public interface HsnCodeRepository extends JpaRepository<HsnCode, Long> {

	HsnCode findByhsnCodeId(Long id);
	
	@Query("from HsnCode as h where h.category.catid =:catid")
	HsnCode findHsnCodeByCategory(@Param("catid") Long catid);
}
