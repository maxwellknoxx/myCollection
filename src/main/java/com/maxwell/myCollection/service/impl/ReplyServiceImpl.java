package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.Reply;
import com.maxwell.myCollection.exception.EntityNotFoundException;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.ReplyDTO;
import com.maxwell.myCollection.repository.ReplyRepository;
import com.maxwell.myCollection.service.ReplyService;
import com.maxwell.myCollection.utils.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository repository;

	@Override
	public List<ReplyDTO> findAll() throws ResourceNotFoundException {
		List<Reply> list = repository.findAll();
		if (list.isEmpty()) {
			throw new ResourceNotFoundException(Reply.class, "No reply found");
		}
		return ReplyMapper.getListDTO(list);
	}

	@Override
	public ReplyDTO findById(Long id) {
		return ReplyMapper.getDTO(repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Reply.class, "id", id.toString())));
	}

	@Override
	public ReplyDTO addReply(Reply reply) {
		Reply entity = repository.save(reply);
		if (entity == null) {
			return null;
		}
		return ReplyMapper.getDTO(entity);
	}

	@Override
	public ReplyDTO updateReply(Reply reply) {
		Reply entity = repository.save(reply);
		if (entity == null) {
			return null;
		}
		return ReplyMapper.getDTO(entity);
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