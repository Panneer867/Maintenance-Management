package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardTempTools;

@Repository
public interface InwardTempToolsRepository extends JpaRepository<InwardTempTools, Long> {

	List<InwardTempTools> findByUsername(String username);

}
