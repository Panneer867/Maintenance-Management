package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ingroinfo.mm.entity.WapWorkOrderItems;

@Repository
public interface WapWorkOrderItemsRepository extends JpaRepository<WapWorkOrderItems, Long> {

	List<WapWorkOrderItems> getByComplNoAndIndentNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM WapWorkOrderItems i WHERE i.complNo = :complNo")
	void deleteByComplNo(String complNo);

}
