package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.Reply;
import com.maxwell.myCollection.model.ReplyDTO;

@Component
public class ReplyMapper {

	public static ReplyDTO getDTO(Reply entity) {
		return ReplyDTO.builder().id(entity.getId()).reply(entity.getReply()).commentaryId(entity.getCommentary().getId())
				.profileId(entity.getProfile().getId()).profileName(entity.getProfile().getName()).build();
	}

	public static List<ReplyDTO> getListDTO(List<Reply> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> ReplyDTO.builder().id(entity.getId()).reply(entity.getReply())
						.commentaryId(entity.getCommentary().getId()).profileId(entity.getProfile().getId())
						.profileName(entity.getProfile().getName()).build())
				.collect(Collectors.toList());

	}

}
