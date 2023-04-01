package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.CancelWorkOrderLabours;

@Repository
public interface CancelWorkOrderLaboursRepository extends JpaRepository<CancelWorkOrderLabours, Long> {

}
