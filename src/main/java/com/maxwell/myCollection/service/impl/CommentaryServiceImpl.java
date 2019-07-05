package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.CommentaryEntity;
import com.maxwell.myCollection.repository.CommentaryRepository;
import com.maxwell.myCollection.service.CommentaryService;

@Service
public class CommentaryServiceImpl implements CommentaryService {

	@Autowired
	private CommentaryRepository repository;

	@Override
	public List<CommentaryEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<CommentaryEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public CommentaryEntity addCommentary(CommentaryEntity commentary) {
		return repository.save(commentary);
	}

	@Override
	public CommentaryEntity updateCommentary(CommentaryEntity commentary) {
		return repository.save(commentary);
	}

	@Override
	public void removeCommentary(Long id) {
		repository.deleteById(id);
	}

}