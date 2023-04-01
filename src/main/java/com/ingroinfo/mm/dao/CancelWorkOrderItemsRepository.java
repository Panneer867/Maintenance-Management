package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.CancelWorkOrderItems;

@Repository
public interface CancelWorkOrderItemsRepository extends JpaRepository<CancelWorkOrderItems, Long> {

}
