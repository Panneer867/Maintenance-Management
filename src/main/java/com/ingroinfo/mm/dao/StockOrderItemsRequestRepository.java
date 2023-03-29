package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.StockOrderItemsRequest;

@Repository
public interface StockOrderItemsRequestRepository extends JpaRepository<StockOrderItemsRequest, Long> {

	StockOrderItemsRequest findByItemId(String itemId);

	List<StockOrderItemsRequest> findByStockOrderNo(Long workOrderNo);

	StockOrderItemsRequest findByStockOrderNoAndItemId(Long workOrderNo, String itemId);

	List<StockOrderItemsRequest> findByComplNoAndIndentNo(String complNo, String indentNo);
}
