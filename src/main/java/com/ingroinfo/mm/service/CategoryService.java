package com.ingroinfo.mm.service;

import java.util.List;

import com.ingroinfo.mm.dto.CategoryDto;

public interface CategoryService {

	//save Data
	CategoryDto saveCategory(CategoryDto categoryDto);
	
	//Get All Data
	List<CategoryDto> findAllCategory();
}
