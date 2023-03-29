package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrderApprovedItems;

@Repository
public interface WorkOrderApprovedItemsRepository extends JpaRepository<WorkOrderApprovedItems, Long> {

}
