package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.CategoryEntity;
import com.maxwell.myCollection.repository.CategoryRepository;
import com.maxwell.myCollection.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public List<CategoryEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<CategoryEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public CategoryEntity addCategory(CategoryEntity category) {
		return repository.save(category);
	}

	@Override
	public CategoryEntity updateCategory(CategoryEntity category) {
		return repository.save(category);
	}

	@Override
	public void removeCategory(Long id) {
		repository.deleteById(id);
	}

}