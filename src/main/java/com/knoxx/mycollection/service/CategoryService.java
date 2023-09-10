package com.knoxx.mycollection.service;

import com.knoxx.mycollection.entity.Category;
import com.knoxx.mycollection.exception.EntityNotFoundException;
import com.knoxx.mycollection.exception.ResourceNotFoundException;
import com.knoxx.mycollection.model.CategoryDto;
import com.knoxx.mycollection.repository.CategoryRepository;
import com.knoxx.mycollection.utils.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository repository;

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> findAll() throws ResourceNotFoundException {
        List<Category> list = repository.findAll();

        if (list.isEmpty()) {
            throw new ResourceNotFoundException(Category.class, "Message={No categories found}");
        }

        return categoryMapper.toDtoList(list);
    }

    public CategoryDto findByCategoryId(String categoryId) throws EntityNotFoundException {
        return categoryMapper.toDto(repository.findByCategoryId(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(Category.class, "id", categoryId)));
    }

    public CategoryDto save(CategoryDto categoryDTO) {
        Category entity = repository.save(categoryMapper.toEntity(categoryDTO));

        return categoryMapper.toDto(entity);
    }

    public CategoryDto updateCategory(CategoryDto category) {
        return this.save(category);
    }

    public Boolean removeCategory(String categoryId) {
        try {
            repository.deleteById(categoryId);
            return true;
        } catch (Exception e) {
            log.error("Error deleting category {}", categoryId);
            log.error(e.getMessage());
            return false;
        }
    }

}