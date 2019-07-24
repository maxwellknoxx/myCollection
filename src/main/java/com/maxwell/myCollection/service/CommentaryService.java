package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.CommentaryEntity;

public interface CommentaryService {

	List<CommentaryEntity> findAll();

	Optional<CommentaryEntity> findById(Long id);
	
	List<CommentaryEntity> findByItemId(Long id);

	CommentaryEntity addCommentary(CommentaryEntity commentary);

	CommentaryEntity updateCommentary(CommentaryEntity commentary);

	void removeCommentary(Long id);

}