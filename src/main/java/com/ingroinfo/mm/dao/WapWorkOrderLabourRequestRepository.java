package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WapWorkOrderLabourRequest;

@Repository
public interface WapWorkOrderLabourRequestRepository extends JpaRepository<WapWorkOrderLabourRequest, Long> {

	List<WapWorkOrderLabourRequest> getByComplNoAndIndentNo(String complNo, String indentNo);

}
