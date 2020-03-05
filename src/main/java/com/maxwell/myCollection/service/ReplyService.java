package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.Reply;
import com.maxwell.myCollection.model.ReplyDTO;

public interface ReplyService {

	List<ReplyDTO> findAll();

	ReplyDTO findById(Long id);

	ReplyDTO addReply(Reply reply);

	ReplyDTO updateReply(Reply reply);

	Boolean removeReply(Long id);

}