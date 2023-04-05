package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrderApprovedLabours;

@Repository
public interface WorkOrderApprovedLaboursRepository extends JpaRepository<WorkOrderApprovedLabours, Long> {

	List<WorkOrderApprovedLabours> getByWorkOrder(String workOrder);

}
