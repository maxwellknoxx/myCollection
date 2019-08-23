package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.CommentaryEntity;
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
		List<CommentaryEntity> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return CommentaryMapper.convertEntityToModelList(list);
	}

	@Override
	public Commentary findById(Long id) {
		CommentaryEntity commentary = repository.findById(id).orElse(null);
		if (commentary == null) {
			return null;
		}
		return CommentaryMapper.convertEntityToModel(commentary);
	}

	@Override
	public Commentary addCommentary(CommentaryEntity commentary) {
		CommentaryEntity entity = repository.save(commentary);
		if (entity == null) {
			return null;
		}
		return CommentaryMapper.convertEntityToModel(entity);
	}

	@Override
	public Commentary updateCommentary(CommentaryEntity commentary) {
		CommentaryEntity entity = repository.save(commentary);
		if (entity == null) {
			return null;
		}
		return CommentaryMapper.convertEntityToModel(entity);
	}

	@Override
	public Boolean removeCommentary(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Commentary> findByItemId(Long id) {
		List<CommentaryEntity> list = repository.findByItemId(id);
		if (list.isEmpty()) {
			return null;
		}
		return CommentaryMapper.convertEntityToModelList(list);
	}

}