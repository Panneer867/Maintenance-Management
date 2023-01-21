package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>{

}
