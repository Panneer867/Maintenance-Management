package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.MeterReplace;

@Repository
public interface MeterReplaceRepository  extends JpaRepository<MeterReplace, Integer>{

}
