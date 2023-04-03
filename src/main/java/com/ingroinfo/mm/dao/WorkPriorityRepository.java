package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WorkPriority;

@Repository
public interface WorkPriorityRepository extends JpaRepository<WorkPriority, Long> {

	boolean existsByWorkPriority(String workPriority);

}
