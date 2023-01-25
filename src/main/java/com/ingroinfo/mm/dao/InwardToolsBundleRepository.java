package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.InwardToolsBundle;

@Repository
public interface InwardToolsBundleRepository extends JpaRepository<InwardToolsBundle, Long> {

	InwardToolsBundle findBybundleId(Long itemId);

	List<InwardToolsBundle> findByInwardTools(InwardTools newInwardTools);


}
