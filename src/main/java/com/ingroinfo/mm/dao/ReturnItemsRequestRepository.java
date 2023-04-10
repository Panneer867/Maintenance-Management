package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ReturnItemsRequest;

@Repository
public interface ReturnItemsRequestRepository extends JpaRepository<ReturnItemsRequest, Long> {

}
