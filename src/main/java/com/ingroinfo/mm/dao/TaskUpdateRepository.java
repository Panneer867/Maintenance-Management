package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.Complaints;

@Repository
public interface TaskUpdateRepository extends JpaRepository<Complaints, Long> {

	Complaints findByComplNo(String complNo);

}
