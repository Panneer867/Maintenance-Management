package com.ingroinfo.mm.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.CategoryRepository;
import com.ingroinfo.mm.dto.CategoryDto;
import com.ingroinfo.mm.entity.Category;
import com.ingroinfo.mm.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category savedCategory = this.categoryRepository.save(category);
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> findAllCategory() {
		List<Category> categories = this.categoryRepository.findAll();
		return categories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteCategory(Long catid) {
		Category category = this.categoryRepository.findById(catid).get();
		this.categoryRepository.delete(category);
	}

	@Override
	public String getMaxCategoryId() {
		String maxCategoryId = this.categoryRepository.getMaxCategoryId();
		return maxCategoryId;
	}	
}
