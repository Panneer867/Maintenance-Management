package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardMaterialTempBundle;

@Repository
public interface InwardMaterialTempBundleRepository extends JpaRepository<InwardMaterialTempBundle, Long> {

	List<InwardMaterialTempBundle> findByUsername(String username);

}
