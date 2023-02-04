package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardSpares;

@Repository
public interface InwardSparesRepository extends JpaRepository<InwardSpares, Long> {

	InwardSpares findBySpareId(Long id);

}
