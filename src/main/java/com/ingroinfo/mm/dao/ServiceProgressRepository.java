package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingroinfo.mm.entity.ServiceProgress;

public interface ServiceProgressRepository extends JpaRepository<ServiceProgress, Long> {

	boolean existsBysevcProgress(String sevcProgress);

}
