package com.ingroinfo.mm.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrderItemsRequest;

@Repository
public interface WorkOrderItemsRequestRepository extends JpaRepository<WorkOrderItemsRequest, Long> {

	List<WorkOrderItemsRequest> findByWorkOrderId(Long workOrderId);

	Optional<WorkOrderItemsRequest> findByItemIdAndWorkOrderId(Long itemId, Long workOrderId);

	WorkOrderItemsRequest findByItemId(Long itemId);

}
