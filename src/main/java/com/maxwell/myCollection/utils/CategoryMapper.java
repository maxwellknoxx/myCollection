package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.CategoryEntity;
import com.maxwell.myCollection.model.Category;

@Component
public class CategoryMapper {

	public static Category convertEntityToModel(CategoryEntity entity) {
		return Category.builder().id(entity.getId()).name(entity.getName()).description(entity.getDescription())
				.build();
	}

	public static List<Category> convertEntitiesToModel(List<CategoryEntity> entities) {
		return entities
				.stream().filter(Objects::nonNull).map(entity -> Category.builder().id(entity.getId())
						.name(entity.getName()).description(entity.getDescription()).build())
				.collect(Collectors.toList());
	}

}
