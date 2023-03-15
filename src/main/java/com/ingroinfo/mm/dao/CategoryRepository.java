package com.ingroinfo.mm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.mm.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
