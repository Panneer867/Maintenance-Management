package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardTempSpares;

@Repository
public interface InwardTempSparesRepository extends JpaRepository<InwardTempSpares, Long> {

	List<InwardTempSpares> findByUsername(String username);

}
