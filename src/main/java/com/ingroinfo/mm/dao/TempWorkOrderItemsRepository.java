package com.ingroinfo.mm.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempWorkOrderItems;

@Repository
public interface TempWorkOrderItemsRepository extends JpaRepository<TempWorkOrderItems, Long> {

	List<TempWorkOrderItems> findByWorkOrderNo(Long workOrderNo);

	Optional<TempWorkOrderItems> findByItemIdAndWorkOrderNo(Long itemId, Long workOrderNo);

	TempWorkOrderItems findByItemId(Long itemId);

}
