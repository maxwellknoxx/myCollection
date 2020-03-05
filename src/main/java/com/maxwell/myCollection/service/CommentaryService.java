package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.Commentary;
import com.maxwell.myCollection.model.CommentaryDTO;

public interface CommentaryService {

	List<CommentaryDTO> findAll();

	CommentaryDTO findById(Long id);
	
	List<CommentaryDTO> findByItemId(Long id);

	CommentaryDTO addCommentary(Commentary commentary);

	CommentaryDTO updateCommentary(Commentary commentary);

	Boolean removeCommentary(Long id);

}