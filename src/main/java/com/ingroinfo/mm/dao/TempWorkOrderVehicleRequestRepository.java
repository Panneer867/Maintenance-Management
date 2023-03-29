package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempWorkOrderVehicleRequest;

@Repository
public interface TempWorkOrderVehicleRequestRepository extends JpaRepository<TempWorkOrderVehicleRequest, Long> {

	List<TempWorkOrderVehicleRequest> getByComplNoAndIndentNo(String complNo, String indentNo);

}
