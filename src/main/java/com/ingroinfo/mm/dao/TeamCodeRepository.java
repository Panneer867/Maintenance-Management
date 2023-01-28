package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.TeamCode;

@Repository
public interface TeamCodeRepository extends JpaRepository<TeamCode, Long> {

}