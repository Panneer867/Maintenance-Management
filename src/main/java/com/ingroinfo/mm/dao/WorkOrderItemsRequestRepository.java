package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrderItemsRequest;

@Repository
public interface WorkOrderItemsRequestRepository extends JpaRepository<WorkOrderItemsRequest, Long> {

	WorkOrderItemsRequest findByItemId(String itemId);

	List<WorkOrderItemsRequest> findByWorkOrderNo(Long workOrderNo);

	WorkOrderItemsRequest findByWorkOrderNoAndItemId(Long workOrderNo, String itemId);

}
