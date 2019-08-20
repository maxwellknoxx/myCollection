package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.CategoryEntity;
import com.maxwell.myCollection.model.Category;

public interface CategoryService {

	List<Category> findAll();

	Category findById(Long id);

	Category addCategory(CategoryEntity category);

	Category updateCategory(CategoryEntity category);

	Boolean removeCategory(Long id);

}