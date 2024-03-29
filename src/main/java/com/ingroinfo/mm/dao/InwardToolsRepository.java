package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardTools;

@Repository
public interface InwardToolsRepository extends JpaRepository<InwardTools, Long> {

	InwardTools findByToolId(Long id);

}
