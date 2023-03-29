package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ApprovedStockOrders;

@Repository
public interface ApprovedStockOrdersRepository extends JpaRepository<ApprovedStockOrders, Long> {

	ApprovedStockOrders findByStockOrderNo(Long workOrderNo);

}
