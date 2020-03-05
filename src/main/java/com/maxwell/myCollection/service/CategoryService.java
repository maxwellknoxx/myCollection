package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.Category;
import com.maxwell.myCollection.model.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> findAll();

	CategoryDTO findById(Long id);

	CategoryDTO addCategory(Category category);

	CategoryDTO updateCategory(Category category);

	Boolean removeCategory(Long id);

}