package com.ingroinfo.mm.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.IndentApprovedLabours;

@Repository
public interface IndentApprovedLaboursRepository extends JpaRepository<IndentApprovedLabours, Long> {

	List<IndentApprovedLabours> getByComplNoAndIndentNo(String complNo, String indentNo);


}
