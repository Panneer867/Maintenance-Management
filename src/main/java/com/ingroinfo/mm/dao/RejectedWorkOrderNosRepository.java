package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.RejectedWorkOrders;

@Repository
public interface RejectedWorkOrderNosRepository extends JpaRepository<RejectedWorkOrders, Long> {

}
