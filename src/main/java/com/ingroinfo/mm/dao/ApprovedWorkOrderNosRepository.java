package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ApprovedWorkOrderNos;

@Repository
public interface ApprovedWorkOrderNosRepository extends JpaRepository<ApprovedWorkOrderNos, Long> {

	ApprovedWorkOrderNos findByWorkOrderNo(Long workOrderNo);

}
