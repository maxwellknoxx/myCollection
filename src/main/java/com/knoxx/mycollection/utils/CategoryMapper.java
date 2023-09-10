package com.knoxx.mycollection.utils;

import com.knoxx.mycollection.entity.Category;
import com.knoxx.mycollection.model.CategoryDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends Converter<Category, CategoryDto> {

   CategoryDto toDto(Category entity);

    Category toEntity(CategoryDto categoryDto);

    default List<CategoryDto> toDtoList(List<Category> categoriesList) {
        List<CategoryDto> categoriesDtoList = new ArrayList<>();

        categoriesList.forEach(category -> categoriesDtoList.add(toDto(category)));
        return categoriesDtoList;
    }

    default List<Category> toEntityList(List<CategoryDto> categoriesDtoList) {
        List<Category> categoriesList = new ArrayList<>();

        categoriesDtoList.forEach(categoryDto -> categoriesList.add(toEntity(categoryDto)));
        return categoriesList;
    }

}
