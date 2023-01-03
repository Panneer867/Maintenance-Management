package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardItem;

@Repository
public interface InwardItemRepo extends JpaRepository<InwardItem, Long> {

}
