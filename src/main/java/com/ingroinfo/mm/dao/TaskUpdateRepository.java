package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.Complaints;

@Repository
public interface TaskUpdateRepository extends JpaRepository<Complaints, Long> {

	Complaints findByComplNo(String complNo);

	@Query("from Complaints c where c.department=:department and c.complStatus=:complSts and c.esclatedFromId=:esclatedFromId")
	List<Complaints> getComplainByDeptComplStsUserId(String department,String complSts,String esclatedFromId);
	
	@Query("from Complaints c where c.department=:department and c.complStatus=:complSts")
	List<Complaints> getComplainByDeptComplSts(String department,String complSts);

	List<Complaints> findByComplStatus(String complStatus);
}
