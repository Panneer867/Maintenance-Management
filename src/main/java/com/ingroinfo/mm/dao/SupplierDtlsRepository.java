package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.SupplierDtls;

@Repository
public interface SupplierDtlsRepository extends JpaRepository<SupplierDtls, Long> {

}
