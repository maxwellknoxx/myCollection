package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.ReplyEntity;
import com.maxwell.myCollection.model.Reply;

@Component
public class ReplyMapper {

	public static Reply convertEntityToModel(ReplyEntity entity) {
		return Reply.builder().id(entity.getId()).reply(entity.getReply()).commentaryId(entity.getCommentary().getId())
				.profileId(entity.getProfile().getId()).profileName(entity.getProfile().getName()).build();
	}

	public static List<Reply> convertEntityToModel(List<ReplyEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> Reply.builder().id(entity.getId()).reply(entity.getReply())
						.commentaryId(entity.getCommentary().getId()).profileId(entity.getProfile().getId())
						.profileName(entity.getProfile().getName()).build())
				.collect(Collectors.toList());

	}

}
