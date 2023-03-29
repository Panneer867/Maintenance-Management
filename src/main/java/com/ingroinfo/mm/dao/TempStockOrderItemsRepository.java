package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempStockOrderItems;

@Repository
public interface TempStockOrderItemsRepository extends JpaRepository<TempStockOrderItems, Long> {

	 List<TempStockOrderItems> findByStockOrderNo(Long workOrderNo);

}
