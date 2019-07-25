package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.CommentaryEntity;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.Commentary;
import com.maxwell.myCollection.repository.CommentaryRepository;
import com.maxwell.myCollection.service.CommentaryService;
import com.maxwell.myCollection.utils.CommentaryMapper;

@Service
public class CommentaryServiceImpl implements CommentaryService {

	@Autowired
	private CommentaryRepository repository;

	@Override
	public List<Commentary> findAll() {
		return CommentaryMapper.convertEntitiesToModel(repository.findAll());
	}

	@Override
	public Commentary findById(Long id) {
		Commentary commentary = CommentaryMapper.convertEntityToModel(repository.findById(id).orElseThrow());
		if (commentary == null) {
			throw new ResourceNotFoundException("Commentary does not exist");
		}
		return commentary;
	}

	@Override
	public Commentary addCommentary(CommentaryEntity commentary) {
		try {
			return CommentaryMapper.convertEntityToModel(repository.save(commentary));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong " + e.getMessage());
		}
	}

	@Override
	public Commentary updateCommentary(CommentaryEntity commentary) {
		try {
			return CommentaryMapper.convertEntityToModel(repository.save(commentary));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong " + e.getMessage());
		}
	}

	@Override
	public void removeCommentary(Long id) {
		Commentary commentary = CommentaryMapper.convertEntityToModel(repository.findById(id).orElseThrow());
		if (Objects.isNull(commentary)) {
			throw new ResourceNotFoundException("Commentary does not exist");
		}
		repository.deleteById(id);
	}

	@Override
	public List<Commentary> findByItemId(Long id) {
		return CommentaryMapper.convertEntitiesToModel(repository.findByItemId(id));
	}

}