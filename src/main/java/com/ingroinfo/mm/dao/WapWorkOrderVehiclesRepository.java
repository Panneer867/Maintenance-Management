package com.ingroinfo.mm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WapWorkOrderVehicles;

@Repository
public interface WapWorkOrderVehiclesRepository extends JpaRepository<WapWorkOrderVehicles, Long> {

	List<WapWorkOrderVehicles> getByComplNoAndIndentNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM WapWorkOrderVehicles m WHERE m.complNo = :complNo")
	void deleteByComplNo(String complNo);

}
