package com.ingroinfo.mm.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempListItems;

@Repository
public interface TempListItemsRepository extends JpaRepository<TempListItems, Long> {

	List<TempListItems> findByStockOrderNo(Long workOrderNo);

	Optional<TempListItems> findByItemIdAndStockOrderNo(String itemId, Long workOrderNo);

	TempListItems findByItemId(String itemId);

}
