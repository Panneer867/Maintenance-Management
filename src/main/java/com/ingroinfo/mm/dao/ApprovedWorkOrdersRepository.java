package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ApprovedWorkOrders;

@Repository
public interface ApprovedWorkOrdersRepository extends JpaRepository<ApprovedWorkOrders, Long> {

	ApprovedWorkOrders findByWorkOrderNo(Long workOrderNo);

}
