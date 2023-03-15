package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ItemMaster;

@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMaster, Long> {
		
	@Query("from ItemMaster as i where i.category.catid =:categoryId")
	List<ItemMaster> findItemsByCategoryId(@Param("categoryId") Long categoryId);

	List<ItemMaster> findByStockType(String stockType);
}
