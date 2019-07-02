package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.CategoryEntity;

public interface CategoryService {

	List<CategoryEntity> findAll();

	Optional<CategoryEntity> findById(Long id);

	CategoryEntity addCategory(CategoryEntity category);

	CategoryEntity updateCategory(CategoryEntity category);

	void removeCategory(Long id);

}