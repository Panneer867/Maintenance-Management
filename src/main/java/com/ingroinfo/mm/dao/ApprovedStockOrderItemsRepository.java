package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ApprovedStockOrderItems;

@Repository
public interface ApprovedStockOrderItemsRepository extends JpaRepository<ApprovedStockOrderItems, Long> {

	List<ApprovedStockOrderItems> findByStockOrderNo(Long workOrderNo);

	ApprovedStockOrderItems findByItemIdAndStockOrderNo(String itemId, Long workOrderNo);

}
