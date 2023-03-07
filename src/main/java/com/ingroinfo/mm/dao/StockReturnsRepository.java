package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.StockReturns;

@Repository
public interface StockReturnsRepository extends JpaRepository<StockReturns, Long> {

}
