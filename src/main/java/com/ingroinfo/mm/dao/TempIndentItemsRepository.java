package com.ingroinfo.mm.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempIndentItems;

@Repository
public interface TempIndentItemsRepository extends JpaRepository<TempIndentItems, Long> {

	List<TempIndentItems> findByWorkOrderNo(Long workOrderNo);

	Optional<TempIndentItems> findByItemIdAndWorkOrderNo(String itemId, Long workOrderNo);

	TempIndentItems findByItemId(String itemId);

}
