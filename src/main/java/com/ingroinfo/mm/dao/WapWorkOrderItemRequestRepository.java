package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WapWorkOrderItemRequest;

@Repository
public interface WapWorkOrderItemRequestRepository extends JpaRepository<WapWorkOrderItemRequest, Long> {

	List<WapWorkOrderItemRequest> getByComplNoAndIndentNo(String complNo, String indentNo);

}
