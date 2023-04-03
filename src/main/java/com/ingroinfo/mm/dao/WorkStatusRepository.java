package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkStatus;

@Repository
public interface WorkStatusRepository extends JpaRepository<WorkStatus, Long> {

	boolean existsByWorkStatus(String workStatus);

}
