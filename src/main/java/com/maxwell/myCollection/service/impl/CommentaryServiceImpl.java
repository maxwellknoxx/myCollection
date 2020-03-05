package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.Commentary;
import com.maxwell.myCollection.exception.EntityNotFoundException;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.CommentaryDTO;
import com.maxwell.myCollection.repository.CommentaryRepository;
import com.maxwell.myCollection.service.CommentaryService;
import com.maxwell.myCollection.utils.CommentaryMapper;

@Service
public class CommentaryServiceImpl implements CommentaryService {

	@Autowired
	private CommentaryRepository repository;

	@Override
	public List<CommentaryDTO> findAll() throws ResourceNotFoundException {
		List<Commentary> list = repository.findAll();
		if (list.isEmpty()) {
			throw new ResourceNotFoundException(Commentary.class, "No commentary found");
		}
		return CommentaryMapper.getListDTO(list);
	}

	@Override
	public CommentaryDTO findById(Long id) {
		return CommentaryMapper.getDTO(repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Commentary.class, "id", id.toString())));
	}

	@Override
	public CommentaryDTO addCommentary(Commentary commentary) {
		Commentary entity = repository.save(commentary);
		if (entity == null) {
			return null;
		}
		return CommentaryMapper.getDTO(entity);
	}

	@Override
	public CommentaryDTO updateCommentary(Commentary commentary) {
		Commentary entity = repository.save(commentary);
		if (entity == null) {
			return null;
		}
		return CommentaryMapper.getDTO(entity);
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
	public List<CommentaryDTO> findByItemId(Long id) throws ResourceNotFoundException {
		List<Commentary> list = repository.findByItemId(id);
		if (list.isEmpty()) {
			throw new ResourceNotFoundException(Commentary.class, "No commentary found");
		}
		return CommentaryMapper.getListDTO(list);
	}

}