package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.Commentary;
import com.maxwell.myCollection.model.CommentaryDTO;

@Component
public class CommentaryMapper {

	public static CommentaryDTO getDTO(Commentary entity) {
		return CommentaryDTO.builder().id(entity.getId()).commentary(entity.getCommentary())
				.userId(entity.getUser().getId()).username(entity.getUser().getName()).itemId(entity.getItem().getId())
				.replies(ReplyMapper.getListDTO(entity.getReplies())).build();
	}

	public static List<CommentaryDTO> getListDTO(List<Commentary> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> CommentaryDTO.builder().id(entity.getId()).commentary(entity.getCommentary())
						.itemId(entity.getItem().getId()).replies(ReplyMapper.getListDTO(entity.getReplies())).build())
				.collect(Collectors.toList());
	}

}
