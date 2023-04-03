package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.PipeManufacture;

@Repository
public interface PipeManufactureRepository extends JpaRepository<PipeManufacture, Long> {

	boolean existsByManufactureNameAndPipeType(String manufactureName, String pipeType);

	boolean existsByManufactureId(String manufactureId);

}
