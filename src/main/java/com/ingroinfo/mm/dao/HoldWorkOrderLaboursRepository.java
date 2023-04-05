package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.HoldWorkOrderLabours;

@Repository
public interface HoldWorkOrderLaboursRepository extends JpaRepository<HoldWorkOrderLabours, Long> {

	List<HoldWorkOrderLabours> getByWorkOrder(String workOrder);

}
