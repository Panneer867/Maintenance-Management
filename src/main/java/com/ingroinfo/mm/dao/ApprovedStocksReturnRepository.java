package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ApprovedStocksReturn;

@Repository
public interface ApprovedStocksReturnRepository extends JpaRepository<ApprovedStocksReturn, Long> {


}
