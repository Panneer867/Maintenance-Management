package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrders;

@Repository
public interface WorkOrdersRepository extends JpaRepository<WorkOrders, Long> {

	

}
