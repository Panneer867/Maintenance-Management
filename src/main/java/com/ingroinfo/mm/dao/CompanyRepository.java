package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>
{

	Company findByEmail(String email);

	Company findByCompanyId(Long companyId);

}
