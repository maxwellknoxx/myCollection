package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.ItemCommentsEntity;

public interface ItemCommentsService {

	List<ItemCommentsEntity> findAll();

	Optional<ItemCommentsEntity> findById(Long id);

	ItemCommentsEntity addItemComments(ItemCommentsEntity itemcomments);

	ItemCommentsEntity updateItemComments(ItemCommentsEntity itemcomments);

	void removeItemComments(Long id);

}