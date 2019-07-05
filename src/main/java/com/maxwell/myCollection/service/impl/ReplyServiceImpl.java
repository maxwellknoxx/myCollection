package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.ReplyEntity;
import com.maxwell.myCollection.repository.ReplyRepository;
import com.maxwell.myCollection.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository repository;

	@Override
	public List<ReplyEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<ReplyEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ReplyEntity addReply(ReplyEntity reply) {
		return repository.save(reply);
	}

	@Override
	public ReplyEntity updateReply(ReplyEntity reply) {
		return repository.save(reply);
	}

	@Override
	public void removeReply(Long id) {
		repository.deleteById(id);
	}

}