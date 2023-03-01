package com.ingroinfo.mm.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrders;

@Repository
public interface WorkOrdersRepository extends JpaRepository<WorkOrders, Long> {

	List<WorkOrders> findByWorkOrderId(Long workOrderId);

	Optional<WorkOrders> findByItemIdAndWorkOrderId(Long itemId, Long workOrderId);

	WorkOrders findByItemId(Long itemId);

}
