package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrderItemsRequest;
import com.ingroinfo.mm.entity.WorkOrderRemovedItems;

@Repository
public interface WorkOrderRemovedItemsRepository extends JpaRepository<WorkOrderRemovedItems, Long> {


}
