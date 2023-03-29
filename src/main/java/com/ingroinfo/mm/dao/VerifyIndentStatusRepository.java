package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.VerifyIndentStatus;

@Repository
public interface VerifyIndentStatusRepository extends JpaRepository<VerifyIndentStatus, String> {

	@Query("from VerifyIndentStatus vi where vi.itemApproved='Y' and vi.laborApproved='Y' and vi.vehicleApproved='Y'")
	List<VerifyIndentStatus> getAllAprovedIndents();
	
}
