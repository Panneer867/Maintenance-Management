package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.SaftyPrecautions;

@Repository
public interface SaftyPrecautionRepository extends JpaRepository<SaftyPrecautions, Long> {

}
