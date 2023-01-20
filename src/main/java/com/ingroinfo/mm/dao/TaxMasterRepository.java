package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TaxMaster;

@Repository
public interface TaxMasterRepository extends JpaRepository<TaxMaster, Long> {

}
