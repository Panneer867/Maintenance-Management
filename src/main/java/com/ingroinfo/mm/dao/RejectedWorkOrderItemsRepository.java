package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.RejectedWorkOrderItems;

@Repository
public interface RejectedWorkOrderItemsRepository extends JpaRepository<RejectedWorkOrderItems, Long> {

}
