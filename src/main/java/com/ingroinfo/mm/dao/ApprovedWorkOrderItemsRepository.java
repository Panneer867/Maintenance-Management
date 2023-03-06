package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ApprovedWorkOrderItems;

@Repository
public interface ApprovedWorkOrderItemsRepository extends JpaRepository<ApprovedWorkOrderItems, Long> {

	List<ApprovedWorkOrderItems> findByWorkOrderNo(Long workOrderNo);

	ApprovedWorkOrderItems findByItemIdAndWorkOrderNo(String itemId, Long workOrderNo);

}
