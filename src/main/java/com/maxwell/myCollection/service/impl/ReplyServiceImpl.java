package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.ReplyEntity;
import com.maxwell.myCollection.model.Reply;
import com.maxwell.myCollection.repository.ReplyRepository;
import com.maxwell.myCollection.service.ReplyService;
import com.maxwell.myCollection.utils.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository repository;

	@Override
	public List<Reply> findAll() {
		List<ReplyEntity> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return ReplyMapper.convertEntityToModelList(list);
	}

	@Override
	public Reply findById(Long id) {
		ReplyEntity entity = repository.findById(id).orElse(null);
		if (entity == null) {
			return null;
		}
		return ReplyMapper.convertEntityToModel(entity);
	}

	@Override
	public Reply addReply(ReplyEntity reply) {
		ReplyEntity entity = repository.save(reply);
		if (entity == null) {
			return null;
		}
		return ReplyMapper.convertEntityToModel(entity);
	}

	@Override
	public Reply updateReply(ReplyEntity reply) {
		ReplyEntity entity = repository.save(reply);
		if (entity == null) {
			return null;
		}
		return ReplyMapper.convertEntityToModel(entity);
	}

	@Override
	public Boolean removeReply(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}