package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.ItemCommentsEntity;
import com.maxwell.myCollection.repository.ItemCommentsRepository;
import com.maxwell.myCollection.service.ItemCommentsService;

@Service
public class ItemCommentsServiceImpl implements ItemCommentsService {

	@Autowired
	private ItemCommentsRepository repository;

	@Override
	public List<ItemCommentsEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<ItemCommentsEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ItemCommentsEntity addItemComments(ItemCommentsEntity itemcomments) {
		return repository.save(itemcomments);
	}

	@Override
	public ItemCommentsEntity updateItemComments(ItemCommentsEntity itemcomments) {
		return repository.save(itemcomments);
	}

	@Override
	public void removeItemComments(Long id) {
		repository.deleteById(id);
	}

}