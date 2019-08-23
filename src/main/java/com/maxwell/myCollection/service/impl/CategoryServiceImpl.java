package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.CategoryEntity;
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
		List<CategoryEntity> list = repository.findAll();
		if (list == null) {
			return null;
		}
		return CategoryMapper.convertEntityToModelList(list);
	}

	@Override
	public Category findById(Long id) {
		CategoryEntity entity = repository.findById(id).orElseThrow();
		if (entity == null) {
			return null;
		}
		return CategoryMapper.convertEntityToModel(entity);
	}

	@Override
	public Category addCategory(CategoryEntity category) {
		CategoryEntity entity = repository.save(category);
		if (entity == null) {
			return null;
		}
		return CategoryMapper.convertEntityToModel(entity);
	}

	@Override
	public Category updateCategory(CategoryEntity category) {
		CategoryEntity entity = repository.save(category);
		if (entity == null) {
			return null;
		}
		return CategoryMapper.convertEntityToModel(entity);
	}

	@Override
	public Boolean removeCategory(Long id) {
		CategoryEntity category = repository.findById(id).orElseThrow();
		if (Objects.isNull(category)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}

}