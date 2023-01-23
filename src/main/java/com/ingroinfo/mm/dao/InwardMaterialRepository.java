package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.InwardMaterial;

@Repository
public interface InwardMaterialRepository extends JpaRepository<InwardMaterial, Long> {

}
