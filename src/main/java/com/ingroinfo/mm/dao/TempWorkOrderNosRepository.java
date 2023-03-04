package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempWorkOrderNos;

@Repository
public interface TempWorkOrderNosRepository extends JpaRepository<TempWorkOrderNos, Long> {

	TempWorkOrderNos findByWorkOrderNo(Long workOrderNo);

	

}
