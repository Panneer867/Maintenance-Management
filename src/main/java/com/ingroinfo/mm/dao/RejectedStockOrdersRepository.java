package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.RejectedStockOrders;

@Repository
public interface RejectedStockOrdersRepository extends JpaRepository<RejectedStockOrders, Long> {

}
