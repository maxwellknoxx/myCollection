package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.Category;
import com.maxwell.myCollection.exception.EntityNotFoundException;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.CategoryDTO;
import com.maxwell.myCollection.repository.CategoryRepository;
import com.maxwell.myCollection.service.CategoryService;
import com.maxwell.myCollection.utils.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public List<CategoryDTO> findAll() throws ResourceNotFoundException {
		List<Category> list = repository.findAll();
		if (list.isEmpty()) {
			throw new ResourceNotFoundException(Category.class, "No categories found");
		}
		return CategoryMapper.getListDTO(list);
	}

	@Override
	public CategoryDTO findById(Long id) throws EntityNotFoundException {
		return CategoryMapper.getDTO(repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Category.class, "id", id.toString())));
	}

	@Override
	public CategoryDTO addCategory(Category category) {
		Category entity = repository.save(category);
		if (entity == null) {
			return null;
		}
		return CategoryMapper.getDTO(entity);
	}

	@Override
	public CategoryDTO updateCategory(Category category) {
		Category entity = repository.save(category);
		if (entity == null) {
			return null;
		}
		return CategoryMapper.getDTO(entity);
	}

	@Override
	public Boolean removeCategory(Long id) {
		try {
			findById(id);
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}