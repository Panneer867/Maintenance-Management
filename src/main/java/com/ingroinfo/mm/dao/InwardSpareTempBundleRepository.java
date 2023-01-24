package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardSpareTempBundle;

@Repository
public interface InwardSpareTempBundleRepository extends JpaRepository<InwardSpareTempBundle, Long> {

	List<InwardSpareTempBundle> findByUsername(String username);

}
