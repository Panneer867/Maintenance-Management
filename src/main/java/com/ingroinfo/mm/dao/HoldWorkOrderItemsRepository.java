package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.HoldWorkOrderItems;

@Repository
public interface HoldWorkOrderItemsRepository extends JpaRepository<HoldWorkOrderItems, Long> {

}
