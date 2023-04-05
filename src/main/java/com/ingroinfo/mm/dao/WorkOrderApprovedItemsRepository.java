package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrderApprovedItems;

@Repository
public interface WorkOrderApprovedItemsRepository extends JpaRepository<WorkOrderApprovedItems, Long> {

	List<WorkOrderApprovedItems> getByWorkOrder(String workOrder);

	List<WorkOrderApprovedItems> getByComplNoAndIndentNo(String complNo, String indentNo);

}
