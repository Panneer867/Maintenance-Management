package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.StockOrderRemovedItems;

@Repository
public interface WorkOrderRemovedItemsRepository extends JpaRepository<StockOrderRemovedItems, Long> {


}
