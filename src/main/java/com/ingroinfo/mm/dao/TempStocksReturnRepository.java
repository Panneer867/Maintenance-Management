package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TempStocksReturn;

@Repository
public interface TempStocksReturnRepository extends JpaRepository<TempStocksReturn, Long> {

	TempStocksReturn findByWorkOrderNoAndItemId(Long workOrderNo, String itemId);

	List<TempStocksReturn> findByUsername(String username);

}
