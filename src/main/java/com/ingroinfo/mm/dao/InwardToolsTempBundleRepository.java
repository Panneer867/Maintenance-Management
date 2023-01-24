package com.ingroinfo.mm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardToolsTempBundle;

@Repository
public interface InwardToolsTempBundleRepository extends JpaRepository<InwardToolsTempBundle, Long> {

	List<InwardToolsTempBundle> findByUsername(String username);

}
