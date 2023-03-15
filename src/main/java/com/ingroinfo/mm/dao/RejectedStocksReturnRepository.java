package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.RejectedStocksReturn;

@Repository
public interface RejectedStocksReturnRepository extends JpaRepository<RejectedStocksReturn, Long> {


}
