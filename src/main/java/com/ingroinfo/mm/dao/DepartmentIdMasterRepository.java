package com.ingroinfo.mm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.DepartmentIdMaster;

@Repository
public interface DepartmentIdMasterRepository extends JpaRepository<DepartmentIdMaster, Long> {

	public List<DepartmentIdMaster> findByMasterIdName(String masterIdName);

}
