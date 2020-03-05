package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.Item;
import com.maxwell.myCollection.model.ItemDTO;

@Component
public class ItemMapper {

	public static ItemDTO getDTO(@Valid Item entity) {
		return ItemDTO.builder().id(entity.getId()).Name(entity.getName()).description(entity.getDescription())
				.categoryId(entity.getCategory().getId()).categoryName(entity.getCategory().getName())
				.itemCondition(entity.getItemCondition()).photo(entity.getPhoto()).status(entity.getStatus())
				.trade(entity.getTrade()).userId(entity.getUser().getId()).username(entity.getUser().getName())
				.location(entity.getUser().getLocation())
				.commentaries(CommentaryMapper.getListDTO(entity.getCommentaries())).build();
	}

	public static List<ItemDTO> getListDTO(@Valid List<Item> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> ItemDTO.builder().id(entity.getId()).Name(entity.getName())
						.description(entity.getDescription()).categoryId(entity.getCategory().getId())
						.categoryName(entity.getCategory().getName()).itemCondition(entity.getItemCondition())
						.photo(entity.getPhoto()).status(entity.getStatus()).trade(entity.getTrade())
						.userId(entity.getUser().getId()).username(entity.getUser().getName())
						.location(entity.getUser().getLocation())
						.commentaries(CommentaryMapper.getListDTO(entity.getCommentaries())).build())
				.collect(Collectors.toList());
	}

}
