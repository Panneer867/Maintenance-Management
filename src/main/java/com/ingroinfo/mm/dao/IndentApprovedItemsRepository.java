package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.IndentApprovedItems;

@Repository
public interface IndentApprovedItemsRepository extends JpaRepository<IndentApprovedItems, Long> {

	List<IndentApprovedItems> getByComplNoAndIndentNo(String complNo, String indentNo);

	IndentApprovedItems findByComplNoAndItemId(String complNo, String itemId);

	List<IndentApprovedItems> getByApprovedSts(String approvedSts);

}
