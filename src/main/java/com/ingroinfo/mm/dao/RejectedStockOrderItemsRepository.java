package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.RejectedStockOrderItems;

@Repository
public interface RejectedStockOrderItemsRepository extends JpaRepository<RejectedStockOrderItems, Long> {

}
