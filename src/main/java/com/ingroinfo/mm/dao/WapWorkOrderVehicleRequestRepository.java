package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WapWorkOrderVehicleRequest;

@Repository
public interface WapWorkOrderVehicleRequestRepository extends JpaRepository<WapWorkOrderVehicleRequest, Long> {

	List<WapWorkOrderVehicleRequest> getByComplNoAndIndentNo(String complNo, String indentNo);

}
