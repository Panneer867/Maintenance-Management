package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.MaintanceFrequency;

@Repository
public interface MaintanceFrequencyRepository extends JpaRepository<MaintanceFrequency, Long> {

}
