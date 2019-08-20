package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.ItemEntity;
import com.maxwell.myCollection.model.Item;

@Component
public class ItemMapper {

	public static Item convertEntityToModel(@Valid ItemEntity entity) {
		return Item.builder().id(entity.getId()).Name(entity.getName()).description(entity.getDescription())
				.categoryId(entity.getCategory().getId()).categoryName(entity.getCategory().getName())
				.itemCondition(entity.getItemCondition()).photo(entity.getPhoto()).status(entity.getStatus())
				.trade(entity.getTrade()).userId(entity.getUser().getId()).username(entity.getUser().getName())
				.location(entity.getUser().getLocation())
				.commentaries(CommentaryMapper.convertEntitiesToModel(entity.getCommentaries())).build();
	}

	public static List<Item> convertEntitiesToModel(@Valid List<ItemEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> Item.builder().id(entity.getId()).Name(entity.getName())
						.description(entity.getDescription()).categoryId(entity.getCategory().getId())
						.categoryName(entity.getCategory().getName()).itemCondition(entity.getItemCondition())
						.photo(entity.getPhoto()).status(entity.getStatus()).trade(entity.getTrade())
						.userId(entity.getUser().getId()).username(entity.getUser().getName())
						.location(entity.getUser().getLocation())
						.commentaries(CommentaryMapper.convertEntitiesToModel(entity.getCommentaries())).build())
				.collect(Collectors.toList());
	}

}
