package com.ingroinfo.mm.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.WapWorkOrderLabours;

@Repository
public interface WapWorkOrderLaboursRepository extends JpaRepository<WapWorkOrderLabours, Long> {

	List<WapWorkOrderLabours> getByComplNoAndIndentNo(String complNo, String indentNo);

	@Transactional
    @Modifying
    @Query("DELETE FROM WapWorkOrderLabours l WHERE l.complNo = :complNo")
	void deleteByComplNo(String complNo);

}
