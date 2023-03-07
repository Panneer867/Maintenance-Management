package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.TempStockReturn;

@Repository
public interface TempStockReturnRepository extends JpaRepository<TempStockReturn, Long> {

	TempStockReturn findByWorkOrderNoAndItemId(Long workOrderNo, String itemId);

	List<TempStockReturn> findByUsername(String username);

}
