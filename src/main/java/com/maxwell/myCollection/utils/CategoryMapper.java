package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.Category;
import com.maxwell.myCollection.model.CategoryDTO;

@Component
public class CategoryMapper {

	public static CategoryDTO getDTO(Category entity) {
		return CategoryDTO.builder().id(entity.getId()).name(entity.getName()).description(entity.getDescription())
				.build();
	}

	public static List<CategoryDTO> getListDTO(List<Category> entities) {
		return entities
				.stream().filter(Objects::nonNull).map(entity -> CategoryDTO.builder().id(entity.getId())
						.name(entity.getName()).description(entity.getDescription()).build())
				.collect(Collectors.toList());
	}

}
