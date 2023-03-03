package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.ItemMaster;

@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMaster, Long> {

	@Query("select max(itemId) from ItemMaster")
	String getMaxItemId();

	List<ItemMaster> findByCategory(String category);
}
