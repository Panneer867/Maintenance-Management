package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ingroinfo.mm.entity.TempAddedReturnItems;

@Repository
public interface TempAddedReturnItemsRepository extends JpaRepository<TempAddedReturnItems, Long> {

	List<TempAddedReturnItems> getByIndentNoAndComplNo(String indentNo, String complNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM TempAddedReturnItems i WHERE i.complNo = :complNo")
	void deleteAllByComplNo(String complNo);

}
