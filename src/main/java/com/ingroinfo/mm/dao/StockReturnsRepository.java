package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.StocksReturn;

@Repository
public interface StockReturnsRepository extends JpaRepository<StocksReturn, Long> {

	StocksReturn findByRecordId(Long id);

}
