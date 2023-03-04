package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardApprovedTools;

@Repository
public interface InwardApprovedToolsRepository extends JpaRepository<InwardApprovedTools, Long> {

	InwardApprovedTools findByApprovedToolId(Long id);

	InwardApprovedTools findByItemId(String itemId);

	InwardApprovedTools findByItemIdAndStockType(String itemId, String string);


}
