package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempWorkOrderItemRequest;

@Repository
public interface TempWorkOrderItemRequestRepository extends JpaRepository<TempWorkOrderItemRequest, Long> {

	List<TempWorkOrderItemRequest> getByComplNoAndIndentNo(String complNo, String indentNo);


}
