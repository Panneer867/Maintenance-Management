package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardItemTemp;

@Repository
public interface InwardTempBundleItemRepository extends JpaRepository<InwardItemTemp, Long> {

	List<InwardItemTemp> findByUsername(String username);

}
