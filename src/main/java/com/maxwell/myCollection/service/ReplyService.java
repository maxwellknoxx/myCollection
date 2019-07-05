package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.ReplyEntity;

public interface ReplyService {

	List<ReplyEntity> findAll();

	Optional<ReplyEntity> findById(Long id);

	ReplyEntity addReply(ReplyEntity reply);

	ReplyEntity updateReply(ReplyEntity reply);

	void removeReply(Long id);

}