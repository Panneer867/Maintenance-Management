package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.ServiceProgress;

@Repository
public interface ServiceProgressRepository extends JpaRepository<ServiceProgress, Long> {

	boolean existsBysevcProgress(String sevcProgress);

}
