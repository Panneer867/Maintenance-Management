package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.StoreBranch;

@Repository
public interface StoreBranchRepository extends JpaRepository<StoreBranch, Long> {

	@Query("select max(storeBranchId) from StoreBranch")
	String getMaxStoreBranchId();
}
