package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>
{

	Branch findByEmail(String email);

	Branch findByBranchId(Long id);

      Branch findByBranchName(String branchName);
}
