package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.RejectedStockReturns;

@Repository
public interface RejectedStockReturnsRepository extends JpaRepository<RejectedStockReturns, Long> {


}