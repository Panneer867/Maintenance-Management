package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkOrderLabourRequest;

@Repository
public interface WorkOrderLabourRequestRepository extends JpaRepository<WorkOrderLabourRequest, Long> {

	List<WorkOrderLabourRequest> findByComplNoAndIndentNo(String complNo, String indentNo);

}
