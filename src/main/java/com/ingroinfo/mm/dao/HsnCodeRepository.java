package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.HsnCode;

@Repository
public interface HsnCodeRepository extends JpaRepository<HsnCode, Long> {

	HsnCode findByhsnCodeId(Long id);

	HsnCode findByCategory(String category);

}
