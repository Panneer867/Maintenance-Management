package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WapWorkOrderLabours;

@Repository
public interface WapWorkOrderLaboursRepository extends JpaRepository<WapWorkOrderLabours, Long> {

}
