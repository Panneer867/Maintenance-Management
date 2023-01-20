package com.ingroinfo.mm.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.GenerateWorkOrder;


@Repository
public interface GenerateWorkOrderRepository extends JpaRepository<GenerateWorkOrder,Long> {

	GenerateWorkOrder findByIndentNo(String indentNo);


	
}
