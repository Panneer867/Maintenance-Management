package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardSpare;

@Repository
public interface InwardSpareRepository extends JpaRepository<InwardSpare, Long> {

	InwardSpare findByAllSparesId(Long allSparesId);

}
