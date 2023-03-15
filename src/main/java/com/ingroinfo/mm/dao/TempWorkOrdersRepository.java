package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempWorkOrders;

@Repository
public interface TempWorkOrdersRepository extends JpaRepository<TempWorkOrders, Long> {

	TempWorkOrders findByWorkOrderNo(Long workOrderNo);

	

}
