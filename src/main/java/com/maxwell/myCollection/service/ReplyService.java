package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.ReplyEntity;
import com.maxwell.myCollection.model.Reply;

public interface ReplyService {

	List<Reply> findAll();

	Reply findById(Long id);

	Reply addReply(ReplyEntity reply);

	Reply updateReply(ReplyEntity reply);

	Boolean removeReply(Long id);

}