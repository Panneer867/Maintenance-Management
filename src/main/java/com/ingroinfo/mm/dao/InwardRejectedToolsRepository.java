package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardRejectedTools;

@Repository
public interface InwardRejectedToolsRepository extends JpaRepository<InwardRejectedTools, Long> {


}
