package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.DmaWard;

@Repository
public interface DmaWardRepository extends JpaRepository<DmaWard, Long> {

}
