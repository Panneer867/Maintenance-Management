package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempWorkOrderLabourRequest;

@Repository
public interface TempWorkOrderLabourRequestRepository extends JpaRepository<TempWorkOrderLabourRequest, Long> {

	List<TempWorkOrderLabourRequest> getByComplNoAndIndentNo(String complNo, String indentNo);

}
