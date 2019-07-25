package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.CommentaryEntity;
import com.maxwell.myCollection.model.Commentary;

@Component
public class CommentaryMapper {

	public static Commentary convertEntityToModel(CommentaryEntity entity) {
		return Commentary.builder().id(entity.getId()).commentary(entity.getCommentary())
				.profileId(entity.getProfile().getId()).profileName(entity.getProfile().getName())
				.itemId(entity.getItem().getId()).replies(ReplyMapper.convertEntityToModel(entity.getReplies()))
				.build();
	}

	public static List<Commentary> convertEntitiesToModel(List<CommentaryEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> Commentary.builder().id(entity.getId()).commentary(entity.getCommentary())
						.profileId(entity.getProfile().getId()).profileName(entity.getProfile().getName())
						.itemId(entity.getItem().getId())
						.replies(ReplyMapper.convertEntityToModel(entity.getReplies())).build())
				.collect(Collectors.toList());

	}

}
