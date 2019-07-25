package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.ItemEntity;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.Item;
import com.maxwell.myCollection.repository.ItemRepository;
import com.maxwell.myCollection.service.ItemService;
import com.maxwell.myCollection.utils.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository repository;

	@Override
	public List<Item> findAll() {
		return ItemMapper.convertEntitiesToModel(repository.findAll());
	}

	@Override
	public Item findById(Long id) {

		Item item = ItemMapper.convertEntityToModel(repository.findById(id).orElseThrow());
		if (item == null) {
			throw new ResourceNotFoundException("Item " + id + " does not exist");
		}
		return item;

	}

	@Override
	public Item addItem(ItemEntity item) {
		try {
			return ItemMapper.convertEntityToModel(repository.save(item));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong " + e.getMessage());
		}

	}

	@Override
	public Item updateItem(ItemEntity item) {
		try {
			return ItemMapper.convertEntityToModel(repository.save(item));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong " + e.getMessage());
		}
	}

	@Override
	public void removeItem(Long id) {
		Item item = ItemMapper.convertEntityToModel(repository.findById(id).orElseThrow());
		if (Objects.isNull(item)) {
			throw new ResourceNotFoundException("Item " + id + " does not exist");
		}

		repository.deleteById(id);
	}

	@Override
	public List<Item> findByCategoryId(Long id) {
		return ItemMapper.convertEntitiesToModel(repository.findByCategoryId(id));
	}

}