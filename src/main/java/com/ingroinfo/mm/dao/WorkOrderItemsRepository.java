package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrderItems;

@Repository
public interface WorkOrderItemsRepository extends JpaRepository<WorkOrderItems, Long> {

	 List<WorkOrderItems> findByWorkOrderNo(Long workOrderNo);

}
