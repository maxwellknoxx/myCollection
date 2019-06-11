package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.ItemEntity;
import com.maxwell.myCollection.repository.ItemRepository;
import com.maxwell.myCollection.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository repository;

	@Override
	public List<ItemEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<ItemEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ItemEntity addItem(ItemEntity item) {
		return repository.save(item);
	}

	@Override
	public ItemEntity updateItem(ItemEntity item) {
		return repository.save(item);
	}

	@Override
	public void removeItem(Long id) {
		repository.deleteById(id);
	}

}