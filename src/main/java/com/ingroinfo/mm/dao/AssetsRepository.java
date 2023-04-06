package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingroinfo.mm.entity.Assets;

@Repository
public interface AssetsRepository extends JpaRepository<Assets, Long> {

}
