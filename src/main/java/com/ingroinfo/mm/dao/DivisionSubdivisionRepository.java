package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.DivisionSubdivision;

@Repository
public interface DivisionSubdivisionRepository extends JpaRepository<DivisionSubdivision, Long> {

}
