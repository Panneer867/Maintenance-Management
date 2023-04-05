package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.StockOrders;

@Repository
public interface StockOrdersRepository extends JpaRepository<StockOrders, Long> {

	StockOrders findByStockOrderNo(Long workOrderNo);

	

}
