package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.ContactManagement;

@Repository
public interface ContactManagementRepository extends JpaRepository<ContactManagement, Long>{

}
