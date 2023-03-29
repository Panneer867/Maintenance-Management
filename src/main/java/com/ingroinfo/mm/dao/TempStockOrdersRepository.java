package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempStockOrders;

@Repository
public interface TempStockOrdersRepository extends JpaRepository<TempStockOrders, Long> {

	TempStockOrders findByStockOrderNo(Long workOrderNo);

	

}
