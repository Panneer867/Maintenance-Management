package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardApprovedSpares;

@Repository
public interface InwardApprovedSparesRepository extends JpaRepository<InwardApprovedSpares, Long> {

	InwardApprovedSpares findByApprovedSpareId(Long id);

	InwardApprovedSpares findByItemId(String itemId);

	InwardApprovedSpares findByItemIdAndStockType(String itemId, String string);


}
