package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.StockOrderItems;

@Repository
public interface StockOrderItemsRepository extends JpaRepository<StockOrderItems, Long> {

	 List<StockOrderItems> findByStockOrderNo(Long workOrderNo);

	 StockOrderItems findByItemIdAndStockOrderNo(String itemId, Long stockOrderNo);

}
