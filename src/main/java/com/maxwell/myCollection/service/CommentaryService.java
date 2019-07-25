package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.CommentaryEntity;
import com.maxwell.myCollection.model.Commentary;

public interface CommentaryService {

	List<Commentary> findAll();

	Commentary findById(Long id);
	
	List<Commentary> findByItemId(Long id);

	Commentary addCommentary(CommentaryEntity commentary);

	Commentary updateCommentary(CommentaryEntity commentary);

	void removeCommentary(Long id);

}