package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.CategoryEntity;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.Category;
import com.maxwell.myCollection.repository.CategoryRepository;
import com.maxwell.myCollection.service.CategoryService;
import com.maxwell.myCollection.utils.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public List<Category> findAll() {
		return CategoryMapper.convertEntitiesToModel(repository.findAll());
	}

	@Override
	public Category findById(Long id) {
		return CategoryMapper.convertEntityToModel(repository.findById(id).orElseThrow());
	}

	@Override
	public Category addCategory(CategoryEntity category) {
		try {
			return CategoryMapper.convertEntityToModel(repository.save(category));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong " + e.getMessage());
		}
	}

	@Override
	public Category updateCategory(CategoryEntity category) {
		try {
			return CategoryMapper.convertEntityToModel(repository.save(category));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong " + e.getMessage());
		}
	}

	@Override
	public void removeCategory(Long id) {
		Category category = CategoryMapper.convertEntityToModel(repository.findById(id).orElseThrow());
		if (Objects.isNull(category)) {
			throw new ResourceNotFoundException("Category does not exist");
		}
		repository.deleteById(id);
	}

}